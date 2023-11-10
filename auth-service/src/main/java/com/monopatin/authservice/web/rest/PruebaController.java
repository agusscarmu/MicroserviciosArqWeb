package com.monopatin.authservice.web.rest;

import com.monopatin.authservice.service.AuthorityConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/prueba")
public class PruebaController {

    @GetMapping("")
    @PreAuthorize( "hasAuthority( \"" + AuthorityConstant.ADMIN + "\" )" )
    public List<String> prueba(){
        return List.of( "jajaja" );
    }

}
