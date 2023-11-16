package com.example.dataservice.Service;

import com.example.dataservice.Model.DataTravel;
import com.example.dataservice.Repository.DataTravelRepository;
import com.example.dataservice.Service.Interface.DataTravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

@Service
public class DataTravelServiceImpl implements DataTravelService {

    @Autowired
    private DataTravelRepository dataTravelRepository;

    @Override
    public ResponseEntity<String> addDataTravel(Double price, Long pauseLimit, Double extraPricePerMinute, Date date) {
        if(dataTravelRepository.count()==0){
            dataTravelRepository.save(new DataTravel(price, pauseLimit, extraPricePerMinute, date));
        }else{
            DataTravel dataTravel = dataTravelRepository.findLastDataTravel();
            Double newPricePerMinute = price != null ? price: dataTravel.getPricePerMinute();
            Long newPauseLimit = pauseLimit != null ? pauseLimit: dataTravel.getPauseLimit();
            Double newExtraPricePerMinute = extraPricePerMinute != null ? extraPricePerMinute: dataTravel.getExtraPricePerMinute();
            Date newLastPriceChange = date != null ? date : dataTravel.getAppliedDate();
            dataTravelRepository.save(new DataTravel(newPricePerMinute, newPauseLimit, newExtraPricePerMinute, newLastPriceChange));
        }
        return ResponseEntity.ok("DataTravel added");
    }

    @Override
    public Serializable getLastUpdate() {
        return dataTravelRepository.findLastDataTravel();
    }
}
