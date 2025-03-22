import React from "react";
import { FaRegClock } from "react-icons/fa";
import LeftSidebar from "../../components/LeftSidebar";
import HistoryTestCard from "../../components/HistoryTestCard";

const HistoryPage: React.FC = () => {
	// Tạo dữ liệu cố định 6 item
	const historyTests = [
		{
			id: 1,
			imageSrc: "src/assets/images/toeic-online-full-test.png",
			title: "TOEIC Full Test 01",
			score: "25 / 100",
			time: "2h20'",
		},
		{
			id: 2,
			imageSrc: "src/assets/images/toeic-online-full-test.png",
			title: "TOEIC Full Test 02",
			score: "30 / 100",
			time: "1h45'",
		},
		{
			id: 3,
			imageSrc: "src/assets/images/toeic-online-full-test.png",
			title: "TOEIC Full Test 03",
			score: "40 / 100",
			time: "2h10'",
		},
		{
			id: 4,
			imageSrc: "src/assets/images/toeic-online-full-test.png",
			title: "TOEIC Full Test 04",
			score: "50 / 100",
			time: "2h00'",
		},
		{
			id: 5,
			imageSrc: "src/assets/images/toeic-online-full-test.png",
			title: "TOEIC Full Test 05",
			score: "60 / 100",
			time: "2h25'",
		},
		{
			id: 6,
			imageSrc: "src/assets/images/toeic-online-full-test.png",
			title: "TOEIC Full Test 06",
			score: "70 / 100",
			time: "2h15'",
		},
	];

	return (
		<div className="min-h-screen flex flex-col">
			{/* Phần trên: LeftSidebar + Biểu đồ */}
			<div className="flex-1 flex pl-8 pr">
				{/* Left Sidebar */}
				<LeftSidebar customHeight="h-auto w-64" />

				{/* Khu vực Biểu đồ (Tổng quan) */}
				<div className="flex-1 p-8 bg-gray-50">
					<h1 className="text-2xl font-bold text-gray-800 mb-6">
						Lịch sử làm bài
					</h1>

					<div className="bg-white rounded-lg shadow p-6 flex flex-col md:flex-row gap-4">
						{/* Placeholder Biểu đồ */}
						<div className="flex-1 flex flex-col items-center justify-center border-r border-gray-200 pr-4">
							<h2 className="text-xl font-semibold mb-2">Tổng quan</h2>
							<div className="w-full h-40 bg-blue-50 flex items-center justify-center text-blue-400">
								(Chart Placeholder)
							</div>
							<div className="flex space-x-4 mt-3 text-sm">
								<div className="flex items-center space-x-1 text-blue-600">
									<div className="w-3 h-3 rounded-full bg-blue-600" />
									<span>Reading</span>
								</div>
								<div className="flex items-center space-x-1 text-orange-500">
									<div className="w-3 h-3 rounded-full bg-orange-500" />
									<span>Listening</span>
								</div>
							</div>
						</div>

						{/* Thông tin thống kê */}
						<div className="flex-1 flex flex-col items-center justify-center md:justify-around">
							<div className="text-center">
								<p className="text-gray-700">
									Số bài thi đã thực hiện:
								</p>
								<p className="text-xl font-bold text-blue-600">10</p>
							</div>
							<div className="text-center mt-4 md:mt-0">
								<p className="text-gray-700">Điểm trung bình:</p>
								<p className="text-xl font-bold text-orange-500">
									25/100
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>

			{/* Phần dưới: Danh sách bài test */}
			<div className="bg-white p-8">
				<div className="grid grid-cols-1 md:grid-cols-2 gap-6">
					{historyTests.map((item) => (
						<HistoryTestCard
							key={item.id}
							imageSrc={item.imageSrc}
							title={item.title}
							score={item.score}
							time={item.time}
						/>
					))}
				</div>
			</div>
		</div>
	);
};

export default HistoryPage;
