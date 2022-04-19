package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dia.modelo.dto.DtoDia;
import com.ceiba.dia.servicio.ServicioListarDia;
import com.ceiba.dia.testdatabuilder.DtoDiaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.habitacion.modelo.dto.DtoHabitacion;
import com.ceiba.habitacion.servicio.ServicioListarHabitacion;
import com.ceiba.habitacion.testdatabuilder.DtoHabitacionTestDataBuilder;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.testdatabuilder.ReservaTestDataBuilder;
import com.ceiba.tipo_habitacion.modelo.dto.DtoTipoHabitacion;
import com.ceiba.tipo_habitacion.servicio.ServicioListarTipoHabitacion;
import com.ceiba.tipo_habitacion.testdatabuilder.DtoTipoHabitacionTestDataBuilder;
import com.ceiba.tipo_pago.modelo.dto.DtoTipoPago;
import com.ceiba.tipo_pago.servicio.ServicioListarTipoPago;
import com.ceiba.tipo_pago.testdatabuilder.DtoTipoPagoTestDataBuilder;
import com.ceiba.usuario.servicio.ServicioListarUsuario;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ServicioActualizarReservaTest {

    @Test
    void deberiaValidarLaExistenciaPreviaDeLaReserva() {

        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conId(1L).build();

        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioListarUsuario servicioListarUsuario = Mockito.mock(ServicioListarUsuario.class);
        ServicioListarDia servicioListarDia = Mockito.mock(ServicioListarDia.class);
        ServicioListarHabitacion servicioListarHabitacion = Mockito.mock(ServicioListarHabitacion.class);
        ServicioListarTipoHabitacion servicioListarTipoHabitacion = Mockito.mock(ServicioListarTipoHabitacion.class);
        ServicioListarTipoPago servicioListarTipoPago = Mockito.mock(ServicioListarTipoPago.class);
        Mockito.when(repositorioReserva.existePorId(Mockito.anyLong())).thenReturn(false);

        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva, servicioListarUsuario,
                servicioListarDia, servicioListarHabitacion, servicioListarTipoHabitacion, servicioListarTipoPago);

        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarReserva.ejecutar(reserva), ExcepcionDuplicidad.class,"La reserva no existe en el sistema");
    }

    @Test
    void deberiaActualizarCorrectamenteEnElRepositorio() {

        // arrange
        List<DtoDia> dtoDias = Arrays.asList(new DtoDiaTestDataBuilder().conId(1L).build());
        DtoHabitacion dtoHabitacion = new DtoHabitacionTestDataBuilder().conId(1L).build();
        DtoTipoHabitacion dtoTipoHabitacion = new DtoTipoHabitacionTestDataBuilder().conId(1L).build();
        DtoTipoPago dtoTipoPago = new DtoTipoPagoTestDataBuilder().conId(1L).build();

        Reserva reserva = new ReservaTestDataBuilder().conId(1L).build();

        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioListarUsuario servicioListarUsuario = Mockito.mock(ServicioListarUsuario.class);
        ServicioListarDia servicioListarDia = Mockito.mock(ServicioListarDia.class);
        ServicioListarHabitacion servicioListarHabitacion = Mockito.mock(ServicioListarHabitacion.class);
        ServicioListarTipoHabitacion servicioListarTipoHabitacion = Mockito.mock(ServicioListarTipoHabitacion.class);
        ServicioListarTipoPago servicioListarTipoPago = Mockito.mock(ServicioListarTipoPago.class);

        Mockito.when(servicioListarDia.ejecutar()).thenReturn(dtoDias);
        Mockito.when(servicioListarHabitacion.obtenerHabitacionPorId(Mockito.anyLong())).thenReturn(dtoHabitacion);
        Mockito.when(servicioListarTipoHabitacion.obtenerTipoHabitacionPorId(Mockito.anyLong())).thenReturn(dtoTipoHabitacion);
        Mockito.when(servicioListarTipoPago.obtenerTipoPagoPorId(Mockito.anyLong())).thenReturn(dtoTipoPago);

        Mockito.when(repositorioReserva.existePorId(Mockito.anyLong())).thenReturn(true);

        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva, servicioListarUsuario,
                servicioListarDia, servicioListarHabitacion, servicioListarTipoHabitacion, servicioListarTipoPago);

        // act
        servicioActualizarReserva.ejecutar(reserva);

        //assert
        Mockito.verify(repositorioReserva,Mockito.times(1)).actualizar(reserva);
    }

}
