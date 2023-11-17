package com.example.monitoringservice.Loader;

import com.example.monitoringservice.Service.Interface.AuthService;
import com.example.monitoringservice.dto.RegisterRequestDTO;
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
    private AuthService authService;

    private void loadAccount(){
        try(CSVReader reader = new CSVReader(new FileReader("./src/main/java/com/example/monitoringservice/Loader/Csvs/account.csv"))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
                registerRequestDTO.setUsername(line[0]);
                registerRequestDTO.setPassword(line[1]);
                registerRequestDTO.setAdmin(Boolean.parseBoolean(line[2]));
                registerRequestDTO.setMaintenance(Boolean.parseBoolean(line[3]));
                authService.register(registerRequestDTO);

                System.out.println("Account added: \n" +
                        "----------------\n"+
                        "Username: " + line[0] + "\n" +
                        "Password: " + line[1] + "\n" +
                        "Role: " + roles(registerRequestDTO) + "\n");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loader running");
        loadAccount();
    }

    private String roles(RegisterRequestDTO registerRequestDTO){
        ArrayList<String> roles = new ArrayList<>();
        if(registerRequestDTO.isAdmin()){
            roles.add("ADMIN");
        }
        if(registerRequestDTO.isMaintenance()){
            roles.add("MAINTENANCE");
        }
        return roles.toString();
    }
}
