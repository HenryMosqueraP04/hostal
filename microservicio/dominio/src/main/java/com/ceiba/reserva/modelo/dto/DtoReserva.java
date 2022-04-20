package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoReserva {

    private Long id;
    private Long usuarioId;
    private Long habitacionId;
    private Long tipoPagoId;
    private BigDecimal valor;
    private boolean estado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    private String nombreHabitacion;
    private String nombreTipoHabitacion;
    private String nombreTipoPago;
    private String nombreUsuario;

}
