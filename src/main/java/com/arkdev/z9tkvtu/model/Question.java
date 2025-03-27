package com.arkdev.z9tkvtu.model;

import com.arkdev.z9tkvtu.util.DifficultyLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "question")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "question_id",
                nullable = false))
})
public class Question extends AbstractEntity<Integer> {
    @Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
    private String content;

    @Column(name = "options", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> options;

    @Column(name = "correct_answer", length = 1)
    private String correctAnswer;

    @Column(name = "explanation", length = Integer.MAX_VALUE)
    private String explanation;

    @Column(name = "difficulty")
    private DifficultyLevel difficulty;

    @OneToOne
    private Media media;
}