package com.ceiba.usuario.testdatabuilder;

import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.modelo.entidad.Usuario;

public class DtoUsuarioTestDataBuilder {

    private Long id;
    private String nombreCompleto;
    private String numeroDocumento;
    private boolean estado;

    public DtoUsuarioTestDataBuilder() {

        this.nombreCompleto = "HENRY MOSQUERA";
        this.numeroDocumento = "1003815494";
        this.estado = true;
    }


    public DtoUsuarioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoUsuarioTestDataBuilder conNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
        return this;
    }

    public DtoUsuarioTestDataBuilder conNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }


    public DtoUsuarioTestDataBuilder conEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public DtoUsuario build() {

        return new DtoUsuario(this.id, this.nombreCompleto, this.numeroDocumento, this.estado);

    }

}
