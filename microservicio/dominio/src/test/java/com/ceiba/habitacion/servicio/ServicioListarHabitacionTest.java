package com.ceiba.habitacion.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.habitacion.modelo.dto.DtoHabitacion;
import com.ceiba.habitacion.puerto.dao.DaoHabitacion;
import com.ceiba.habitacion.puerto.repositorio.RepositorioHabitacion;
import com.ceiba.habitacion.testdatabuilder.DtoHabitacionTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServicioListarHabitacionTest {

    @Test
    void deberiaObtenerLaHabitacionPorId() {

        // arrange
        DtoHabitacion dtoHabitacionEsperado = new DtoHabitacionTestDataBuilder().conId(1L).build();
        DaoHabitacion daoHabitacion = Mockito.mock(DaoHabitacion.class);
        RepositorioHabitacion repositorioHabitacion = Mockito.mock(RepositorioHabitacion.class);
        Mockito.when(repositorioHabitacion.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoHabitacion.obtenerPorId(Mockito.anyLong())).thenReturn(dtoHabitacionEsperado);

        ServicioListarHabitacion servicioListarHabitacion = new ServicioListarHabitacion(daoHabitacion, repositorioHabitacion);

        // act
        DtoHabitacion dtoHabitacionRespuesta = servicioListarHabitacion.obtenerHabitacionPorId(1L);

        //- assert
        assertEquals(dtoHabitacionEsperado.getId(), dtoHabitacionRespuesta.getId());
        Mockito.verify(repositorioHabitacion, Mockito.times(1)).existePorId(Mockito.anyLong());
        Mockito.verify(daoHabitacion, Mockito.times(1)).obtenerPorId(Mockito.anyLong());
    }

    @Test
    void deberiaFallarAlObtenerLaHabitacionPorId() {

        // arrange
        final String expecionHabitacionNoEncontrada = "El registro de la habitación no fue encontrado";

        DaoHabitacion daoHabitacion = Mockito.mock(DaoHabitacion.class);
        RepositorioHabitacion repositorioHabitacion = Mockito.mock(RepositorioHabitacion.class);
        Mockito.when(repositorioHabitacion.existePorId(Mockito.anyLong())).thenReturn(false);

        ServicioListarHabitacion servicioListarHabitacion = new ServicioListarHabitacion(daoHabitacion, repositorioHabitacion);

        //- assert
        BasePrueba.assertThrows(()-> servicioListarHabitacion.obtenerHabitacionPorId(1L), ExcepcionSinDatos.class, expecionHabitacionNoEncontrada);
        Mockito.verify(repositorioHabitacion, Mockito.times(1)).existePorId(Mockito.anyLong());
    }

    @Test
    void deberiaFallarLaHabitacionOcupada(){

        // arrange
        final String expecionHabitacionOcupada = "La habitación se encuentra ocupada";
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fin = ahora.plusHours(1);
        DaoHabitacion daoHabitacion = Mockito.mock(DaoHabitacion.class);
        RepositorioHabitacion repositorioHabitacion = Mockito.mock(RepositorioHabitacion.class);
        Mockito.when(repositorioHabitacion.esHabitacionOcupada(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(true);

        ServicioListarHabitacion servicioListarHabitacion = new ServicioListarHabitacion(daoHabitacion, repositorioHabitacion);

        //- assert
        BasePrueba.assertThrows(()-> servicioListarHabitacion.validarHabitacionOcupada(1L, ahora, fin), ExcepcionDuplicidad.class, expecionHabitacionOcupada);
        Mockito.verify(repositorioHabitacion, Mockito.times(1)).esHabitacionOcupada(Mockito.anyLong(),Mockito.any(), Mockito.any());
    }

    @Test
    void deberiaFuncionarHabitacionNoOcupada(){

        // arrange
        final String expecionHabitacionOcupada = "La habitación se encuentra ocupada";
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fin = ahora.plusHours(1);
        DaoHabitacion daoHabitacion = Mockito.mock(DaoHabitacion.class);
        RepositorioHabitacion repositorioHabitacion = Mockito.mock(RepositorioHabitacion.class);
        Mockito.when(repositorioHabitacion.esHabitacionOcupada(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(false);

        ServicioListarHabitacion servicioListarHabitacion = new ServicioListarHabitacion(daoHabitacion, repositorioHabitacion);

        //act
        servicioListarHabitacion.validarHabitacionOcupada(1L, ahora, fin);

        //asser
        Mockito.verify(repositorioHabitacion, Mockito.times(1)).esHabitacionOcupada(Mockito.anyLong(),Mockito.any(), Mockito.any());
    }

    @Test
    void deberiaDevolverUnaListaLlenaDeHabitacionDesocupadas() {

        // arrange
        List<DtoHabitacion> habitacionesDesocupadasEsperado = Arrays.asList(new DtoHabitacionTestDataBuilder().conId(1L).build());

        DaoHabitacion daoHabitacion = Mockito.mock(DaoHabitacion.class);
        RepositorioHabitacion repositorioHabitacion = Mockito.mock(RepositorioHabitacion.class);

        Mockito.when(daoHabitacion.obtenerHabitacionDisponibles()).thenReturn(habitacionesDesocupadasEsperado);

        ServicioListarHabitacion servicioListarHabitacion = new ServicioListarHabitacion(daoHabitacion, repositorioHabitacion);

        // act
        List<DtoHabitacion> habitacionesDesocupadasRespuesta = servicioListarHabitacion.listarHabitacionesDisponibles();

        //- assert
        assertTrue(!habitacionesDesocupadasRespuesta.isEmpty());
        assertEquals(habitacionesDesocupadasEsperado.size(), habitacionesDesocupadasRespuesta.size());
        Mockito.verify(daoHabitacion, Mockito.times(1)).obtenerHabitacionDisponibles();
    }

    @Test
    void deberiaDevolverUnaListaVaciaDeHabitacionDesocupadas() {

        // arrange
        List<DtoHabitacion> habitacionesDesocupadasEsperado = new ArrayList<>();

        DaoHabitacion daoHabitacion = Mockito.mock(DaoHabitacion.class);
        RepositorioHabitacion repositorioHabitacion = Mockito.mock(RepositorioHabitacion.class);

        Mockito.when(daoHabitacion.obtenerHabitacionDisponibles()).thenReturn(habitacionesDesocupadasEsperado);

        ServicioListarHabitacion servicioListarHabitacion = new ServicioListarHabitacion(daoHabitacion, repositorioHabitacion);

        // act
        List<DtoHabitacion> habitacionesDesocupadasRespuesta = servicioListarHabitacion.listarHabitacionesDisponibles();

        //- assert
        assertTrue(habitacionesDesocupadasRespuesta.isEmpty());
        assertEquals(habitacionesDesocupadasEsperado.size(), habitacionesDesocupadasRespuesta.size());
        Mockito.verify(daoHabitacion, Mockito.times(1)).obtenerHabitacionDisponibles();
    }

    @Test
    void deberiaDevolverUnaListaVaciaDeHabitacion() {

        // arrange
        List<DtoHabitacion> habitacionesEsperado = new ArrayList<>();

        DaoHabitacion daoHabitacion = Mockito.mock(DaoHabitacion.class);
        RepositorioHabitacion repositorioHabitacion = Mockito.mock(RepositorioHabitacion.class);

        Mockito.when(daoHabitacion.listar()).thenReturn(habitacionesEsperado);

        ServicioListarHabitacion servicioListarHabitacion = new ServicioListarHabitacion(daoHabitacion, repositorioHabitacion);

        // act
        List<DtoHabitacion> habitacionesRespuesta = servicioListarHabitacion.listar();

        //- assert
        assertTrue(habitacionesRespuesta.isEmpty());
        Mockito.verify(daoHabitacion, Mockito.times(1)).listar();
    }

    @Test
    void deberiaDevolverUnaListaLlenaDeHabitacion() {

        // arrange
        List<DtoHabitacion> habitacionesEsperado = Arrays.asList(new DtoHabitacionTestDataBuilder().build());

        DaoHabitacion daoHabitacion = Mockito.mock(DaoHabitacion.class);
        RepositorioHabitacion repositorioHabitacion = Mockito.mock(RepositorioHabitacion.class);

        Mockito.when(daoHabitacion.listar()).thenReturn(habitacionesEsperado);

        ServicioListarHabitacion servicioListarHabitacion = new ServicioListarHabitacion(daoHabitacion, repositorioHabitacion);

        // act
        List<DtoHabitacion> habitacionesRespuesta = servicioListarHabitacion.listar();

        //- assert
        assertTrue(!habitacionesRespuesta.isEmpty());
        Mockito.verify(daoHabitacion, Mockito.times(1)).listar();
    }




}
