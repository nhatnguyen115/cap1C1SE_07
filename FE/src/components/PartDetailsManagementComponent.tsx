import { notification } from "antd";
import React, { useEffect, useState } from "react";
import { API_URIS } from "../api/URIConstant";
import AddMediaModal from "../modal/AddMediaModal";
import AddQuestionModal from "../modal/AddQuestionModal";
import { http } from "../service/Http";
import { QuestionType } from "../types/part";
import ConfirmDialogComponent from "./ConfirmDialogComponent";
import PaginationComponent from "./PaginationComponent";

const PartDetailsManagementComponent: React.FC<{
  partId: number;
  isFetchQuestion?: boolean;
}> = ({ partId, isFetchQuestion }) => {
  const [questions, setQuestions] = useState<QuestionType[]>([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const [showAddModal, setShowAddModal] = useState(false);
  const [editData, setEditData] = useState<QuestionType | null>(null);
  const [isEdited, setIsEdited] = useState(false);
  const [isAddMedia, setAddMedia] = useState(false);
  const [showDeleteQuestion, setShowDeleteQuestion] = useState(false);
  const [idQuestionDel, setIdQuestionDel] = useState<number>(0);

  const [questionMedia, setQuestionMedia] = useState<number>(0);
  const handlePageChange = (newPage: number) => {
    if (newPage >= 0 && newPage < totalPages) {
      setPage(newPage);
    }
  };

  const handleAddMedia = async (
    media: { file: File; mediaType: "AUDIO" | "VIDEO" | "IMAGE" },
    questionId: number,
  ) => {
    try {
      const formData = new FormData();
      formData.append("mediaType", media.mediaType);
      formData.append("file", media.file);

      const response = await http.post(
        `/questions/${questionId}/media`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        },
      );

      notification.success({
        message: "Tải lên media thành công!",
      });

      console.log("Upload thành công:", response.data);
    } catch (error) {
      console.error("Upload media thất bại:", error);
      notification.error({
        message: "Tải lên thất bại. Vui lòng thử lại.",
      });
    }
  };
  const handleDeleteQuestion = async (id: number) => {
    try {
      const res = await http.delete(API_URIS.PART.QUESTION_DELETE(id));
      if (res.data.status == 200) {
        notification.success({
          message: res.data.message || "Xoá thành công!",
        });
        setIsEdited(!isEdited);
      }
    } catch (err) {
      console.error("Failed to fetch questions", err);
    }
  };
  useEffect(() => {
    const fetchQuestions = async () => {
      try {
        const res = await http.get(API_URIS.PART.QUESTION.LIST(partId, page));
        setQuestions(res.data.data.items);
        setTotalPages(res.data.data.totalPages);
      } catch (err) {
        console.error("Failed to fetch questions", err);
      }
    };

    fetchQuestions();
  }, [partId, page, isFetchQuestion, isEdited]);

  return (
    <div className="mt-4 p-4 bg-gray-50 rounded shadow-inner">
      <h3 className="text-md font-semibold mb-2">Danh sách câu hỏi</h3>
      {questions.map((q) => (
        <div
          key={q.id}
          className="mb-3 p-3 bg-white border border-gray-200 rounded"
        >
          <div className="font-medium text-gray-800 mb-1">
            Câu hỏi: {q.content}
          </div>
          <ul className="pl-4 list-disc text-sm text-gray-700">
            {Object.entries(q.options).map(([key, value]) => (
              <li key={key}>
                <strong>{key}</strong>: {value}
              </li>
            ))}
          </ul>
          <div className="text-sm mt-1">
            <strong>Đáp án đúng:</strong> {q.correctAnswer} |{" "}
            <strong>Độ khó:</strong> {q.difficulty}
          </div>
          <div className="mt-2 space-x-2">
            <button
              className="px-3 py-1 bg-blue-600 text-white text-sm rounded"
              onClick={(e) => {
                e.stopPropagation();
                setAddMedia(true);
                setQuestionMedia(q.id ?? 0);
              }}
            >
              Thêm media
            </button>
            <button
              className="px-2 py-1 text-xs bg-yellow-500 text-white rounded"
              onClick={(e) => {
                e.stopPropagation();
                setEditData(q);
                setShowAddModal(true);
              }}
            >
              Sửa
            </button>

            <button
              onClick={(e) => {
                e.stopPropagation();
                setIdQuestionDel(q.id ?? 0);
                setShowDeleteQuestion(true);
              }}
              className="px-2 py-1 text-xs bg-red-500 text-white rounded"
            >
              Xoá
            </button>
          </div>
        </div>
      ))}
      {showAddModal ? (
        <AddQuestionModal
          onClose={() => {
            setShowAddModal(false);
            setEditData(null);
          }}
          dataEdit={editData ?? undefined}
          onSubmitSuccess={() => {
            setShowAddModal(false);
            setEditData(null);
            setIsEdited(!isEdited);
            // reload lại danh sách câu hỏi sau khi sửa
            setPage(0); // hoặc giữ nguyên nếu muốn
          }}
          partId={partId}
        />
      ) : null}
      <ConfirmDialogComponent
        isOpen={showDeleteQuestion}
        title="Xác nhận xoá câu hỏi"
        message="Bạn có muốn xoá câu hỏi này không?"
        onConfirm={() => {
          handleDeleteQuestion(idQuestionDel);
          setShowDeleteQuestion(false);
        }}
        onCancel={() => setShowDeleteQuestion(false)}
      />
      <AddMediaModal
        isOpen={isAddMedia}
        onClose={() => setAddMedia(false)}
        onSubmit={(media) => handleAddMedia(media, questionMedia)}
      />

      <PaginationComponent
        currentPage={page}
        totalPages={totalPages}
        onPageChange={handlePageChange}
        onOther={(e) => e.stopPropagation()}
      />
    </div>
  );
};

export default PartDetailsManagementComponent;
