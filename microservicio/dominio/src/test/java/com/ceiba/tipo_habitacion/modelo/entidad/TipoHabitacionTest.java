package com.ceiba.tipo_habitacion.modelo.entidad;


import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.testdatabuilder.ReservaTestDataBuilder;
import com.ceiba.tipo_habitacion.testdatabuilder.TipoHabitacionTestDataBuilder;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TipoHabitacionTest {


    @Test
    void deberiaCrearCorrectamenteElTipoHabitacion() {

        TipoHabitacion tipoHabitacion = new TipoHabitacionTestDataBuilder().conId(1L).build();

        //assert
        assertEquals(1L, tipoHabitacion.getId());
        assertEquals("PERSONAL", tipoHabitacion.getNombre());
        assertEquals("8500", tipoHabitacion.getValor().toPlainString());
        assertEquals(true, tipoHabitacion.isEstado());
    }

    @Test
    void deberiaFallarEsRequeridoElNombre() {


        final String excepcion = "Se debe ingresar el nombre";

        //act
        TipoHabitacionTestDataBuilder tipoHabitacionTestDataBuilder = new TipoHabitacionTestDataBuilder().conNombre(null);

        BasePrueba.assertThrows(() -> {tipoHabitacionTestDataBuilder.build(); },  ExcepcionValorObligatorio.class,
                excepcion);
    }

    @Test
    void deberiaFallarElValorEsNegativo() {

        final String excepcion = "El valor mínimo del tipo de habitación es $0";
        final BigDecimal valor = new BigDecimal("-1");

        //act
        TipoHabitacionTestDataBuilder tipoHabitacionTestDataBuilder = new TipoHabitacionTestDataBuilder().conValor(valor);

        BasePrueba.assertThrows(() -> {tipoHabitacionTestDataBuilder.build(); },  ExcepcionValorInvalido.class,
                excepcion);
    }

}
