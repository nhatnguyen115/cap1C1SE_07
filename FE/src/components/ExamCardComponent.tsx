import React from "react";
import { FaClock, FaLightbulb } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import { PATH_CONSTANTS } from "../api/PathConstant";

type ExamCardProps = {
  examName: string;
  duration: number;
  totalScore: number;
  id: number | string;
};

const ExamCardComponent: React.FC<ExamCardProps> = ({
  examName,
  duration,
  totalScore,
  id,
}) => {
  const navigate = useNavigate();

  return (
    <div className="p-10 mb-3 flex flex-col rounded-2xl shadow-xl transform transition duration-300 hover:scale-105">
      <div>
        <img />
      </div>
      <div>
        {" "}
        <h2 className="text-lg font-semibold">{examName}</h2>
      </div>
      <div className="flex flex-row items-center">
        <FaClock />
        <p>Thời lượng: {duration} phút</p>
      </div>
      <div className="flex flex-row items-center">
        <FaLightbulb />
        <p>Tổng điểm: {totalScore}</p>
      </div>
      <div className="flex flex-col justify-center items-center">
        <button
          onClick={() =>
            navigate(
              PATH_CONSTANTS.USER_TEST.RANK.replace(":id", id.toString()),
            )
          }
          className="bg-green-500 text-white rounded-2xl px-5 py-2 mt-3 hover:shadow-xl"
        >
          Bảng xếp hạng
        </button>
        <button
          onClick={() =>
            navigate(
              PATH_CONSTANTS.EXAM.EXAMS_DO_BY_ID.replace(":id", id.toString()),
            )
          }
          className="bg-blue-500 text-white rounded-2xl px-5 py-2 mt-3 hover:shadow-xl"
        >
          Luyện tập ngay
        </button>
      </div>
    </div>
  );
};

export default ExamCardComponent;
