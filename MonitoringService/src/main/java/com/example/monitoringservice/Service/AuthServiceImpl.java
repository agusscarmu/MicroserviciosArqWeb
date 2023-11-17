package com.example.monitoringservice.Service;

import com.example.monitoringservice.Model.User;
import com.example.monitoringservice.Model.Role;
import com.example.monitoringservice.Repository.AdminRepository;
import com.example.monitoringservice.Security.AuthResponse;
import com.example.monitoringservice.Security.jwt.JwtService;
import com.example.monitoringservice.Service.Interface.AdminService;
import com.example.monitoringservice.Service.Interface.AuthService;
import com.example.monitoringservice.dto.RegisterRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    AdminRepository adminRepository;
    private final AdminService adminService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return getAuthResponse(username);
    }

    @Override
    public AuthResponse register(RegisterRequestDTO registerUser) {
        if(findByUsername(registerUser.getUsername()).isPresent())
            return new AuthResponse("Username already exists");
        User newUser=new User();
        newUser.setUsername(registerUser.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        if(registerUser.isAdmin())
            newUser.addRole(Role.ADMIN);
        if(registerUser.isMaintenance())
            newUser.addRole(Role.MAINTENANCE);
        addAdmin(newUser);
        return getAuthResponse(registerUser.getUsername());
    }

    private AuthResponse getAuthResponse(String username){
        UserDetails user= (UserDetails) findByUsername(username).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }



    private Optional<Object> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }


    private void addAdmin(User user) {
        adminRepository.save(user);
    }
}
