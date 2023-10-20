package com.example.maintenanceservice.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScooterReport {

    @Id
    @Column(name = "scooter_id")
    private long id;
    private double usageTimeWithoutPause;
    private double usageTimeWithPause;
    private double kmTraveled;
    private long totalRides;
    private double totalPauseTime;
}
