import React, { useEffect, useState } from "react";
import { http } from "../service/Http";
import PaginationComponent from "./PaginationComponent";

interface Part {
  id: number;
  partName: string;
  description: string;
  questionType: string;
  instructions: string | null;
  questionCount: number;
}

const ExamDetailsManagementComponent: React.FC<{ selectedId: number }> = ({
  selectedId,
}) => {
  const [parts, setParts] = useState<Part[]>([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);

  const handlePageChange = (newPage: number) => {
    if (newPage >= 0 && newPage < totalPages) {
      setPage(newPage);
    }
  };

  useEffect(() => {
    const fetchParts = async () => {
      try {
        const res = await http.get(`/parts`, {
          params: {
            selectedId: selectedId,
            checked: false,
            page: page,
            size: 5,
          },
        });
        setParts(res.data.data.items);
        setTotalPages(res.data.data.totalPages);
      } catch (error) {
        console.error("Failed to fetch parts", error);
      }
    };

    fetchParts();
  }, [selectedId, page]);

  return (
    <div className="mt-2 p-4 bg-gray-100 rounded-md shadow-inner">
      <h4 className="text-md font-semibold mb-2">Danh sách phần thi (Parts)</h4>
      {parts.map((part) => (
        <div
          key={part.id}
          className="mb-3 p-3 bg-white border border-gray-300 rounded"
        >
          <div className="font-medium text-gray-800 mb-1">
            Tên phần: {part.partName}
          </div>
          <div className="text-sm text-gray-700 mb-1">
            <strong>Mô tả:</strong> {part.description}
          </div>
          <div className="text-sm text-gray-700 mb-1">
            <strong>Loại câu hỏi:</strong> {part.questionType}
          </div>
          <div className="text-sm text-gray-700 mb-1">
            <strong>Số lượng câu hỏi:</strong> {part.questionCount}
          </div>
          <div className="mt-2 space-x-2">
            <button className="px-2 py-1 text-xs bg-red-500 text-white rounded">
              Xoá
            </button>
          </div>
        </div>
      ))}

      <PaginationComponent
        currentPage={page}
        totalPages={totalPages}
        onPageChange={handlePageChange}
      />
    </div>
  );
};

export default ExamDetailsManagementComponent;
