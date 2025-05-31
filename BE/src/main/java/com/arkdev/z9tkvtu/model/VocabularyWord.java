package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vocabulary_word")
public class VocabularyWord extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Column(name = "word", nullable = false, length = 200)
    private String word;

    @Column(name = "meaning", length = Integer.MAX_VALUE)
    private String meaning;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
}