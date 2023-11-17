package com.example.monitoringservice.Service.Interface;

import com.example.monitoringservice.Model.User;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AdminService {

    ResponseEntity<String> changeAccountStatus(String id, boolean status);

    List<Serializable> reportByTravels(int travels);

    Double getTotalFactured(Integer m1, Integer m2, Integer y);

    Serializable getStatus();

    ResponseEntity<String> updatePrice(Double price, Long pauseLimit, Double extraPricePerMinute, Date date, String url);

}
