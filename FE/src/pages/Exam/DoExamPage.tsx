import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { API_URIS } from "../../api/URIConstant";
import IcBreadcrumbGbk from "../../assets/icons/IcBreadcrumbGbk";
import PaginationStaticComponent from "../../components/PaginationStaticComponent"; // đường dẫn tuỳ vào project
import { http } from "../../service/Http";
import { PartDetailType, QuestionType } from "../../types/exam";

interface TestProps {
  isView: boolean;
}

export const DoExamPage: React.FC<TestProps> = ({ isView = false }) => {
  const { id } = useParams();

  const [answers, setAnswers] = useState<{ [questionId: number]: string }>({});
  const [examDetails, setExamDetails] = useState<PartDetailType[]>([]);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchExam = async () => {
      try {
        const res = await http.get(API_URIS.EXAMS.DO_BY_EXAM_ID(id!));
        setExamDetails(res.data.data.details);
      } catch (error) {
        console.error("Failed to fetch exam:", error);
      }
    };
    fetchExam();
  }, []);

  const handleAnswer = (questionId: number, option: string) => {
    setAnswers((prev) => ({ ...prev, [questionId]: option }));
  };

  const renderQuestion = (question: QuestionType, index: number) => (
    <div key={question.id} className="mb-4">
      <p className="font-semibold">
        Câu {index + 1}: {question.content}
      </p>
      {Object.entries(question.options).map(([key, text]) => (
        <button
          key={key}
          onClick={() => handleAnswer(question.id, key)}
          className={`block w-full text-left border p-2 rounded-md mb-2 ${
            answers[question.id] === key
              ? "bg-blue-500 text-white"
              : "hover:bg-gray-200"
          }`}
        >
          {key}. {text}
        </button>
      ))}
    </div>
  );

  return (
    <div className="flex flex-col h-screen">
      <div className="flex flex-row justify-between flex-1 overflow-hidden">
        <div className="flex-1 flex flex-col justify-start items-center p-4 overflow-auto">
          <div
            className="text-lg w-full text-main font-normal flex gap-3 text-start mb-5 cursor-pointer items-center"
            onClick={() => navigate(-1)}
          >
            <IcBreadcrumbGbk />
            <span>Return</span>
          </div>

          {examDetails.map((partDetail, index) => (
            <div key={index} className="w-full max-w-4xl mb-10">
              <h3 className="text-xl font-semibold mb-3">
                {partDetail.part.partName}
              </h3>
              <PaginationStaticComponent
                items={partDetail.questions}
                itemsPerPage={1}
                renderItem={renderQuestion}
              />
            </div>
          ))}
        </div>

        <div className="p-4 bg-white h-full w-fit overflow-y-scroll"></div>
      </div>
    </div>
  );
};
