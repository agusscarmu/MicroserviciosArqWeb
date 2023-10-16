package com.example.scooterservice.DTO.Travel;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
public class TravelDTO implements Serializable {
    private Long accountId;
    private Long scooterId;
    private Date createdAt;
    private Date finishedAt;
    private boolean pause;
    private Date pauseStartedAt;
    private Date pauseFinishedAt;
}
