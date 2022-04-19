package com.ceiba.tipo_pago.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.tipo_pago.modelo.dto.DtoTipoPago;
import com.ceiba.tipo_pago.puerto.dao.DaoTipoPago;
import com.ceiba.tipo_pago.puerto.repositorio.RepositorioTipoPago;
import com.ceiba.tipo_pago.testdatabuilder.DtoTipoPagoTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServicioListarTipoPagoTest {


    @Test
    void deberiaObtenerElTipoDePagoPorId() {

        // arrange
        DtoTipoPago dtoTipoPago = new DtoTipoPagoTestDataBuilder().conId(1L).build();

        DaoTipoPago daoTipoPago = Mockito.mock(DaoTipoPago.class);
        RepositorioTipoPago repositorioTipoPago = Mockito.mock(RepositorioTipoPago.class);

        Mockito.when(repositorioTipoPago.existePorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoTipoPago.obtenerPorId(Mockito.anyLong())).thenReturn(dtoTipoPago);

        ServicioListarTipoPago servicioListarTipoPago = new ServicioListarTipoPago(daoTipoPago, repositorioTipoPago);

        // act
        DtoTipoPago dtoTipoPagoRespuesta = servicioListarTipoPago.obtenerTipoPagoPorId(1L);

        //- assert
        assertEquals(dtoTipoPago.getId(), dtoTipoPagoRespuesta.getId());
        Mockito.verify(repositorioTipoPago, Mockito.times(1)).existePorId(Mockito.anyLong());
        Mockito.verify(daoTipoPago, Mockito.times(1)).obtenerPorId(Mockito.anyLong());
    }

    @Test
    void deberiaFallarAlObtenerEltipoDePagoPorId() {

        // arrange
        final String expecionTipoPagoNoEncontrado = "El registro del tipo de pago no fue encontrado";

        DaoTipoPago daoTipoPago = Mockito.mock(DaoTipoPago.class);
        RepositorioTipoPago repositorioTipoPago = Mockito.mock(RepositorioTipoPago.class);

        Mockito.when(repositorioTipoPago.existePorId(Mockito.anyLong())).thenReturn(false);

        ServicioListarTipoPago servicioListarTipoPago = new ServicioListarTipoPago(daoTipoPago, repositorioTipoPago);

        //- assert
        BasePrueba.assertThrows(()-> servicioListarTipoPago.obtenerTipoPagoPorId(1L), ExcepcionSinDatos.class, expecionTipoPagoNoEncontrado);
        Mockito.verify(repositorioTipoPago, Mockito.times(1)).existePorId(Mockito.anyLong());
    }


    @Test
    void deberiaObtenerLaListaLlenaDeTiposDePago() {

        // arrange
        List<DtoTipoPago> tipoPagosEsperado = Arrays.asList(new DtoTipoPagoTestDataBuilder().conId(1L).build());

        DaoTipoPago daoTipoPago = Mockito.mock(DaoTipoPago.class);
        RepositorioTipoPago repositorioTipoPago = Mockito.mock(RepositorioTipoPago.class);

        Mockito.when(daoTipoPago.listar()).thenReturn(tipoPagosEsperado);

        ServicioListarTipoPago servicioListarTipoPago = new ServicioListarTipoPago(daoTipoPago, repositorioTipoPago);

        // act
        List<DtoTipoPago> tipoPagosRespuesta = servicioListarTipoPago.ejecutar();

        //- assert
        assertTrue(!tipoPagosRespuesta.isEmpty());
        assertEquals(tipoPagosEsperado.size(), tipoPagosRespuesta.size());
        Mockito.verify(daoTipoPago, Mockito.times(1)).listar();
    }

    @Test
    void deberiaObtenerLaListaVaciaDeTiposDePago() {

        // arrange
        List<DtoTipoPago> tipoPagosEsperado = new ArrayList<>();

        DaoTipoPago daoTipoPago = Mockito.mock(DaoTipoPago.class);
        RepositorioTipoPago repositorioTipoPago = Mockito.mock(RepositorioTipoPago.class);

        Mockito.when(daoTipoPago.listar()).thenReturn(tipoPagosEsperado);

        ServicioListarTipoPago servicioListarTipoPago = new ServicioListarTipoPago(daoTipoPago, repositorioTipoPago);

        // act
        List<DtoTipoPago> tipoPagosRespuesta = servicioListarTipoPago.ejecutar();

        //- assert
        assertTrue(tipoPagosRespuesta.isEmpty());
        assertEquals(tipoPagosEsperado.size(), tipoPagosRespuesta.size());
        Mockito.verify(daoTipoPago, Mockito.times(1)).listar();
    }


}
