package com.example.accountservice.Repository;

import com.example.accountservice.Model.MercadoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("mercadoPagoRepository")
public interface MercadoPagoRepository extends JpaRepository<MercadoPago, Long> {


}
