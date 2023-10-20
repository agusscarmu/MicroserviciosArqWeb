package com.example.accountservice.Controller;

import com.example.accountservice.DTO.User.UserDTO;
import com.example.accountservice.Model.User;
import com.example.accountservice.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<UserDTO> users(){
        return userService.findAllUsers();
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.addUser(user));
    }

    @RequestMapping(value="/localizeScooter", method = RequestMethod.GET)
    public List<Object> getScooters(@RequestParam("id") long id){
        return userService.localizeScooter(id);
    }


}
