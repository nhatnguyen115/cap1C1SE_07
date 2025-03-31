package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "permission")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "permission_id",
                nullable = false))
})
public class Permission extends AbstractEntity<Integer> {
    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "description")
    private String description;

}