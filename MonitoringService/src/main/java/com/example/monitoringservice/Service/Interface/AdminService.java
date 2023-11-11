package com.example.monitoringservice.Service.Interface;

import com.example.monitoringservice.Model.User;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AdminService {

    String changeAccountStatus(String id, boolean status);

    List<Serializable> reportByTravels(int travels);

    Double getTotalFactured(Integer m1, Integer m2, Integer y);

    Serializable getStatus();

    void updatePrice(Double price, Long pauseLimit, Double extraPricePerMinute, Date date, String url);

    Optional<Object> findByUsername(String username);

    void addAdmin(User user);
}
