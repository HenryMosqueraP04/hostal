package com.ceiba.habitacion.puerto.repositorio;

import java.time.LocalDateTime;

public interface RepositorioHabitacion {
    boolean existePorId(Long id);
    boolean esHabitacionOcupada(Long id, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
