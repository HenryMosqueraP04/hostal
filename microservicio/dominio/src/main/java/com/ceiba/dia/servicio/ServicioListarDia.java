package com.ceiba.dia.servicio;

import com.ceiba.dia.modelo.dto.DtoDia;
import com.ceiba.dia.puerto.dao.DaoDia;

import java.util.List;

public class ServicioListarDia {

    private final DaoDia daoDia;

    public ServicioListarDia(DaoDia daoDia) {
        this.daoDia = daoDia;
    }

    public List<DtoDia> ejecutar() {
        return this.daoDia.listar();
    }

}
