package com.example.dataservice.Service.Interface;

import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Date;

public interface DataTravelService {
    ResponseEntity<String> addDataTravel(Double price, Long pauseLimit, Double extraPricePerMinute, Date date);

    Serializable getLastUpdate();
}
