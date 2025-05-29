package com.arkdev.z9tkvtu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {
//    private long totalUsers;
//    private long totalExams;
//    private long totalRevenue;
//    private double completionRate;
    private List<Long> revenueChartData;
    private List<String> revenueLabels;
}
