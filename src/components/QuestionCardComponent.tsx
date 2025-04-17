import React from "react";
import { QuestionType } from "../types/part";

type Props = {
  question: QuestionType;
  index: number;
};

const QuestionCardComponent: React.FC<Props> = ({ question, index }) => {
  return (
    <div className="bg-white p-4 rounded-lg shadow mb-6">
      <div className="mb-4">
        <img
          src={question.url}
          alt={`Question ${index + 1}`}
          className="w-60 h-auto rounded"
        />
      </div>
      <div className="space-y-2">
        {Object.entries(question.options).map(([key, value]) => (
          <div key={key} className="flex items-center gap-2">
            <input
              type="radio"
              name={`question-${question.id}`}
              id={`${question.id}-${key}`}
            />
            <label
              htmlFor={`${question.id}-${key}`}
              className="text-sm font-medium"
            >{`(${key}) ${value}`}</label>
          </div>
        ))}
      </div>
    </div>
  );
};

export default QuestionCardComponent;
