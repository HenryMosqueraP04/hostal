SELECT res.id, res.usuario_id, res.habitacion_id, res.tipo_pago_id,
res.valor, res.estado, res.fecha_inicio, res.fecha_fin,
hab.nombre AS nombre_habitacion, tih.nombre AS nombre_tipo_habitacion,
tip.nombre AS nombre_tipo_pago, usu.nombre_completo AS nombre_usuario
FROM reserva res
INNER JOIN habitacion hab ON res.habitacion_id = hab.id
INNER JOIN tipo_habitacion tih ON hab.tipo_habitacion_id = tih.id
INNER JOIN tipo_pago tip ON res.tipo_pago_id = tip.id
INNER JOIN usuario usu ON res.usuario_id = usu.id
