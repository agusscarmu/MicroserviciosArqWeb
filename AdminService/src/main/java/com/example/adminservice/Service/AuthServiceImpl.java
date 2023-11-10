package com.example.adminservice.Service;

import com.example.adminservice.Model.Admin;
import com.example.adminservice.Model.Role;
import com.example.adminservice.Security.AuthResponse;
import com.example.adminservice.Security.jwt.JwtService;
import com.example.adminservice.Service.Interface.AdminService;
import com.example.adminservice.Service.Interface.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;;
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
    public AuthResponse register(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(Role.ADMIN);
        adminService.addAdmin(admin);
        UserDetails user= (UserDetails) adminService.findByUsername(admin.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
