package com.ceiba.usuario.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import org.springframework.jdbc.core.RowMapper;

public class MapeoUsuario implements RowMapper<DtoUsuario>, MapperResult {

    @Override
    public DtoUsuario mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombreCompleto = resultSet.getString("nombre_completo");
        String numeroDocumento = resultSet.getString("numero_documento");
        boolean estado = resultSet.getBoolean("estado");

        return new DtoUsuario(id,nombreCompleto,numeroDocumento,estado);
    }

}
