package com.ceiba.tipo_habitacion.adaptador.dao;

import com.ceiba.tipo_habitacion.modelo.dto.DtoTipoHabitacion;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoTipoHabitacion implements RowMapper<DtoTipoHabitacion> {
    @Override
    public DtoTipoHabitacion mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String nombre = rs.getString("nombre");
        BigDecimal valor = rs.getBigDecimal("valor");
        boolean estado = rs.getBoolean("estado");
        return new DtoTipoHabitacion(id,nombre,valor,estado);
    }
}
