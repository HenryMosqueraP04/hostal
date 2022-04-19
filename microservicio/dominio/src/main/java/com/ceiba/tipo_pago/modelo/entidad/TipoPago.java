package com.ceiba.tipo_pago.modelo.entidad;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class TipoPago {

    private static final String MENSAJE_NOMBRE_REQUERIDO = "Se debe ingresar el nombre";
    private static final String MENSAJE_TIPO_IMPUESTO_VALIDO = "El porcentaje debe estar entre %s y %s";
    private static double MINIMO_IMPUESTO = 0.0;
    private static double MAXIMO_IMPUESTO = 99.9;

    private Long id;
    private String nombre;
    private double porcentajeImpuesto;
    private boolean estado;

    public TipoPago(Long id, String nombre, double porcentajeImpuesto, boolean estado) {
        validarObligatorio(nombre, MENSAJE_NOMBRE_REQUERIDO);
        this.validarPorcentajeImpuesto(porcentajeImpuesto, String.format(MENSAJE_TIPO_IMPUESTO_VALIDO,
                        MINIMO_IMPUESTO, MAXIMO_IMPUESTO));
        this.id = id;
        this.nombre = nombre;
        this.porcentajeImpuesto = porcentajeImpuesto;
        this.estado = estado;
    }

    private void validarPorcentajeImpuesto(double porcentajeImpuesto, String mensaje){
        if(porcentajeImpuesto > MAXIMO_IMPUESTO ) throw new ExcepcionValorInvalido(mensaje);
    }

}
