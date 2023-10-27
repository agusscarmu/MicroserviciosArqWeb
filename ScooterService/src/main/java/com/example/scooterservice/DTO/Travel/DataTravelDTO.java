package com.example.scooterservice.DTO.Travel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTravelDTO implements Serializable{
    private long id;

    private Double pricePerMinute;
    private Long pauseLimit;
    private Double extraPricePerMinute;


    private Date appliedDate;
}
