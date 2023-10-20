package com.example.maintenanceservice.Service.Interface;


import java.io.Serializable;
import java.util.List;

public interface ScooterReportService {
    boolean existsById(Long id);

    void saveReport(Long id, double usageTime, double pauseTime, double km);

    List<Serializable> getReport(String filter);

    List<Serializable> getReportWithPauseTime();

    List<Serializable> getReportByTravels(long x);
}
