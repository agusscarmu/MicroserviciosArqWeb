package com.example.accountservice.Repository;

import com.example.accountservice.DTO.User.UserDTO;
import com.example.accountservice.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{}", fields = "{ 'id' : 1, 'name' : 1, 'lastName' : 1, 'email' : 1, 'phone' : 1, 'address' : 1, 'createdAt' : 1, 'active' : 1 }")
    List<UserDTO> findAllUsers();


    User findUserById(String id);
}
