package com.example.dataservice.Service.Interface;

import java.io.Serializable;
import java.util.Date;

public interface DataTravelService {
    void addDataTravel(Double price, Long pauseLimit, Double extraPricePerMinute, Date date);

    Serializable getLastUpdate();
}
