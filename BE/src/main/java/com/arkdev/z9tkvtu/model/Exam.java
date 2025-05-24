package com.arkdev.z9tkvtu.model;

import com.arkdev.z9tkvtu.util.DifficultyLevel;
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
@Table(name = "exam")
public class Exam extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id", nullable = false, updatable = false)
    Integer id;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "total_score")
    private Integer totalScore;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "question_count")
    private Integer questionCount;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "level")
    private DifficultyLevel level;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "test")
    private TestType testType;

    @ManyToMany
    @JoinTable(
            name = "exam_structure",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    private Set<Part> parts = new HashSet<>();

    @ManyToMany(mappedBy = "exams")
    private Set<Section> sections = new HashSet<>();
}