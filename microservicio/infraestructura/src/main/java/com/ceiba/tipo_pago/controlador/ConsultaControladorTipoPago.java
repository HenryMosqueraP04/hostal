package com.ceiba.tipo_pago.controlador;


import com.ceiba.tipo_pago.consulta.ManejadorListarTipoPago;
import com.ceiba.tipo_pago.modelo.dto.DtoTipoPago;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipo-pagos")
@Api(tags={"Controlador consulta tipo de pagos"})
public class ConsultaControladorTipoPago {

    private final ManejadorListarTipoPago manejadorListarTipoPago;

    public ConsultaControladorTipoPago(ManejadorListarTipoPago manejadorListarTipoPago) {
        this.manejadorListarTipoPago = manejadorListarTipoPago;
    }

    @GetMapping
    @ApiOperation("Listar usuario por id")
    public List<DtoTipoPago> listar() {
        return this.manejadorListarTipoPago.ejecutar();
    }


}
