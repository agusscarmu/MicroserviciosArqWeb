package com.example.monitoringservice.Service;

import com.example.monitoringservice.Model.User;
import com.example.monitoringservice.Model.Role;
import com.example.monitoringservice.Security.AuthResponse;
import com.example.monitoringservice.Security.jwt.JwtService;
import com.example.monitoringservice.Service.Interface.AdminService;
import com.example.monitoringservice.Service.Interface.AuthService;
import com.example.monitoringservice.dto.RegisterRequestDTO;
import org.springframework.security.authentication.AuthenticationManager;
import lombok.RequiredArgsConstructor;
;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminService adminService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetails user= (UserDetails) adminService.findByUsername(username).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();


    }

    @Override
    public AuthResponse register(RegisterRequestDTO registerUser) {
        User newUser=new User();
        newUser.setUsername(registerUser.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        if(registerUser.isAdmin())
            newUser.addRole(Role.ADMIN);
        if(registerUser.isMaintenance())
            newUser.addRole(Role.MAINTENANCE);
        adminService.addAdmin(newUser);
        UserDetails user= (UserDetails) adminService.findByUsername(newUser.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
