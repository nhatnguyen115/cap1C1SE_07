package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "external_provider")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "provider_id",
                nullable = false))
})
public class ExternalProvider extends AbstractEntity<Integer> {
    @Size(max = 20)
    @Column(name = "provider_name", length = 20)
    private String providerName;

    @Size(max = 50)
    @Column(name = "ws_endpoint", length = 50)
    private String wsEndpoint;

}