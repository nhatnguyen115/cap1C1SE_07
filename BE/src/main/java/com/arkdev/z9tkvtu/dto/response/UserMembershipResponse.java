package com.arkdev.z9tkvtu.dto.Response;

import com.arkdev.z9tkvtu.util.MembershipStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record UserMembershipResponse(
        String planName,
        BigDecimal price,
        Integer durationDays,
        Timestamp startDate,
        MembershipStatus status) {
}
