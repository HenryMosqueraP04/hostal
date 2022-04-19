package com.ceiba.reserva.testdatabuilder;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DtoReservaTestDataBuilder {

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


    public DtoReservaTestDataBuilder() {

        LocalDateTime fechaInicio = LocalDateTime.now();
        LocalDateTime fechaFin = fechaInicio.plusHours(1);

        this.usuarioId = 1L;
        this.habitacionId = 1L;
        this.tipoPagoId = 1L;
        this.valor = new BigDecimal("0");
        this.estado = true;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombreHabitacion = "HABITACION PERSONAL";
        this.nombreTipoHabitacion = "PERSONAL";
        this.nombreTipoPago = "EFECTIVO";
        this.nombreUsuario = "HENRY MOSQUERA";
    }

    public DtoReservaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoReservaTestDataBuilder conUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
        return this;
    }

    public DtoReservaTestDataBuilder conHabitacionId(Long habitacionId) {
        this.habitacionId = habitacionId;
        return this;
    }

    public DtoReservaTestDataBuilder conTipoPagoId(Long tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
        return this;
    }


    public DtoReservaTestDataBuilder conValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public DtoReservaTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public DtoReservaTestDataBuilder conFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public DtoReservaTestDataBuilder conFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public DtoReservaTestDataBuilder conNombreHabitacion(String nombreHabitacion) {
        this.nombreHabitacion = nombreHabitacion;
        return this;
    }

    public DtoReservaTestDataBuilder conNombreTipoHabitacion(String nombreTipoHabitacion) {
        this.nombreTipoHabitacion = nombreTipoHabitacion;
        return this;
    }

    public DtoReservaTestDataBuilder conNombreTipoPago(String nombreTipoPago) {
        this.nombreTipoPago = nombreTipoPago;
        return this;
    }

    public DtoReservaTestDataBuilder conNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = DtoReservaTestDataBuilder.this.nombreUsuario;
        return this;
    }

    public DtoReserva build() {

        return new DtoReserva(this.id, this.usuarioId, this.habitacionId, this.tipoPagoId, this.valor, this.estado,
                this.fechaInicio, this.fechaFin, this.nombreHabitacion, this.nombreTipoHabitacion, this.nombreTipoPago, this.nombreUsuario);

    }
}
