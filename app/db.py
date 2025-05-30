from pymongo import MongoClient
import os
from dotenv import load_dotenv
load_dotenv()

client = MongoClient(os.getenv("MONGODB_URI"))
db = client["toeic_db"]
collection = db["questions_semantic"]

def find_by_question_number(test_id: int, q_number: int):
    doc = collection.find_one({
        "test_id": str(test_id),
        "question_number": q_number
    })
    return doc

def semantic_search(query_vector, k=3):
    pipeline = [
        {
            "$search": {
                "index": "default",  # Tên index vector đã tạo trên Atlas
                "knnBeta": {
                    "vector": query_vector,
                    "path": "embedding",
                    "k": k
                }
            }
        },
        {"$project": {"text": 1, "question_number": 1, "_id": 0}}
    ]
    results = list(collection.aggregate(pipeline))
    return results
