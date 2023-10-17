package com.example.maintenanceservice.Service;

import com.example.maintenanceservice.Model.ScooterReport;
import com.example.maintenanceservice.Repository.ScooterReportRepository;
import com.example.maintenanceservice.Service.Interface.ScooterReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScooterReportServiceImpl implements ScooterReportService {

    @Autowired
    private ScooterReportRepository scooterReportRepository;


    @Override
    public boolean existsById(Long id) {
        return scooterReportRepository.existsById(id);
    }

    @Override
    public void saveReport(Long id, int usageTime, int pauseTime) {
        if(scooterReportRepository.existsById(id)){
            ScooterReport sR = scooterReportRepository.findById(id).get();
            sR.setUsageTimeWithoutPause(sR.getUsageTimeWithoutPause()+usageTime-pauseTime);
            sR.setUsageTimeWithPause(sR.getUsageTimeWithPause()+usageTime);
            scooterReportRepository.save(sR);
        }else{
            ScooterReport sR = new ScooterReport(id, usageTime-pauseTime, usageTime);
            scooterReportRepository.save(sR);
        }
    }

}
