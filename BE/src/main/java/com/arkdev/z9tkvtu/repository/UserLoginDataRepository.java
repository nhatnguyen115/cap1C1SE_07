package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.dto.response.UserVIPResponse;
import com.arkdev.z9tkvtu.model.UserLoginData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserLoginDataRepository extends JpaRepository<UserLoginData, UUID> {
  Optional<UserLoginData> findByUsername(String username);
  boolean existsByUsername(@NotNull(message = "Username must be not null")
                             @Size(message = "Username must be greater than 50 char", max = 50)
                             @NotBlank(message = "Username must be not blank") String username);

    Optional<UserLoginData> findByEmail(String email);

    Page<UserLoginData> findAllByOrderByUsernameAsc(Pageable pageable);

    @Query(value = """
                SELECT count(*)
                FROM USER_ACCOUNT UA
                INNER JOIN USER_LOGIN_DATA ULD ON UA.USER_ID = ULD.USER_ID
                WHERE UA.ACTIVE = true;
            """, nativeQuery = true)
    Long findUserActive();

    @Query(value = """
                SELECT COUNT(*)
                FROM USER_ACCOUNT UA
                INNER JOIN USER_LOGIN_DATA ULD ON UA.USER_ID = ULD.USER_ID
                WHERE   UA.ACTIVE = TRUE
                        AND UA.CREATED_AT::date = CURRENT_DATE;           
            """, nativeQuery = true)
    Long findUserActiveToday();

    @Query(value = """
    SELECT 
        ua.user_id AS id,
        ua.first_name AS firstName,
        ua.last_name AS lastName,
        ua.gender AS gender,
        ua.dob AS dob,
        uld.email AS email,
        uld.phone_number AS phoneNumber,
        CASE 
            WHEN um.status = 'ACTIVE' THEN 1
            ELSE 0
        END AS isVIP
    FROM user_account ua
    JOIN user_login_data uld ON ua.user_id = uld.user_id
    LEFT JOIN user_membership um ON ua.user_id = um.user_id AND um.status = 'ACTIVE'
    WHERE ua.user_id = :userId
    """, nativeQuery = true)
    UserVIPResponse findAllUsersWithVIPStatus(@Param("userId") UUID userId);


}