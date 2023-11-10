package com.monopatin.authservice.web.rest.producto;

import com.monopatin.authservice.entity.mongodb.Producto;
import com.monopatin.authservice.service.dto.producto.ProductoDTO;
import com.monopatin.authservice.service.producto.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producto" )
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("")
    public List getList(){
        return this.productoService.findAll();
    }

    @GetMapping("/{id}")
    public Producto getList( @PathVariable String id ){
        return this.productoService.findById( id );
    }

    @PostMapping("")
    public ResponseEntity<String> save( @RequestBody ProductoDTO productoDTO ){
        final var id = this.productoService.save( productoDTO );
        return new ResponseEntity( id, HttpStatus.CREATED );
    }




}
