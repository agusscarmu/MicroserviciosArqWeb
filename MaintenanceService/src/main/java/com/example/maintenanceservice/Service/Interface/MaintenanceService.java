package com.example.maintenanceservice.Service.Interface;

import com.example.maintenanceservice.Model.Maintenance;
import org.springframework.http.ResponseEntity;

public interface MaintenanceService {
    ResponseEntity<String> underMaintenance(Maintenance maintenance);

    ResponseEntity<String> finalizeMaintenance(long id);
}
