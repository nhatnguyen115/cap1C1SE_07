package com.arkdev.z9tkvtu.dto.response;

import com.arkdev.z9tkvtu.util.Gender;

import java.util.Date;
import java.util.UUID;

public interface UserVIPResponse {
    UUID getId();
    String getFirstName();
    String getLastName();
    Gender getGender();
    Date getDob();
    String getEmail();
    String getPhoneNumber();
    Integer getIsVIP();
}
