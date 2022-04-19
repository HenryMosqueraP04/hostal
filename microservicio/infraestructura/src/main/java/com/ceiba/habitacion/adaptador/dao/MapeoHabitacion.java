package com.ceiba.habitacion.adaptador.dao;

import com.ceiba.habitacion.modelo.dto.DtoHabitacion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoHabitacion implements RowMapper<DtoHabitacion> {

    @Override
    public DtoHabitacion mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String nombre = rs.getString("nombre");
        Long tipoHabitacionId = rs.getLong("tipo_habitacion_id");
        boolean estado= rs.getBoolean("estado");

        return new DtoHabitacion(id,nombre,tipoHabitacionId,estado);
    }
}
