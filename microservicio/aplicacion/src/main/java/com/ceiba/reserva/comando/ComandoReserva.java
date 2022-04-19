package com.ceiba.reserva.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoReserva {

    private Long id;
    private Long usuarioId;
    private Long habitacionId;
    private Long tipoPagoId;
    private boolean estado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;



}
