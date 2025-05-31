package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.response.DashboardResponse;
import com.arkdev.z9tkvtu.dto.response.DashboardSummaryResponse;
import com.arkdev.z9tkvtu.dto.response.RevenueProjection;
import com.arkdev.z9tkvtu.dto.response.ScoreProjection;
import com.arkdev.z9tkvtu.repository.UserLoginDataRepository;
import com.arkdev.z9tkvtu.repository.UserMembershipRepository;
import com.arkdev.z9tkvtu.repository.UserTestAttemptRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED,  makeFinal = true)
public class DashboardService {

    UserLoginDataRepository userRepository;
    UserTestAttemptRepository userTestAttemptRepository;
    UserMembershipRepository userMembershipRepository;

    private static final double PERCENT_PASS = 50;
    public DashboardSummaryResponse getDashboardSummary(){

        Long totalUsers = userRepository.findUserActive();
        Long totalUsersToday = userRepository.findUserActiveToday();

        Double totalUsersGrowthPercent = 0.0;
        if (totalUsers != null && totalUsersToday != null && (totalUsers - totalUsersToday) != 0) {
            totalUsersGrowthPercent = (double) totalUsersToday / (totalUsers - totalUsersToday) * 100;
            totalUsersGrowthPercent = Math.round(totalUsersGrowthPercent * 100.0) / 100.0;
        }

        Long totalExamsTaken = userTestAttemptRepository.findTestComplete();
        Long totalExamsTakenToday = userTestAttemptRepository.findTestCompleteToday();

        Double totalExamsGrowthPercent = 0.0;
        if (totalExamsTaken != null && totalExamsTakenToday != null && (totalExamsTaken - totalExamsTakenToday) != 0) {
            totalExamsGrowthPercent = (double) totalExamsTakenToday / (totalExamsTaken - totalExamsTakenToday) * 100;
            totalExamsGrowthPercent = Math.round(totalExamsGrowthPercent * 100.0) / 100.0;
        }

        Long totalRevenue = userMembershipRepository.totalRevenue();
        Long totalRevenueToday = userMembershipRepository.totalRevenueToday();
        Double totalRevenueGrowthPercent = 0.0;
        if (totalRevenue != null && totalRevenueToday != null && (totalRevenue - totalRevenueToday) != 0) {
            totalRevenueGrowthPercent = (double) totalRevenueToday / (totalRevenue - totalRevenueToday) * 100;
            totalRevenueGrowthPercent = Math.round(totalRevenueGrowthPercent * 100.0) / 100.0;
        }

        List<ScoreProjection> completionRate = userTestAttemptRepository.findTestCompletePass();
        List<ScoreProjection> completionRateToday = userTestAttemptRepository.findTestCompletePassToday();

        Long countCompletionRate = (Long) completionRate.stream()
                .filter(s -> {
                    Long listening = s.getListeningScore();
                    Long reading = s.getReadingScore();
                    if (listening == null || reading == null) return false;

                    Long totalScore = listening + reading;
                    double percentage = (totalScore / 990.0) * 100;
                    return percentage >= PERCENT_PASS;
                })
                .count();

        Long countCompletionRatetoday = (Long) completionRateToday.stream()
                .filter(s -> {
                    Long listening = s.getListeningScore();
                    Long reading = s.getReadingScore();
                    if (listening == null || reading == null) return false;

                    Long totalScore = listening + reading;
                    double percentage = (totalScore / 990.0) * 100;
                    return percentage >= PERCENT_PASS;
                })
                .count();

        Double completionRate50 = 0.0;
        if (countCompletionRate != null && completionRate.size() != 0 && (countCompletionRate - completionRate.size()) != 0) {
            completionRate50 = (double) countCompletionRate / (completionRate.size() - countCompletionRate) * 100;
            completionRate50 = Math.round(completionRate50 * 100.0) / 100.0;
        }

        Double completionRateGrowthPercent = 0.0;
        if (countCompletionRate != null && countCompletionRatetoday != null && (countCompletionRate - countCompletionRatetoday) != 0) {
            completionRateGrowthPercent = (double) countCompletionRatetoday / (countCompletionRate - countCompletionRatetoday) * 100;
            completionRateGrowthPercent = Math.round(completionRateGrowthPercent * 100.0) / 100.0;
        }

        DashboardSummaryResponse dashboardSummaryResponse = new DashboardSummaryResponse();

        dashboardSummaryResponse.setTotalUsers(totalUsers);
        dashboardSummaryResponse.setTotalUsersGrowthPercent(totalUsersGrowthPercent);
        dashboardSummaryResponse.setTotalExamsTaken(totalExamsTaken);
        dashboardSummaryResponse.setTotalExamsGrowthPercent(totalExamsGrowthPercent);
        dashboardSummaryResponse.setTotalRevenue(totalRevenue);
        dashboardSummaryResponse.setTotalRevenueGrowthPercent(totalRevenueGrowthPercent);
        dashboardSummaryResponse.setCompletionRate(completionRate50);
        dashboardSummaryResponse.setCompletionRateGrowthPercent(completionRateGrowthPercent);

        return dashboardSummaryResponse;
    }
    public DashboardResponse getDashboardData(Integer year){
        if (Objects.isNull(year)){
            year = 2025;
        }
        List<RevenueProjection> totalRevenueFollowMonthByYear = userMembershipRepository.totalRevenueFollowMonthByYear(year);
        Map<Long, Long> monthToRevenue = totalRevenueFollowMonthByYear.stream()
                .filter(p -> p.getMonth() != null && p.getTotalPrice() != null)
                .collect(Collectors.toMap(
                        RevenueProjection::getMonth,
                        RevenueProjection::getTotalPrice
                ));

        List<Long> revenueChartData = IntStream.rangeClosed(1, 12)
                .mapToObj(month -> monthToRevenue.getOrDefault((long) month, 0L))
                .collect(Collectors.toList());

        List<String> revenueLabels = IntStream.rangeClosed(1, 12)
                .mapToObj(month -> "Month " + month)
                .collect(Collectors.toList());

        DashboardResponse response = new DashboardResponse();
        response.setRevenueChartData(revenueChartData);
        response.setRevenueLabels(revenueLabels);
        return response;
    }
}
