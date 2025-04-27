import React, { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import { API_URIS } from "../../api/URIConstant";
import ExamCardComponent from "../../components/ExamCardComponent";
import { http } from "../../service/Http";
interface Exam {
  id: number;
  examName: string;
  duration: number;
  totalScore: number;
}

const ExamPageDetails: React.FC = () => {
  const [searchParams] = useSearchParams();
  const testId = searchParams.get("testId");
  const [exams, setExams] = useState<Exam[]>([]);

  useEffect(() => {
    const fetchExams = async () => {
      if (!testId) return;
      const res = await http.get(API_URIS.EXAMS.GET_BY_TEST_ID(testId));
      setExams(res.data.data.items);
    };
    fetchExams();
  }, [testId]);

  return (
    <div className="min-h-screen flex flex-col items-center p-4">
      <h1 className="text-2xl font-bold mb-6">Danh sách bài thi</h1>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
        {exams.map((exam) => (
          <ExamCardComponent
            key={exam.id}
            examName={exam.examName}
            duration={exam.duration}
            totalScore={exam.totalScore}
            id={exam.id}
          />
        ))}
      </div>
    </div>
  );
};

export default ExamPageDetails;
