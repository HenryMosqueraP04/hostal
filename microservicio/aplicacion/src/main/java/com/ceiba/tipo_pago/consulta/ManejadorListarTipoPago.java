package com.ceiba.tipo_pago.consulta;

import com.ceiba.tipo_pago.modelo.dto.DtoTipoPago;
import com.ceiba.tipo_pago.servicio.ServicioListarTipoPago;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarTipoPago {

    private final ServicioListarTipoPago servicioListarTipoPago;

    public ManejadorListarTipoPago(ServicioListarTipoPago servicioListarTipoPago) {
        this.servicioListarTipoPago = servicioListarTipoPago;
    }

    public List<DtoTipoPago> ejecutar(){
        return this.servicioListarTipoPago.ejecutar();
    }
}
