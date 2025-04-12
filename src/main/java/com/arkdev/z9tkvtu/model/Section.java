package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "section")
public class Section extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id", nullable = false, updatable = false)
    Integer id;

    @Column(name = "section_name")
    private String sectionName;

    @Column(name = "description")
    private String description;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "section_type")
    private String sectionType;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lesson> lessons = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "section_part",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    private Set<Part> parts = new HashSet<>();
}