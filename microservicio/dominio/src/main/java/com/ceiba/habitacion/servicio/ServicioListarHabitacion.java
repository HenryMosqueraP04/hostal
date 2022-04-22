package com.ceiba.habitacion.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.habitacion.modelo.dto.DtoHabitacion;
import com.ceiba.habitacion.puerto.dao.DaoHabitacion;
import com.ceiba.habitacion.puerto.repositorio.RepositorioHabitacion;

import java.util.List;

public class ServicioListarHabitacion {

    private static final String HABITACION_NO_ENCONTRADA = "El registro de la habitación no fue encontrado";
    private static final String HABITACION_OCUPADA = "La habitación se encuentra ocupada";

    private final DaoHabitacion daoHabitacion;
    private final RepositorioHabitacion repositorioHabitacion;

    public ServicioListarHabitacion(DaoHabitacion daoHabitacion, RepositorioHabitacion repositorioHabitacion) {
        this.daoHabitacion = daoHabitacion;
        this.repositorioHabitacion = repositorioHabitacion;
    }

    private void validarExistenciaHabitacionPorId(Long id){
        boolean existe = this.repositorioHabitacion.existePorId(id);
        if(!existe) {
            throw new ExcepcionSinDatos(HABITACION_NO_ENCONTRADA);
        }
    }

    public DtoHabitacion obtenerHabitacionPorId(Long id) {
        this.validarExistenciaHabitacionPorId(id);
        return this.daoHabitacion.obtenerPorId(id);
    }

    public List<DtoHabitacion> listar(){
        return this.daoHabitacion.listar();
    }

    public List<DtoHabitacion> listarHabitacionesDisponibles(){
        return this.daoHabitacion.obtenerHabitacionDisponibles();
    }

    public void validarHabitacionOcupadaPorId(Long id){
        boolean ocupada = this.repositorioHabitacion.esHabitacionOcupadaPorId(id);
        if(ocupada){
            throw new ExcepcionDuplicidad(HABITACION_OCUPADA);
        }
    }


}
