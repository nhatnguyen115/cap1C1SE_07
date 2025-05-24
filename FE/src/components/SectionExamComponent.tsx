import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { PATH_CONSTANTS } from "../api/PathConstant";
import { API_URIS } from "../api/URIConstant";
import { PART_DETAILS_CONSTANT } from "../pages/Part/PartDetailsPage";
import { http } from "../service/Http";
import { LessonPartType } from "../types/lesson";
import { Lesson, Part, SectionType } from "../types/section";
import {ExamType} from "../types/exam";
import ExamCardComponent from "./ExamCardComponent";
import {SRC_IMAGE} from "../constant/SrcImage";

interface SectionDetailsComponentProps {
  sectionId: string;
  sectionName: string;
  sections: SectionType[];
  moduleId: number | string;
}
const SectionExamComponent: React.FC<SectionDetailsComponentProps> = ({
  sectionId,
  sectionName,
  sections
}) => {
  const [currentSections, setCurrentSections] = useState<SectionType[]>(sections);

  const [exams, setExams] = useState<ExamType[]>([]);
  const navigate = useNavigate();


  useEffect(() => {
    const fetchExams = async () => {
      const res = await http.get(`/practice?sectionId=${sectionId}`);
      setExams(res.data.data.items);
    };
    fetchExams();
  }, [sectionId]);

  return (
    <div className="p-4 max-w-4xl flex flex-col justify-center items-center min-h-screen mx-auto">
      <h1 className="text-3xl font-bold mb-6">{sectionName}</h1>
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="min-h-screen flex flex-col items-center md:col-span-2 p-4">
          <h1 className="text-2xl font-bold mb-6">Danh sách bài thi</h1>
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
            {exams.map((exam) => (
                <ExamCardComponent
                    key={exam.id}
                    examName={exam.examName}
                    duration={exam.duration ?? 0}
                    totalScore={exam.totalScore}
                    id={exam.id ?? 0}
                    questions={exam.questionCount ?? 10}
                    students={exam.students ?? 10}
                    level={exam.level ?? "BEGINNER"}
                    isPractice={true}
                    image={SRC_IMAGE.TEST}
                />
            ))}
          </div>
        </div>
        {/* RIGHT: Sections scroll box */}
        <div className="bg-gray-50 p-4 rounded-xl shadow-inner items-end overflow-y-auto max-h-[600px]">
          <h2 className="text-xl font-semibold mb-4 text-gray-700">
            All Sections
          </h2>
          <div className="flex flex-col gap-3">
            {currentSections.map((s) => {
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
                      state: { sectionName: s.sectionName, currentSections },
                    })
                  }
                >
                  <h3 className="text-sm">{s.sectionName}</h3>
                </div>
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
};

export default SectionExamComponent;
