package com.ceiba.tipo_habitacion.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.tipo_habitacion.modelo.dto.DtoTipoHabitacion;
import com.ceiba.tipo_habitacion.puerto.dao.DaoTipoHabitacion;
import com.ceiba.tipo_habitacion.puerto.repositorio.RepositorioTipoHabitacion;

public class ServicioListarTipoHabitacion {

    private static final String TIPO_HABITACION_NO_ENCONTRADA = "El registro del tipo de habitación no fue encontrado";

    private final DaoTipoHabitacion daoTipoHabitacion;
    private final RepositorioTipoHabitacion repositorioTipoHabitacion;

    public ServicioListarTipoHabitacion(DaoTipoHabitacion daoTipoHabitacion, RepositorioTipoHabitacion repositorioTipoHabitacion) {
        this.daoTipoHabitacion = daoTipoHabitacion;
        this.repositorioTipoHabitacion = repositorioTipoHabitacion;
    }

    public DtoTipoHabitacion obtenerTipoHabitacionPorId(Long id) {
        this.validarExistenciaTipoHabitacionPorId(id);
        return this.daoTipoHabitacion.obtenerPorId(id);
    }

    private void validarExistenciaTipoHabitacionPorId(Long id){
        boolean existe = this.repositorioTipoHabitacion.existePorId(id);
        if(!existe) throw new ExcepcionSinDatos(TIPO_HABITACION_NO_ENCONTRADA);
    }
}
