package com.monopatin.authservice.repository.mongodb;

import com.monopatin.authservice.entity.mongodb.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoMongoRepository extends MongoRepository<Producto, String> {
}
