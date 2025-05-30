from fastapi import FastAPI, Query
from fastapi.middleware.cors import CORSMiddleware
from app.rag import ask_hybrid
import uvicorn

app = FastAPI()


app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:3000"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
@app.get("/ask")
async def ask_question(
    test_id: int = Query(None),
    q_number: int = Query(None),
    question_text: str = Query(None)
):
    answer = ask_hybrid(test_id, q_number, question_text)
    return {"answer": answer}
if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=5000)
# run: uvicorn app.main:app --reload