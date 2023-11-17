package com.example.scooterservice.Loader;

import com.example.scooterservice.Model.Scooter;
import com.example.scooterservice.Model.Station;
import com.example.scooterservice.Service.Interface.ScooterService;
import com.example.scooterservice.Service.Interface.StationService;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

@Component
public class Loader implements CommandLineRunner {

    @Autowired
    private ScooterService scooterService;

    @Autowired
    private StationService stationService;


    private void loadStation(){
        try(CSVReader reader = new CSVReader(new FileReader("./src/main/java/com/example/scooterservice/Loader/Csvs/station.csv"))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                Station station = new Station();
                station.setId(Long.parseLong(line[0]));
                station.setLocation(line[1]);
                stationService.addStation(station);
                System.out.println("Station added");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void loadScooter(){
        try(CSVReader reader = new CSVReader(new FileReader("./src/main/java/com/example/scooterservice/Loader/Csvs/scooter.csv"))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                Scooter scooter = new Scooter();
                scooter.setId(Long.parseLong(line[4]));
                scooter.setUnderMaintenance(Boolean.parseBoolean(line[0]));
                scooter.setInUse(Boolean.parseBoolean(line[1]));
                scooter.setLocation(line[2]);
                scooter.setStationId(Long.parseLong(line[3]));
                scooterService.addScooter(scooter);
                System.out.println("Scooter added");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loader running");
        loadStation();
        loadScooter();
    }
}
