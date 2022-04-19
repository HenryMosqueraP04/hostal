package com.ceiba.dia.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class DtoDia {

    private Long id;
    private String nombre;
    private BigDecimal valorPorHora ;
    private boolean estado;

}
