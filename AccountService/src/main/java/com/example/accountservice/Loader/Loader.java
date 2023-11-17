package com.example.accountservice.Loader;

import com.example.accountservice.Model.Account;
import com.example.accountservice.Model.MercadoPago;
import com.example.accountservice.Model.User;
import com.example.accountservice.Service.Interface.AccountService;
import com.example.accountservice.Service.Interface.MercadoPagoService;
import com.example.accountservice.Service.Interface.UserService;
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
    private UserService userService;

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @Autowired
    private AccountService accountService;


    private void loadUser(){
        try(CSVReader reader = new CSVReader(new FileReader("./src/main/java/com/example/accountservice/Loader/Csvs/user.csv"))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                User user = new User(line[0], line[1], line[2], line[3], line[4]);
                userService.addUser(user);

                System.out.println("User added");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void loadMercadoPago(){
        try(CSVReader reader = new CSVReader(new FileReader("./src/main/java/com/example/accountservice/Loader/Csvs/mercadoPago.csv"))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                MercadoPago mp = new MercadoPago(line[0], Double.parseDouble(line[1]));
                mercadoPagoService.addMp(mp);

                System.out.println("MercadoPago added");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void loadAccount(){
        try(CSVReader reader = new CSVReader(new FileReader("./src/main/java/com/example/accountservice/Loader/Csvs/account.csv"))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                Account account = new Account(line[0], null, new ArrayList<>(), new Date(), true);
                MercadoPago mp = mercadoPagoService.findMercadoPagoById(line[1]);
                account.setMercadoPago(mp);
                User user = userService.findUserById(line[2]);
                account.addUser(user);
                accountService.addAccount(account);

                System.out.println("Account added");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loader running");
        loadUser();
        loadMercadoPago();
        loadAccount();
    }
}
