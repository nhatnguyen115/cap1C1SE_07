import React, { useEffect, useState } from "react";
import { LessonDetails } from "../types/lesson";
import { http } from "../service/Http";
import { API_URIS } from "../api/URIConstant";
import { ChevronLeft, List } from "lucide-react";

interface LessonDetailsComponentProps {
  lessonId: string;
}

const LessonDetailsComponent: React.FC<LessonDetailsComponentProps> = ({
  lessonId,
}) => {
  const [lesson, setLesson] = useState<LessonDetails | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchLesson = async () => {
      try {
        const response = await http.get(API_URIS.LESSON.GET_BY_ID(lessonId));
        setLesson(response.data.data);
      } catch (err) {
        setError("Failed to load lesson.");
      } finally {
        setLoading(false);
      }
    };

    fetchLesson();
  }, [lessonId]);

  if (loading) return <p>Loading...</p>;
  if (error) return <p className="text-red-500">{error}</p>;
  if (!lesson) return <p>No lesson found.</p>;

  const [showSidebar, setShowSidebar] = useState(true);

  return (
    <div className="flex h-screen overflow-hidden">
      {/* Sidebar */}
      <aside
        className={`${
          showSidebar ? "block" : "hidden"
        } w-64 bg-gray-100 p-4 border-r md:block`}
      >
        <h2 className="text-xl font-semibold mb-4">Danh sách bài học</h2>
        <ul className="space-y-2">
          <li className="hover:text-blue-500 cursor-pointer">
            Bài 1: Giới thiệu
          </li>
          <li className="hover:text-blue-500 cursor-pointer">
            Bài 2: Khái niệm
          </li>
          <li className="hover:text-blue-500 cursor-pointer">
            Bài 3: Thực hành
          </li>
        </ul>
      </aside>

      {/* Main Content */}
      <div className="flex-1 flex flex-col">
        {/* Topbar */}
        <div className="flex justify-between items-center p-4 border-b bg-white shadow-sm">
          <div className="flex items-center gap-2">
            <button
              onClick={() => setShowSidebar(!showSidebar)}
              className="p-2 rounded hover:bg-gray-200 md:hidden"
            >
              {showSidebar ? <ChevronLeft size={20} /> : <List size={20} />}
            </button>
            <h1 className="text-lg font-semibold">Bài học #{lessonId}</h1>
          </div>
          <button className="bg-green-600 text-white px-3 py-1 rounded-full text-sm shadow">
            100%
          </button>
        </div>

        {/* Lesson Content */}
        <div className="flex-1 p-6 overflow-y-auto bg-gray-50">
          <h2 className="text-2xl font-bold mb-4">Nội dung bài học</h2>
          <p>Đây là nội dung chi tiết của bài học có ID: {lessonId}.</p>
          {/* Thêm nội dung HTML hoặc component động ở đây */}
        </div>
      </div>
    </div>
  );
};

export default LessonDetailsComponent;
