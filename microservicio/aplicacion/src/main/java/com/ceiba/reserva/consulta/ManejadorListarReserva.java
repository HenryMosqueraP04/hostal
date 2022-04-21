package com.ceiba.reserva.consulta;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.servicio.ServicioListarReserva;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarReserva {

    private final ServicioListarReserva servicioListarReserva;

    public ManejadorListarReserva(ServicioListarReserva servicioListarReserva) {
        this.servicioListarReserva = servicioListarReserva;
    }

    public List<DtoReserva> ejecutar(){
        return this.servicioListarReserva.listar();
    }

    public List<DtoReserva> listarPorUsuarioId(Long usuarioId) {
        return this.servicioListarReserva.listarPorUsuarioId(usuarioId);
    }
    public DtoReserva obtenerPorId(Long id) {
        return this.servicioListarReserva.obtenerPorId(id);
    }


}
