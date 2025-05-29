export interface DashboardType {
  totalUsers: number;
  totalExams: number;
  totalRevenue: number;
  completionRate: number;
  revenueChartData: number[];
  revenueLabels: string[];
}

export interface DashboardSummaryType {
  totalUsers: number;
  totalUsersGrowthPercent: number;
  totalExamsTaken: number;
  totalExamsGrowthPercent: number;
  totalRevenue: number;
  totalRevenueGrowthPercent: number;
  completionRate: number;
  completionRateGrowthPercent: number;
}
