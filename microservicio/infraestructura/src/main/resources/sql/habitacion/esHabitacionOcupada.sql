SELECT COUNT(1)
FROM habitacion hab
INNER JOIN reserva res ON hab.id = res.habitacion_id
WHERE ((:fechaInicio BETWEEN res.fecha_inicio AND res.fecha_fin)
OR (:fechaFin BETWEEN res.fecha_inicio AND res.fecha_fin))
AND hab.id = :id