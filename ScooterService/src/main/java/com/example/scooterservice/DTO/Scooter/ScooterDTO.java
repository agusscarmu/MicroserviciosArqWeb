package com.example.scooterservice.DTO.Scooter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ScooterDTO implements Serializable {

    private long id;
    private boolean underMaintenance;
    private boolean inUse;
    private String location;

}
