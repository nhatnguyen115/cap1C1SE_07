import React, { useEffect, useState } from "react";
import { http } from "../service/Http";
import { useNavigate } from "react-router-dom";
import { Lesson, Part } from "../types/section";
import { API_URIS } from "../api/URIConstant";

interface SectionDetailsComponentProps {
  sectionId: string;
  sectionName: string;
}
const SectionDetailsComponent: React.FC<SectionDetailsComponentProps> = ({
  sectionId,
  sectionName,
}) => {
  const [lessons, setLessons] = useState<Lesson[]>([]);
  const [parts, setParts] = useState<Part[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchSections = async () => {
      try {
        const response = await http.get(
          API_URIS.PRACTICE.GET_ALL_BY_SECTION(sectionId),
        );
        const data = response.data?.data;
        setLessons(data.lessons || []);
        setParts(data.parts || []);
      } catch (error) {
        console.error("Failed to fetch section details:", error);
      }
    };

    fetchSections();
  }, [sectionId]);

  return (
    <div className="p-6">
      <div>
        <h1 className="text-3xl">{sectionName}</h1>
      </div>
      {/* Lessons Section */}
      <div className="mb-8">
        <h2 className="text-2xl font-semibold mb-4 text-gray-800">Lessons</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          {lessons.map((lesson) => (
            <div
              key={lesson.id}
              className="border border-gray-300 rounded-lg p-4 shadow-sm hover:shadow-md transition duration-300 bg-white"
            >
              <h3 className="text-lg font-medium text-blue-700">
                {lesson.lessonName}
              </h3>
              <p className="text-sm text-gray-600">
                Type: {lesson.contentType}
              </p>
              <p className="text-sm text-gray-500">
                Duration: {lesson.duration ? `${lesson.duration} mins` : "N/A"}
              </p>
            </div>
          ))}
        </div>
      </div>

      {/* Parts Section */}
      <div>
        <h2 className="text-2xl font-semibold mb-4 text-gray-800">Parts</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          {parts.map((part) => (
            <div
              key={part.partId}
              className="border border-gray-300 rounded-lg p-4 shadow-sm hover:shadow-md transition duration-300 bg-white"
            >
              <h3 className="text-lg font-medium text-green-700">
                {part.partName}
              </h3>
              <p className="text-sm text-gray-600">Type: {part.questionType}</p>
              <p className="text-sm text-gray-500">
                Questions: {part.questionCount}
              </p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default SectionDetailsComponent;
