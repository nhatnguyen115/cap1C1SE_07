package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_test_attempt")
public class UserTestAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attempt_id", nullable = false, updatable = false)
    Integer id;

    @Column(name = "total_score")
    private Integer totalScore;

    @Column(name = "listening_score")
    private Integer listeningScore;

    @Column(name = "reading_score")
    private Integer readingScore;

    @Column(name = "correct_count")
    private Integer correctCount;

    @Column(name = "incorrect_count")
    private Integer incorrectCount;

    @Column(name = "skip_count")
    private Integer skipCount;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "complete")
    private Boolean complete;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<UserAnswer> userAnswers;
}