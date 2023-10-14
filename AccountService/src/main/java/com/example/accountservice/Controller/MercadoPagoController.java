package com.example.accountservice.Controller;

import com.example.accountservice.Model.MercadoPago;
import com.example.accountservice.Service.Interface.MercadoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mercadoPago")
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping("/add")
    public ResponseEntity<MercadoPago> mercadoPago(@RequestBody MercadoPago mercadoPago){
        return ResponseEntity.ok(mercadoPagoService.addMp(mercadoPago));
    }
}
