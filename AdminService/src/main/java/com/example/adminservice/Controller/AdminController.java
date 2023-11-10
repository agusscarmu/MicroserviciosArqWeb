package com.example.adminservice.Controller;

import com.example.adminservice.Model.Admin;

import com.example.adminservice.Model.Role;
import com.example.adminservice.Security.AuthorityConstant;
import com.example.adminservice.Service.Interface.AdminService;
import com.example.adminservice.dto.AuthRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    AdminService adminService;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PutMapping("/changeAccountStatus")
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

    static class JWTToken {
        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
