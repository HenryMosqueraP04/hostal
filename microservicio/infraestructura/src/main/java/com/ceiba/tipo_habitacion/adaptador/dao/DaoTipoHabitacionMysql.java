package com.ceiba.tipo_habitacion.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.tipo_habitacion.modelo.dto.DtoTipoHabitacion;
import com.ceiba.tipo_habitacion.puerto.dao.DaoTipoHabitacion;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class DaoTipoHabitacionMysql implements DaoTipoHabitacion {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "tipo_habitacion", value = "obtenerPorId")
    private static String sqlObtenerPorId;

    public DaoTipoHabitacionMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public DtoTipoHabitacion obtenerPorId(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPorId,mapSqlParameterSource,new MapeoTipoHabitacion());
    }
}
