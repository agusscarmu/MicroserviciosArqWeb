package com.example.adminservice.Service.Interface;

import com.example.adminservice.Model.User;
import com.example.adminservice.Security.AuthResponse;
import com.example.adminservice.dto.RegisterRequestDTO;

public interface AuthService {

    AuthResponse login(String username, String password);

    AuthResponse register(RegisterRequestDTO user);
}
