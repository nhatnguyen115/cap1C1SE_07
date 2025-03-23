import React from "react";
import { Pie } from "react-chartjs-2";
import { Chart as ChartJS, Title, Tooltip, Legend, ArcElement, CategoryScale, LinearScale } from "chart.js";

// Đăng ký các phần tử của Chart.js
ChartJS.register(Title, Tooltip, Legend, ArcElement, CategoryScale, LinearScale);

type ResultProps = {
  totalQuestions: number;
  correctAnswers: number;
  wrongAnswers: number;
  skippedQuestions: number;
  score: number;
};

const Result: React.FC<ResultProps> = ({
  totalQuestions,
  correctAnswers,
  wrongAnswers,
  skippedQuestions,
  score,
}) => {
  const correctPercentage = (correctAnswers / totalQuestions) * 100;
  const wrongPercentage = (wrongAnswers / totalQuestions) * 100;
  const skippedPercentage = (skippedQuestions / totalQuestions) * 100;

  // Dữ liệu cho biểu đồ hình tròn
  const data = {
    labels: ['Correct', 'Wrong', 'Skipped'],
    datasets: [
      {
        data: [correctPercentage, wrongPercentage, skippedPercentage],
        backgroundColor: ['#4CAF50', '#F44336', '#FF9800'],
        hoverBackgroundColor: ['#45a049', '#e53935', '#fb8c00'],
        borderColor: '#fff',
        borderWidth: 1,
      },
    ],
  };

  return (
    <div className="max-w-4xl mx-auto p-6 bg-white rounded-lg shadow-lg">
      <div className="text-center mb-8">
        <div className="text-5xl text-yellow-500">🏆</div>
        <h1 className="text-4xl font-bold text-gray-800">{`Your Score: ${score}/100`}</h1>
        <p className="mt-2 text-lg text-gray-500">This is your approximate level. You should start studying the grammar lesson for AI.</p>
      </div>

      <div className="grid grid-cols-2 gap-4 mb-6">
        <div className="flex justify-between items-center">
          <span className="text-gray-600">Total Questions:</span>
          <span className="font-semibold text-gray-800">{totalQuestions}</span>
        </div>
        <div className="flex justify-between items-center">
          <span className="text-gray-600">Correct Answers:</span>
          <span className="font-semibold text-green-600">{correctAnswers}</span>
        </div>
        <div className="flex justify-between items-center">
          <span className="text-gray-600">Wrong Answers:</span>
          <span className="font-semibold text-red-600">{wrongAnswers}</span>
        </div>
        <div className="flex justify-between items-center">
          <span className="text-gray-600">Skipped Questions:</span>
          <span className="font-semibold text-orange-600">{skippedQuestions}</span>
        </div>
      </div>

      <div className="flex justify-center mb-6">
        <div className="w-1/2">
          <h3 className="text-xl text-gray-700">Answer Distribution</h3>
          <div className="mt-4">
            <Pie data={data} />
          </div>
        </div>
      </div>

      <div className="text-center">
        <p className="text-lg text-blue-500">You need to improve Part 3 - Short Conversations</p>
        <button className="mt-4 px-6 py-2 bg-blue-500 text-white rounded-lg shadow-md hover:bg-blue-600">Review Your Answers</button>
      </div>
    </div>
  );
};

const App: React.FC = () => {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <Result
        totalQuestions={75}
        correctAnswers={25}
        wrongAnswers={50}
        skippedQuestions={25}
        score={25}
      />
    </div>
  );
};

export default App;
