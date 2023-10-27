package com.example.adminservice.Service;

import com.example.adminservice.Model.Admin;
import com.example.adminservice.Repository.AdminRepository;
import com.example.adminservice.Security.SystemSecurity;
import com.example.adminservice.Service.Interface.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    private final WebClient webClientAccount = WebClient.builder().baseUrl("http://localhost:8081").build();

    private final WebClient webClientScooter = WebClient.builder().baseUrl("http://localhost:8082").build();

    private final WebClient webClientMaintenance = WebClient.builder().baseUrl("http://localhost:8083").build();

    @Override
    public String addAdmin(Admin admin) {
        adminRepository.save(admin);
        return "Admin added successfully";
    }

    @Override
    public String changeAccountStatus(long id, boolean active) {
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
    public String updatePrice(float price, String dateParam) {
        if(dateParam != null){
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateParam);
                return webClientScooter
                        .put()
                        .uri("/travels/updatePrice?price={price}&date={date}", price, date)
                        .header("Authorization", "Bearer " + SystemSecurity.getToken())
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
            }catch (Exception e){
                return "Invalid date format";
            }
        }
        return webClientScooter
                .put()
                .uri("/travels/updatePrice?price={price}", price)
                .header("Authorization", "Bearer " + SystemSecurity.getToken())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


}
