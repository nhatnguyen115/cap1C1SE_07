package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_section_attempt")
@AllArgsConstructor
@NoArgsConstructor
public class UserSectionAttempt extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attempt_id", nullable = false)
    private Integer id;

    @Column(name = "correct_count")
    private Short correctCount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<UserSectionAnswer> answers;
}