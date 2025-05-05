import React, { useEffect, useState } from "react";
import { http } from "../service/Http";
import { QuestionType, TestNavigationProps } from "../types/exam";

const ExamNavigationComponent: React.FC<TestNavigationProps> = ({
  isView,
  details,
  currentQuestion,
  answers,
  duration,
  onNavigate,
}) => {
  const [time, setTime] = useState<number>((duration || 120) * 60);

  const formatTime = (timeInSeconds: number) => {
    const minutes = Math.floor(timeInSeconds / 60);
    const seconds = timeInSeconds % 60;
    return `${String(minutes).padStart(2, "0")}:${String(seconds).padStart(
      2,
      "0",
    )}`;
  };
  let questionCounter = 1;

  const handleSubmitTest = async () => {
    try {
      const payload = answers.map((a) => ({
        questionId: a.questionId,
        selectedAnswer: a.selectedOption,
      }));

      const response = await http.post("/user-test/submit-test", payload, {
        params: {
          attemptId: 1, // hoặc lấy từ state/router tùy logic
        },
      });

      console.log("Submit thành công:", response.data);
      // Hiển thị thông báo thành công, hoặc chuyển trang
    } catch (error) {
      console.error("Lỗi khi submit:", error);
      // Hiển thị thông báo lỗi
    }
  };

  useEffect(() => {
    // Only start timer if time is greater than 0
    if (time > 0) {
      const timer = setInterval(() => {
        setTime((prevTime) => prevTime - 1);
      }, 1000);

      // Cleanup interval on component unmount or when time changes
      return () => clearInterval(timer);
    }
  }, [time]);

  useEffect(() => {
    console.log("timeInSeconds: ", duration);
  }, []);

  const renderQuestionButtons = (questions: QuestionType[]) => {
    return questions.map((q, index) => {
      const questionNumber = questionCounter++;
      const questionIndex = questionNumber - 1;

      const isAnswered = answers.some((a) => a.questionId === q.id);
      const isCurrent = currentQuestion === q.id;

      return (
        <button
          key={q.id}
          onClick={() => onNavigate(q.id)}
          className={`border rounded-md text-center text-sm transition-all duration-200 p-1 ${
            isCurrent
              ? "bg-blue-500 text-white"
              : isAnswered
              ? "bg-green-500 text-white"
              : "hover:bg-blue-500 hover:text-white"
          }`}
        >
          {questionNumber}
        </button>
      );
    });
  };

  let questionOffset = 0;

  return (
    <div className="space-y-4">
      <div className="flex justify-between mb-4">
        <span className="text-sm">Thời gian còn lại:</span>
        <span className="font-semibold text-xl">
          {time > 0 ? formatTime(time) : "Hết giờ"}
        </span>
      </div>
      {details?.map((detail, idx) => {
        const partName = detail.part.partName || `Part ${idx + 1}`;

        return (
          <div key={idx}>
            <h3 className="text-lg font-semibold mb-2">{partName}</h3>
            <div className="grid grid-cols-4 gap-2">
              {renderQuestionButtons(detail.questions)}
            </div>
          </div>
        );
      })}
      <div className="mt-4 text-center">
        <button
          className="bg-blue-500 text-white p-2 rounded-md w-full hover:bg-blue-600"
          onClick={handleSubmitTest}
        >
          Submit Test
        </button>
      </div>
    </div>
  );
};

export default ExamNavigationComponent;
