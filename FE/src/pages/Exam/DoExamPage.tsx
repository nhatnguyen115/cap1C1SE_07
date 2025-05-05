import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { API_URIS } from "../../api/URIConstant";
import IcBreadcrumbGbk from "../../assets/icons/IcBreadcrumbGbk";
import ExamNavigationComponent from "../../components/ExamNavigationComponent";
import { toeicTest } from "../../data/toeicMockData";
import { http } from "../../service/Http";
import {
  AnswerType,
  DoExamType,
  PartWithQuestionsType,
  QuestionType,
} from "../../types/exam";
interface TestProps {
  isView: boolean;
}
export const DoExamPage: React.FC<TestProps> = ({ isView = false }) => {
  if (isView) console.log("viewing");

  const [answers, setAnswers] = useState<AnswerType[]>([]);
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
  const handleAnswer = (
    questionId: number,
    selectedOption: "A" | "B" | "C" | "D",
  ) => {
    const updatedAnswers = [...answers];
    const existingIndex = updatedAnswers.findIndex(
      (a) => a.questionId === questionId,
    );

    if (existingIndex !== -1) {
      // Nếu đã tồn tại, cập nhật
      updatedAnswers[existingIndex].selectedOption = selectedOption;
    } else {
      // Nếu chưa có, thêm mới
      updatedAnswers.push({ questionId, selectedOption });
    }

    setAnswers(updatedAnswers);
  };

  const handleNavigate = (questionIndex: number) => {
    setCurrentQuestion(questionIndex);
    const element = document.getElementById(`question-${questionIndex}`);
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
          const selected = answers.find((a) => a.questionId === item.id);

          return (
            <div key={item.id} className="mb-4" id={`question-${item.id}`}>
              <p className="font-semibold">Question {index + 1}</p>

              {item.url && (
                <img src={item.url} alt="question" className="w-full mb-2" />
              )}

              {Object.entries(item.options).map(([optionKey, optionValue]) => (
                <button
                  key={optionKey}
                  onClick={() => {
                    handleAnswer(item.id, optionKey as "A" | "B" | "C" | "D");
                    setCurrentQuestion(item.id); // hoặc item.id nếu bạn theo dõi theo id
                  }}
                  className={`border p-2 rounded-md w-full text-left mb-2 ${
                    selected?.selectedOption === optionKey
                      ? "bg-blue-500 text-white"
                      : "hover:bg-gray-200"
                  }`}
                >
                  {optionValue}
                </button>
              ))}
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
          <ExamNavigationComponent
            isView={isView}
            details={examDetails?.details}
            currentQuestion={currentQuestion}
            answers={answers}
            duration={examDetails?.exam.duration}
            onNavigate={handleNavigate}
          />
        </div>
      </div>
    </div>
  );
};
