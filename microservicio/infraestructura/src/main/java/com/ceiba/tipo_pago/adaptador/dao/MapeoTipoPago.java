package com.ceiba.tipo_pago.adaptador.dao;

import com.ceiba.tipo_pago.modelo.dto.DtoTipoPago;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoTipoPago implements RowMapper<DtoTipoPago> {
    @Override
    public DtoTipoPago mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String nombre = rs.getString("nombre");
        double porcentajeImpuesto = rs.getDouble("porcentaje_impuesto");
        boolean estado = rs.getBoolean("estado");
        return new DtoTipoPago(id,nombre,porcentajeImpuesto,estado);
    }
}
