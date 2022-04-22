package com.ceiba.habitacion.controlador;


import com.ceiba.habitacion.consulta.ManejadorListarHabitacion;
import com.ceiba.habitacion.modelo.dto.DtoHabitacion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/habitaciones")
@Api(tags={"Controlador consulta habitaciones"})
@CrossOrigin
public class ConsultaControladorHabitacion {

    private final ManejadorListarHabitacion manejadorListarHabitacion;

    public ConsultaControladorHabitacion(ManejadorListarHabitacion manejadorListarHabitacion) {
        this.manejadorListarHabitacion = manejadorListarHabitacion;
    }

    @GetMapping
    @ApiOperation("Listar habitaciones disponibles")
    public List<DtoHabitacion> listarHabitacionesDisponibles() {
        return this.manejadorListarHabitacion.listarHabitacionesDisponibles();
    }

}
