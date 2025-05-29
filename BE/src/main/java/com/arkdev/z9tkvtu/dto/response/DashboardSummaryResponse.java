package com.arkdev.z9tkvtu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardSummaryResponse {
    private Long totalUsers;
    private Double totalUsersGrowthPercent;

    private Long totalExamsTaken;
    private Double totalExamsGrowthPercent;

    private Long totalRevenue;
    private Double totalRevenueGrowthPercent;

    private Double completionRate;
    private Double completionRateGrowthPercent;
}
