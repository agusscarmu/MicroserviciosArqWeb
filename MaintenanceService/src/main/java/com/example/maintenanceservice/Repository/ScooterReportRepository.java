package com.example.maintenanceservice.Repository;

import com.example.maintenanceservice.Model.ScooterReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScooterReportRepository extends JpaRepository<ScooterReport, Long> {
}
