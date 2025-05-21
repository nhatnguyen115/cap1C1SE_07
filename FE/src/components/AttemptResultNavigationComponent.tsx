import React from "react"
import {AttemptNavigationProps} from "../types/exam";

const AttemptResultNavigationComponent:
    React.FC<AttemptNavigationProps> = ({details,
                                            currentQuestion,
                                            onNavigate,}) => {
    let questionCounter = 1;
    return (
        <div className="space-y-4">
            {details?.map((detail, index) => {
                const partName = detail.part.partName || `Part ${index + 1}`;
                return (
                    <div key={index}>
                        <h3 className="text-lg font-semibold mb-2">{partName}</h3>
                        <div className="grid grid-cols-4 gap-2">
                            {detail.questions?.map((question, index) => {
                                const questionNumber = questionCounter++;
                                let style = "border rounded-md text-center text-sm transition-all duration-200 p-1 "
                                if (currentQuestion === question.id) style += "bg-blue-500 text-white"
                                else if (question.selectedAnswer === question.correctAnswer) style += "bg-green-500 text-white"
                                else if (question.selectedAnswer === null) style += "bg-yellow-600 text-white"
                                else style += "bg-red-800 text-white"
                                return (
                                    <button key={question.id}
                                            onClick={() => onNavigate(question.id)}
                                            className={style}>
                                        {questionNumber}
                                    </button>
                                )
                            })}
                        </div>
                    </div>
                )
            })}
        </div>
    )
}
export default AttemptResultNavigationComponent