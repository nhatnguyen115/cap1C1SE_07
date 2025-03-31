package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_answer")
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "answer_id",
                nullable = false))
})
public class UserAnswer extends AbstractEntity<Integer> {
    @Column(name = "selected_answer")
    private String selectedAnswer;

    @ManyToOne
    @JoinColumn(name = "attempt_id")
    private UserTestAttempt attempt;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public UserAnswer(UserTestAttempt attempt,
                      Question question,
                      String selectedAnswer) {
        this.attempt = attempt;
        this.question = question;
        this.selectedAnswer = selectedAnswer;
    }
}