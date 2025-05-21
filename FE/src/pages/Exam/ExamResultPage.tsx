import React, {useEffect, useState} from "react";
import {ResultProps} from "../../types/exam";
import {http} from "../../service/Http";
import {API_URIS} from "../../api/URIConstant";
import {CircleCheck, CircleX, Clock} from "lucide-react";
import {useNavigate, useParams} from "react-router-dom";

const StatItem = ({
                      label,
                      value,
                      color,
                      icon,
                  }: {
    label: string;
    value: number;
    color?: string;
    icon?: React.ReactNode;
}) => (
    <div className={`flex items-center justify-between rounded-xl bg-white p-4 shadow-sm`}>
        <div className="flex items-center gap-2 text-gray-600 font-medium">
            {icon} {label}
        </div>
        <div className={`text-lg font-bold ${color || 'text-gray-800'}`}>{value}</div>
    </div>
);

const ExamResultPage: React.FC = () => {
    const [result, setResult] = useState<ResultProps>()
    const {attemptId} = useParams();
    const navigate = useNavigate();
    useEffect(() => {
        const fetchResult = async () => {
            const res = await http.get(API_URIS.USER_TEST.ATTEMPT, {
                params: { attemptId: attemptId },
            });
            setResult(res.data.data);
        };
        fetchResult();
    }, []);
    return (
        <div className="max-w-xl w-full mx-auto p-6 bg-gradient-to-br from-indigo-50 to-white rounded-2xl shadow-xl mt-10">
            <h2 className="text-3xl font-bold text-center text-indigo-700 mb-6">🎓 Kết Quả Bài Thi TOEIC</h2>

            <div className="bg-indigo-100 text-indigo-800 rounded-xl py-4 mb-6 flex items-center justify-center shadow">
                <span className="text-xl font-semibold">Tổng điểm:</span>
                <span className="ml-3 text-3xl font-bold">{result?.totalScore || 0}</span>
            </div>

            <div className="grid grid-cols-1 sm:grid-cols-2 gap-4 mb-6">
                <StatItem label="Nghe (Listening)" value={result?.listeningScore || 0} color="text-blue-600" />
                <StatItem label="Đọc (Reading)" value={result?.readingScore || 0} color="text-purple-600" />
                <StatItem
                    label="Câu đúng"
                    value={result?.correctCount || 0}
                    color="text-green-600"
                    icon={<CircleCheck className="w-5 h-5 text-green-500" />}
                />
                <StatItem
                    label="Câu sai"
                    value={result?.incorrectCount || 0}
                    color="text-red-600"
                    icon={<CircleX className="w-5 h-5 text-red-500" />}
                />
                <StatItem
                    label="Bỏ qua"
                    value={result?.skipCount || 0}
                    color="text-gray-500"
                    icon={<Clock className="w-5 h-5 text-gray-400" />}
                />
            </div>

            {/* Buttons */}
            <div className="flex flex-col sm:flex-row justify-center gap-4">
                <button
                    onClick={() => navigate(`/exams/view/${attemptId}`)}
                    className="w-full sm:w-auto bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-2 px-6 rounded-xl shadow transition duration-200"
                >
                    👁️ Xem chi tiết
                </button>
                <button
                    onClick={() => navigate(`/tests`)}
                    className="w-full sm:w-auto bg-gray-200 hover:bg-gray-300 text-gray-800 font-semibold py-2 px-6 rounded-xl shadow transition duration-200"
                >
                    🔁 Danh sách đề thi
                </button>
            </div>
        </div>
    )
};

export default ExamResultPage