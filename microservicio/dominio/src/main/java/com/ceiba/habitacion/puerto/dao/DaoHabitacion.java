package com.ceiba.habitacion.puerto.dao;

import com.ceiba.habitacion.modelo.dto.DtoHabitacion;

import java.util.List;

public interface DaoHabitacion {
    DtoHabitacion obtenerPorId(Long id);
    List<DtoHabitacion> obtenerHabitacionDisponibles();
    List<DtoHabitacion> listar();
}
