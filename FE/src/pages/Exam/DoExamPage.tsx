import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { API_URIS } from "../../api/URIConstant";
import IcBreadcrumbGbk from "../../assets/icons/IcBreadcrumbGbk";
import { toeicTest } from "../../data/toeicMockData";
import { http } from "../../service/Http";
import {
  DoExamType,
  PartWithQuestionsType,
  QuestionType,
} from "../../types/exam";
import TestNavigationComponent from "./../../components/TestNavigationComponent";
interface TestProps {
  isView: boolean;
}
export const DoExamPage: React.FC<TestProps> = ({ isView = false }) => {
  if (isView) console.log("viewing");

  const [answers, setAnswers] = useState(
    new Array(toeicTest.questions).fill(null),
  );
  const navigate = useNavigate();

  const { id } = useParams();

  // const [answers, setAnswers] = useState<{ [questionId: number]: string }>({});
  const [examDetails, setExamDetails] = useState<DoExamType>();
  const [currentQuestion, setCurrentQuestion] = useState<number>(0); // Track current question index

  useEffect(() => {
    const fetchExam = async () => {
      try {
        const res = await http.get(API_URIS.EXAMS.DO_BY_EXAM_ID(id!));
        console.log("res.data.data: ", res.data.data);

        setExamDetails(res.data.data);
      } catch (error) {
        console.error("Failed to fetch exam:", error);
      }
    };
    fetchExam();
  }, [id]);

  useEffect(() => {
    console.log("answers: ", answers);
  }, [answers]);

  const handleGoBack = () => {
    navigate(-1);
  };
  const handleAnswer = (questionIndex: number, answerIndex: number) => {
    const updatedAnswers = [...answers];
    updatedAnswers[questionIndex] = answerIndex;
    setAnswers(updatedAnswers);
    setCurrentQuestion(questionIndex); // Update current question when answering
  };
  const handleNavigate = (questionIndex: number) => {
    setCurrentQuestion(questionIndex);
    const element = document.getElementById(`question-${questionIndex + 1}`);
    if (element) {
      element.scrollIntoView({ behavior: "smooth" });
    }
  };
  let questionCounter = 1;

  const getQuestionProps = () => {
    const id = `question-${questionCounter}`;
    const number = questionCounter;
    questionCounter++;
    return { id, number };
  };

  const renderListeningPart = (part: PartWithQuestionsType) => {
    return part.questions && part.questions.length > 0 ? (
      <div>
        <h3 className="text-lg font-semibold mb-2">{part.part.partName}</h3>
        {part.questions.map((item: QuestionType, index: number) => {
          const { id, number } = getQuestionProps();
          return (
            <div key={index} className="mb-4" id={id}>
              <p className="font-semibold">Question {number}</p>
              {item.url && (
                <img src={item.url} alt="question" className="w-full mb-2" />
              )}
              {Object.entries(item.options).map(
                ([optionKey, optionValue], optionIndex) => (
                  <button
                    key={optionKey}
                    onClick={() => handleAnswer(number - 1, optionIndex)}
                    className={`border p-2 rounded-md w-full text-left mb-2 ${
                      answers[number - 1] === optionIndex
                        ? "bg-blue-500 text-white"
                        : "hover:bg-gray-200"
                    }`}
                  >
                    {optionValue}
                  </button>
                ),
              )}
            </div>
          );
        })}
      </div>
    ) : null;
  };

  return (
    <div className="flex flex-col h-screen">
      <div className="flex flex-row justify-between flex-1 overflow-hidden">
        <div className="flex-1 flex flex-col justify-start items-center p-4 overflow-auto">
          <div
            className="text-lg w-full text-main font-normal flex gap-3 text-start mb-5 cursor-pointer items-center"
            onClick={handleGoBack}
          >
            <IcBreadcrumbGbk />
            <span>{"Return"}</span>
          </div>

          <div className="w-full max-w-2xl bg-gray-100 top-0 z-10 rounded-full my-10">
            <audio
              controls
              className="w-full max-w-4xl mx-auto block"
              src={toeicTest.audio}
            >
              Your browser does not support the audio element.
            </audio>
          </div>

          <div className="w-full max-w-4xl">
            {examDetails?.details.map((part, index) => {
              return renderListeningPart(part);
            })}
          </div>
        </div>

        <div className=" p-4 bg-white h-full w-fit overflow-y-scroll">
          <TestNavigationComponent
            isView={isView}
            details={examDetails?.details}
            currentQuestion={currentQuestion}
            answers={answers}
            onNavigate={handleNavigate}
          />
        </div>
      </div>
    </div>
  );
};
