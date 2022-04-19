package com.ceiba.usuario.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoUsuario {

    private Long id;
    private String nombreCompleto;
    private String numeroDocumento;
    private boolean estado;

}
