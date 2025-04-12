import React, { useEffect, useState } from "react";
import { http } from "../service/Http";
import { useNavigate } from "react-router-dom";
import { Lesson, Part, SectionType } from "../types/section";
import { API_URIS } from "../api/URIConstant";
import { PATH_CONSTANTS } from "../api/PathConstant";

interface SectionDetailsComponentProps {
  sectionId: string;
  sectionName: string;
  sections: SectionType[];
}
const SectionDetailsComponent: React.FC<SectionDetailsComponentProps> = ({
  sectionId,
  sectionName,
  sections,
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
      <h1 className="text-3xl font-bold mb-6">{sectionName}</h1>
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {/* LEFT: Lessons + Parts */}
        <div className="md:col-span-2">
          {/* Lessons */}
          <div className="mb-8">
            <h2 className="text-2xl font-semibold mb-4 text-gray-800">
              Lessons
            </h2>
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
                    Duration:{" "}
                    {lesson.duration ? `${lesson.duration} mins` : "N/A"}
                  </p>
                </div>
              ))}
            </div>
          </div>

          {/* Parts */}
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
                  <p className="text-sm text-gray-600">
                    Type: {part.questionType}
                  </p>
                  <p className="text-sm text-gray-500">
                    Questions: {part.questionCount}
                  </p>
                </div>
              ))}
            </div>
          </div>
        </div>

        {/* RIGHT: Sections scroll box */}
        <div className="bg-gray-50 p-4 rounded-xl shadow-inner overflow-y-auto max-h-[600px]">
          <h2 className="text-xl font-semibold mb-4 text-gray-700">
            All Sections
          </h2>
          <div className="flex flex-col gap-3">
            {sections.map((s) => {
              const isActive = s.id.toString() === sectionId.toString();
              return (
                <div
                  key={s.id}
                  className={`p-4 rounded-lg border cursor-pointer transition duration-200 
                  ${
                    isActive
                      ? "bg-orange-100 border-orange-500 text-orange-500 font-semibold"
                      : "bg-white border-gray-300 hover:bg-gray-100"
                  }`}
                  onClick={() =>
                    navigate(PATH_CONSTANTS.SECTION.GET_BY_ID(s.id), {
                      state: { sectionName: s.sectionName, sections },
                    })
                  }
                >
                  <p className="text-sm">Section {s.id}</p>
                  <h3 className="text-base font-medium">{s.sectionName}</h3>
                </div>
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
};

export default SectionDetailsComponent;
