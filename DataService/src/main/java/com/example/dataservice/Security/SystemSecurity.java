package com.example.dataservice.Security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.List;

public class SystemSecurity {

    private static final List<String> allowed = List.of("AdminService");

    private static String key = "Data";

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

    public static String decode(String token) {
        String[] parts = token.split("\\.");
        String payload = parts[1];
        byte[] decodedPayloadBytes = java.util.Base64.getDecoder().decode(payload);
        String decodedPayload = new String(decodedPayloadBytes);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(decodedPayload);
            if (jsonNode.has("sub")) {
                return jsonNode.get("sub").asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getToken(){
        String token = Jwts.builder()
                .setSubject("DataService")
                .signWith(SignatureAlgorithm.HS256, SystemSecurity.getKey())
                .compact();
        return token;
    }
}