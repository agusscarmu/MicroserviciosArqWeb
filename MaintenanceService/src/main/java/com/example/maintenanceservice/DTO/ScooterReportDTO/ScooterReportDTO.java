package com.example.maintenanceservice.DTO.ScooterReportDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ScooterReportDTO implements Serializable {
    private long id;
    private double data;
}
