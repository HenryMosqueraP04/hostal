package com.ceiba.habitacion.consulta;

import com.ceiba.habitacion.modelo.dto.DtoHabitacion;
import com.ceiba.habitacion.servicio.ServicioListarHabitacion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarHabitacion {

    private final ServicioListarHabitacion servicioListarHabitacion;

    public ManejadorListarHabitacion(ServicioListarHabitacion servicioListarHabitacion) {
        this.servicioListarHabitacion = servicioListarHabitacion;
    }

    public List<DtoHabitacion> listarHabitacionesDisponibles() {
        return this.servicioListarHabitacion.listarHabitacionesDisponibles();
    }

}
