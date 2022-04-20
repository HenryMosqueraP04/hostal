package com.ceiba.usuario.testdatabuilder;

import com.ceiba.usuario.modelo.entidad.Usuario;

public class UsuarioTestDataBuilder {

    private Long id;
    private String nombreCompleto;
    private String numeroDocumento;
    private boolean estado;

    public UsuarioTestDataBuilder() {

        this.nombreCompleto = "HENRY MOSQUERA";
        this.numeroDocumento = "1003815494";
        this.estado = true;
    }


    public UsuarioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioTestDataBuilder conNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
        return this;
    }

    public UsuarioTestDataBuilder conNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }


    public UsuarioTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public Usuario build() {

        return new Usuario(this.id, this.nombreCompleto, this.numeroDocumento, this.estado);

    }

}
