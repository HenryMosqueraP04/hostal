package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.puerto.dao.DaoUsuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;

import java.util.List;

public class ServicioListarUsuario {

    private static final String USUARIO_NO_ENCONTRADO = "El registro del usuario no fue encontrado";

    private final DaoUsuario daoUsuario;
    private final RepositorioUsuario repositorioUsuario;

    public ServicioListarUsuario(DaoUsuario daoUsuario, RepositorioUsuario repositorioUsuario) {
        this.daoUsuario = daoUsuario;
        this.repositorioUsuario = repositorioUsuario;
    }

    public List<DtoUsuario> listar(){
        return this.daoUsuario.listar();
    }

    public DtoUsuario obtenerUsuarioPorId(Long id) {
        this.validarExistenciaUsuarioPorId(id);
        return this.daoUsuario.obtenerPorId(id);
    }

    public void validarExistenciaUsuarioPorId(Long id){
        boolean existe = this.repositorioUsuario.existePorId(id);
        if(!existe) throw new ExcepcionSinDatos(USUARIO_NO_ENCONTRADO);
    }

}
