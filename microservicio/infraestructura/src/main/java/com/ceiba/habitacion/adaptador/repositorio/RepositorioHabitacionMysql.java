package com.ceiba.habitacion.adaptador.repositorio;

import com.ceiba.habitacion.puerto.repositorio.RepositorioHabitacion;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RepositorioHabitacionMysql implements RepositorioHabitacion {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "habitacion", value = "existePorId")
    private static String sqlExistePorId;

    @SqlStatement(namespace = "habitacion", value = "esHabitacionOcupada")
    private static String sqlEsHabitacionOcupada;

    public RepositorioHabitacionMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",id);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId,mapSqlParameterSource, Boolean.class );
    }

    @Override
    public boolean esHabitacionOcupada(Long id, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",id);
        mapSqlParameterSource.addValue("fechaInicio",fechaInicio);
        mapSqlParameterSource.addValue("fechaFin",fechaFin);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlEsHabitacionOcupada,mapSqlParameterSource, Boolean.class );
    }
}
