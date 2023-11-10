package com.example.monitoringservice.Service.Interface;

import com.example.monitoringservice.Security.AuthResponse;
import com.example.monitoringservice.dto.RegisterRequestDTO;

public interface AuthService {

    AuthResponse login(String username, String password);

    AuthResponse register(RegisterRequestDTO user);
}
