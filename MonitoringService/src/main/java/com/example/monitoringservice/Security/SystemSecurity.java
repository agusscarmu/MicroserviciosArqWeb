package com.example.monitoringservice.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.List;

public class SystemSecurity {

    private static final List<String> allowed = List.of("MaintenanceService", "AdminService", "AccountService", "TravelService", "ScooterService");

    private static String key = "Admin";

    private static SystemSecurity instance = null;

    public static SystemSecurity getInstance() {
        if (instance == null) {
            instance = new SystemSecurity();
        }
        return instance;
    }

    public static boolean isAllowed(String request) {
        return allowed.contains(request);
    }

    public static String getKey() {
        return key;
    }

    public static String getToken(){
        String token = Jwts.builder()
                .setSubject("AdminService")
                .signWith(SignatureAlgorithm.HS256, KeyEncoder.getKey())
                .compact();
        return token;
    }
}