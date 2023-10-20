package com.example.scooterservice.DTO.Scooter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ScooterQuantityMaintenanceVsInUseDTO implements Serializable {
    public long currentlyInUse;
    public long currentlyUnderMaintenance;
}
