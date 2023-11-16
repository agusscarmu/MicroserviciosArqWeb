package com.example.accountservice.Service;



import com.example.accountservice.DTO.User.UserDTO;
import com.example.accountservice.Model.User;
import com.example.accountservice.Repository.UserRepository;
import com.example.accountservice.Service.Interface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final WebClient webClientScooter = WebClient.builder().baseUrl("http://localhost:8082").build();

    @Override
    public List<UserDTO> findAllUsers() {
        // Considera implementar el método findAllUsers en tu UserRepository si es necesario
        return userRepository.findAllUsers();
    }

    @Override
    public ResponseEntity<String> addUser(User user) {
        userRepository.save(user);
        return ResponseEntity.ok("User added");
    }

    @Override
    public List<Object> localizeScooter(String id) {
        Flux<Object> scooters = webClientScooter
                .get()
                .uri("/scooter/getBy?location={location}", getCurrentLocation(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Object.class);

        return scooters.collectList().block();
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findUserById(id);
    }

    private String getCurrentLocation(String id) {
        // Tu lógica para obtener la ubicación actual basada en el id
        return "Plaza centro";
    }
}

