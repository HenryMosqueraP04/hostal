package com.ceiba.reserva.puerto.dao;

import com.ceiba.reserva.modelo.dto.DtoReserva;

import java.util.List;

public interface DaoReserva {
    DtoReserva obtenerPorId(Long id);
    List<DtoReserva> listarPorUsuarioId(Long usuarioId);
}
