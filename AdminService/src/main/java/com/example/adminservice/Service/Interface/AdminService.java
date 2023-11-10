package com.example.adminservice.Service.Interface;

import com.example.adminservice.Model.Admin;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AdminService {

    String changeAccountStatus(long id, boolean status);

    List<Serializable> reportByTravels(int travels);

    Double getTotalFactured(Integer m1, Integer m2, Integer y);

    Serializable getStatus();

    void updatePrice(Double price, Long pauseLimit, Double extraPricePerMinute, Date date, String url);

    Optional<Object> findByUsername(String username);

    void addAdmin(Admin admin);
}
