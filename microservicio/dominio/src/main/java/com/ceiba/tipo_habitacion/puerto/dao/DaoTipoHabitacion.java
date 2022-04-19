package com.ceiba.tipo_habitacion.puerto.dao;

import com.ceiba.tipo_habitacion.modelo.dto.DtoTipoHabitacion;

public interface DaoTipoHabitacion {
    DtoTipoHabitacion obtenerPorId(Long id);
}
