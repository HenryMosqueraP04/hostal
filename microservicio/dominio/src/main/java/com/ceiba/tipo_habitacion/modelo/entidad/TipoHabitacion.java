package com.ceiba.tipo_habitacion.modelo.entidad;

import lombok.Getter;

import java.math.BigDecimal;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarValorMenorMatematico;

@Getter
public class TipoHabitacion {

    private static final String MENSAJE_NOMBRE_REQUERIDO = "Se debe ingresar el nombre";
    private static final String MENSAJE_MINIMO_VALOR_TIPO_HABITACION = "El valor mínimo del tipo de habitación es $%s";

    private static final String MINIMO_VALOR_TIPO_HABITACION= "0";

    private Long id;
    private String nombre;
    private BigDecimal valor;
    private boolean estado;

    public TipoHabitacion(Long id, String nombre, BigDecimal valor, boolean estado) {

        validarObligatorio(nombre, MENSAJE_NOMBRE_REQUERIDO);
        validarValorMenorMatematico(valor, new BigDecimal(MINIMO_VALOR_TIPO_HABITACION),
                String.format(MENSAJE_MINIMO_VALOR_TIPO_HABITACION, MINIMO_VALOR_TIPO_HABITACION));

        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.estado = estado;
    }
}
