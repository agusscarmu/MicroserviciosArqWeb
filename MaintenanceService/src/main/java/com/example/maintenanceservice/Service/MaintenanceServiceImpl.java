package com.example.maintenanceservice.Service;

import com.example.maintenanceservice.Model.Maintenance;
import com.example.maintenanceservice.Repository.MaintenanceRepository;
import com.example.maintenanceservice.Security.JWTSecretKey;
import com.example.maintenanceservice.Service.Interface.MaintenanceService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    private final WebClient webClientScooter = WebClient.create("http://localhost:8082");

    @Override
    public ResponseEntity<String> underMaintenance(long scooterId) {
        String token = Jwts.builder()
                .setSubject("MaintenanceService")
                .signWith(SignatureAlgorithm.HS256, JWTSecretKey.getKey())
                .compact();
        Maintenance maintenance = new Maintenance(scooterId);
        maintenance.startMaintenance();
        maintenanceRepository.save(maintenance);
        webClientScooter.put()
                .uri("/scooter/maintenance?scooterId={id}&maintenance={maintenance}",maintenance.getScooterId(), true)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(maintenance.toString());
    }

    @Override
    public ResponseEntity<String> finalizeMaintenance(long id) {
        Maintenance maintenance = maintenanceRepository.findById(id).get();
        maintenance.finalizeMaintenance();
        maintenanceRepository.save(maintenance);
        webClientScooter.put()
                .uri("/scooter/maintenance?scooterId={id}&maintenance={maintenance}",maintenance.getScooterId(), false)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(maintenance.toString());
    }
}
