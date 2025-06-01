import {Award, BookOpen, Users, Workflow} from "lucide-react";
import React from "react";
import { useNavigate } from "react-router-dom";
import { PATH_CONSTANTS } from "../api/PathConstant";
import {http} from "../service/Http";
import {notification} from "antd";

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
  isPremium?: boolean;
  image?: string;
};

const ExamCardComponent: React.FC<ExamCardProps> = ({
  examName,
  duration,
  totalScore,
  id,
  questions,
  students,
  level, isTest, isPractice, attemptCount, isPremium,
  image,
}) => {
  const navigate = useNavigate();
    const fetchResult = async () => {
        try {
            const res = await http.get(`/practice/get-attempt`, {params: {examId: id}});
            return res.data
        } catch (e) {
            console.error(e)
        }
    };
  return (
      <div
          className={`relative border rounded-xl shadow-md p-4 w-full max-w-sm transition-all ${
              isPremium ? "bg-yellow-50 border-yellow-400" : "bg-white"
          }`}
      >
          {/* PREMIUM BADGE */}
          {isPremium && (
              <div className="absolute top-2 right-2 bg-yellow-500 text-white text-xs font-bold px-2 py-1 rounded">
                  PREMIUM
              </div>
          )}

          <img
              src={image}
              alt={examName}
              className="w-full h-40 object-cover rounded-md"
          />
          <h2 className="text-lg font-semibold mt-3">{examName}</h2>

          <div className="flex items-center gap-2 text-gray-600 text-sm mt-2">
              <BookOpen size={12} />
              <span>Questions: {questions}</span>
              {isTest && (
                  <>
                      <Users size={12} className="ml-3" />
                      <span>Students: {students}</span>
                  </>
              )}
          </div>

          <div className="flex items-center gap-2 text-gray-600 text-sm mt-2">
              <Award size={12} />
              <span className="font-medium">{level}</span>
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
                                      navigate(PATH_CONSTANTS.EXAM.PRACTICE_RESUL_BY_ID(res.data));
                                  } else {
                                      notification.error({
                                          message: "Bạn chưa làm bài lần nào",
                                      });
                                  }
                              })
                              .catch(() =>
                                  notification.error({ message: "Lấy kết quả thất bại" }),
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
                          navigate(PATH_CONSTANTS.EXAM.EXAMS_DO_BY_ID.replace(":id", id.toString()));
                      } else {
                          navigate(PATH_CONSTANTS.EXAM.PRACTICE.replace(":id", id.toString()));
                      }
                  }}
                  className={`mt-4 w-full text-white py-2 rounded-md transition-all ${
                      isPremium ? "bg-yellow-600 hover:bg-yellow-700" : "bg-blue-600 hover:bg-blue-700"
                  }`}
              >
                  Luyện tập ngay
              </button>
          </div>
      </div>

  );
};

export default ExamCardComponent;
