package com.ceiba.usuario.consulta;

import com.ceiba.usuario.servicio.ServicioListarUsuario;
import org.springframework.stereotype.Component;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import java.util.List;

@Component
public class ManejadorListarUsuario {

    private final ServicioListarUsuario servicioListarUsuario;

    public ManejadorListarUsuario(ServicioListarUsuario servicioListarUsuario) {
        this.servicioListarUsuario = servicioListarUsuario;
    }

    public List<DtoUsuario> ejecutar(){
        return this.servicioListarUsuario.listar();
    };

    public DtoUsuario obtenerUsuarioPorId(Long id) {
        return this.servicioListarUsuario.obtenerUsuarioPorId(id);
    }
}
