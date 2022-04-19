package com.ceiba.dia.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.dia.testdatabuilder.DiaTestDataBuilder;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiaTest {

    @Test
    void deberiaCrearCorrectamenteElDia() {

        // Arrange
        final BigDecimal valorPorHoraEsperado = new BigDecimal("3000");
        final String diaEsperado = LocalDateTime.now().getDayOfWeek().name();

        //act
        Dia dia = new DiaTestDataBuilder().conId(1L).build();

        //assert
        assertEquals(1L, dia.getId());
        assertEquals(diaEsperado, dia.getNombre());
        assertTrue(valorPorHoraEsperado.compareTo(dia.getValorPorHora()) == 0);
        assertEquals(true, dia.isEstado());
    }

    @Test
    void deberiaFallarSinNombreDelDia() {

        //Arrange
        final String excepcion = "Se debe ingresar el nombre del día";
        DiaTestDataBuilder diaTestDataBuilder = new DiaTestDataBuilder().conNombre(null);

        //act-assert
        BasePrueba.assertThrows(() -> {diaTestDataBuilder.build(); },  ExcepcionValorObligatorio.class,
                excepcion);
    }


    @Test
    void deberiaFallarSinValorPorHoraDelDia() {

        //Arrange
        final String excepcion = "Se debe ingresar el valor por hora del día";
        DiaTestDataBuilder diaTestDataBuilder = new DiaTestDataBuilder().conValorPorHora(null);

        //act-assert
        BasePrueba.assertThrows(() -> {diaTestDataBuilder.build(); },  ExcepcionValorObligatorio.class,
                excepcion);
    }

    @Test
    void deberiaFallarSielValorPorHoraDelDiaEsNegativo() {

        //Arrange
        final String excepcion = "El valor mínimo por hora del día es $0";
        final BigDecimal valorPorHora = new BigDecimal("-4");
        DiaTestDataBuilder diaTestDataBuilder = new DiaTestDataBuilder().conValorPorHora(valorPorHora);

        //act-assert
        BasePrueba.assertThrows(() -> {diaTestDataBuilder.build(); },  ExcepcionValorInvalido.class,
                excepcion);
    }

}
