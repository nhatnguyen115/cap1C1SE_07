import React from "react";
import { QuestionType } from "../types/part";
import QuestionCardComponent from "./QuestionCardComponent";

type PartDetailsComponentProps = {
  partName: string;
  questions: QuestionType[];
  elapsedSeconds: number;
  formatTime: (seconds: number) => string;
};

const PartDetailsComponent: React.FC<PartDetailsComponentProps> = ({
  partName,
  questions,
  elapsedSeconds,
  formatTime,
}) => {
  return (
    <div>
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">{partName}</h1>
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
    </div>
  );
};

export default PartDetailsComponent;
