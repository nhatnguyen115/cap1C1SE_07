import React, {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {API_URIS} from "../../api/URIConstant";
import IcBreadcrumbGbk from "../../assets/icons/IcBreadcrumbGbk";
import ExamNavigationComponent from "../../components/ExamNavigationComponent";
import {http} from "../../service/Http";

import {animateScroll as scroll} from "react-scroll";

import {notification} from "antd";
import {
    AnswerType,
    DoExamType,
    PartWithQuestionsType,
    QuestionType,
} from "../../types/exam";
import MediaComponent from "../../components/MediaComponent";

export const AttemptExamPage: React.FC = () => {
    const [answers, setAnswers] = useState<AnswerType[]>([]);
    const navigate = useNavigate();
    const [attemptId, setAttemptId] = useState<number>(0);
    const {id} = useParams();

    const [examDetails, setExamDetails] = useState<DoExamType>();
    const [currentQuestion, setCurrentQuestion] = useState<number>(0); // Track current question index

    useEffect(() => {
        const fetchData = async () => {
            try {
                const startTest = await http.post(API_URIS.USER_TEST.START,
                    {}, {params: {examId: id}},
                );
                return startTest.data
            } catch (error) {
                console.error("Failed to start exam:", error);
            }
        };

        const fetchExam = async () => {
            try {
                const res = await http.get(API_URIS.EXAMS.DO_BY_EXAM_ID(id!));
                return res.data
            } catch (error) {
                console.error("Failed to fetch exam:", error);
            }
        }

        fetchData().then(res => {
            if (res.status === 200) {
                const attemptId = res.data;
                setAttemptId(attemptId);
                fetchExam().then(r => {
                    setExamDetails(r.data);
                }).catch(error => {
                    console.log(error)
                });
                // const shuffledDetails = rawData.details.map((detail) => ({
                //   ...detail,
                //   questions: shuffleArray(detail.questions),
                // }));

                // const shuffledData: DoExamType = {
                //   ...rawData,
                //   details: shuffledDetails,
                // };
            } else {
                navigate("/payment")
                notification.error({
                    message: res.message,
                });
            }
        }).catch(error => {
                console.log(error)
            });
    }, [id]);

    useEffect(() => {
        console.log("answers: ", answers);
    }, [answers]);

    useEffect(() => {
        scroll.scrollToTop({smooth: true, duration: 500});
    }, []);
    const handleAnswer = (
        questionId: number,
        selectedOption: "A" | "B" | "C" | "D",
    ) => {
        const updatedAnswers = [...answers];
        const existingIndex = updatedAnswers.findIndex(
            (a) => a.questionId === questionId,
        );

        if (existingIndex !== -1) {
            // Nếu đã tồn tại, cập nhật
            updatedAnswers[existingIndex].selectedOption = selectedOption;
        } else {
            // Nếu chưa có, thêm mới
            updatedAnswers.push({questionId, selectedOption});
        }

        setAnswers(updatedAnswers);
    };

    const handleNavigate = (questionIndex: number) => {
        setCurrentQuestion(questionIndex);
        const element = document.getElementById(`question-${questionIndex}`);
        if (element) {
            element.scrollIntoView({behavior: "smooth"});
        }
    };
    let questionCounter = 1;

    const getQuestionProps = () => {
        const id = `question-${questionCounter}`;
        const number = questionCounter;
        questionCounter++;
        return {id, number};
    };

    const renderListeningPart = (part: PartWithQuestionsType) => {
        if (!part.questions || part.questions.length === 0) return null;

        return (
            <div className="mb-8">
                <h3 className="text-3xl text-blue-700 font-semibold mb-4">
                    {part.part.partName}
                </h3>
                <MediaComponent
                    mediaType={part.part.url}
                    url={part.part.url}
                />
                {part.questions.map((question: QuestionType) => {
                    const questionNumber = questionCounter++;
                    const selectedAnswer = answers.find(
                        (a) => a.questionId === question.id,
                    );

                    const renderOptionButton = (
                        optionKey: string,
                        optionValue: string,
                    ) => {
                        const isSelected = selectedAnswer?.selectedOption === optionKey;

                        let style =
                            "border p-2 rounded-md w-full text-left mb-2 transition-colors ";
                        style += isSelected
                            ? "bg-blue-500 text-white"
                            : "hover:bg-gray-200";

                        return (
                            <button
                                key={optionKey}
                                onClick={() => {
                                    handleAnswer(
                                        question.id,
                                        optionKey as "A" | "B" | "C" | "D",
                                    );
                                    setCurrentQuestion(question.id);
                                }}
                                className={style}
                            >
                                {optionValue}
                            </button>
                        );
                    };

                    return (
                        <div
                            key={question.id}
                            className="mb-6"
                            id={`question-${question.id}`}
                        >
                            <div className="flex flex-row items-center">
                                <div className=" rounded-full bg-blue-400 w-10 h-10 flex flex-col items-center justify-center mr-3">
                                    <p className="text-blue-700 fa-bold">{questionNumber}</p>
                                </div>
                                <p className="font-bold text-2xl ">
                                    Question {}: {question.content}
                                </p>
                            </div>
                            <MediaComponent
                                mediaType={question.mediaType}
                                url={question.url}
                            />
                            {Object.entries(question.options).map(([key, value]) =>
                                renderOptionButton(key, value),
                            )}
                        </div>
                    );
                })}
            </div>
        );
    };

    return (
        <div className="flex flex-col h-screen">
            <div className="flex flex-row justify-between flex-1 overflow-hidden">
                <div className="flex-1 flex flex-col justify-start items-center p-4 overflow-auto">
                    <div
                        className="text-lg w-full text-main font-normal flex gap-3 text-start mb-5 cursor-pointer items-center"
                        onClick={() => navigate(-1)}>
                        <IcBreadcrumbGbk/>
                        <span>{"Return"}</span>
                    </div>

                    <div>
                        <h2 className="text-4xl font-bold text-blue-600">
                            {examDetails?.exam.examName}
                        </h2>
                    </div>
                    <div className="w-full max-w-4xl">
                        {examDetails?.details.map((part, index) => {
                            return renderListeningPart(part);
                        })}
                    </div>
                </div>

                <div className=" p-4 bg-white h-full w-fit overflow-y-scroll">
                    <ExamNavigationComponent
                        isView= {false}
                        attemptId={attemptId}
                        details={examDetails?.details}
                        currentQuestion={currentQuestion}
                        answers={answers}
                        duration={examDetails?.exam.duration ?? -1}
                        onNavigate={handleNavigate}
                    />
                </div>
            </div>
        </div>
    );
};
