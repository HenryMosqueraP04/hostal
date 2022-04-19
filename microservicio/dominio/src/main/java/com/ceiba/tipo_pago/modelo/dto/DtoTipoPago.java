package com.ceiba.tipo_pago.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoTipoPago {

    private Long id;
    private String nombre;
    private double porcentajeImpuesto;
    private boolean estado;
}
