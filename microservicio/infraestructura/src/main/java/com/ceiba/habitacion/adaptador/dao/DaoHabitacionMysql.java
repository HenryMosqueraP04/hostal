package com.ceiba.habitacion.adaptador.dao;

import com.ceiba.habitacion.modelo.dto.DtoHabitacion;
import com.ceiba.habitacion.puerto.dao.DaoHabitacion;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoHabitacionMysql implements DaoHabitacion {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "habitacion", value = "obtenerPorId")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "habitacion", value = "obtenerHabitacionDisponibles")
    private static String sqlObtenerHabitacionDisponibles;

    public DaoHabitacionMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public DtoHabitacion obtenerPorId(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPorId,mapSqlParameterSource, new MapeoHabitacion());
    }

    @Override
    public List<DtoHabitacion> obtenerHabitacionDisponibles() {
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlObtenerHabitacionDisponibles,new MapeoHabitacion());
    }
}
