package com.arkdev.z9tkvtu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_login_external")
@PrimaryKeyJoinColumn(name = "user_id")
public class UserLoginExternal extends UserAccount {
    @NotNull
    @Column(name = "provider_id", nullable = false)
    private Integer providerId;

    @Size(max = 255)
    @Column(name = "external_token")
    private String externalToken;

}