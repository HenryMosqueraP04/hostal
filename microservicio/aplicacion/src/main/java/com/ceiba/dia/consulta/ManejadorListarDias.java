package com.ceiba.dia.consulta;

import com.ceiba.dia.modelo.dto.DtoDia;
import com.ceiba.dia.servicio.ServicioListarDia;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarDias {

    private final ServicioListarDia servicioListarDia;

    public ManejadorListarDias(ServicioListarDia servicioListarDia) {
        this.servicioListarDia = servicioListarDia;
    }

    public List<DtoDia> ejecutar() {
        return this.servicioListarDia.ejecutar();
    }

}
