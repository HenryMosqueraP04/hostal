package com.ceiba.usuario.puerto.dao;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import java.util.List;

public interface DaoUsuario {

    DtoUsuario obtenerPorId(Long id);
    List<DtoUsuario> listar();
}
