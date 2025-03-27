package com.arkdev.z9tkvtu.model;

import com.arkdev.z9tkvtu.util.ModuleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "module")
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "module_id",
                nullable = false))
})
public class Module extends AbstractEntity<Integer> {
    @Column(name = "module_type", nullable = false)
    private ModuleType moduleType;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Section> sections;
}