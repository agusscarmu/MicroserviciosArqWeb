package com.monopatin.authservice.service.dto.producto;

import com.monopatin.authservice.entity.mongodb.Producto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoDTO {

    private String id;
    private String nombre;
    private String codigo;

    public ProductoDTO( String id, String nombre, String codigo ){
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
    }
}
