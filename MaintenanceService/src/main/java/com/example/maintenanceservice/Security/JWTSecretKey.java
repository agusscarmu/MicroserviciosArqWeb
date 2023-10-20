package com.example.maintenanceservice.Security;

public class JWTSecretKey {

    private static String key = "asdfghjklzxcvbnmqwertyuiop";

    public static String getKey() {
        return key;
    }
}
