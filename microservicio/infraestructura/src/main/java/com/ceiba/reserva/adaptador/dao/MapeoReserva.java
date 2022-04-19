package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {
    @Override
    public DtoReserva mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        Long usuarioId = rs.getLong("usuario_id");
        Long habitacionId = rs.getLong("habitacion_id");
        Long tipoPagoId = rs.getLong("tipo_pago_id");
        BigDecimal valor = rs.getBigDecimal("valor");
        boolean estado = rs.getBoolean("estado");
        LocalDateTime fechaInicio = extraerLocalDateTime(rs, "fecha_inicio");
        LocalDateTime fechaFin = extraerLocalDateTime(rs, "fecha_fin");

        String nombreHabitacion = rs.getString("nombre_habitacion");
        String nombreTipoHabitacion = rs.getString("nombre_tipo_habitacion");
        String nombreTipoPago = rs.getString("nombre_tipo_pago");
        String nombreUsuario = rs.getString("nombre_usuario");

        return new DtoReserva(id, usuarioId, habitacionId, tipoPagoId, valor, estado, fechaInicio, fechaFin, nombreHabitacion, nombreTipoHabitacion, nombreTipoPago, nombreUsuario);
    }
}
