package com.example.adminservice.Service;

import com.example.adminservice.Model.User;
import com.example.adminservice.Model.Role;
import com.example.adminservice.Security.AuthResponse;
import com.example.adminservice.Security.jwt.JwtService;
import com.example.adminservice.Service.Interface.AdminService;
import com.example.adminservice.Service.Interface.AuthService;
import com.example.adminservice.dto.RegisterRequestDTO;
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
