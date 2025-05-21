import React, {useEffect, useState} from "react";
import {http} from "../../service/Http";
import {API_URIS} from "../../api/URIConstant";
import {useNavigate, useParams} from "react-router-dom";
import {DoExamType, PartWithQuestionsType, QuestionType} from "../../types/exam";
import IcBreadcrumbGbk from "../../assets/icons/IcBreadcrumbGbk";
import AttemptResultNavigationComponent from "../../components/AttemptResultNavigationComponent";
import MediaComponent from "../../components/MediaComponent";

const ExamResultDetailsPage: React.FC = () => {
    const {attemptIdView} = useParams();
    const navigate = useNavigate();
    const [examDetails, setExamDetails] = useState<DoExamType>();
    const [details, setDetails] = useState<PartWithQuestionsType[]>([]);
    const [currentQuestion, setCurrentQuestion] = useState<number>(0);
    let questionCounter = 1

    useEffect(() => {
        const fetchResult = async () => {
            try {
                const res = await http.get(API_URIS.USER_TEST.RESULT, {
                    params: { attemptId: attemptIdView },
                });
                return res.data
            } catch (error) {
                console.error("Failed to fetch result: ", error)
            }
        }
        fetchResult().then(res => {
            setExamDetails(res.data);
            setDetails(res.data.details)
        }).catch(error => {
            console.log(error)
        });
    }, []);
    const handleNavigate = (questionIndex: number) => {
        setCurrentQuestion(questionIndex);
        const element = document.getElementById(`question-${questionIndex}`);
        if (element) {
            element.scrollIntoView({behavior: "smooth"});
        }
    };

    return (
        <div className="flex flex-col h-screen">
            <div className="flex flex-row justify-between flex-1 overflow-hidden">
                <div className="flex-1 flex flex-col justify-start items-center p-4 overflow-auto">
                    <div
                        className="text-lg w-full text-main font-normal flex gap-3 text-start mb-5 cursor-pointer items-center"
                        onClick={() => navigate(-1)}
                    >
                        <IcBreadcrumbGbk />
                        <span>{"Return"}</span>
                    </div>

                    <div>
                        <h2 className="text-4xl font-bold text-blue-600">
                            {examDetails?.exam.examName}
                        </h2>
                    </div>
                    <div className="w-full max-w-4xl">
                        {details.map((detail) => {
                            if (!detail.questions || detail.questions.length === 0) return null
                            return (
                                <div className="mb-8">
                                    <h3 className="text-3xl text-blue-700 font-semibold mb-4">
                                        {detail.part.partName}
                                    </h3>
                                    <MediaComponent
                                        mediaType={detail.part.mediaType}
                                        url={detail.part.url}
                                    />
                                    {detail.questions.map((question : QuestionType)=> {
                                        const index = questionCounter++;
                                        return (
                                            <div key={question.id} className="mb-6"
                                                 id={`question-${question.id}`}>
                                                <div className="flex flex-row items-center mb-2">
                                                    <div className=" rounded-full bg-blue-400 w-10 h-10 flex flex-col items-center justify-center mr-3">
                                                        <p className="text-black">{index}</p>
                                                    </div>
                                                    <p className="font-normal">
                                                        {question.content}
                                                    </p>
                                                </div>
                                                <MediaComponent
                                                    mediaType={question.mediaType}
                                                    url={question.url}
                                                />
                                                {Object.entries(question.options).map(([key, value]) => {
                                                    let style = "border p-2 rounded-md w-full text-left mb-2 transition-colors "
                                                    if (question.selectedAnswer == question.correctAnswer && question.selectedAnswer == key) style += "bg-green-500 text-white"
                                                    else if (question.correctAnswer == key) style += "border-green-500 bg-green-100"
                                                    if (question.selectedAnswer != question.correctAnswer && question.selectedAnswer == key) style += "bg-red-500 text-white"
                                                    return (
                                                        <button key={key} className={style}>
                                                            {value}
                                                        </button>
                                                    )
                                                })}
                                                <span className="italic text-black">
                                                    <strong>Giải Thích: </strong>
                                                    {question.explanation}
                                                </span>
                                            </div>
                                        )
                                    })}
                                </div>
                            )
                        })}
                    </div>
                </div>
                <div className=" p-4 bg-white h-full w-fit overflow-y-scroll">
                    <AttemptResultNavigationComponent
                        details={details}
                        currentQuestion={currentQuestion}
                        onNavigate={handleNavigate}/>
                </div>
            </div>
        </div>
    );
};
export default ExamResultDetailsPage