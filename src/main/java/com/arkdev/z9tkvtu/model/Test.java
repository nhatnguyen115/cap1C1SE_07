package com.arkdev.z9tkvtu.model;

import com.arkdev.z9tkvtu.util.TestType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
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
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "test_type")
    private TestType testType;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Exam> exams = new HashSet<>();
}