package com.ceiba.dia.controlador;


import com.ceiba.dia.consulta.ManejadorListarDias;
import com.ceiba.dia.modelo.dto.DtoDia;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dias")
@Api(tags={"Controlador consulta dia"})
public class ConsultaControladorDia {

    private final ManejadorListarDias manejadorListarDias;

    public ConsultaControladorDia(ManejadorListarDias manejadorListarDias) {
        this.manejadorListarDias = manejadorListarDias;
    }

    @GetMapping
    @ApiOperation("Listar dias")
    public List<DtoDia> listar() {
        return this.manejadorListarDias.ejecutar();
    }

}
