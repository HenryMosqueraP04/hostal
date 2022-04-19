package com.ceiba.tipo_pago.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.tipo_pago.modelo.dto.DtoTipoPago;
import com.ceiba.tipo_pago.puerto.dao.DaoTipoPago;
import com.ceiba.tipo_pago.puerto.repositorio.RepositorioTipoPago;

import java.util.List;

public class ServicioListarTipoPago {

    private final String TIPO_PAGO_NO_ENCONTRADO = "El registro del tipo de pago no fue encontrado";

    private final DaoTipoPago daoTipoPago;
    private final RepositorioTipoPago repositorioTipoPago;

    public ServicioListarTipoPago(DaoTipoPago daoTipoPago, RepositorioTipoPago repositorioTipoPago) {
        this.daoTipoPago = daoTipoPago;
        this.repositorioTipoPago = repositorioTipoPago;
    }

    private void validarExistenciaTipoPagoPorId(Long id){
        boolean existe = this.repositorioTipoPago.existePorId(id);
        if(!existe) throw new ExcepcionSinDatos(TIPO_PAGO_NO_ENCONTRADO);
    }

    public List<DtoTipoPago> ejecutar(){
        return this.daoTipoPago.listar();
    }

    public DtoTipoPago obtenerTipoPagoPorId(Long id) {
        this.validarExistenciaTipoPagoPorId(id);
        return this.daoTipoPago.obtenerPorId(id);
    }



}
