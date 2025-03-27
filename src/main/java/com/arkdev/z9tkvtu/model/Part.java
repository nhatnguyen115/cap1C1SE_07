package com.arkdev.z9tkvtu.model;

import com.arkdev.z9tkvtu.util.QuestionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "part")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "part_id",
                nullable = false))
})
public class Part extends AbstractEntity<Integer> {
    @Column(name = "part_name", length = 255)
    private String partName;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "question_type")
    private QuestionType questionType;

    @Column(name = "instructions", length = Integer.MAX_VALUE)
    private String instructions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id")
    private Media media;

    @OneToMany
    private Set<Question> questions;
}