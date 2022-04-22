package com.ceiba.usuario.controlador;


import com.ceiba.usuario.consulta.ManejadorListarUsuario;
import org.springframework.web.bind.annotation.*;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Api(tags={"Controlador consulta usuario"})
@CrossOrigin
public class ConsultaControladorUsuario {

    private final ManejadorListarUsuario manejadorListarUsuarios;

    public ConsultaControladorUsuario(ManejadorListarUsuario manejadorListarUsuarios) {
        this.manejadorListarUsuarios = manejadorListarUsuarios;
    }

    @GetMapping
    @ApiOperation("Listar usuarios")
    public List<DtoUsuario> listar() {
        return this.manejadorListarUsuarios.ejecutar();
    }

    @GetMapping("/{id}")
    @ApiOperation("Listar usuario por id")
    public DtoUsuario obtenerUsuarioPorId(@PathVariable Long id) {
        return this.manejadorListarUsuarios.obtenerUsuarioPorId(id);
    }

}
