package com.ceiba.usuario.modelo.entidad;


import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Usuario {

    private static final String MENSAJE_NOMBRE_COMPLETO_REQUERIDO = "Se debe ingresar el nombre completo";
    private static final String MENSAJE_DOCUMENTO_REQUERIDO = "Se debe ingresar el número de documento";

    private Long id;
    private String nombreCompleto;
    private String numeroDocumento;
    private boolean estado;

    public Usuario(Long id, String nombreCompleto, String numeroDocumento, boolean estado) {

        validarObligatorio(nombreCompleto, MENSAJE_NOMBRE_COMPLETO_REQUERIDO);
        validarObligatorio(numeroDocumento, MENSAJE_DOCUMENTO_REQUERIDO );

        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.numeroDocumento = numeroDocumento;
        this.estado = estado;
    }
}
