package com.ceiba.habitacion.testdatabuilder;

import com.ceiba.habitacion.modelo.entidad.Habitacion;

public class HabitacionTestDataBuilder {

    private Long id;
    private String nombre;
    private Long tipoHabitacionId;
    private boolean estado;

    public HabitacionTestDataBuilder() {
        this.nombre = "HABITACION PERSONAL";
        this.tipoHabitacionId = 1L;
        this.estado = true;
    }


    public HabitacionTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public HabitacionTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public HabitacionTestDataBuilder conTipoHabitacionId(Long tipoHabitacionId) {
        this.tipoHabitacionId = tipoHabitacionId;
        return this;
    }

    public HabitacionTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public Habitacion build() {
        return new Habitacion(this.id,this.nombre, this.tipoHabitacionId,this.estado);
    }
}
