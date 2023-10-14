package com.example.accountservice.Service;

import com.example.accountservice.Model.MercadoPago;
import com.example.accountservice.Repository.MercadoPagoRepository;
import com.example.accountservice.Service.Interface.MercadoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoServiceImpl implements MercadoPagoService {

    @Autowired
    private MercadoPagoRepository mercadoPagoRepository;

    @Override
    public MercadoPago addMp(MercadoPago mp) {
        return mercadoPagoRepository.save(mp);
    }
}
