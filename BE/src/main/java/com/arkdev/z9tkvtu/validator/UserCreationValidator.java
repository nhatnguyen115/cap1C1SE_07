package com.arkdev.z9tkvtu.validator;

import com.arkdev.z9tkvtu.dto.request.UserCreationRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserCreationValidator {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@gmail\\.com$");

    public static List<String> validate(UserCreationRequest request) {
        List<String> errors = new ArrayList<>();

        if (request == null) {
            errors.add("Request cannot be null");
            return errors;
        }

        String email = request.getEmail();
        if (isBlank(email)) {
            errors.add("Email must not be blank");
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            errors.add("Email must be a valid gmail address (e.g., xxx@gmail.com)");
        }

        if (isBlank(request.getUsername())) {
            errors.add("Username must not be blank");
        } else if (request.getUsername().length() > 50) {
            errors.add("Username must not exceed 50 characters");
        }

        if (isBlank(request.getPassword())) {
            errors.add("Password must not be blank");
        }

        return errors;
    }

    private static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}
