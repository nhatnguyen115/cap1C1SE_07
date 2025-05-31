package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.dto.response.RevenueProjection;
import com.arkdev.z9tkvtu.model.UserMembership;
import com.arkdev.z9tkvtu.util.MembershipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserMembershipRepository extends JpaRepository<UserMembership, Integer> {

    @Query(value = """
        select exists (
                select um.*
                from user_membership um
                where um.user_id = :userId
                and um.status = 'ACTIVE'
            )
    """, nativeQuery = true)
    boolean existsActiveMembership(@Param("userId") UUID userId);

    List<UserMembership> findByEndDateBeforeAndStatus(Timestamp currentTime, MembershipStatus membershipStatus);

    Optional<UserMembership> findByUserId(UUID id);

    Optional<UserMembership> findByUserIdAndStatus(UUID user_id, MembershipStatus status);

    @Query(value = """
                 SELECT SUM(mp.PRICE)
                 from USER_MEMBERSHIP UM\s
                 inner join MEMBERSHIP_PLAN MP on um.PLAN_ID = mp.PLAN_ID
                 where um.STATUS = 'ACTIVE'
                    or um.STATUS = 'EXPIRED';
                     
    """, nativeQuery = true)
    Long totalRevenue();

    @Query(value = """
                SELECT SUM(mp.PRICE)
                from USER_MEMBERSHIP UM\s
                inner join MEMBERSHIP_PLAN MP on um.PLAN_ID = mp.PLAN_ID
                where (um.STATUS = 'ACTIVE'
                	or um.STATUS = 'EXPIRED')
                	 AND UM.UPDATED_AT ::date = CURRENT_DATE;
    """, nativeQuery = true)
    Long totalRevenueToday();

    @Query(value = """
                 SELECT
                     SUM(MP.PRICE) AS totalPrice,
                     EXTRACT(MONTH FROM UM.UPDATED_AT) AS month
                 FROM USER_MEMBERSHIP UM
                 INNER JOIN MEMBERSHIP_PLAN MP ON UM.PLAN_ID = MP.PLAN_ID
                 WHERE
                     (UM.STATUS = 'ACTIVE' OR UM.STATUS = 'EXPIRED')
                     AND EXTRACT(YEAR FROM UM.UPDATED_AT) = :year
                 GROUP BY EXTRACT(MONTH FROM UM.UPDATED_AT)
                 ORDER BY month;
    """, nativeQuery = true)
    List<RevenueProjection> totalRevenueFollowMonthByYear(@Param("year") Integer year);

}