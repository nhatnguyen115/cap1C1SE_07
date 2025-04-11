package com.arkdev.z9tkvtu.configuration;

import java.util.Arrays;
import java.util.List;

public class SecurityConstant {
    public static final String[] PUBLIC_URLS = {
            "/"
            ,"/home"
            ,"/public/**"
            ,"/user/add"
            ,"/api/v1/product/search"
            ,"/user/add"
            ,"/auth/**"
            ,"/menu"
    };

    public static final List<String> ORIGINS_ALLOWED = Arrays.asList(
            "http://127.0.0.1:5500"
            ,"http://localhost:3000"
    );

    public static final List<String> HEADERS_ALLOWED = Arrays.asList(
            "Authorization"
            ,"Cache-Control"
            ,"Content-Type"
    );

    public static final List<String> METHODS_ALLOWED = Arrays.asList(
            "GET"
            ,"POST"
            ,"PUT"
            ,"DELETE"
            ,"OPTIONS"
    );

    public static final Boolean CREDENTIALS_ALLOWED = true;

    public static final Boolean CORS_CONFIGURATION = true;



}
