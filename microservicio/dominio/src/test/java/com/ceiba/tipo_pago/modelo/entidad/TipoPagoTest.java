package com.ceiba.tipo_pago.modelo.entidad;


import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.tipo_habitacion.modelo.entidad.TipoHabitacion;
import com.ceiba.tipo_habitacion.testdatabuilder.TipoHabitacionTestDataBuilder;
import com.ceiba.tipo_pago.testdatabuilder.TipoPagoTestDataBuilder;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TipoPagoTest {


    @Test
    void deberiaCrearCorrectamenteElTipoHabitacion() {

        TipoPago tipoHabitacion = new TipoPagoTestDataBuilder().conId(1L).build();

        //assert
        assertEquals(1L, tipoHabitacion.getId());
        assertEquals("EFECTIVO", tipoHabitacion.getNombre());
        assertEquals(1, tipoHabitacion.getPorcentajeImpuesto());
        assertEquals(true, tipoHabitacion.isEstado());
    }

    @Test
    void deberiaFallarEsRequeridoElNombre() {


        final String excepcion = "Se debe ingresar el nombre";

        //act
        TipoPagoTestDataBuilder tipoHabitacionTestDataBuilder = new TipoPagoTestDataBuilder().conNombre(null);

        BasePrueba.assertThrows(() -> {tipoHabitacionTestDataBuilder.build(); },  ExcepcionValorObligatorio.class,
                excepcion);
    }

    @Test
    void deberiaFallarElPorcentajeTieneIntervalos() {

        final String excepcion = "El porcentaje debe estar entre 0.0 y 99.9";

        //act
        TipoPagoTestDataBuilder tipoPagoTestDataBuilder = new TipoPagoTestDataBuilder().conPorcentajeImpuesto(100.1);

        BasePrueba.assertThrows(() -> {tipoPagoTestDataBuilder.build(); },  ExcepcionValorInvalido.class,
                excepcion);
    }

}
