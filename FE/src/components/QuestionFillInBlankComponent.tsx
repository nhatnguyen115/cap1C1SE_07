import React, { useEffect, useState } from "react";
import { QuestionType } from "../types/part";

type Props = {
  answer?: string;
  onAnswer: (answer: string) => void;
};

const QuestionFillInBlankComponent: React.FC<Props> = ({
  answer,
  onAnswer,
}) => {
  const [userInput, setUserInput] = useState(answer || "");

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setUserInput(value);
    onAnswer(value);
  };

  useEffect(() => {
    setUserInput(answer || "");
  }, [answer]);

  return (
    <div className="bg-white p-4 rounded-lg shadow mb-6">
      <input
        type="text"
        value={userInput}
        onChange={handleChange}
        placeholder="Nhập câu trả lời..."
        className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
      />
    </div>
  );
};

export default QuestionFillInBlankComponent;
