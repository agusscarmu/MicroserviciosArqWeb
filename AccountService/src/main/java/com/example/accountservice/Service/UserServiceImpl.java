package com.example.accountservice.Service;

import com.example.accountservice.DTO.User.UserDTO;
import com.example.accountservice.Model.User;
import com.example.accountservice.Repository.UserRepository;
import com.example.accountservice.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }


}
