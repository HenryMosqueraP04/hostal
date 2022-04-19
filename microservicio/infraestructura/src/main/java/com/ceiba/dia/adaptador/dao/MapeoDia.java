package com.ceiba.dia.adaptador.dao;

import com.ceiba.dia.modelo.dto.DtoDia;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoDia implements RowMapper<DtoDia> {
    @Override
    public DtoDia mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String nombre = rs.getString("nombre");
        BigDecimal valorPorHora = rs.getBigDecimal("valor_por_hora");
        boolean estado = rs.getBoolean("estado");

        return new DtoDia(id,nombre,valorPorHora,estado);
    }
}
