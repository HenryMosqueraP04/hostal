package com.ceiba.tipo_pago.testdatabuilder;

import com.ceiba.tipo_pago.modelo.dto.DtoTipoPago;

public class DtoTipoPagoTestDataBuilder {


    private Long id;
    private String nombre;
    private double porcentajeImpuesto;
    private boolean estado;

    public DtoTipoPagoTestDataBuilder() {

        this.nombre = "EFECTIVO";
        this.porcentajeImpuesto = 1;
        this.estado = true;
    }


    public DtoTipoPagoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoTipoPagoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public DtoTipoPagoTestDataBuilder conPorcentajeImpuesto(double porcentajeImpuesto) {
        this.porcentajeImpuesto = porcentajeImpuesto;
        return this;
    }


    public DtoTipoPagoTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public DtoTipoPago build() {

        return new DtoTipoPago(this.id, this.nombre, this.porcentajeImpuesto, this.estado);

    }

}
