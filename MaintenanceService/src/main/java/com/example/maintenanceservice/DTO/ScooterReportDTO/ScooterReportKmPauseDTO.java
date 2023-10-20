package com.example.maintenanceservice.DTO.ScooterReportDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ScooterReportKmPauseDTO implements Serializable {

    private long id;
    private double kmTraveled;
    private double pauseTime;
}
