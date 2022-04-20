package com.ceiba.habitacion.modelo.entidad;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Habitacion {

    private static final String MENSAJE_NOMBRE_HABITACION_REQUERIDO = "Se debe ingresar el nombre de habitación";
    private static final String MENSAJE_TIPO_HABITACION_REQUERIDO = "Se debe ingresar el tipo de habitación";


    private Long id;
    private String nombre;
    private Long tipoHabitacionId;
    private boolean estado;

    public Habitacion(Long id, String nombre, Long tipoHabitacionId, boolean estado) {

        validarObligatorio(nombre, MENSAJE_NOMBRE_HABITACION_REQUERIDO);
        validarObligatorio(tipoHabitacionId, MENSAJE_TIPO_HABITACION_REQUERIDO);

        this.id = id;
        this.nombre = nombre;
        this.tipoHabitacionId = tipoHabitacionId;
        this.estado = estado;

    }
}

