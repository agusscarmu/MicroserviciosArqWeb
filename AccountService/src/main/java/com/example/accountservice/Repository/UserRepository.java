package com.example.accountservice.Repository;

import com.example.accountservice.DTO.User.UserDTO;
import com.example.accountservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT NEW com.example.accountservice.DTO.User.UserDTO(u.firstName, u.lastName, u.email, u.phone) FROM User u")
    List<UserDTO> findAllUsers();
}
