package com.example.accountservice.Service;

import com.example.accountservice.DTO.User.UserDTO;
import com.example.accountservice.Model.User;
import com.example.accountservice.Repository.UserRepository;
import com.example.accountservice.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final WebClient webClientScooter = WebClient.builder().baseUrl("http://localhost:8082").build();

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<Object> localizeScooter(long id) {
        
        Flux<Object> scooters = webClientScooter
                .get()
                .uri("/scooter/getBy?location={location}", getCurrentLocation(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Object.class);

        return scooters.collectList().block();
    }
    
    private String getCurrentLocation(long id){
        return "Plaza centro";
    }


}
