package com.ceiba.dia.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dia.modelo.dto.DtoDia;
import com.ceiba.dia.puerto.dao.DaoDia;
import com.ceiba.dia.testdatabuilder.DtoDiaTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServicioListarDiaTest {

    @Test
    void deberiaDevolverUnaListaDeDias() {

        // arrange
        List<DtoDia> dtoDias = Arrays.asList(new DtoDiaTestDataBuilder().build());
        DaoDia daoDia = Mockito.mock(DaoDia.class);
        Mockito.when(daoDia.listar()).thenReturn(dtoDias);

        ServicioListarDia servicioListarDia = new ServicioListarDia(daoDia);

        // act
        List<DtoDia> reservas = servicioListarDia.ejecutar();

        //- assert
        assertTrue(!reservas.isEmpty());
        assertEquals(dtoDias.size(), reservas.size());
        Mockito.verify(daoDia, Mockito.times(1)).listar();
    }

}
