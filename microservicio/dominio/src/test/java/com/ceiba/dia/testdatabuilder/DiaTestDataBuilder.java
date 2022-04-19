package com.ceiba.dia.testdatabuilder;

import com.ceiba.dia.modelo.entidad.Dia;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DiaTestDataBuilder {

    private Long id;
    private String nombre;
    private BigDecimal valorPorHora ;
    private boolean estado;

    public DiaTestDataBuilder() {
        this.nombre = LocalDateTime.now().getDayOfWeek().name();
        this.valorPorHora = new BigDecimal("3000");
        this.estado = true;
    }


    public DiaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DiaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public DiaTestDataBuilder conValorPorHora(BigDecimal valorPorHora) {
        this.valorPorHora = valorPorHora;
        return this;
    }

    public DiaTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public Dia build() {
        return new Dia(this.id,this.nombre, this.valorPorHora,this.estado);
    }
}
