package com.example.accountservice.Service.Interface;

import com.example.accountservice.Model.MercadoPago;
import org.springframework.http.ResponseEntity;

public interface MercadoPagoService {

    ResponseEntity<String> addMp(MercadoPago mp);

    MercadoPago findMercadoPagoById(String id);
}
