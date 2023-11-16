package com.example.maintenanceservice.Service;

import com.example.maintenanceservice.Model.Maintenance;
import com.example.maintenanceservice.Repository.MaintenanceRepository;
import com.example.maintenanceservice.Security.SystemSecurity;
import com.example.maintenanceservice.Service.Interface.MaintenanceService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    private final WebClient webClientScooter = WebClient.create("http://localhost:8082");

    @Override
    public ResponseEntity<String> underMaintenance(long scooterId) {
        try{
            webClientScooter.put()
                    .uri("/scooter/maintenance?scooterId={id}&maintenance={maintenance}",scooterId, true)
                    .header("Authorization", "Bearer " + SystemSecurity.getToken())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }catch (WebClientException e){
            return ResponseEntity.badRequest().body("Scooter not found");
        }
        Maintenance maintenance = new Maintenance(scooterId);
        maintenance.startMaintenance();
        maintenanceRepository.save(maintenance);
        return ResponseEntity.ok(maintenance.toString());
    }

    @Override
    public ResponseEntity<String> finalizeMaintenance(long id) {
        if(!maintenanceRepository.existsById(id)){
            return ResponseEntity.badRequest().body("Maintenance not found");
        }

        Maintenance maintenance = maintenanceRepository.findById(id).get();

        try{
            webClientScooter.put()
                    .uri("/scooter/maintenance?scooterId={id}&maintenance={maintenance}",maintenance.getScooterId(), false)
                    .header("Authorization", "Bearer " + SystemSecurity.getToken())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }catch (WebClientException e){
            return ResponseEntity.badRequest().body("Scooter not found");
        }
        maintenance.finalizeMaintenance();
        maintenanceRepository.save(maintenance);
        return ResponseEntity.ok(maintenance.toString());
    }
}
