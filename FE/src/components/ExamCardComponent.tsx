import { notification } from "antd";
import { Award, BookOpen, Users, Workflow } from "lucide-react";
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { PATH_CONSTANTS } from "../api/PathConstant";
import { http } from "../service/Http";

type ExamCardProps = {
  examName: string;
  duration: number;
  totalScore: number;
  id: number | string;
  questions?: number;
  students?: number;
  level?: string;
  isTest?: boolean;
  isPractice?: boolean;
  attemptCount?: number;
  image?: string;
  isPremium?: boolean;
};

const ExamCardComponent: React.FC<ExamCardProps> = ({
  examName,
  duration,
  totalScore,
  id,
  questions,
  students,
  level,
  isTest,
  isPractice,
  attemptCount,
  image,
  isPremium,
}) => {
  const navigate = useNavigate();
  const fetchResult = async () => {
    try {
      const res = await http.get(`/practice/get-attempt`, {
        params: { examId: id },
      });
      return res.data;
    } catch (e) {
      console.error(e);
    }
  };
  useEffect(() => {
    console.log("isPremium: ", isPremium);
  }, []);
  return (
    <div className="border rounded-xl shadow-md p-4 bg-white w-full max-w-sm">
      <div className="relative">
        <img
          src={image}
          alt={examName}
          className="w-full h-40 object-cover rounded-md"
        />
        {isPremium && (
          <img
            src="src/assets/crown1.png"
            alt="Crown"
            className="w-7 h-7 absolute -top-1 -right-1 rotate-[45deg]"
          />
        )}
      </div>

      <h2 className="text-lg font-semibold mt-3">{examName}</h2>
      <div className="flex items-center gap-2 text-gray-600 text-sm mt-2">
        <BookOpen size={12} /> <span>Questions: {questions}</span>
        {isTest && (
          <>
            <Users size={12} className="ml-3" />
            <span>Students: {students}</span>
          </>
        )}
      </div>
      <div className="flex items-center gap-2 text-gray-600 text-sm mt-2">
        <Award size={12} /> <span className="font-medium">{level}</span>
        {isTest && (
          <>
            <Workflow size={12} className="ml-3" />
            <span>Attempts: {students}</span>
          </>
        )}
      </div>
      <div className="flex flex-col justify-center items-center">
        {isTest && (
          <button
            onClick={() =>
              navigate(
                PATH_CONSTANTS.USER_TEST.RANK.replace(":id", id.toString()),
              )
            }
            className="mt-4 w-full bg-green-600 text-white py-2 rounded-md hover:bg-green-700 transition-all"
          >
            Bảng xếp hạng
          </button>
        )}

        {isPractice && (
          <button
            onClick={() => {
              fetchResult()
                .then((res) => {
                  if (res.data != null) {
                    navigate(
                      PATH_CONSTANTS.EXAM.PRACTICE_RESUL_BY_ID(res.data),
                    );
                  } else {
                    notification.error({
                      message: "Bạn chưa làm bài lần nào",
                    });
                  }
                })
                .catch((error) =>
                  notification.error({
                    message: "Lấy kết quả thất bại",
                  }),
                );
            }}
            className="mt-4 w-full bg-green-600 text-white py-2 rounded-md hover:bg-green-700 transition-all"
          >
            Kết Quả
          </button>
        )}
        <button
          onClick={() => {
            if (isTest) {
              navigate(
                PATH_CONSTANTS.EXAM.EXAMS_DO_BY_ID.replace(
                  ":id",
                  id.toString(),
                ),
              );
            } else {
              navigate(
                PATH_CONSTANTS.EXAM.PRACTICE.replace(":id", id.toString()),
              );
            }
          }}
          className="mt-4 w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700 transition-all"
        >
          Luyện tập ngay
        </button>
      </div>
    </div>
  );
};

export default ExamCardComponent;
