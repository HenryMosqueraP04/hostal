package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class RepositorioReservaMysql implements RepositorioReserva {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "reserva", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "reserva", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "reserva", value = "eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace = "reserva", value = "existePorId")
    private static String sqlExistePorId;

    public RepositorioReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Reserva reserva) {
        return this.customNamedParameterJdbcTemplate.crear(reserva, sqlCrear);
    }

    @Override
    public void actualizar(Reserva reserva) {
        this.customNamedParameterJdbcTemplate.actualizar(reserva,sqlActualizar);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
    this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, mapSqlParameterSource);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId, mapSqlParameterSource, Boolean.class);
    }
}
