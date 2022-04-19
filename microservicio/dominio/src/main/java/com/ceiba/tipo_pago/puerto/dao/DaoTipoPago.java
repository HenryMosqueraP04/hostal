package com.ceiba.tipo_pago.puerto.dao;

import com.ceiba.tipo_pago.modelo.dto.DtoTipoPago;

import java.util.List;

public interface DaoTipoPago {
    DtoTipoPago obtenerPorId(Long id);
    List<DtoTipoPago> listar();
}
