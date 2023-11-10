package com.example.monitoringservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO implements Serializable {

    private String username;
    private String password;
    private boolean admin;
    private boolean maintenance;
}