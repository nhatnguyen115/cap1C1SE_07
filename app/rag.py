from sentence_transformers import SentenceTransformer
from app.db import find_by_question_number, semantic_search
from app.llm import ask_llama
import redis
import json
import hashlib

# Initialize SentenceTransformer model
model = SentenceTransformer('all-MiniLM-L6-v2')

# Initialize Redis client
try:
    redis_client = redis.Redis(
        host='localhost',  # Update with your Redis host
        port=6379,        # Update with your Redis port
        decode_responses=True
    )
    redis_client.ping()
except redis.ConnectionError:
    redis_client = None
    print("Warning: Could not connect to Redis. Caching disabled.")

def is_general_toeic_question(question_text: str) -> bool:
    """
    Determine if the query is a general TOEIC knowledge question.
    """
    general_keywords = [
        "có mấy phần", "cấu trúc", "thời gian", "điểm số", "giới thiệu", "các phần thi",
        "các kỹ năng", "các dạng câu hỏi", "cách làm bài", "cách ôn thi", "luyện tập",
        "các dạng bài", "các phần", "các kỹ năng nghe", "các kỹ năng đọc", "các kỹ năng nói",
        "các kỹ năng viết", "các dạng câu hỏi nghe", "các dạng câu hỏi đọc hiểu",
        "các dạng câu hỏi nói", "các dạng câu hỏi viết", "cách làm bài nghe", "cách làm bài đọc",
        "cách làm bài nói", "cách làm bài viết", "các mẹo làm bài", "các chiến lược ôn thi",
        "các dạng bài tập", "các dạng câu hỏi TOEIC", "các phần của TOEIC", "cách làm bài TOEIC",
        "cách ôn thi TOEIC", "các kỹ năng cần thiết", "các dạng câu hỏi phổ biến",
        "các phần thi TOEIC", "các kỹ năng nghe nói đọc viết", "các dạng bài tập TOEIC",
        "các dạng câu hỏi TOEIC phổ biến", "các phần thi TOEIC cơ bản", "cách làm bài thi TOEIC",
        "cách tính", "bao nhiêu câu", "phần nào", "tổng quan", "gồm những gì"
    ]
    question_text = question_text.lower().strip()
    return any(keyword in question_text for keyword in general_keywords)

def get_cached_response(question_text: str) -> str:
    """
    Check Redis for a cached response using a hashed key.
    """
    if redis_client:
        try:
            # Use MD5 hash of normalized question for consistent cache keys
            cache_key = f"toeic:general:{hashlib.md5(question_text.encode()).hexdigest()}"
            cached = redis_client.get(cache_key)
            if cached:
                print(f"Cache hit for: {question_text}")
                return json.loads(cached).get("answer")
        except redis.RedisError as e:
            print(f"Redis error: {e}")
    return None

def cache_response(question_text: str, response: str):
    """
    Cache the response in Redis with a 24-hour expiration.
    """
    if redis_client:
        try:
            cache_key = f"toeic:general:{hashlib.md5(question_text.encode()).hexdigest()}"
            redis_client.setex(
                cache_key,
                86400,
                json.dumps({"answer": response})
            )
            print(f"Cached response for: {question_text}")
        except redis.RedisError as e:
            print(f"Redis cache error: {e}")

def hybrid_search(test_id: int = None, q_number: int = None, question_text: str = None):
    """
    Perform hybrid search: exact match or semantic search.
    """
    if test_id is not None and q_number is not None:
        doc = find_by_question_number(test_id, q_number)
        if doc:
            print(f"Found document for test_id={test_id}, q_number={q_number}: {doc['text']}")
            return doc["text"]
        else:
            print(f"No document found for test_id={test_id}, q_number={q_number}")
    
    if question_text:
        query_vec = model.encode(question_text).tolist()
        results = semantic_search(query_vec, k=1)
        if results:
            print(f"Semantic search result for '{question_text}': {results[0]['text']}")
            return results[0]["text"]
    
    print(f"No results for test_id={test_id}, q_number={q_number}, question_text={question_text}")
    return None

def ask_hybrid(test_id: int = None, q_number: int = None, question_text: str = None):
    """
    Handle user queries with RAG or direct LLM.
    """
    if not question_text and (test_id is None or q_number is None):
        return "Vui lòng cung cấp câu hỏi hoặc thông tin bài thi hợp lệ."

    # Handle specific test questions with RAG
    if test_id is not None and q_number is not None:
        context = hybrid_search(test_id, q_number, question_text)
        if context:
            prompt = f"""
Bạn là trợ lý tiếng Việt thông minh về TOEIC. Dưới đây là nội dung câu hỏi TOEIC:

{context}

→ Hãy giải thích rõ ràng lý do chọn đáp án đúng, bằng tiếng Việt dễ hiểu, ngắn gọn và chính xác.
"""
            response = ask_llama(prompt)
            print(f"RAG response for test_id={test_id}, q_number={q_number}: {response}")
            return response if response else "❌ Lỗi khi xử lý câu hỏi. Vui lòng thử lại."
        else:
            return f"Không tìm thấy câu hỏi {q_number} trong bài thi {test_id}. Vui lòng kiểm tra lại."
    
    # Handle natural language questions
    if question_text:
        # Normalize question text for caching
        normalized_question = question_text.lower().strip()
        
        # Check if it's a general TOEIC question
        if is_general_toeic_question(normalized_question):
            # Check cache
            cached_response = get_cached_response(normalized_question)
            if cached_response:
                return cached_response
            
            # Query LLM
            prompt = f"""
Bạn là trợ lý tiếng Việt thông minh chuyên về TOEIC. Hãy trả lời câu hỏi sau một cách rõ ràng, ngắn gọn, chính xác bằng tiếng Việt, dựa trên kiến thức chung về TOEIC:

{question_text}
"""
            response = ask_llama(prompt)
            if response and response != "❌ Lỗi khi gọi API LLM. Vui lòng thử lại.":
                cache_response(normalized_question, response)
                return response
            return "❌ Lỗi khi xử lý câu hỏi. Vui lòng thử lại."
        
        # Use RAG for specific test questions
        context = hybrid_search(question_text=question_text)
        if context:
            prompt = f"""
Bạn là trợ lý tiếng Việt thông minh về TOEIC. Dưới đây là nội dung câu hỏi TOEIC:

{context}

→ Hãy giải thích rõ ràng lý do chọn đáp án đúng, bằng tiếng Việt dễ hiểu, ngắn gọn và chính xác.
"""
            response = ask_llama(prompt)
            print(f"RAG response for question_text={question_text}: {response}")
            return response if response else "❌ Lỗi khi xử lý câu hỏi. Vui lòng thử lại."
        
        # Fallback to LLM
        prompt = f"""
Bạn là trợ lý tiếng Việt thông minh về TOEIC. Hãy trả lời câu hỏi sau một cách rõ ràng, ngắn gọn, chính xác bằng tiếng Việt:

{question_text}
"""
        response = ask_llama(prompt)
        print(f"LLM fallback response for question_text={question_text}: {response}")
        return response if response else "❌ Lỗi khi xử lý câu hỏi. Vui lòng thử lại."
    
    return "Vui lòng cung cấp câu hỏi hợp lệ."