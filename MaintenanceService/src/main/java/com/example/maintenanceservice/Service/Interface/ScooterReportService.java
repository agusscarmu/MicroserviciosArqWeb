package com.example.maintenanceservice.Service.Interface;

import org.springframework.stereotype.Service;

public interface ScooterReportService {
    boolean existsById(Long id);

    void saveReport(Long id, int usageTime, int pauseTime);

}
