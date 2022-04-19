package com.ceiba.usuario.puerto.dao;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

public interface DaoUsuario {

    DtoUsuario obtenerPorId(Long id);
}
