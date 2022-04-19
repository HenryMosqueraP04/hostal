package com.ceiba.dia.testdatabuilder;

import com.ceiba.dia.modelo.dto.DtoDia;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DtoDiaTestDataBuilder {

    private Long id;
    private String nombre;
    private BigDecimal valorPorHora ;
    private boolean estado;

    public DtoDiaTestDataBuilder() {
        this.nombre = LocalDateTime.now().getDayOfWeek().name();
        this.valorPorHora = new BigDecimal("3000");
        this.estado = true;
    }


    public DtoDiaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoDiaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public DtoDiaTestDataBuilder conValorPorHora(BigDecimal valorPorHora) {
        this.valorPorHora = valorPorHora;
        return this;
    }

    public DtoDiaTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public DtoDia build() {
        return new DtoDia(this.id,this.nombre, this.valorPorHora,this.estado);
    }
}
