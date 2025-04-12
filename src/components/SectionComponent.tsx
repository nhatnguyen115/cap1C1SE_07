import React, { useEffect, useState } from "react";
import { http } from "../service/Http";
import { useNavigate } from "react-router-dom";
import { SectionType } from "../types/section";
import { sectionMockData } from "../data/sectionMockData";
import { API_URIS } from "../api/URIConstant";
import { PATH_CONSTANTS } from "../api/PathConstant";

interface SectionComponentProps {
  moduleId: string;
}
const SectionComponent: React.FC<SectionComponentProps> = ({ moduleId }) => {
  const [sections, setSections] = useState<SectionType[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchSections = async () => {
      try {
        const response = await http.get(
          API_URIS.SECTION.GET_ALL_BY_MODULE(moduleId),
        );
        if (response.status === 200) {
          setSections(response.data.data.items);
          console.log("response.data.data.items", response.data.data.items);
        }
      } catch (error) {
        console.error("Lỗi khi tải dữ liệu sections:", error);
        setSections(sectionMockData);
      }
    };

    fetchSections();
  }, []);

  const renderCards = (
    type: "LISTENING" | "READING" | "SPEAKING" | "WRITING",
  ) => {
    return sections
      .filter((s) => s.sectionType === type)
      .map((s, index) => (
        <div
          key={s.id}
          className="bg-white rounded-xl shadow-md p-4 cursor-pointer hover:shadow-lg transition"
          onClick={() =>
            navigate(PATH_CONSTANTS.SECTION.GET_BY_ID(s.id), {
              state: {
                sectionName: s.sectionName,
                sections, // OK
              },
            })
          }
        >
          <p className="font-bold text-sm text-gray-500">Phần {index + 1}</p>
          <h3 className="font-bold text-lg">{s.sectionName}</h3>
          <p className="text-sm text-gray-600 mt-2">{s.description}</p>
        </div>
      ));
  };

  const hasSection = (type: SectionType["sectionType"]) =>
    sections.some((s) => s.sectionType === type);

  return (
    <div className="max-w-6xl mx-auto px-4 py-10 space-y-10">
      {hasSection("LISTENING") && (
        <div className="flex justify-center">
          <h1 className="text-3xl font-bold mb-6 text-center">
            Luyện thi TOEIC® Listening và Reading
          </h1>
        </div>
      )}

      {hasSection("SPEAKING") && (
        <div className="flex justify-center">
          <h1 className="text-3xl font-bold mb-6 text-center">
            Luyện Thi TOEIC® Speaking Writing Online
          </h1>
        </div>
      )}

      {hasSection("LISTENING") && (
        <div>
          <h2 className="text-2xl font-bold mb-6">Luyện Nghe</h2>
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
            {renderCards("LISTENING")}
          </div>
        </div>
      )}

      {hasSection("READING") && (
        <div>
          <h2 className="text-2xl font-bold mb-6">Luyện Đọc</h2>
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
            {renderCards("READING")}
          </div>
        </div>
      )}

      {hasSection("SPEAKING") && (
        <div>
          <h2 className="text-2xl font-bold mb-6">Luyện Nói</h2>
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
            {renderCards("SPEAKING")}
          </div>
        </div>
      )}

      {hasSection("WRITING") && (
        <div>
          <h2 className="text-2xl font-bold mb-6">Luyện Viết</h2>
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
            {renderCards("WRITING")}
          </div>
        </div>
      )}
    </div>
  );
};

export default SectionComponent;
