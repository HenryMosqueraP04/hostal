package com.ceiba.tipo_pago.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.tipo_pago.modelo.dto.DtoTipoPago;
import com.ceiba.tipo_pago.puerto.dao.DaoTipoPago;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoTipoPagoMysql implements DaoTipoPago {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "tipo_pago", value = "obtenerPorId")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "tipo_pago", value = "listar")
    private static String sqlListar;

    public DaoTipoPagoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public DtoTipoPago obtenerPorId(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPorId, mapSqlParameterSource, new MapeoTipoPago());
    }

    @Override
    public List<DtoTipoPago> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoTipoPago());
    }
}
