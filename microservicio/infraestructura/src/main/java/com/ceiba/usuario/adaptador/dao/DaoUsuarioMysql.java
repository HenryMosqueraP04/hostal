package com.ceiba.usuario.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.usuario.puerto.dao.DaoUsuario;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

@Component
public class DaoUsuarioMysql implements DaoUsuario {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace= "usuario", value="obtenerPorId")
    private static String sqlObtenerPorId;

    public DaoUsuarioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public DtoUsuario obtenerPorId(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPorId,mapSqlParameterSource, new MapeoUsuario() );
    }
}
