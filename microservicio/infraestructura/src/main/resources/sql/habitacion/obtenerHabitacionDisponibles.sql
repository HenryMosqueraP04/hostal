SELECT id, nombre, tipo_habitacion_id, estado
FROM habitacion
WHERE id NOT IN(
SELECT habitacion_id FROM reserva WHERE
NOW() BETWEEN fecha_inicio AND fecha_fin
)
