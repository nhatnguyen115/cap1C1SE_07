import React, { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import { http } from "../../../service/Http";
import LeftSidebarAdmin from "../../../components/LeftSidebarAdmin";
import AddLessonModal from "../../../modal/AddLessonModal";
import { LessonType } from "../../../types/lesson";

type PartType = {
  partId: number;
  partName: string;
  questionType: string;
  questionCount: number;
};

const SectionDetailsManagement: React.FC = () => {
  const [lessons, setLessons] = useState<LessonType[]>([]);
  const [parts, setParts] = useState<PartType[]>([]);
  const [activeTab, setActiveTab] = useState<"lesson" | "part">("lesson");
  const [searchParams] = useSearchParams();

  const sectionId = searchParams.get("sectionId");

  const [showConfirmModal, setShowConfirmModal] = useState(false);
  const [selectedPartId, setSelectedPartId] = useState<number | null>(null);
  const [selectedLessonId, setSelectedLessonId] = useState<number | null>(null);

  const [lessonToEdit, setLessonToEdit] = useState<LessonType | null>(null);

  const [isAddModalOpen, setAddModalOpen] = useState(false);
  const [isEditModalOpen, setEditModalOpen] = useState(false);

  const fetchData = async () => {
    if (!sectionId) return;

    try {
      const response = await http.get(`/practice?sectionId=${sectionId}`);
      if (response.status === 200) {
        setLessons(response.data.data.lessons);
        setParts(response.data.data.parts);
      }
    } catch (error) {
      console.error("Lỗi khi tải dữ liệu:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [sectionId]);

  const handleAddLesson = async (lesson: LessonType) => {
    if (!sectionId) return;
    try {
      const res = await http.post(`/sections/${sectionId}/lessons`, {
        ...lesson,
      });
      if (res.status === 200) {
        alert(res.data.message);
        fetchData(); // gọi lại useEffect hoặc fetchData riêng
      }
    } catch (err) {
      console.error("Lỗi khi thêm bài học:", err);
    }
  };

  const handleUpdateLesson = async (lesson: LessonType) => {
    try {
      const res = await http.put(`/lessons/${lesson.id}`, {
        ...lesson,
      });
      if (res.status === 200) {
        alert(res.data.message);
        fetchData(); // cập nhật lại list
      }
    } catch (err) {
      console.error("Lỗi khi cập nhật bài học:", err);
    }
  };

  const handleDeleteLesson = async (lessonId: number) => {
    try {
      await http.delete(`/lessons/${lessonId}`);
      setLessons((prev) => prev.filter((l) => l.id !== lessonId));
    } catch (error) {
      console.error("Lỗi khi xóa bài học:", error);
    }
  };

  const handleAddPart = async () => {
    if (!sectionId) return;
    try {
      const response = await http.post(`/sections/${sectionId}/parts`, {
        partName: "New Part",
        description: "",
        questionType: "MULTIPLE_CHOICE",
        instructions: "",
        questionCount: 0,
      });
      if (response.status === 200) {
        setParts((prev) => [...prev, response.data.data]);
      }
    } catch (error) {
      console.error("Lỗi khi thêm phần:", error);
    }
  };

  const handleUpdatePart = async (part: PartType) => {
    try {
      await http.put(`/parts/${part.partId}`, {
        ...part,
      });
    } catch (error) {
      console.error("Lỗi khi cập nhật phần:", error);
    }
  };

  const handleDeletePart = async (partId: number) => {
    try {
      await http.delete(`/parts/${partId}`);
      setParts((prev) => prev.filter((p) => p.partId !== partId));
    } catch (error) {
      console.error("Lỗi khi xóa phần:", error);
    }
  };

  return (
    <div className="min-h-screen flex bg-gray-100">
      <LeftSidebarAdmin customHeight="h-auto w-64" />
      <div className="flex-1 p-8">
        <h1 className="text-2xl font-bold mb-6 text-gray-800">
          Section Detail Management
        </h1>
        <div className="flex space-x-4 border-b border-gray-200 mb-4">
          <button
            onClick={() => setActiveTab("lesson")}
            className={`pb-2 px-4 text-sm font-medium border-b-2 transition ${
              activeTab === "lesson"
                ? "border-blue-600 text-blue-600"
                : "border-transparent text-gray-500 hover:text-blue-600"
            }`}
          >
            Lesson
          </button>
          <button
            onClick={() => setActiveTab("part")}
            className={`pb-2 px-4 text-sm font-medium border-b-2 transition ${
              activeTab === "part"
                ? "border-blue-600 text-blue-600"
                : "border-transparent text-gray-500 hover:text-blue-600"
            }`}
          >
            Part
          </button>
        </div>

        {activeTab === "lesson" && (
          <div>
            <div className="flex justify-between items-center mb-3">
              <h2 className="text-lg font-semibold text-gray-700">
                Danh sách bài học
              </h2>
              <button
                onClick={() => setAddModalOpen(true)}
                className="px-3 py-1 bg-blue-600 text-white text-sm rounded"
              >
                Thêm bài học
              </button>

              <AddLessonModal
                isOpen={isAddModalOpen}
                onClose={() => setAddModalOpen(false)}
                onSubmit={handleAddLesson}
              />
            </div>
            {lessons.length === 0 ? (
              <p className="text-gray-500">Không có dữ liệu bài học.</p>
            ) : (
              <ul className="space-y-2">
                {lessons.map((lesson) => (
                  <li
                    key={lesson.id}
                    className="p-4 bg-white rounded-md shadow-sm border border-gray-200 flex justify-between items-center"
                  >
                    <div>
                      <div className="font-medium text-gray-800">
                        {lesson.lessonName}
                      </div>
                      <div className="text-sm text-gray-500">
                        Loại nội dung: {lesson.contentType}
                      </div>
                    </div>
                    <div className="space-x-2">
                      <button
                        onClick={() => {
                          setLessonToEdit(lesson);
                          setEditModalOpen(true);
                        }}
                        className="px-2 py-1 text-xs bg-yellow-400 text-white rounded"
                      >
                        Sửa
                      </button>

                      <button
                        onClick={() => {
                          setSelectedLessonId(lesson.id!);
                          setShowConfirmModal(true);
                        }}
                        className="px-2 py-1 text-xs bg-red-500 text-white rounded"
                      >
                        Xoá
                      </button>
                    </div>
                  </li>
                ))}
                {/* Sửa lesson */}
                <AddLessonModal
                  isOpen={isEditModalOpen}
                  onClose={() => setEditModalOpen(false)}
                  initialData={lessonToEdit!}
                  onSubmit={handleUpdateLesson}
                />
                {/* Modal xác nhận xoá - đặt ngoài .map() */}
                {showConfirmModal && selectedLessonId !== null && (
                  <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
                    <div className="bg-white p-4 rounded shadow-md w-80">
                      <h2 className="text-lg font-semibold mb-2">
                        Xác nhận xoá
                      </h2>
                      <p className="mb-4">
                        Bạn có chắc chắn muốn xoá bài học này không?
                      </p>
                      <div className="flex justify-end gap-2">
                        <button
                          className="px-3 py-1 bg-gray-300 rounded"
                          onClick={() => setShowConfirmModal(false)}
                        >
                          Huỷ
                        </button>
                        <button
                          className="px-3 py-1 bg-red-500 text-white rounded"
                          onClick={() => {
                            handleDeleteLesson(selectedLessonId);
                            setShowConfirmModal(false);
                          }}
                        >
                          Xoá
                        </button>
                      </div>
                    </div>
                  </div>
                )}
              </ul>
            )}
          </div>
        )}

        {activeTab === "part" && (
          <div>
            <div className="flex justify-between items-center mb-3">
              <h2 className="text-lg font-semibold text-gray-700">
                Danh sách phần
              </h2>
              <button
                onClick={handleAddPart}
                className="px-3 py-1 bg-blue-600 text-white text-sm rounded"
              >
                Thêm phần
              </button>
            </div>
            {parts.length === 0 ? (
              <p className="text-gray-500">Không có dữ liệu phần.</p>
            ) : (
              <ul className="space-y-2">
                {parts.map((part) => (
                  <li
                    key={part.partId}
                    className="p-4 bg-white rounded-md shadow-sm border border-gray-200 flex justify-between items-center"
                  >
                    <div>
                      <div className="font-medium text-gray-800">
                        {part.partName}
                      </div>
                      <div className="text-sm text-gray-500">
                        Loại câu hỏi: {part.questionType} — Số câu:{" "}
                        {part.questionCount}
                      </div>
                    </div>
                    <div className="space-x-2">
                      <button
                        onClick={() => handleUpdatePart(part)}
                        className="px-2 py-1 text-xs bg-yellow-400 text-white rounded"
                      >
                        Sửa
                      </button>
                      <button
                        onClick={() => {
                          setSelectedPartId(part.partId);
                          setSelectedLessonId(null);
                          setShowConfirmModal(true);
                        }}
                        className="px-2 py-1 text-xs bg-red-500 text-white rounded"
                      >
                        Xoá
                      </button>
                      {showConfirmModal && (
                        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
                          <div className="bg-white p-4 rounded shadow-md w-80">
                            <h2 className="text-lg font-semibold mb-2">
                              Xác nhận xoá
                            </h2>
                            <p className="mb-4">
                              Bạn có chắc chắn muốn xoá phần này không?
                            </p>
                            <div className="flex justify-end gap-2">
                              <button
                                className="px-3 py-1 bg-gray-300 rounded"
                                onClick={() => setShowConfirmModal(false)}
                              >
                                Huỷ
                              </button>
                              <button
                                className="px-3 py-1 bg-red-500 text-white rounded"
                                onClick={() => {
                                  if (selectedPartId !== null) {
                                    handleDeletePart(selectedPartId);
                                  }
                                  if (selectedLessonId !== null) {
                                    handleDeleteLesson(selectedLessonId);
                                  }
                                  setShowConfirmModal(false);
                                }}
                              >
                                Xoá
                              </button>
                            </div>
                          </div>
                        </div>
                      )}
                    </div>
                  </li>
                ))}
              </ul>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default SectionDetailsManagement;
