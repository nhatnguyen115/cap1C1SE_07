package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_section_answer")
public class UserSectionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private Integer id;

    @Column(name = "selected_answer")
    private String selectedAnswer;

    @ManyToOne
    @JoinColumn(name = "attempt_id")
    private UserSectionAttempt attempt;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}