package com.ceiba.reserva.testdatabuilder;


import com.ceiba.reserva.comando.ComandoReserva;

import java.time.LocalDateTime;

public class ComandoReservaTestDataBuilder {

    private Long id;
    private Long usuarioId;
    private Long habitacionId;
    private Long tipoPagoId;
    private boolean estado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public ComandoReservaTestDataBuilder() {

        LocalDateTime fechaInicio = LocalDateTime.now();
        LocalDateTime fechaFin = fechaInicio.plusHours(48);

        this.usuarioId = 1L;
        this.habitacionId = 1L;
        this.tipoPagoId = 1L;
        this.estado = true;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }


    public ComandoReservaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoReservaTestDataBuilder conUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
        return this;
    }

    public ComandoReservaTestDataBuilder conHabitacionId(Long habitacionId) {
        this.habitacionId = habitacionId;
        return this;
    }

    public ComandoReservaTestDataBuilder conTipoPagoId(Long tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
        return this;
    }

    public ComandoReservaTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public ComandoReservaTestDataBuilder conFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public ComandoReservaTestDataBuilder conFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public ComandoReserva build() {

        return new ComandoReserva(this.id, this.usuarioId, this.habitacionId, this.tipoPagoId, this.estado,
                this.fechaInicio, this.fechaFin);

    }
}
