package com.example.adminservice.Service.Interface;

import com.example.adminservice.Model.Admin;
import com.example.adminservice.Security.AuthResponse;

public interface AuthService {

    AuthResponse login(String username, String password);

    AuthResponse register(Admin admin);
}
