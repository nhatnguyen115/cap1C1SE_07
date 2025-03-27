package com.arkdev.z9tkvtu.model;

import com.arkdev.z9tkvtu.util.TestType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "test")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "test_id",
                nullable = false))
})
public class Test extends AbstractEntity<Integer> {
    @Column(name = "test_type", nullable = false)
    private TestType testType;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Exam> exams;
}