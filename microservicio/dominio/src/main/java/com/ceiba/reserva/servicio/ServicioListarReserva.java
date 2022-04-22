package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.util.List;

public class ServicioListarReserva {

    private static final String RESERVA_NO_ENCONTRADA = "El registro de la reserva no fue encontrado";

    private final DaoReserva daoReserva;
    private final RepositorioReserva repositorioReserva;

    public ServicioListarReserva(DaoReserva daoReserva, RepositorioReserva repositorioReserva) {
        this.daoReserva = daoReserva;
        this.repositorioReserva = repositorioReserva;
    }



    public List<DtoReserva> listar(){return this.daoReserva.listar();}

    public List<DtoReserva> listarPorUsuarioId(Long usuarioId) {
        return this.daoReserva.listarPorUsuarioId(usuarioId);
    }

    public DtoReserva obtenerPorId(Long id) {
        this.validarExistenciaPorId(id);
        return this.daoReserva.obtenerPorId(id);
    }

    private void validarExistenciaPorId(Long id){
        boolean existe = this.repositorioReserva.existePorId(id);
        if(!existe){
            throw new ExcepcionSinDatos(RESERVA_NO_ENCONTRADA);
        }
    }


}
