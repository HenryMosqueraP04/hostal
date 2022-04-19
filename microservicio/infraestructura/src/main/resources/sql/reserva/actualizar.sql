UPDATE reserva SET
usuario_id = :usuarioId,
habitacion_id = :habitacionId,
tipo_pago_id = :tipoPagoId,
valor = :valor,
estado = :estado,
fecha_inicio = :fechaInicio,
fecha_fin = :fechaFin
WHERE id = :id
