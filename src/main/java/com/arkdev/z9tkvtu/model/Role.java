package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "role_id",
                nullable = false))
})
public class Role extends AbstractEntity<Integer> {
    @Size(max = 10)
    @Column(name = "role_name", length = 10)
    private String roleName;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

}