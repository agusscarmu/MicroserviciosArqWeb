package com.example.adminservice.Service.Interface;

import com.example.adminservice.Model.Admin;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

public interface AdminService {
    String addAdmin(Admin admin);

    String changeAccountStatus(long id, boolean status);

    List<Serializable> reportByTravels(int travels);

    Double getTotalFactured(Integer m1, Integer m2, Integer y);

    Serializable getStatus();

    String updatePrice(float price, String dateParam);
}
