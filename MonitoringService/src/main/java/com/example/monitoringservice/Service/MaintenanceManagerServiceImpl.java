package com.example.monitoringservice.Service;

import com.example.monitoringservice.Security.SystemSecurity;
import com.example.monitoringservice.Service.Interface.MaintenanceManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.Serializable;
import java.util.List;

@Service
public class MaintenanceManagerServiceImpl implements MaintenanceManagerService {

    private final WebClient webClientMaintenance = WebClient.builder().baseUrl("http://localhost:8083").build();
    @Override
    public ResponseEntity<String> underMaintenance(long ScooterId) {
        try{
            webClientMaintenance.post()
                    .uri("/maintenance/underMaintenance?ScooterId={ScooterId}", ScooterId)
                    .header("Authorization", "Bearer " + SystemSecurity.getToken())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return ResponseEntity.ok("Maintenance started");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Scooter not found");
        }
    }

    @Override
    public ResponseEntity<String> finalizeMaintenance(long id) {
        try{
            webClientMaintenance.put()
                    .uri("/maintenance/finalizeMaintenance?id={id}", id)
                    .header("Authorization", "Bearer " + SystemSecurity.getToken())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return ResponseEntity.ok("Maintenance finalized");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Maintenance not found");
        }
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
