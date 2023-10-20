package com.example.maintenanceservice.Repository;

import com.example.maintenanceservice.Model.ScooterReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface ScooterReportRepository extends JpaRepository<ScooterReport, Long> {

    @Query(value = "SELECT new com.example.maintenanceservice.DTO.ScooterReportDTO.ScooterReportDTO(sR.id ,sR.kmTraveled) FROM ScooterReport sR ORDER BY sR.kmTraveled DESC")
    List<Serializable> getReportByKm();

    @Query(value = "SELECT new com.example.maintenanceservice.DTO.ScooterReportDTO.ScooterReportDTO(sR.id ,sR.usageTimeWithPause) FROM ScooterReport sR ORDER BY sR.usageTimeWithPause DESC")
    List<Serializable> getReportByUsageTimeWithPause();

    @Query(value = "SELECT new com.example.maintenanceservice.DTO.ScooterReportDTO.ScooterReportDTO(sR.id ,sR.usageTimeWithoutPause) FROM ScooterReport sR ORDER BY sR.usageTimeWithoutPause DESC")
    List<Serializable> getReportByUsageTimeWithoutPause();

    @Query(value = "SELECT new com.example.maintenanceservice.DTO.ScooterReportDTO.ScooterReportKmPauseDTO(sR.id ,sR.kmTraveled, sR.totalPauseTime) FROM ScooterReport sR ORDER BY sR.kmTraveled DESC")
    List<Serializable> getReportByKmAndUsageTimeWithPause();

    @Query(value = "SELECT new com.example.maintenanceservice.DTO.ScooterReportDTO.ScooterReportByTravelsDTO(sR.id , sR.kmTraveled ,sR.totalRides) FROM ScooterReport sR WHERE sR.totalRides > ?1 ORDER BY sR.totalRides DESC")
    List<Serializable> getReportByTravels(long x);
}
