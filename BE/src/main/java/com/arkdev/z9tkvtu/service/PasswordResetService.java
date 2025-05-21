package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.model.PasswordResetToken;
import com.arkdev.z9tkvtu.model.UserLoginData;
import com.arkdev.z9tkvtu.repository.PasswordResetTokenRepository;
import com.arkdev.z9tkvtu.repository.UserLoginDataRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PasswordResetService {

    @Autowired
    private UserLoginDataRepository userLoginDataRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Transactional
    public void generateAndSendOtp(String email) {
        var userOpt = userLoginDataRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Email không tồn tại trong hệ thống");
        }

        String otp = String.format("%06d", new Random().nextInt(1000000));
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);

        tokenRepository.deleteByEmail(email); // Xoá OTP cũ nếu có
        PasswordResetToken token = new PasswordResetToken();
        token.setEmail(email);
        token.setOtp(otp);
        token.setExpiryTime(expiry);
        tokenRepository.save(token);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Khôi phục mật khẩu");
        message.setText("Mã OTP của bạn là: " + otp + " (Hiệu lực trong 5 phút)");
        mailSender.send(message);
    }

    public boolean verifyOtp(String email, String otp) {
        return tokenRepository.findByEmailAndOtp(email, otp)
                .filter(t -> t.getExpiryTime().isAfter(LocalDateTime.now()))
                .isPresent();
    }

    @Transactional
    public void resetPassword(String email, String newPassword) {
        UserLoginData user = userLoginDataRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        String hashed = new BCryptPasswordEncoder().encode(newPassword);
        user.setPassword(hashed);
        userLoginDataRepository.save(user);

        tokenRepository.deleteByEmail(email); // xoá token sau khi sử dụng
    }
}
