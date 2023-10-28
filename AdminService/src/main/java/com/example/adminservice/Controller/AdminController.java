package com.example.adminservice.Controller;

import com.example.adminservice.Model.Admin;

import com.example.adminservice.Service.Interface.AdminService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/add")
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin){
        return ResponseEntity.ok(adminService.addAdmin(admin).toString());
    }

    @RequestMapping(value = "/changeAccountStatus", method = RequestMethod.PUT)
    public ResponseEntity<String> changeAccountStatus(@RequestParam("id") long id, @RequestParam("status") boolean status){
        return ResponseEntity.ok(adminService.changeAccountStatus(id, status).toString());
    }

    @RequestMapping(value="/scooter/reportBy", method = RequestMethod.GET)
    public List<Serializable> reportByTravels(@RequestParam("travels") int travels){
        return adminService.reportByTravels(travels);
    }

    @RequestMapping(value = "/totalFactured", method = RequestMethod.GET)
    public ResponseEntity<Double> getTotalFactured(@RequestParam(required = false) Integer month1, @RequestParam(required = false) Integer month2, @RequestParam(required = false) Integer year){
        return ResponseEntity.ok(adminService.getTotalFactured(month1,month2,year));
    }

    @RequestMapping(value = "/scooter/status", method = RequestMethod.GET)
    public Serializable getStatus(){
        return adminService.getStatus();
    }

    @RequestMapping(value = "/travel/updatePrice", method = RequestMethod.POST)
    public String addDataTravel(HttpServletRequest request,
            @RequestParam(required = false) Double price, @RequestParam(required = false) Long pauseLimit, @RequestParam(required = false) Double extraPricePerMinute, @RequestParam(required = false) Date date){

        String queryString = "?"+request.getQueryString();

        adminService.updatePrice(price, pauseLimit, extraPricePerMinute, date, queryString);
        return "Price updated";
    }


}
