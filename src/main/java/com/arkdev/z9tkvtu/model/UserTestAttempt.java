package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_test_attempt")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "attempt_id",
                nullable = false))
})
public class UserTestAttempt extends AbstractEntity<Integer> {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    private Timestamp startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "total_score")
    private Integer totalScore;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @OneToMany
    private Set<UserAnswer> userAnswers;
}