package com.monopatin.authservice.entity.mongodb;


import com.monopatin.authservice.service.dto.producto.ProductoDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//La anotacion @Document Esto seria similar a la anotacion @Entity de JPA ( Mysql).

@Document
@Data
public class Producto {

    @Id
    private String id;

    private String nombre;
    private String codigo;

    public Producto(String nombre, String codigo ) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

}
