package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "section")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "section_id",
                nullable = false))
})
public class Section extends AbstractEntity<Integer>{

    @Column(name = "section_name", nullable = false, length = 255)
    private String sectionName;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "order_number", nullable = false)
    private Integer orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Lesson> lessons;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "section_part",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    private Set<Part> parts;
}