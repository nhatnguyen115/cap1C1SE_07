import React from "react";
import { TestNavigationProps } from "../types/exam";

const TestNavigationComponent: React.FC<TestNavigationProps> = ({
  isView,
  details,
  currentQuestion,
  answers,
  onNavigate,
}) => {
  const renderQuestionButtons = (count: number, offset: number) => {
    return Array.from({ length: count }).map((_, i) => (
      <button
        key={i}
        className={`border rounded-md text-center text-sm transition-all duration-200 p-1 ${
          currentQuestion === offset
            ? "bg-blue-500 text-white"
            : answers[offset] != null
            ? "bg-green-500 text-white"
            : "hover:bg-blue-500 hover:text-white"
        }`}
        onClick={() => onNavigate(offset + i)}
      >
        {offset + i + 1}
      </button>
    ));
  };

  let questionOffset = 0;

  return (
    <div className="space-y-4">
      {details?.map((detail, idx) => {
        const partName = detail.part.partName || `Part ${idx + 1}`;
        const questionCount = detail.questions.length;
        const currentOffset = questionOffset;
        questionOffset += questionCount;

        return (
          <div key={idx}>
            <h3 className="text-lg font-semibold mb-2">{partName}</h3>
            <div className="grid grid-cols-4 gap-2">
              {renderQuestionButtons(questionCount, currentOffset)}
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default TestNavigationComponent;
