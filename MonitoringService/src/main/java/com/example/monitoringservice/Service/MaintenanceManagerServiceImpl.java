package com.example.monitoringservice.Service;

import com.example.monitoringservice.Security.SystemSecurity;
import com.example.monitoringservice.Service.Interface.MaintenanceManagerService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.Serializable;
import java.util.List;

@Service
public class MaintenanceManagerServiceImpl implements MaintenanceManagerService {

    private final WebClient webClientMaintenance = WebClient.builder().baseUrl("http://localhost:8083").build();
    @Override
    public String underMaintenance(long ScooterId) {
        return webClientMaintenance.post()
                .uri("/maintenance/underMaintenance?ScooterId={ScooterId}", ScooterId)
                .header("Authorization", "Bearer " + SystemSecurity.getToken())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String finalizeMaintenance(long id) {
        return webClientMaintenance.put()
                .uri("/maintenance/finalizeMaintenance?id={id}", id)
                .header("Authorization", "Bearer " + SystemSecurity.getToken())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public List<Serializable> getReport(String filter, Boolean pauseTime) {

        String url = pauseTime!=null?"/scooterReport/getReport?filter={filter}&pauseTime={pauseTime}":"/scooterReport/getReport?filter={filter}";

        return webClientMaintenance.get()
                .uri(url, filter, pauseTime)
                .header("Authorization", "Bearer " + SystemSecurity.getToken())
                .retrieve()
                .bodyToFlux(Serializable.class)
                .collectList()
                .block();
    }


}
