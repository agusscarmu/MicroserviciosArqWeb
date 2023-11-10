package com.monopatin.authservice.service.producto;

import com.monopatin.authservice.entity.mongodb.Producto;
import com.monopatin.authservice.repository.mongodb.ProductoMongoRepository;
import com.monopatin.authservice.service.dto.producto.ProductoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoMongoRepository productoMongoRepository;

    public List<ProductoDTO> findAll(){
        return this.productoMongoRepository.findAll().stream().map( p -> new ProductoDTO( p.getId(), p.getNombre(), p.getCodigo() ) ).toList();
    }

    public String save(ProductoDTO request ){
        final var producto = this.productoMongoRepository.save( new Producto( request.getNombre(), request.getCodigo() ) );
        return producto.getId();
    }

    public Producto findById(String id) {
        return this.productoMongoRepository.findById( id ).orElseThrow();
    }
}
