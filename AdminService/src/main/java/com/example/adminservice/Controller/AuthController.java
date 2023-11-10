package com.example.adminservice.Controller;

import com.example.adminservice.Model.User;
import com.example.adminservice.Security.AuthResponse;
import com.example.adminservice.Service.Interface.AuthService;
import com.example.adminservice.dto.AuthRequestDTO;
import com.example.adminservice.dto.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthService authService;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> addAdmin(@RequestBody RegisterRequestDTO user){
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginAdmin(@RequestBody AuthRequestDTO request){
        return new ResponseEntity<>(authService.login(request.getUsername(), request.getPassword()), HttpStatus.OK);
    }
}
