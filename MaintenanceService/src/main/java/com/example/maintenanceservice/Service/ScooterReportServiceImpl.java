package com.example.maintenanceservice.Service;

import com.example.maintenanceservice.Model.ScooterReport;
import com.example.maintenanceservice.Repository.ScooterReportRepository;
import com.example.maintenanceservice.Service.Interface.ScooterReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ScooterReportServiceImpl implements ScooterReportService {

    @Autowired
    private ScooterReportRepository scooterReportRepository;


    @Override
    public boolean existsById(Long id) {
        return scooterReportRepository.existsById(id);
    }

    @Override
    public void saveReport(Long id, double usageTime, double pauseTime, double km) {
        if(scooterReportRepository.existsById(id)){
            ScooterReport sR = scooterReportRepository.findById(id).get();
            sR.setUsageTimeWithoutPause(sR.getUsageTimeWithoutPause()+usageTime-pauseTime);
            sR.setUsageTimeWithPause(sR.getUsageTimeWithPause()+usageTime);
            sR.setKmTraveled(sR.getKmTraveled()+km);
            sR.setTotalPauseTime(sR.getTotalPauseTime()+pauseTime);
            sR.setTotalRides(sR.getTotalRides()+1);
            scooterReportRepository.save(sR);
        }else{
            ScooterReport sR = new ScooterReport(id, usageTime-pauseTime, usageTime, km, 1,pauseTime);
            scooterReportRepository.save(sR);
        }
    }

    @Override
    public List<Serializable> getReport(String filter) {
        switch (filter){
            case "km":
                return scooterReportRepository.getReportByKm();
            case "usageTimeWithPause":
                return scooterReportRepository.getReportByUsageTimeWithPause();
            case "usageTimeWithoutPause":
                return scooterReportRepository.getReportByUsageTimeWithoutPause();
            default:
                return null;
        }
    }

    @Override
    public List<Serializable> getReportWithPauseTime() {
        return scooterReportRepository.getReportByKmAndUsageTimeWithPause();
    }

    @Override
    public List<Serializable> getReportByTravels(long x) {
        return scooterReportRepository.getReportByTravels(x);
    }

}
