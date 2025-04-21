import React, { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import { API_URIS } from "../../api/URIConstant";
import { http } from "../../service/Http";
import { ExamType } from "../../types/exam";

const ExamPageDetails: React.FC = () => {
  const [searchParams] = useSearchParams();
  const testId = searchParams.get("testId");
  const [exams, setExams] = useState<ExamType[]>([]);

  useEffect(() => {
    const fetchExams = async () => {
      if (!testId) return;

      try {
        const res = await http.get(API_URIS.EXAMS.GET_BY_TEST_ID(testId));
        setExams(res.data.data.items);
      } catch (error) {
        console.error("Error fetching exams:", error);
      }
    };

    fetchExams();
  }, [testId]);

  return (
    <div className="min-h-screen p-4 max-w-3xl mx-auto">
      <h1 className="text-xl font-bold mb-4">Danh sách bài thi</h1>

      {exams.length > 0 ? (
        exams.map((exam) => (
          <div
            key={exam.id}
            className="p-4 mb-3 border rounded-lg shadow bg-white"
          >
            <h2 className="text-lg font-semibold">{exam.examName}</h2>
            <p>Thời lượng: {exam.duration} phút</p>
            <p>Tổng điểm: {exam.totalScore}</p>
          </div>
        ))
      ) : (
        <p>Không có bài thi nào.</p>
      )}
    </div>
  );
};

export default ExamPageDetails;
