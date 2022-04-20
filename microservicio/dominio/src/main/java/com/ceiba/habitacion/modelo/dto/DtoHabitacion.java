package com.ceiba.habitacion.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class DtoHabitacion {
    private Long id;
    private String nombre;
    private Long tipoHabitacionId;
    private boolean estado;
}


