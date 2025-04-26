import React, { useEffect, useState } from "react";
import { FaEllipsisH, FaTimes, FaUpload } from "react-icons/fa";
import LeftSidebarAdmin from "../../../components/LeftSidebarAdmin";
import PaginationComponent from "../../../components/PaginationComponent";
import AddExamModal from "../../../modal/AddExamModal";
import { http } from "../../../service/Http";
import { ExamType } from "../../../types/exam";

const TestManagementPage: React.FC = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isEditExamModalOpen, setEditExamModalOpen] = useState(false);
  const [isAddExamModalOpen, setAddExamModalOpen] = useState(false);
  const [tests, setTests] = useState<ExamType[]>([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);

  const handlePageChange = (newPage: number) => {
    if (newPage >= 0 && newPage < totalPages) {
      setPage(newPage);
    }
  };
  const fetchData = async () => {
    try {
      const response = await http.get(`/exams?page=${page}`);
      if (response.status === 200) {
        setTests(response.data.data.items); // chỉ lấy danh sách exams
      }
    } catch (error) {
      console.error("Lỗi khi tải dữ liệu:", error);
    }
  };

  const handleAddExam = async (exam: ExamType) => {
    if (!exam.testId) return;
    try {
      const res = await http.post(`/tests/${exam.testId}/exams`, {
        ...exam,
      });
      if (res.status === 200) {
        alert(res.data.message);
        fetchData(); // gọi lại useEffect hoặc fetchData riêng
      }
    } catch (err) {
      console.error("Lỗi khi thêm bài học:", err);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);
  return (
    <div className="min-h-screen flex bg-gray-100">
      <LeftSidebarAdmin customHeight="h-auto w-64" />
      <div className="flex-1 p-8">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-3xl font-bold text-gray-800">Quản lý Đề thi</h1>
          <button
            className="bg-blue-600 text-white px-6 py-3 rounded-md hover:bg-blue-700 transition"
            onClick={() => setAddExamModalOpen(true)}
          >
            Thêm đề thi mới
          </button>
          <AddExamModal
            isOpen={isAddExamModalOpen}
            onClose={() => setAddExamModalOpen(false)}
            onSubmit={handleAddExam}
          />
        </div>

        {/* Bảng danh sách đề thi */}
        <div className="bg-white shadow-lg rounded-xl overflow-hidden">
          <table className="w-full table-auto">
            <thead>
              <tr className="bg-gray-200 text-gray-700 uppercase text-sm leading-normal">
                <th className="py-3 px-4 text-left">ID</th>
                <th className="py-3 px-4 text-left">Tên bài thi</th>
                <th className="py-3 px-4 text-left">Loại bài thi</th>
                <th className="py-3 px-4 text-center">Tổng điểm</th>
                <th className="py-3 px-4 text-center">Thời gian</th>
                <th className="py-3 px-4 text-center">Hành động</th>
              </tr>
            </thead>
            <tbody className="text-gray-600 text-sm">
              {tests.map((exam, index) => (
                <tr
                  key={exam.id}
                  className={`border-b hover:bg-gray-100 transition ${
                    index % 2 === 0 ? "bg-gray-50" : ""
                  }`}
                >
                  <td className="py-4 px-4">{exam.id}</td>
                  <td className="py-4 px-4">{exam.examName}</td>
                  <td className="py-4 px-4">
                    <span
                      className={`px-2 py-1 text-xs rounded-full font-semibold ${
                        exam.testType === "Simulation Test"
                          ? "bg-orange-200 text-orange-800"
                          : exam.testType === "MINI TEST"
                          ? "bg-blue-200 text-blue-800"
                          : "bg-yellow-200 text-yellow-800"
                      }`}
                    >
                      {exam.testType}
                    </span>
                  </td>
                  <td className="py-4 px-4 text-center">{exam.totalScore}</td>
                  <td className="py-4 px-4 text-center">
                    {exam.duration} phút
                  </td>
                  <td className="py-4 px-4 text-center">
                    <button className="text-gray-500 hover:text-gray-700 transition">
                      <FaEllipsisH size={18} />
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <PaginationComponent
            currentPage={page}
            totalPages={totalPages}
            onPageChange={handlePageChange}
          />
        </div>
      </div>

      {/* Modal Thêm Câu Hỏi */}
      {isModalOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
          <div className="bg-white p-6 rounded-lg shadow-lg w-96 relative">
            <button
              className="absolute top-3 right-3 text-red-600 text-xl"
              onClick={() => setIsModalOpen(false)}
            >
              <FaTimes />
            </button>

            <button className="flex items-center gap-2 border border-gray-400 px-4 py-2 rounded bg-gray-200 hover:bg-gray-300 transition">
              <FaUpload />
              Upload File
            </button>

            <button className="mt-4 w-full py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition">
              Thêm đề thi
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default TestManagementPage;
