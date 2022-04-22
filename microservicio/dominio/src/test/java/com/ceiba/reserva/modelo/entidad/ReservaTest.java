package com.ceiba.reserva.modelo.entidad;


import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservaTest {


    @Test
    void deberiaCrearCorrectamenteLaReserva() {

        // Arrange
        LocalDateTime localDateTime = this.normalizarFecha(LocalDateTime.now());
        LocalDateTime inicio = localDateTime;
        LocalDateTime fin = this.normalizarFecha(inicio.plusHours(1));

        BigDecimal valor = new BigDecimal("0");

        //act
        Reserva reserva = new ReservaTestDataBuilder().conId(1L).conFechaInicio(inicio).conFechaFin(fin).build();

        //assert
        assertEquals(1L, reserva.getId());
        assertEquals(1L, reserva.getUsuarioId());
        assertEquals(1L, reserva.getHabitacionId());
        assertEquals(1L, reserva.getTipoPagoId());
        assertEquals(valor.toPlainString(), reserva.getValor().toPlainString());
        assertEquals(true, reserva.isEstado());
        assertTrue(inicio.isEqual(reserva.getFechaInicio()));
        assertTrue(fin.isEqual(reserva.getFechaFin()));
    }

    @Test
    void deberiaFallarEsRequeridoElUsuarioId() {


        final String excepcion = "Se debe ingresar el id del usuario";

        //act
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conUsuarioId(null);

        BasePrueba.assertThrows(() -> {reservaTestDataBuilder.build(); },  ExcepcionValorObligatorio.class,
                excepcion);
    }

    @Test
    void deberiaFallarEsRequeridoLaHabitacionId() {


        final String excepcion = "Se debe ingresar el id de la habitación";

        //act
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conHabitacionId(null);

        BasePrueba.assertThrows(() -> {reservaTestDataBuilder.build(); },  ExcepcionValorObligatorio.class,
                excepcion);
    }

    @Test
    void deberiaFallarEsRequeridoElPagoId() {

        final String excepcion = "Se debe ingresar el id del tipo de pago";

        //act
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conTipoPagoId(null);

        BasePrueba.assertThrows(() -> {reservaTestDataBuilder.build(); },  ExcepcionValorObligatorio.class,
                excepcion);
    }

    @Test
    void deberiaFallarEsRequeridaLaFechaInicio() {

        final String excepcion = "Se debe ingresar la fecha de inicio";

        //act
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaInicio(null);

        BasePrueba.assertThrows(() -> {reservaTestDataBuilder.build(); },  ExcepcionValorObligatorio.class,
                excepcion);
    }
    @Test
    void deberiaFallarEsRequeridaLaFechaFin() {

        final String excepcion = "Se debe ingresar la fecha de fin";

        //act
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaFin(null);

        BasePrueba.assertThrows(() -> {reservaTestDataBuilder.build(); },  ExcepcionValorObligatorio.class,
                excepcion);
    }

    @Test
    void deberiaFallarEsRequeridoElValor() {

        final String excepcion = "Se debe ingresar el valor de la reserva";

        //act
        Reserva reserva = new ReservaTestDataBuilder().build();

        BasePrueba.assertThrows(() -> {reserva.establecerValorReserva(null); },  ExcepcionValorObligatorio.class,
                excepcion);
    }

    @Test
    void deberiaFallarElValorEsNegativo() {

        final String excepcion = "El valor mínimo de la reserva es $0";
        final BigDecimal valor = new BigDecimal("-1");

        //act
        Reserva reserva = new ReservaTestDataBuilder().build();

        BasePrueba.assertThrows(() -> {reserva.establecerValorReserva(valor); },  ExcepcionValorInvalido.class,
                excepcion);
    }

    @Test
    void deberiaFallarLaFechaInicioEsMenorQueLaActual() {

        // Arrange
        LocalDateTime fechaInicio = LocalDateTime.now();
        fechaInicio = fechaInicio.minusHours(1);
        final String excepcion = "La fecha de inicio tiene que ser mayor a la actual";

        //act
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaInicio(fechaInicio);

        BasePrueba.assertThrows(() -> {reservaTestDataBuilder.build(); },  ExcepcionValorInvalido.class,
                excepcion);
    }

    @Test
    void deberiaFallarFechaFinDebeSerMinimaDeUnaHora() {

        // Arrange
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fin = inicio.minusHours(1);

        final String excepcion = "La fecha de fin debe ser mínimo 1 hora mayor a la fecha de inicio";

        //act
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaInicio(inicio).conFechaFin(fin);

        BasePrueba.assertThrows(() -> {reservaTestDataBuilder.build(); },  ExcepcionValorInvalido.class,
                excepcion);
    }

    private LocalDateTime normalizarFecha(LocalDateTime fecha){
        return LocalDateTime.of(fecha.getYear(), fecha.getMonthValue(), fecha.getDayOfMonth(), fecha.getHour(), fecha.getMinute());
    }


}
