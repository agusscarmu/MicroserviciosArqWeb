package com.example.accountservice.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
