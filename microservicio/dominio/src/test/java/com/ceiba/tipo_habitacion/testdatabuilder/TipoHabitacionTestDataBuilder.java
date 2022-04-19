package com.ceiba.tipo_habitacion.testdatabuilder;

import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.tipo_habitacion.modelo.entidad.TipoHabitacion;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TipoHabitacionTestDataBuilder {


    private Long id;
    private String nombre;
    private BigDecimal valor;
    private boolean estado;

    public TipoHabitacionTestDataBuilder() {

        this.nombre = "PERSONAL";
        this.valor = new BigDecimal("8500");
        this.estado = true;
    }


    public TipoHabitacionTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public TipoHabitacionTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public TipoHabitacionTestDataBuilder conValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }


    public TipoHabitacionTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public TipoHabitacion build() {

        return new TipoHabitacion(this.id, this.nombre, this.valor, this.estado);

    }

}
