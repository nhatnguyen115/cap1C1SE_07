package com.arkdev.z9tkvtu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_token")
@Getter
@Setter
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reset_token_seq")
    @SequenceGenerator(
            name = "reset_token_seq",
            sequenceName = "password_reset_token_seq",
            allocationSize = 50
    )
    private Long id;

    private String email;
    private String otp;

    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;

}
