package com.ceiba.habitacion.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class DtoHabitacion {
    private Long id;
    private String nombre;
    private Long tipoHabitacionId;
    private boolean estado;
}


