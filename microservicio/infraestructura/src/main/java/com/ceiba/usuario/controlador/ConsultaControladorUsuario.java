package com.ceiba.usuario.controlador;


import com.ceiba.usuario.consulta.ManejadorListarUsuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Api(tags={"Controlador consulta usuario"})
public class ConsultaControladorUsuario {

    private final ManejadorListarUsuario manejadorListarUsuarios;

    public ConsultaControladorUsuario(ManejadorListarUsuario manejadorListarUsuarios) {
        this.manejadorListarUsuarios = manejadorListarUsuarios;
    }

    @GetMapping
    @ApiOperation("Listar usuario por id")
    public List<DtoUsuario> listar() {
        return this.manejadorListarUsuarios.ejecutar();
    }

    @GetMapping("/{id}")
    @ApiOperation("Listar usuario por id")
    public DtoUsuario obtenerUsuarioPorId(@PathVariable Long id) {
        return this.manejadorListarUsuarios.obtenerUsuarioPorId(id);
    }

}
