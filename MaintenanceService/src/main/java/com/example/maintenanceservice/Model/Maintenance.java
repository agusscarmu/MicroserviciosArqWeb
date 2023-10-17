package com.example.maintenanceservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long scooterId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    public Maintenance(long scooterId) {
        this.scooterId = scooterId;
    }

    public void startMaintenance() {
        this.startDate = new Date();
    }
    public void finalizeMaintenance() {
        this.endDate = new Date();
    }
}
