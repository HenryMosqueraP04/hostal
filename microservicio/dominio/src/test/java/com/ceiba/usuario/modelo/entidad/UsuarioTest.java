package com.ceiba.usuario.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.usuario.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioTest {

    @Test
    void deberiaCrearCorrectamenteElTipoHabitacion() {

        Usuario usuario = new UsuarioTestDataBuilder().conId(1L).build();

        //assert
        assertEquals(1L, usuario.getId());
        assertEquals("HENRY MOSQUERA", usuario.getNombreCompleto());
        assertEquals("1003815494", usuario.getNumeroDocumento());
        assertEquals(true, usuario.isEstado());
    }

    @Test
    void deberiaFallarEsRequeridoElNombreCompleto() {


        final String excepcion = "Se debe ingresar el nombre completo";

        //act
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conNombreCompleto(null);

        BasePrueba.assertThrows(() -> {usuarioTestDataBuilder.build(); },  ExcepcionValorObligatorio.class,
                excepcion);
    }

    @Test
    void deberiaFallarEsRequeridoElDocumento() {


        final String excepcion = "Se debe ingresar el número de documento";

        //act
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conNumeroDocumento(null);

        BasePrueba.assertThrows(() -> {usuarioTestDataBuilder.build(); },  ExcepcionValorObligatorio.class,
                excepcion);
    }

}
