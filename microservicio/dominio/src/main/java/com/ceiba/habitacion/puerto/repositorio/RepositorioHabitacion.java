package com.ceiba.habitacion.puerto.repositorio;

public interface RepositorioHabitacion {
    boolean existePorId(Long id);
    boolean esHabitacionOcupadaPorId(Long id);
}
