import React, {useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {PATH_CONSTANTS} from "../api/PathConstant";
import {API_URIS} from "../api/URIConstant";
import {PART_DETAILS_CONSTANT} from "../pages/Part/PartDetailsPage";
import {http} from "../service/Http";
import {LessonPartType} from "../types/lesson";
import {Lesson, Part, SectionType} from "../types/section";
import {ExamType, Grammar} from "../types/exam";
import ExamCardComponent from "./ExamCardComponent";
import {SRC_IMAGE} from "../constant/SrcImage";
import SearchComponent from "./SearchComponent";

interface SectionDetailsComponentProps {
    section: SectionType;
    sections: SectionType[];
}

const SectionExamComponent: React.FC<SectionDetailsComponentProps> = ({
                                                                          section,
                                                                          sections
                                                                      }) => {
    const [currentSections, setCurrentSections] = useState<SectionType[]>(sections);

    const [exams, setExams] = useState<ExamType[]>([]);
    const [grammars, setGrammars] = useState<Grammar[]>([])
    const navigate = useNavigate();


    useEffect(() => {
        const fetchExams = async () => {
            const res = await http.get(`/practice?sectionId=${section.id}`);
            setExams(res.data.data.items);
        };
        const fetchGrammars = async () => {
            try {
                const res = await http.get(`/grammars?sectionId=${section.id}`)
                return res.data
            } catch (error) {
                console.log(error)
            }
        }
        fetchExams();
        fetchGrammars().then(res => {
            setGrammars(res.data)
        })
    }, [section.id]);

    return (
        <div className="p-4 max-w-6xl flex flex-col min-h-screen mx-auto">
            {(section.sectionType === "VOCABULARY" || section.sectionType === "GRAMMAR") && (
                <SearchComponent/>
            )}
            <h1 className="text-3xl font-bold mb-6 text-center">{section.sectionName}</h1>
            <div className="flex flex-col md:flex-row gap-6">

                {/* LEFT: Grammar content + Exams */}
                <div className="flex-1 flex flex-col gap-6">
                    {section.sectionType === "VOCABULARY" &&
                        (
                            <div
                                onClick={() => navigate(`/vocabularies/${section.id}`)}
                                className="bg-white rounded-xl shadow-md p-6 border border-gray-200 hover:shadow-lg transition"
                            >
                                <h2 className="text-xl font-semibold text-blue-600">
                                    Từ vựng
                                </h2>
                            </div>
                        )}
                    {section.sectionType === "GRAMMAR" && grammars.map((grammar: Grammar, index: number) => {
                        const formattedText = grammar.grammarText
                            .split('\r\n\r\n')
                            .map(paragraph =>
                                `<p>${paragraph.replace(/\r\n/g, '<br/>')}</p>`
                            )
                            .join('');
                        return (
                            <div
                                key={index}
                                className="bg-white rounded-2xl shadow-md p-6 border border-gray-200 hover:shadow-lg transition"
                            >
                                <h2 className="text-xl font-semibold text-blue-600 mb-2">
                                    {grammar.grammarName}
                                </h2>
                                <div
                                    className="prose prose-sm max-w-none text-gray-700"
                                    dangerouslySetInnerHTML={{__html: formattedText}}
                                />
                            </div>
                        );
                    })}

                    <h1 className="text-2xl font-bold">Danh sách bài thi</h1>
                    <div className="grid grid-cols-1 sm:grid-cols-3 gap-6">
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

                {/* RIGHT: All Sections */}
                <div
                    className="w-full md:w-[300px] bg-gray-50 p-4 rounded-xl shadow-inner overflow-y-auto max-h-[600px]">
                    <h2 className="text-xl font-semibold mb-4 text-gray-700">
                        All Sections
                    </h2>
                    <div className="flex flex-col gap-3">
                        {currentSections.map((s) => {
                            const isActive = s.id.toString() === section.id.toString();
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
                                            state: {sectionName: s.sectionName, currentSections},
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
