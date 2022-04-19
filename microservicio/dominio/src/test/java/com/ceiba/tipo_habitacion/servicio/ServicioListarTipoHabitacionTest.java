package com.ceiba.tipo_habitacion.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.tipo_habitacion.modelo.dto.DtoTipoHabitacion;
import com.ceiba.tipo_habitacion.puerto.dao.DaoTipoHabitacion;
import com.ceiba.tipo_habitacion.puerto.repositorio.RepositorioTipoHabitacion;
import com.ceiba.tipo_habitacion.testdatabuilder.DtoTipoHabitacionTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioListarTipoHabitacionTest {


    @Test
    void deberiaObtenerElTipoDeHabitacionPorId() {

        // arrange
        DtoTipoHabitacion dtoTipoHabitacionEsperado = new DtoTipoHabitacionTestDataBuilder().conId(1L).build();

        DaoTipoHabitacion daoTipoHabitacion = Mockito.mock(DaoTipoHabitacion.class);
        RepositorioTipoHabitacion repositorioTipoHabitacion = Mockito.mock(RepositorioTipoHabitacion.class);

        Mockito.when(repositorioTipoHabitacion.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoTipoHabitacion.obtenerPorId(Mockito.anyLong())).thenReturn(dtoTipoHabitacionEsperado);

        ServicioListarTipoHabitacion servicioListarTipoHabitacion = new ServicioListarTipoHabitacion(daoTipoHabitacion, repositorioTipoHabitacion);

        // act
        DtoTipoHabitacion dtoTipoHabitacionRespuesta = servicioListarTipoHabitacion.obtenerTipoHabitacionPorId(1L);

        //- assert
        assertEquals(dtoTipoHabitacionEsperado.getId(), dtoTipoHabitacionRespuesta.getId());
        Mockito.verify(repositorioTipoHabitacion, Mockito.times(1)).existePorId(Mockito.anyLong());
        Mockito.verify(daoTipoHabitacion, Mockito.times(1)).obtenerPorId(Mockito.anyLong());
    }

    @Test
    void deberiaFallarAlObtenerElTipoDeHabitacionPorId() {

        // arrange
        final String expecionTipoHabitacionNoEncontrado = "El registro del tipo de habitación no fue encontrado";

        DaoTipoHabitacion daoTipoHabitacion = Mockito.mock(DaoTipoHabitacion.class);
        RepositorioTipoHabitacion repositorioTipoHabitacion = Mockito.mock(RepositorioTipoHabitacion.class);

        Mockito.when(repositorioTipoHabitacion.existePorId(Mockito.anyLong())).thenReturn(false);

        ServicioListarTipoHabitacion servicioListarTipoHabitacion = new ServicioListarTipoHabitacion(daoTipoHabitacion, repositorioTipoHabitacion);

        //- assert
        BasePrueba.assertThrows(()-> servicioListarTipoHabitacion.obtenerTipoHabitacionPorId(1L), ExcepcionSinDatos.class, expecionTipoHabitacionNoEncontrado);
        Mockito.verify(repositorioTipoHabitacion, Mockito.times(1)).existePorId(Mockito.anyLong());
    }

}
