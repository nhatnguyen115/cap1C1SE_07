import React, { useEffect, useState } from "react";
import { Line } from "react-chartjs-2";
import { FaChartLine, FaFileAlt, FaUsers } from "react-icons/fa";
import { MdOutlineCloudDone } from "react-icons/md";
import { API_URIS } from "../../../api/URIConstant";
import LeftSidebarAdmin from "../../../components/LeftSidebarAdmin";
import { http } from "../../../service/Http";
import { DashboardSummaryType, DashboardType } from "../../../types/dashboard";

const DashboardPage: React.FC = () => {
  const [dashboardData, setDashboardData] = useState<DashboardType | null>(
    null,
  );

  const [summary, setSummary] = useState<DashboardSummaryType>();

  useEffect(() => {
    const fetchDashboardData = async () => {
      try {
        const res = await http.get(API_URIS.DASHBOARD.DASHBOARD);
        setDashboardData(res.data.data);
      } catch (error) {
        console.error("Failed to fetchDashboardData", error);
      }
    };

    const fetchDashboardSummary = async () => {
      try {
        const res = await http.get(API_URIS.DASHBOARD.SUMMARY);
        setSummary(res.data.data);
      } catch (error) {
        console.error("Failed to fetchDashboardSummaryData", error);
      }
    };
    fetchDashboardSummary();
    fetchDashboardData();
  }, []);

  if (!dashboardData) {
    return <div className="p-8">Đang tải dữ liệu...</div>;
  }

  const chartData = {
    labels: dashboardData.revenueLabels,
    datasets: [
      {
        label: "Doanh thu",
        data: dashboardData.revenueChartData,
        borderColor: "#4A90E2",
        fill: false,
        tension: 0.1,
      },
    ],
  };

  const chartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      x: {
        title: {
          display: true,
          text: "Thời gian",
          font: {
            weight: "bold",
            size: 18,
          },
        },
        ticks: {
          autoSkip: false, // ✅ Luôn hiển thị đầy đủ nhãn
          maxRotation: 45,
          minRotation: 20,
        },
      },
      y: {
        title: {
          display: true,
          text: "Doanh thu (VNĐ)",
          font: {
            weight: "bold",
            size: 18,
          },
        },
        beginAtZero: true,
      },
    },
  };

  return (
    <div className="min-h-screen flex bg-gray-50">
      <LeftSidebarAdmin customHeight="h-auto w-64" />
      <div className="flex-1 p-8">
        <h1 className="text-3xl font-bold text-gray-800 mb-6">Dashboard</h1>
        {summary ? (
          <div className="grid grid-cols-1 md:grid-cols-4 gap-6 mb-8">
            {/* Total users */}
            <div className="bg-white p-6 rounded-lg shadow flex items-center justify-between">
              <div>
                <h2 className="text-xl font-semibold text-gray-700">
                  Tổng số người dùng
                </h2>
                <p className="text-2xl font-bold text-gray-800">
                  {summary.totalUsers.toLocaleString()}
                </p>
                <p className="text-green-600">
                  +{summary.totalUsersGrowthPercent}% so với Hôm qua
                </p>
              </div>
              <FaUsers className="text-blue-600 text-4xl" />
            </div>

            {/* Total exams */}
            <div className="bg-white p-6 rounded-lg shadow flex items-center justify-between">
              <div>
                <h2 className="text-xl font-semibold text-gray-700">
                  Tổng số bài thi đã làm
                </h2>
                <p className="text-2xl font-bold text-gray-800">
                  {summary.totalExamsTaken.toLocaleString()}
                </p>
                <p className="text-green-600">
                  +{summary.totalExamsGrowthPercent}% so với Tuần trước
                </p>
              </div>
              <FaFileAlt className="text-orange-600 text-4xl" />
            </div>

            {/* Revenue */}
            <div className="bg-white p-6 rounded-lg shadow flex items-center justify-between">
              <div>
                <h2 className="text-xl font-semibold text-gray-700">
                  Tổng doanh thu
                </h2>
                <p className="text-2xl font-bold text-gray-800">
                  {summary.totalRevenue.toLocaleString()}
                </p>
                <p className="text-green-600">
                  +{summary.totalRevenueGrowthPercent}% so với Hôm qua
                </p>
              </div>
              <FaChartLine className="text-green-600 text-4xl" />
            </div>

            {/* Completion Rate */}
            <div className="bg-white p-6 rounded-lg shadow flex items-center justify-between">
              <div>
                <h2 className="text-xl font-semibold text-gray-700">
                  Tỷ lệ hoàn thành bài
                </h2>
                <p className="text-2xl font-bold text-gray-800">
                  {summary.completionRate}%
                </p>
                <p className="text-green-600">
                  +{summary.completionRateGrowthPercent}% so với Hôm qua
                </p>
              </div>
              <MdOutlineCloudDone className="text-red-600 text-4xl" />
            </div>
          </div>
        ) : null}

        <div className="bg-white p-6 rounded-lg shadow mb-8">
          <h2 className="text-xl font-semibold text-gray-800 mb-4">
            Chi tiết doanh thu
          </h2>

          {/* Wrapper có kích thước cố định và cuộn nếu tràn */}
          <div className="max-w-full max-h-[400px] overflow-auto">
            {/* Biểu đồ cần có kích thước tối thiểu đủ lớn */}
            <div className="min-w-[800px] min-h-[300px]">
              <Line data={chartData} options={chartOptions} />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DashboardPage;
