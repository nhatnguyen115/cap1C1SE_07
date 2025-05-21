import { notification } from "antd";
import React, { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { API_URIS } from "../api/URIConstant";
import { http } from "../service/Http";
import { QuestionType } from "../types/part";

interface AddEditQuestionModalProps {
  partId: number | string;
  dataEdit?: QuestionType;
  onClose: () => void;
  onSubmitSuccess: () => void;
}

const AddEditQuestionModal: React.FC<AddEditQuestionModalProps> = ({
  partId,
  dataEdit,
  onClose,
  onSubmitSuccess,
}) => {
  const [content, setContent] = useState("");
  const [options, setOptions] = useState<Record<string, string>>({
    A: "",
    B: "",
    C: "",
    D: "",
  });
  const [correctAnswer, setCorrectAnswer] = useState("");
  const [explanation, setExplanation] = useState("");
  const [orderNumber, setOrderNumber] = useState(1);
  const [questionType, setQuestionType] = useState("LISTENING");

  useEffect(() => {
    if (dataEdit) {
      setContent(dataEdit.content);
      setOptions(dataEdit.options);
      setCorrectAnswer(dataEdit.correctAnswer);
      setExplanation(dataEdit.explanation);
      setOrderNumber(dataEdit.orderNumber ?? 10);
      setQuestionType(dataEdit.questionType || "LISTENING");
    }
  }, [dataEdit]);

  const handleChangeOption = (key: string, value: string) => {
    setOptions((prev) => ({ ...prev, [key]: value }));
  };

  const handleSubmit = async () => {
    const basePayload = {
      content,
      options,
      correctAnswer,
      explanation,
      questionType,
      orderNumber,
    };

    const payload: Partial<QuestionType> = dataEdit
      ? { ...basePayload, id: dataEdit.id }
      : basePayload;

    try {
      const response = dataEdit
        ? await http.put(
            API_URIS.PART.QUESTION_UPDATE(dataEdit.id ?? 0),
            payload,
          )
        : await http.post(API_URIS.PART.QUESTION_ADD(partId), payload);

      if (response.status == 200 || response.status == 201) {
        notification.success({
          message: response.data.message,
        });
        onSubmitSuccess();
      }
    } catch (err) {
      console.error(dataEdit ? "Lỗi khi cập nhật" : "Lỗi khi thêm", err);
      toast.error("Đã xảy ra lỗi.");
    }
  };

  return (
    <div
      onClick={(e) => e.stopPropagation()}
      className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50"
    >
      <div className="bg-white w-3/5 p-6 rounded shadow">
        <h2 className="text-lg font-semibold mb-4">
          {dataEdit ? "Chỉnh sửa câu hỏi" : "Thêm câu hỏi"}
        </h2>

        <div className="mb-3">
          <label className="block text-sm mb-1">Nội dung</label>
          <textarea
            className="w-full border rounded px-2 py-1"
            rows={3}
            value={content}
            onChange={(e) => setContent(e.target.value)}
          />
        </div>

        {["A", "B", "C", "D"].map((opt) => (
          <div className="mb-3" key={opt}>
            <label className="block text-sm mb-1">Đáp án {opt}</label>
            <input
              className="w-full border rounded px-2 py-1"
              value={options[opt]}
              onChange={(e) => handleChangeOption(opt, e.target.value)}
            />
          </div>
        ))}

        <div className="mb-3">
          <label className="block text-sm mb-1">Đáp án đúng</label>
          <select
            className="w-full border rounded px-2 py-1"
            value={correctAnswer}
            onChange={(e) => setCorrectAnswer(e.target.value)}
          >
            <option value="" disabled>--Chọn--</option>
            <option value="A">A</option>
            <option value="B">B</option>
            <option value="C">C</option>
            <option value="D">D</option>
          </select>
        </div>

        <div className="mb-3">
          <label className="block text-sm mb-1">Giải thích</label>
          <textarea
            className="w-full border rounded px-2 py-1"
            rows={4}
            value={explanation}
            onChange={(e) => setExplanation(e.target.value)}
          />
        </div>

        <div className="flex justify-end gap-2">
          <button
            className="px-3 py-1 bg-gray-300 rounded text-sm"
            onClick={onClose}
          >
            Huỷ
          </button>
          <button
            className="px-3 py-1 bg-blue-600 text-white rounded text-sm"
            onClick={handleSubmit}
          >
            {dataEdit ? "Cập nhật" : "Thêm"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default AddEditQuestionModal;
