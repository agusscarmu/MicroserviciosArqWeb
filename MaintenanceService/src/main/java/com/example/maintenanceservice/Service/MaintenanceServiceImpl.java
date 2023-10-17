package com.example.maintenanceservice.Service;

import com.example.maintenanceservice.Model.Maintenance;
import com.example.maintenanceservice.Repository.MaintenanceRepository;
import com.example.maintenanceservice.Service.Interface.MaintenanceService;
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
    public ResponseEntity<String> underMaintenance(Maintenance maintenance) {
        maintenance.startMaintenance();
        maintenanceRepository.save(maintenance);
        webClientScooter.put()
                .uri("/scooter/maintenance?scooterId={id}&maintenance={maintenance}", maintenance.getScooterId(), true)
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
                .uri("/scooter/maintenance?scooterId={id}&maintenance={maintenance}", maintenance.getScooterId(), false)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(maintenance.toString());
    }
}
