package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_answer")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "answer_id",
                nullable = false))
})
public class UserAnswer extends AbstractEntity<Integer> {
    @Column(name = "selected_answer", length = Integer.MAX_VALUE)
    private String selectedAnswer;

    @ManyToOne
    @JoinColumn(name = "attempt_id", nullable = false)
    private UserTestAttempt attempt;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}