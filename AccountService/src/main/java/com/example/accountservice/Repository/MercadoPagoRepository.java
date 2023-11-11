package com.example.accountservice.Repository;

import com.example.accountservice.Model.MercadoPago;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("mercadoPagoRepository")
public interface MercadoPagoRepository extends MongoRepository<MercadoPago, String> {


    MercadoPago findMercadoPagoById(String id);
}
