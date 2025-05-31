package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "grammar")
public class Grammar extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grammar_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "grammar_name", nullable = false)
    private String grammarName;

    @Column(name = "grammar_text", length = Integer.MAX_VALUE)
    private String grammarText;

    @Column(name = "order_number")
    private Integer orderNumber;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "media_id")
    private Media media;
}