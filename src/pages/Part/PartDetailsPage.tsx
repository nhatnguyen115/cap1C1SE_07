import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import QuestionCardComponent from "../../components/QuestionCardComponent";
import { getQuestions } from "../../service/PartService";
import { QuestionType } from "../../types/part";
import { SectionType } from "../../types/section";
const PartDetailsPage: React.FC = () => {
  const location = useLocation();
  const [questions, setQuestions] = useState<QuestionType[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [elapsedSeconds, setElapsedSeconds] = useState(0);

  const lessonPartState = location.state?.lessonPart;

  const [lessonPart, setLessonPart] = useState<SectionType[]>(lessonPartState);

  const partId = 101; // hoặc lấy từ router params nếu có
  useEffect(() => {
    const fetchQuestions = async () => {
      try {
        console.log("lessonPartState:", lessonPartState);

        const response = await getQuestions(partId, 0, 10);
        if (response) {
          setQuestions(response.items || []);
          setElapsedSeconds(response.elapsedSeconds || 0); // ✅ dùng nếu tồn tại
        }
      } catch (err) {
        setError("Failed to load questions.");
      } finally {
        setLoading(false);
      }
    };
    fetchQuestions();
  }, [partId]);

  // Timer tăng dần
  useEffect(() => {
    const timer = setInterval(() => {
      setElapsedSeconds((prev) => prev + 1);
    }, 1000);
    return () => clearInterval(timer);
  }, []);

  const formatTime = (totalSeconds: number) => {
    const minutes = String(Math.floor(totalSeconds / 60)).padStart(2, "0");
    const seconds = String(totalSeconds % 60).padStart(2, "0");
    return `${minutes}:${seconds}`;
  };

  if (loading) return <p className="text-center mt-10">Đang tải...</p>;
  if (error) return <p className="text-center text-red-500 mt-10">{error}</p>;

  return (
    <div className="flex min-h-screen">
      {/* Sidebar */}
      <aside className="w-64 bg-gray-100 p-4 border-r">
        <h2 className="text-lg font-semibold mb-4">Danh sách bài test</h2>
        {/* ...render danh sách bài test ở đây nếu có */}
      </aside>

      {/* Main Content */}
      <main className="flex-1 p-6">
        {/* Header */}
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-2xl font-bold">Part 1: Photos</h1>
          <div className="text-xl font-mono bg-gray-200 px-4 py-2 rounded">
            ⏳ {formatTime(elapsedSeconds)}
          </div>
        </div>

        {questions.map((question, index) => (
          <QuestionCardComponent
            key={question.id}
            question={question}
            index={index}
          />
        ))}

        {/* Thanh điều hướng */}
        <div className="flex justify-between mt-10">
          <div>
            {questions.map((_, idx) => (
              <button
                key={idx}
                className="mx-1 w-8 h-8 rounded-full bg-gray-300 hover:bg-gray-400"
              >
                {idx + 1}
              </button>
            ))}
          </div>

          {/* Restart */}
          <button
            className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
            onClick={() => window.location.reload()}
          >
            Restart
          </button>
        </div>
      </main>
    </div>
  );
};

export default PartDetailsPage;
