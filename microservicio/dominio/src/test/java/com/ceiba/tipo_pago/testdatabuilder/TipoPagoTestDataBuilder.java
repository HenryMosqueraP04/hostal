package com.ceiba.tipo_pago.testdatabuilder;

import com.ceiba.tipo_habitacion.modelo.entidad.TipoHabitacion;
import com.ceiba.tipo_pago.modelo.entidad.TipoPago;

import java.math.BigDecimal;

public class TipoPagoTestDataBuilder {


    private Long id;
    private String nombre;
    private double porcentajeImpuesto;
    private boolean estado;

    public TipoPagoTestDataBuilder() {

        this.nombre = "EFECTIVO";
        this.porcentajeImpuesto = 1;
        this.estado = true;
    }


    public TipoPagoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public TipoPagoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public TipoPagoTestDataBuilder conPorcentajeImpuesto(double porcentajeImpuesto) {
        this.porcentajeImpuesto = porcentajeImpuesto;
        return this;
    }


    public TipoPagoTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public TipoPago build() {

        return new TipoPago(this.id, this.nombre, this.porcentajeImpuesto, this.estado);

    }

}
