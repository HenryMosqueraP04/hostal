package com.ceiba.dia.modelo.entidad;

import lombok.Getter;

import java.math.BigDecimal;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarValorMenorMatematico;

@Getter
public class Dia {

    private static final String MENSAJE_NOMBRE_DIA_REQUERIDO = "Se debe ingresar el nombre del día";
    private static final String MENSAJE_VALOR_POR_HORA_REQUERIDO = "Se debe ingresar el valor por hora del día";
    private static final String MENSAJE_MINIMO_VALOR_POR_HORA = "El valor mínimo por hora del día es $%s";

    private static final String MINIMO_VALOR_POR_HORA = "0";


    private Long id;
    private String nombre;
    private BigDecimal valorPorHora ;
    private boolean estado;

    public Dia(Long id, String nombre, BigDecimal valorPorHora, boolean estado) {
        validarObligatorio(nombre, MENSAJE_NOMBRE_DIA_REQUERIDO);
        validarObligatorio(valorPorHora, MENSAJE_VALOR_POR_HORA_REQUERIDO);
        validarValorMenorMatematico(valorPorHora, new BigDecimal(MINIMO_VALOR_POR_HORA), String.format(MENSAJE_MINIMO_VALOR_POR_HORA, MINIMO_VALOR_POR_HORA));

        this.id = id;
        this.nombre = nombre;
        this.valorPorHora = valorPorHora;
        this.estado = estado;
    }

}
