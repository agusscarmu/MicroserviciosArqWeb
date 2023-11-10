package com.monopatin.authservice.service.dto.user.response;

import com.monopatin.authservice.entity.mysql.User;
import lombok.Data;

@Data
public class UserResponseDTO {

    private final long id;
    private final String nombre;
    private final String apellido;
    private final String email;

    public UserResponseDTO( User user ){
        this.id = user.getId();
        this.nombre = user.getNombre();
        this.apellido = user.getApellido();
        this.email = user.getEmail();
    }

}
