import React, { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import { http } from "../../../service/Http";
import LeftSidebarAdmin from "../../../components/LeftSidebarAdmin";

type LessonType = {
  id: number;
  lessonName: string;
  contentType: string;
  duration: number | null;
};

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

  useEffect(() => {
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

    fetchData();
  }, [sectionId]);

  return (
    <div>
      <div className="min-h-screen flex bg-gray-100">
        {/* Left Sidebar */}
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
              <h2 className="text-lg font-semibold mb-3 text-gray-700">
                Danh sách bài học
              </h2>
              {lessons.length === 0 ? (
                <p className="text-gray-500">Không có dữ liệu bài học.</p>
              ) : (
                <ul className="space-y-2">
                  {lessons.map((lesson) => (
                    <li
                      key={lesson.id}
                      className="p-4 bg-gray-50 rounded-md shadow-sm border border-gray-200"
                    >
                      <div className="font-medium text-gray-800">
                        {lesson.lessonName}
                      </div>
                      <div className="text-sm text-gray-500">
                        Loại nội dung: {lesson.contentType}
                      </div>
                    </li>
                  ))}
                </ul>
              )}
            </div>
          )}

          {activeTab === "part" && (
            <div>
              <h2 className="text-lg font-semibold mb-3 text-gray-700">
                Danh sách phần
              </h2>
              {parts.length === 0 ? (
                <p className="text-gray-500">Không có dữ liệu phần.</p>
              ) : (
                <ul className="space-y-2">
                  {parts.map((part) => (
                    <li
                      key={part.partId}
                      className="p-4 bg-gray-50 rounded-md shadow-sm border border-gray-200"
                    >
                      <div className="font-medium text-gray-800">
                        {part.partName}
                      </div>
                      <div className="text-sm text-gray-500">
                        Loại câu hỏi: {part.questionType} — Số câu:{" "}
                        {part.questionCount}
                      </div>
                    </li>
                  ))}
                </ul>
              )}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default SectionDetailsManagement;
