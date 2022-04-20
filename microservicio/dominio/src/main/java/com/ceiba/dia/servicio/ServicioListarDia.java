package com.ceiba.dia.servicio;

import com.ceiba.dia.modelo.dto.DtoDia;
import com.ceiba.dia.puerto.dao.DaoDia;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

import java.util.List;

public class ServicioListarDia {

    private static final String LISTA_DE_DIAS_VACIA = "La lista de días se encuentra vacía";
    private final DaoDia daoDia;

    public ServicioListarDia(DaoDia daoDia) {
        this.daoDia = daoDia;
    }

    public List<DtoDia> ejecutar() {
        List<DtoDia> dtoDias = this.daoDia.listar();
        this.validarListaVacia(dtoDias);
        return dtoDias;
    }

    private void validarListaVacia(List<DtoDia> dias){
        if(dias.isEmpty()){
            throw new ExcepcionSinDatos(LISTA_DE_DIAS_VACIA);
        }
    }

}
