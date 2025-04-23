import React, { useEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { PATH_CONSTANTS } from "../../api/PathConstant";
import PartDetailsComponent from "../../components/PartDetailsComponent";
import { TOTAL_PAGE } from "../../constant/PaginationConstant";
import { getQuestions } from "../../service/PartService";
import { LessonPartType } from "../../types/lesson";
import { QuestionType } from "../../types/part";
import { SectionType } from "../../types/section";
import LessonPage from "../Lesson/LessonPage";

export const PART_DETAILS_CONSTANT = {
  TAB_LESSON: "lesson",
  TAB_PART: "part",
};

type TabType =
  (typeof PART_DETAILS_CONSTANT)[keyof typeof PART_DETAILS_CONSTANT];

const PartDetailsPage: React.FC = () => {
  const location = useLocation();
  const [questions, setQuestions] = useState<QuestionType[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [elapsedSeconds, setElapsedSeconds] = useState(0);

  const lessonPartState = location.state?.lessonPart;
  const activeTabState = location.state?.activeTabState;

  const { partId } = useParams();

  const [activeTab, setActiveTab] = useState<TabType>(
    activeTabState ?? PART_DETAILS_CONSTANT.TAB_LESSON,
  );

  const [activeLesson, setActiveLesson] = useState<string | number>(
    lessonPartState.lesson[0]?.id,
  );
  const [activePart, setActivePart] = useState<string | number>("");
  const [lessonPart, setLessonPart] = useState<LessonPartType>(lessonPartState);
  const [currentSections, setCurrentSections] = useState<SectionType[]>(
    location.state?.sections,
  );

  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const handlePageChange = (newPage: number) => {
    if (newPage >= 0 && newPage < totalPages) {
      setPage(newPage);
    }
  };
  const navigate = useNavigate();
  useEffect(() => {
    console.log("activeTabState:", activeTabState);
  }, []);

  useEffect(() => {
    const fetchQuestions = async () => {
      if (!partId) return;
      setLoading(true); // reset loading để hiển thị "Đang tải..." khi chuyển tab
      console.log("currentSections:", currentSections);

      try {
        const response = await getQuestions(partId, page, TOTAL_PAGE[1000]);
        setTotalPages(response.totalPages);

        if (response) {
          console.log(response);

          setQuestions(response.items || []);
          setElapsedSeconds(response.elapsedSeconds || 0);
        }
      } catch (err) {
        setError("Failed to load questions.");
      } finally {
        setLoading(false);
      }
    };

    fetchQuestions();
  }, [partId, page]);

  useEffect(() => {
    if (partId !== undefined) {
      console.log("partId: ", partId);

      setActivePart(String(partId));
    }
  }, [partId]);

  // Timer tăng dần
  useEffect(() => {
    if (activeTab == PART_DETAILS_CONSTANT.TAB_PART) {
      const timer = setInterval(() => {
        setElapsedSeconds((prev) => prev + 1);
      }, 1000);
      return () => clearInterval(timer);
    }
  }, [activeTab]);

  const formatTime = (totalSeconds: number) => {
    const minutes = String(Math.floor(totalSeconds / 60)).padStart(2, "0");
    const seconds = String(totalSeconds % 60).padStart(2, "0");
    return `${minutes}:${seconds}`;
  };

  return (
    <div className="flex min-h-screen">
      {/* Sidebar */}
      <aside className="w-64 bg-gray-100 p-4 border-r">
        <div
          role="button"
          onClick={() => {
            navigate(PATH_CONSTANTS.ROOT.ROOT);
          }}
          className="text-gray-500 hover:text-blue-600 border-b border-blue-600 mb-2"
        >
          <h2 className="text-lg font-semibold mb-4 text-center">Home</h2>
        </div>
        <div className="flex space-x-4 border-b border-gray-200 mb-4">
          <button
            onClick={() => setActiveTab(PART_DETAILS_CONSTANT.TAB_LESSON)}
            className={`pb-2 px-4 text-sm font-medium border-b-2 transition ${
              activeTab === PART_DETAILS_CONSTANT.TAB_LESSON
                ? "border-blue-600 text-blue-600"
                : "border-transparent text-gray-500 hover:text-blue-600"
            }`}
          >
            Lesson
          </button>
          <button
            onClick={() => setActiveTab(PART_DETAILS_CONSTANT.TAB_PART)}
            className={`pb-2 px-4 text-sm font-medium border-b-2 transition ${
              activeTab === PART_DETAILS_CONSTANT.TAB_PART
                ? "border-blue-600 text-blue-600"
                : "border-transparent text-gray-500 hover:text-blue-600"
            }`}
          >
            Part
          </button>
        </div>

        {activeTab === PART_DETAILS_CONSTANT.TAB_LESSON && (
          <div>
            {lessonPart.lesson.map((lesson) => (
              <div
                role="button"
                tabIndex={0}
                onClick={() => {
                  if (lesson.id !== undefined) {
                    setActiveLesson(lesson.id);
                    navigate(PATH_CONSTANTS.LESSON.GET_BY_ID(lesson.id), {
                      state: { lessonPart: lessonPart },
                    });
                  }
                }}
                className={`text-xs transition rounded-xl px-3 py-1 flex flex-row justify-between${
                  activeLesson === lesson.id ? " bg-slate-300" : ""
                }`}
              >
                <div>
                  <span>{lesson.lessonName}</span>
                </div>
                <div>
                  <span>{lesson.lessonName}</span>
                </div>
              </div>
            ))}
          </div>
        )}

        {activeTab === PART_DETAILS_CONSTANT.TAB_PART && (
          <div>
            {lessonPart.part.map((part) => (
              <div
                role="button"
                tabIndex={0}
                onClick={() => {
                  if (part.partId !== undefined) {
                    setActivePart(part.partId);
                    navigate(PATH_CONSTANTS.PART.DETAIL(part.partId), {
                      state: { lessonPart: lessonPart, partId: part.partId },
                    });
                  }
                }}
                className={`text-xs transition rounded-xl px-3 py-1 flex flex-row justify-between${
                  activePart == part.partId ? " bg-slate-300" : ""
                }`}
              >
                <div>
                  <span>{part.partName}</span>
                </div>
                <div>
                  <span>{part.partName}</span>
                </div>
              </div>
            ))}
          </div>
        )}
      </aside>

      {/* Main Content */}
      <main className="flex-1 p-6">
        {activeTab === PART_DETAILS_CONSTANT.TAB_LESSON && (
          <LessonPage lessonIdProps={String(activeLesson)} />
        )}
        {activeTab === PART_DETAILS_CONSTANT.TAB_PART && (
          <div>
            <PartDetailsComponent
              key={partId}
              partName="Part 1: Photos"
              questions={questions}
              elapsedSeconds={elapsedSeconds}
              formatTime={formatTime}
            />
          </div>
        )}
      </main>
    </div>
  );
};

export default PartDetailsPage;
