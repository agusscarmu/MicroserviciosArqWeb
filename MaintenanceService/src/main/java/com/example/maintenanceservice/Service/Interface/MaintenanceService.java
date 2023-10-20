package com.example.maintenanceservice.Service.Interface;

import org.springframework.http.ResponseEntity;

public interface MaintenanceService {
    ResponseEntity<String> underMaintenance(long scooterId);

    ResponseEntity<String> finalizeMaintenance(long id);
}
