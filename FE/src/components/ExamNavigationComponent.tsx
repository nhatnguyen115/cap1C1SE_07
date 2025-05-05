import React from "react";
import { TestNavigationProps } from "../types/exam";

const ExamNavigationComponent: React.FC<TestNavigationProps> = ({
  isView,
  details,
  currentQuestion,
  answers,
  onNavigate,
}) => {
  let questionCounter = 1;

  const renderQuestionButtons = (numQuestions: number) => {
    return Array.from({ length: numQuestions }, (_, index) => {
      const questionNumber = questionCounter++;
      const questionIndex = questionNumber - 1;
      return (
        <button
          key={index}
          onClick={() => onNavigate(questionIndex)}
          className={`border rounded-md text-center text-sm transition-all duration-200 p-1 ${
            currentQuestion === questionIndex
              ? "bg-blue-500 text-white"
              : answers[questionIndex] != null
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
      {details?.map((detail, idx) => {
        const partName = detail.part.partName || `Part ${idx + 1}`;

        return (
          <div key={idx}>
            <h3 className="text-lg font-semibold mb-2">{partName}</h3>
            <div className="grid grid-cols-4 gap-2">
              {renderQuestionButtons(detail.questions.length)}
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default ExamNavigationComponent;
