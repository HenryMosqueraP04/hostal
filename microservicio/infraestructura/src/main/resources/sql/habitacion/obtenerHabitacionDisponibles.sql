SELECT hab.id, hab.nombre, hab.tipo_habitacion_id, hab.estado
FROM habitacion hab
INNER JOIN reserva res ON hab.id = res.habitacion_id
WHERE NOW() NOT BETWEEN res.fecha_inicio AND res.fecha_fin