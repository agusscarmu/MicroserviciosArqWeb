package com.example.accountservice.Service.Interface;

import com.example.accountservice.DTO.User.UserDTO;
import com.example.accountservice.Model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAllUsers();

    ResponseEntity<String> addUser(User user);

    List<Object> localizeScooter(String id);

    User findUserById(String id);
}
