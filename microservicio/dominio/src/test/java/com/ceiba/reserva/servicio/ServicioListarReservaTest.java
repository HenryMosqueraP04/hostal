package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.testdatabuilder.DtoReservaTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServicioListarReservaTest {

    @Test
    void deberiaDevolverUnaListaLlenaReservasPorUsuarioId() {

        // arrange
        List<DtoReserva> reservasEsperadas = Arrays.asList(new DtoReservaTestDataBuilder().conId(1L).build());

        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        Mockito.when(daoReserva.listarPorUsuarioId(Mockito.anyLong())).thenReturn(reservasEsperadas);

        ServicioListarReserva servicioListarReserva = new ServicioListarReserva(daoReserva,repositorioReserva);

        // act
        List<DtoReserva> reservasRespuesta = servicioListarReserva.listarPorUsuarioId(1L);

        //- assert
        assertTrue(!reservasRespuesta.isEmpty());
        assertEquals(reservasEsperadas.size(), reservasRespuesta.size());
        Mockito.verify(daoReserva, Mockito.times(1)).listarPorUsuarioId(1L);
    }

    @Test
    void deberiaDevolverUnaListaVaciaDeHabitacionDesocupadas() {

        // arrange
        List<DtoReserva> reservasEsperadas = new ArrayList<>();

        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        Mockito.when(daoReserva.listarPorUsuarioId(Mockito.anyLong())).thenReturn(reservasEsperadas);

        ServicioListarReserva servicioListarReserva = new ServicioListarReserva(daoReserva,repositorioReserva);

        // act
        List<DtoReserva> reservasRespuesta = servicioListarReserva.listarPorUsuarioId(1L);

        //- assert
        assertTrue(reservasRespuesta.isEmpty());
        assertEquals(reservasEsperadas.size(), reservasRespuesta.size());
        Mockito.verify(daoReserva, Mockito.times(1)).listarPorUsuarioId(1L);
    }

    @Test
    void deberiaFallarAlObtenerLaReservaPorId() {

        // arrange
        final String reservaNoEncontrada = "El registro de la reserva no fue encontrado";

        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existePorId(Mockito.anyLong())).thenReturn(false);

        ServicioListarReserva servicioListarReserva = new ServicioListarReserva(daoReserva,repositorioReserva);

        // act

        //- assert
        BasePrueba.assertThrows(()-> servicioListarReserva.obtenerPorId(1L), ExcepcionSinDatos.class, reservaNoEncontrada );

        Mockito.verify(repositorioReserva, Mockito.times(1)).existePorId(Mockito.anyLong());
    }

    @Test
    void deberiaObtenerLaReservaPorId() {

        // arrange
        DtoReserva reservaEsperada = new DtoReservaTestDataBuilder().build();

        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        Mockito.when(daoReserva.obtenerPorId(Mockito.anyLong())).thenReturn(reservaEsperada);
        Mockito.when(repositorioReserva.existePorId(Mockito.anyLong())).thenReturn(true);

        ServicioListarReserva servicioListarReserva = new ServicioListarReserva(daoReserva,repositorioReserva);

        // act

        DtoReserva reservaEncontrada = servicioListarReserva.obtenerPorId(1L);

        //- assert

        assertEquals(reservaEsperada.getId(), reservaEncontrada.getId());

        Mockito.verify(repositorioReserva, Mockito.times(1)).existePorId(Mockito.anyLong());
        Mockito.verify(daoReserva, Mockito.times(1)).obtenerPorId(Mockito.anyLong());
    }


}
