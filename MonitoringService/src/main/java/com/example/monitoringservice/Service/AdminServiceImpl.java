package com.example.monitoringservice.Service;

import com.example.monitoringservice.Model.User;
import com.example.monitoringservice.Repository.AdminRepository;
import com.example.monitoringservice.Security.SystemSecurity;
import com.example.monitoringservice.Service.Interface.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;



    private final WebClient webClientAccount = WebClient.builder().baseUrl("http://localhost:8081").build();

    private final WebClient webClientScooter = WebClient.builder().baseUrl("http://localhost:8082").build();

    private final WebClient webClientMaintenance = WebClient.builder().baseUrl("http://localhost:8083").build();

    private final WebClient webClientData = WebClient.builder().baseUrl("http://localhost:8086").build();

    @Override
    public String changeAccountStatus(String id, boolean active) {
        webClientAccount
                .put()
                .uri("/account/status?id={id}&active={active}", id, active)
                .header("Authorization", "Bearer " + SystemSecurity.getToken())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return "Account status changed successfully";
    }

    @Override
    public List<Serializable> reportByTravels(int travels) {
        return webClientMaintenance
                .get()
                .uri("/scooterReport/byTravels?moreThan={travels}", travels)
                .header("Authorization", "Bearer " + SystemSecurity.getToken())
                .retrieve()
                .bodyToFlux(Serializable.class)
                .collectList()
                .block();
    }


    @Override
    public Double getTotalFactured(Integer month1, Integer month2, Integer year) {
        String url = (month1 == null || month2 == null || year == null)?
                "/travels/totalFactured" :
                "/travels/totalFactured?month1={month1}&month2={month2}&year={year}";
        return webClientScooter
                .get()
                .uri(url, month1, month2, year)
                .header("Authorization", "Bearer " + SystemSecurity.getToken())
                .retrieve()
                .bodyToMono(Double.class)
                .block();
    }

    @Override
    public Serializable getStatus() {
        return webClientScooter
                .get()
                .uri("/scooter/status")
                .header("Authorization", "Bearer " + SystemSecurity.getToken())
                .retrieve()
                .bodyToMono(Serializable.class)
                .block();
    }

    @Override
    public void updatePrice(Double price, Long pauseLimit, Double extraPricePerMinute, Date date, String url) {
        webClientData.post()
                .uri("/dataTravel/update"+url, price, pauseLimit, extraPricePerMinute, date)
                .header("Authorization", "Bearer " + SystemSecurity.getToken())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public Optional<Object> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public void addAdmin(User user) {
        adminRepository.save(user);
    }


}
