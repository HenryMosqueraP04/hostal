package com.ceiba.tipo_habitacion.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class DtoTipoHabitacion {

    private Long id;
    private String nombre;
    private BigDecimal valor;
    private boolean estado;
}
