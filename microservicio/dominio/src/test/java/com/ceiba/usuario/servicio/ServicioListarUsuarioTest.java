package com.ceiba.usuario.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.puerto.dao.DaoUsuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.testdatabuilder.DtoUsuarioTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioListarUsuarioTest {

    @Test
    void deberiaObtenerElUsuarioPorId() {

        // arrange
        DtoUsuario dtoUsuario = new DtoUsuarioTestDataBuilder().conId(1L).build();

        DaoUsuario daoUsuario = Mockito.mock(DaoUsuario.class);
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);

        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoUsuario.obtenerPorId(Mockito.anyLong())).thenReturn(dtoUsuario);

        ServicioListarUsuario servicioListarUsuario = new ServicioListarUsuario(daoUsuario, repositorioUsuario);

        // act
        DtoUsuario dtoUsuarioRespuesta = servicioListarUsuario.obtenerUsuarioPorId(1L);

        //- assert
        assertEquals(dtoUsuario.getId(), dtoUsuarioRespuesta.getId());
        Mockito.verify(repositorioUsuario, Mockito.times(1)).existePorId(Mockito.anyLong());
        Mockito.verify(daoUsuario, Mockito.times(1)).obtenerPorId(Mockito.anyLong());
    }

    @Test
    void deberiaFallarAlObtenerElUsuarioPorId() {

        // arrange
        final String exepcionUsuarioNoEncontrado = "El registro del usuario no fue encontrado";

        DaoUsuario daoUsuario = Mockito.mock(DaoUsuario.class);
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);

        Mockito.when(repositorioUsuario.existePorId(Mockito.anyLong())).thenReturn(false);

        ServicioListarUsuario servicioListarUsuario = new ServicioListarUsuario(daoUsuario, repositorioUsuario);

        //- assert
        BasePrueba.assertThrows(()-> servicioListarUsuario.obtenerUsuarioPorId(1L), ExcepcionSinDatos.class, exepcionUsuarioNoEncontrado);
        Mockito.verify(repositorioUsuario, Mockito.times(1)).existePorId(Mockito.anyLong());
    }

}
