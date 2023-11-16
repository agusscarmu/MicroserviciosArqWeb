package com.example.accountservice.Controller;

import com.example.accountservice.Model.MercadoPago;
import com.example.accountservice.Service.Interface.MercadoPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mercadoPago")
public class MercadoPagoController {


    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping("/add")
    public ResponseEntity<String> mercadoPago(@RequestBody MercadoPago mercadoPago){
        return mercadoPagoService.addMp(mercadoPago);
    }
}
