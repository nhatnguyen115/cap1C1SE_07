import json
from sentence_transformers import SentenceTransformer
from app.db import collection  # Kết nối collection MongoDB đã tạo

model = SentenceTransformer('all-MiniLM-L6-v2')

docs = []

def add_doc(test_id, q_number, text, meta):
    embedding = model.encode(text).tolist()
    doc = {
        "test_id": str(test_id),
        "question_number": int(q_number),
        "text": text,
        "embedding": embedding
    }
    doc.update(meta)
    docs.append(doc)

def load_and_prepare_docs(json_path):
    global docs
    docs = []  # Reset docs mỗi lần gọi

    with open(json_path, "r", encoding="utf-8") as f:
        data = json.load(f)

    test_id = data["id"]
    for section in ["listening", "reading"]:
        for part in data[section]:
            if part in ["part6", "part7"]:
                passages = data[section][part]
                for p in passages:
                    passage_text = p["passage"] if isinstance(p, dict) and "passage" in p else ""
                    questions = p.get("questions") or p.get("blanks", [])
                    for q in questions:
                        qn = q["question_number"]
                        q_text = q.get("question") or q.get("sentence") or "Câu hỏi không rõ"
                        options = ", ".join(q.get("options", []))
                        explain = q.get("explanation", "")
                        correct = q.get("correct_answer", "")
                        full_text = f"""Đoạn văn: {passage_text}
Câu {qn}: {q_text}
Lựa chọn: {options}
Đáp án đúng: {correct}
Giải thích: {explain}"""
                        add_doc(test_id, qn, full_text, {"section": section, "part": part})
            else:
                for q in data[section][part]:
                    qn = q["question_number"]
                    q_text = q.get("question") or q.get("sentence") or "Câu hỏi không rõ"
                    options = ", ".join(q.get("options", []))
                    explain = q.get("explanation", "")
                    correct = q.get("correct_answer", "")
                    full_text = f"""Câu {qn}: {q_text}
Lựa chọn: {options}
Đáp án đúng: {correct}
Giải thích: {explain}"""
                    add_doc(test_id, qn, full_text, {"section": section, "part": part})
    return docs

def upload_docs_to_mongo():
    if not docs:
        print("Chưa có dữ liệu để upload, vui lòng chạy load_and_prepare_docs() trước.")
        return
    collection.insert_many(docs)
    print(f"Đã upload {len(docs)} câu hỏi lên MongoDB Atlas.")
