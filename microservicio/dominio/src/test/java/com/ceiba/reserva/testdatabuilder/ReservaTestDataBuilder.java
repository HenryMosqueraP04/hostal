package com.ceiba.reserva.testdatabuilder;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservaTestDataBuilder {

    private Long id;
    private Long usuarioId;
    private Long habitacionId;
    private Long tipoPagoId;
    private BigDecimal valor;
    private boolean estado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public ReservaTestDataBuilder() {

        LocalDateTime fechaInicio = LocalDateTime.now();
        LocalDateTime fechaFin = fechaInicio.plusHours(5);

        this.usuarioId = 1L;
        this.habitacionId = 1L;
        this.tipoPagoId = 1L;
        this.estado = true;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }


    public ReservaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ReservaTestDataBuilder conUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
        return this;
    }

    public ReservaTestDataBuilder conHabitacionId(Long habitacionId) {
        this.habitacionId = habitacionId;
        return this;
    }

    public ReservaTestDataBuilder conTipoPagoId(Long tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
        return this;
    }


    public ReservaTestDataBuilder conValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public ReservaTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public ReservaTestDataBuilder conFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public ReservaTestDataBuilder conFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public Reserva build() {

        return new Reserva(this.id, this.usuarioId, this.habitacionId, this.tipoPagoId, this.estado,
                this.fechaInicio, this.fechaFin);

    }
}
