package com.example.monitoringservice.Service.Interface;

import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface MaintenanceManagerService {
    ResponseEntity<String> underMaintenance(long scooterId);

    ResponseEntity<String> finalizeMaintenance(long maintenance);

    List<Serializable> getReport(String filter, Boolean pauseTime);
}
