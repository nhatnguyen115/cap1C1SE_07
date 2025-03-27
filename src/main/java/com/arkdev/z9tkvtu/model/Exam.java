package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exam")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "exam_id",
                nullable = false))
})
public class Exam extends AbstractEntity<Integer> {
    @Column(name = "exam_name", length = 255)
    private String examName;

    @Column(name = "total_score")
    private Short totalScore;

    @Column(name = "duration")
    private Short duration;
}