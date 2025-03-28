package com.arkdev.z9tkvtu.model;

import com.arkdev.z9tkvtu.util.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_account")
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "user_id",
                nullable = false, updatable = false))
})
public class UserAccount extends AbstractEntity<UUID> {
    @Size(max = 20)
    @Column(name = "first_name", length = 20)
    private String firstName;

    @Size(max = 20)
    @Column(name = "last_name", length = 20)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "gender")
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dob;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}