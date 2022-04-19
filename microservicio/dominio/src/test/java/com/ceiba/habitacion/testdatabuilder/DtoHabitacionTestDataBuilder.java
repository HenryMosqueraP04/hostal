package com.ceiba.habitacion.testdatabuilder;

import com.ceiba.habitacion.modelo.dto.DtoHabitacion;

public class DtoHabitacionTestDataBuilder {

    private Long id;
    private String nombre;
    private Long tipoHabitacionId;
    private boolean estado;

    public DtoHabitacionTestDataBuilder() {
        this.nombre = "HABITACION PERSONAL";
        this.tipoHabitacionId = 1L;
        this.estado = true;
    }


    public DtoHabitacionTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoHabitacionTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public DtoHabitacionTestDataBuilder conTipoHabitacionId(Long tipoHabitacionId) {
        this.tipoHabitacionId = tipoHabitacionId;
        return this;
    }

    public DtoHabitacionTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public DtoHabitacion build() {
        return new DtoHabitacion(this.id,this.nombre, this.tipoHabitacionId,this.estado);
    }

}
