import { notification } from "antd";
import React, { useEffect, useState } from "react";
import { http } from "../service/Http";
import { ExamType } from "../types/exam";

interface AddExamModalProps {
  isOpen: boolean;
  onClose: () => void;
  onSubmit: (exam: ExamType) => void;
  testType?: string
  initialData?: ExamType;
}

const AddExamModal: React.FC<AddExamModalProps> = ({
  isOpen, onClose, onSubmit, testType, initialData,
}) => {
  const [id, setId] = useState<number | undefined>(initialData?.id);
  const [examName, setExamName] = useState(initialData?.examName || "");
  const [totalScore, setTotalScore] = useState<number | "">(
    initialData?.totalScore || "",
  );
  const [duration, setDuration] = useState<number | "">(
    initialData?.duration || 0,
  );
  const [questionCount, setQuestionCount] = useState<number | "">(initialData?.questionCount || 0)
  const [level, setLevel] = useState(initialData?.level || "")

  useEffect(() => {
    if (initialData) {
      setId(initialData.id);
      setExamName(initialData.examName || "");
      setTotalScore(initialData.totalScore || 0);
      setDuration(initialData.duration || 0);
      setQuestionCount(initialData.questionCount || 0)
      setLevel(initialData.level || "BEGINNER")
    } else {
      setId(undefined);
      setExamName("EXAM");
      setTotalScore(0);
      setDuration(0);
      setQuestionCount(0);
      setLevel("")
    }
  }, [initialData, isOpen]);

  if (!isOpen) return null;

  const handleSubmit = () => {
    if (!examName.trim()) {
      notification.error({
        message: "Vui lòng nhập tên đề thi.",
      });
      return;
    }

    onSubmit({
      id,
      examName: examName.trim(),
      totalScore: Number(totalScore),
      duration: Number(duration),
      questionCount: Number(questionCount),
      level: level.trim(),
      testType: testType?.trim()
    });

    onClose();
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded shadow-lg w-3/5">
        <h2 className="text-lg font-semibold mb-4">
          {initialData ? "Sửa đề thi" : "Thêm đề thi"}
        </h2>

        {/* Tên đề thi */}
        <div className="mb-4">
          <label className="block mb-1 text-sm">Tên đề thi</label>
          <input
            type="text"
            value={examName}
            onChange={(e) => setExamName(e.target.value)}
            className="w-full border border-gray-300 rounded px-2 py-1 text-sm"
            placeholder="Nhập tên đề thi"
          />
        </div>

        {/* Tổng điểm */}
        <div className="mb-4">
          <label className="block mb-1 text-sm">Tổng điểm</label>
          <input
            type="number"
            min={0}
            value={totalScore}
            onChange={(e) =>
              setTotalScore(e.target.value === "" ? "" : Number(e.target.value))
            }
            className="w-full border border-gray-300 rounded px-2 py-1 text-sm"
            placeholder="Nhập tổng điểm"
          />
        </div>

        {/* Thời gian làm bài */}
        <div className="mb-4">
          <label className="block mb-1 text-sm">Thời gian làm bài (phút)</label>
          <input
            type="number"
            min={0}
            value={duration}
            onChange={(e) =>
              setDuration(e.target.value === "" ? "" : Number(e.target.value))
            }
            className="w-full border border-gray-300 rounded px-2 py-1 text-sm"
            placeholder="Nhập thời gian làm bài"
          />
        </div>

        <div className="mb-4">
          <label className="block mb-1 text-sm">Số lượng câu hỏi</label>
          <input
              type="number"
              min={0}
              value={questionCount}
              onChange={(e) =>
                  setQuestionCount(e.target.value === "" ? "" : Number(e.target.value))
              }
              className="w-full border border-gray-300 rounded px-2 py-1 text-sm"
              placeholder="Nhập số lượng câu hỏi"
          />
        </div>

        <div className="mb-4">
          <label className="block mb-1 text-sm">Cấp độ</label>
          <select
              value={level}
              onChange={(e) => {
                setLevel(e.target.value === "" ? "" : e.target.value)
              }}
              className="w-full border border-gray-300 rounded px-2 py-1 text-sm"
              required={true}
          >
            <option value="" disabled>----Lựa chọn----</option>
            <option value="BEGINNER">BEGINNER</option>
            <option value="INTERMEDIATE">INTERMEDIATE</option>
            <option value="ADVANCED">ADVANCED</option>
          </select>
        </div>
        {/* Buttons */}
        <div className="flex justify-end gap-2">
          <button
            onClick={onClose}
            className="px-3 py-1 bg-gray-300 rounded text-sm"
          >
            Huỷ
          </button>
          <button
            onClick={handleSubmit}
            className="px-3 py-1 bg-blue-600 text-white rounded text-sm"
          >
            {initialData ? "Sửa" : "Thêm"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default AddExamModal;
