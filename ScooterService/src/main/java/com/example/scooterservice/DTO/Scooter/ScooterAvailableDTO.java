package com.example.scooterservice.DTO.Scooter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ScooterAvailableDTO implements Serializable {
    private long id;
    private String location;
}
