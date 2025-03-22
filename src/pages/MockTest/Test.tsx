import React, { useState } from "react";
import { toeicTest } from "../../data/toeicMockData";
import Navigation from "./component/Navigation";

export const Test = () => {
  const [currentQuestion, setCurrentQuestion] = useState(0); // Track current question
  const [answers, setAnswers] = useState(
    new Array(toeicTest.questions).fill(null)
  ); // Store answers

  // Handle answering questions
  const handleAnswer = (questionIndex: number, answerIndex: number) => {
    const updatedAnswers = [...answers];
    updatedAnswers[questionIndex] = answerIndex; // Set answer for the question
    setAnswers(updatedAnswers);
  };

  // Render Listening Part 1
  const renderListeningPart1 = (part: any) => {
    return part && part.length > 0 ? (
      <div>
        <h3 className="text-lg font-semibold mb-2">Part 1: Listening</h3>
        {part.map((item: any, index: number) => (
          <div key={index} className="mb-4">
            {item.image && (
              <img src={item.image} alt="question" className="w-full mb-2" />
            )}
            {item.options.map((option: string, optionIndex: number) => (
              <button
                key={optionIndex}
                onClick={() => handleAnswer(currentQuestion, optionIndex)}
                className={`border p-2 rounded-md w-full text-left mb-2 ${
                  answers[currentQuestion] === optionIndex
                    ? "bg-blue-500 text-white"
                    : "hover:bg-gray-200"
                }`}
              >
                {option}
              </button>
            ))}
          </div>
        ))}
      </div>
    ) : null;
  };

  // Render Listening Part 2
  const renderListeningPart2 = (part: any) => {
    return part && part.length > 0 ? (
      <div>
        <h3 className="text-lg font-semibold mb-2">Part 2: Listening</h3>

        {part.map((item: any, index: number) => (
          <div key={index} className="mb-4">
            <p>{item.question}</p>
            {item.options.map((option: string, optionIndex: number) => (
              <button
                key={optionIndex}
                onClick={() => handleAnswer(currentQuestion, optionIndex)}
                className={`border p-2 rounded-md w-full text-left mb-2 ${
                  answers[currentQuestion] === optionIndex
                    ? "bg-blue-500 text-white"
                    : "hover:bg-gray-200"
                }`}
              >
                {option}
              </button>
            ))}
          </div>
        ))}
      </div>
    ) : null;
  };

  // Render Listening Part 3
  const renderListeningPart3 = (part: any) => {
    return part && part.length > 0 ? (
      <div>
        <h3 className="text-lg font-semibold mb-2">Part 3: Listening</h3>

        {part.map((item: any, index: number) => (
          <div key={index} className="mb-4">
            {item.questions.map((question: any, questionIndex: number) => (
              <div key={questionIndex}>
                <p>{question.question}</p>
                {question.options.map((option: string, optionIndex: number) => (
                  <button
                    key={optionIndex}
                    onClick={() => handleAnswer(currentQuestion, optionIndex)}
                    className={`border p-2 rounded-md w-full text-left mb-2 ${
                      answers[currentQuestion] === optionIndex
                        ? "bg-blue-500 text-white"
                        : "hover:bg-gray-200"
                    }`}
                  >
                    {option}
                  </button>
                ))}
              </div>
            ))}
          </div>
        ))}
      </div>
    ) : null;
  };

  // Render Listening Part 4
  const renderListeningPart4 = (part: any) => {
    return part && part.length > 0 ? (
      <div>
        <h3 className="text-lg font-semibold mb-2">Part 4: Listening</h3>

        {part.map((item: any, index: number) => (
          <div key={index} className="mb-4">
            {item.questions.map((question: any, questionIndex: number) => (
              <div key={questionIndex}>
                <p>{question.question}</p>
                {question.options.map((option: string, optionIndex: number) => (
                  <button
                    key={optionIndex}
                    onClick={() => handleAnswer(currentQuestion, optionIndex)}
                    className={`border p-2 rounded-md w-full text-left mb-2 ${
                      answers[currentQuestion] === optionIndex
                        ? "bg-blue-500 text-white"
                        : "hover:bg-gray-200"
                    }`}
                  >
                    {option}
                  </button>
                ))}
              </div>
            ))}
          </div>
        ))}
      </div>
    ) : null;
  };

  // Render Reading Part 5
  const renderReadingPart5 = (part: any) => {
    return part && part.length > 0 ? (
      <div>
        <h3 className="text-lg font-semibold mb-2">Part 5: Reading</h3>

        {part.map((item: any, index: number) => (
          <div key={index} className="mb-4">
            <p>{item.sentence}</p>
            {item.options.map((option: string, optionIndex: number) => (
              <button
                key={optionIndex}
                onClick={() => handleAnswer(currentQuestion, optionIndex)}
                className={`border p-2 rounded-md w-full text-left mb-2 ${
                  answers[currentQuestion] === optionIndex
                    ? "bg-blue-500 text-white"
                    : "hover:bg-gray-200"
                }`}
              >
                {option}
              </button>
            ))}
          </div>
        ))}
      </div>
    ) : null;
  };

  // Render Reading Part 6
  const renderReadingPart6 = (part: any) => {
    return part && part.length > 0 ? (
      <div>
        <h3 className="text-lg font-semibold mb-2">Part 6: Reading</h3>

        {part.map((item: any, index: number) => (
          <div key={index} className="mb-4">
            <p>{item.passage}</p>
            {item.blanks.map((blank: any, blankIndex: number) => (
              <div key={blankIndex}>
                <p>
                  Fill in the blank:{" "}
                  {blank.options.map((option: string, optionIndex: number) => (
                    <button
                      key={optionIndex}
                      onClick={() => handleAnswer(currentQuestion, optionIndex)}
                      className={`border p-2 rounded-md w-full text-left mb-2 ${
                        answers[currentQuestion] === optionIndex
                          ? "bg-blue-500 text-white"
                          : "hover:bg-gray-200"
                      }`}
                    >
                      {option}
                    </button>
                  ))}
                </p>
              </div>
            ))}
          </div>
        ))}
      </div>
    ) : null;
  };

  // Render Reading Part 7
  const renderReadingPart7 = (part: any) => {
    return part && part.length > 0 ? (
      <div>
        <h3 className="text-lg font-semibold mb-2">Part 7: Reading</h3>

        {part.map((item: any, index: number) => (
          <div key={index} className="mb-4">
            <p>
              {item.passage.title}: {item.passage.content}
            </p>
            {item.questions.map((question: any, questionIndex: number) => (
              <div key={questionIndex}>
                <p>{question.question}</p>
                {question.options.map((option: string, optionIndex: number) => (
                  <button
                    key={optionIndex}
                    onClick={() => handleAnswer(currentQuestion, optionIndex)}
                    className={`border p-2 rounded-md w-full text-left mb-2 ${
                      answers[currentQuestion] === optionIndex
                        ? "bg-blue-500 text-white"
                        : "hover:bg-gray-200"
                    }`}
                  >
                    {option}
                  </button>
                ))}
              </div>
            ))}
          </div>
        ))}
      </div>
    ) : null;
  };

  return (
    <div className="flex flex-row justify-between h-screen">
      {/* Test Content */}
      <div className="flex-1 flex flex-col justify-start items-center p-4 overflow-auto">
        <div className="w-full max-w-4xl">
          <h1 className="text-2xl font-semibold mb-4">{toeicTest.title}</h1>
          <p className="text-sm">
            {toeicTest.level} | {toeicTest.questions} Questions
          </p>

          {/* Listening Parts */}
          {renderListeningPart1(toeicTest.listening.part1)}
          {renderListeningPart2(toeicTest.listening.part2)}
          {renderListeningPart3(toeicTest.listening.part3)}
          {renderListeningPart4(toeicTest.listening.part4)}

          {/* Reading Parts */}
          {renderReadingPart5(toeicTest.reading.part5)}
          {renderReadingPart6(toeicTest.reading.part6)}
          {renderReadingPart7(toeicTest.reading.part7)}
        </div>
      </div>

      {/* Navigation Sidebar */}
      <div className="w-44 p-4 bg-white h-full overflow-auto">
        <Navigation toeicTest={toeicTest} />
      </div>
    </div>
  );
};
