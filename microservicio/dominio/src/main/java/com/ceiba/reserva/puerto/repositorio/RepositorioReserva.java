package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;

public interface RepositorioReserva {

    Long crear(Reserva reserva);

    void actualizar(Reserva reserva);

    void eliminar(Long id);

    boolean existePorId(Long id);

}
