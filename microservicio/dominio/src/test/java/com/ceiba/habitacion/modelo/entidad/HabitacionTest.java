package com.ceiba.habitacion.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.habitacion.testdatabuilder.HabitacionTestDataBuilder;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class HabitacionTest {

    @Test
    void deberiaCrearCorrectamenteLaHabitacion() {

        //act
        Habitacion habitacion = new HabitacionTestDataBuilder().conId(1L).build();
        //assert
        assertEquals(1L, habitacion.getId());
        assertEquals("HABITACION PERSONAL", habitacion.getNombre());
        assertEquals(1L, habitacion.getTipoHabitacionId());
        assertEquals(true, habitacion.isEstado());
    }

    @Test
    void deberiaFallarSinNombreDeHabitacion() {

        //Arrange
        final String excepcion = "Se debe ingresar el nombre de habitación";
        HabitacionTestDataBuilder habitacionTestDataBuilder = new HabitacionTestDataBuilder().conNombre(null);

        //act-assert
        BasePrueba.assertThrows(() -> {habitacionTestDataBuilder.build(); },  ExcepcionValorObligatorio.class,
                excepcion);
    }


    @Test
    void deberiaFallarSinTipoHabitacionId() {

        //Arrange
        final String excepcion = "Se debe ingresar el tipo de habitación";
        HabitacionTestDataBuilder habitacionTestDataBuilder = new HabitacionTestDataBuilder().conTipoHabitacionId(null);

        //act-assert
        BasePrueba.assertThrows(() -> {habitacionTestDataBuilder.build(); },  ExcepcionValorObligatorio.class,
                excepcion);
    }

}
