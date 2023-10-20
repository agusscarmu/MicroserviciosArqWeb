package com.example.maintenanceservice.DTO.ScooterReportDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ScooterReportByTravelsDTO implements Serializable {
    public long id;
    public double totalKm;
    public long totalRides;

}
