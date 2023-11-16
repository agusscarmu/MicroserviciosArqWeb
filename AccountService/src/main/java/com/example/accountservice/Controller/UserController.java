package com.example.accountservice.Controller;

import com.example.accountservice.DTO.User.UserDTO;
import com.example.accountservice.Model.User;
import com.example.accountservice.Service.Interface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping
    public List<UserDTO> users(){
        return userService.findAllUsers();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/localizeScooter")
    public List<Object> getScooters(@RequestParam("id") String id){
        return userService.localizeScooter(id);
    }


}
