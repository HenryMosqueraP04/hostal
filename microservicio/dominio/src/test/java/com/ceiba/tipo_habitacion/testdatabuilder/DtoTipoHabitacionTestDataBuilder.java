package com.ceiba.tipo_habitacion.testdatabuilder;

import com.ceiba.tipo_habitacion.modelo.dto.DtoTipoHabitacion;

import java.math.BigDecimal;

public class DtoTipoHabitacionTestDataBuilder {


    private Long id;
    private String nombre;
    private BigDecimal valor;
    private boolean estado;

    public DtoTipoHabitacionTestDataBuilder() {

        this.nombre = "PERSONAL";
        this.valor = new BigDecimal("8500");
        this.estado = true;
    }


    public DtoTipoHabitacionTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoTipoHabitacionTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public DtoTipoHabitacionTestDataBuilder conValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }


    public DtoTipoHabitacionTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public DtoTipoHabitacion build() {

        return new DtoTipoHabitacion(this.id, this.nombre, this.valor, this.estado);

    }

}
