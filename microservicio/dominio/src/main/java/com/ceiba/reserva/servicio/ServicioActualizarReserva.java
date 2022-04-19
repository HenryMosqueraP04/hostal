package com.ceiba.reserva.servicio;

import com.ceiba.dia.modelo.dto.DtoDia;
import com.ceiba.dia.servicio.ServicioListarDia;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.habitacion.modelo.dto.DtoHabitacion;
import com.ceiba.habitacion.servicio.ServicioListarHabitacion;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.tipo_habitacion.modelo.dto.DtoTipoHabitacion;
import com.ceiba.tipo_habitacion.servicio.ServicioListarTipoHabitacion;
import com.ceiba.tipo_pago.modelo.dto.DtoTipoPago;
import com.ceiba.tipo_pago.servicio.ServicioListarTipoPago;
import com.ceiba.usuario.servicio.ServicioListarUsuario;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public class ServicioActualizarReserva {

    private static final String LA_RESERVA_NO_EXISTE_EN_EL_SISTEMA = "La reserva no existe en el sistema";
    private final int INCREMENTO_HORAS_RESERVA = 1;
    private final int FACTOR_DIVISION_PORCENTAJE = 100;

    private final RepositorioReserva repositorioReserva;
    private final ServicioListarUsuario servicioListarUsuario;
    private final ServicioListarDia servicioListarDia;
    private final ServicioListarHabitacion servicioListarHabitacion;
    private final ServicioListarTipoHabitacion servicioListarTipoHabitacion;
    private final ServicioListarTipoPago servicioListarTipoPago;


    public ServicioActualizarReserva(RepositorioReserva repositorioReserva, ServicioListarUsuario servicioListarUsuario, ServicioListarDia servicioListarDia, ServicioListarHabitacion servicioListarHabitacion, ServicioListarTipoHabitacion servicioListarTipoHabitacion, ServicioListarTipoPago servicioListarTipoPago) {
        this.repositorioReserva = repositorioReserva;
        this.servicioListarUsuario = servicioListarUsuario;
        this.servicioListarDia = servicioListarDia;
        this.servicioListarHabitacion = servicioListarHabitacion;
        this.servicioListarTipoHabitacion = servicioListarTipoHabitacion;
        this.servicioListarTipoPago = servicioListarTipoPago;
    }

    public void ejecutar(Reserva reserva) {

        this.validarExistenciaPrevia(reserva);
        this.servicioListarUsuario.obtenerUsuarioPorId(reserva.getUsuarioId());
        this.servicioListarHabitacion.validarHabitacionOcupadaPorId(reserva.getHabitacionId());

        this.calcularValorReservaPorHoras(reserva);
        this.calcularValorReservaPorTipoDeHabitacion(reserva);
        this.calcularValorReservaPorTipoDePago(reserva);

        this.repositorioReserva.actualizar(reserva);
    }

    private void validarExistenciaPrevia(Reserva reserva) {
        boolean existe = this.repositorioReserva.existePorId(reserva.getId());
        if (!existe) {
            throw new ExcepcionDuplicidad(LA_RESERVA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private void calcularValorReservaPorHoras(Reserva reserva) {

        LocalDateTime fechaInicio = reserva.getFechaInicio();
        LocalDateTime fechaFin = reserva.getFechaFin();

        List<DtoDia> dias = this.servicioListarDia.ejecutar();

        while (fechaInicio.isBefore(fechaFin)) {

            DayOfWeek dia = fechaInicio.getDayOfWeek();

            boolean existe = dias.stream().filter(d -> d.getNombre().equalsIgnoreCase(dia.name())).findFirst().isPresent();

            if (existe) {
                DtoDia dtoDia = dias.stream().filter(d -> d.getNombre().equalsIgnoreCase(dia.name())).findFirst().get();
                BigDecimal valorCalculadoPorHorasDelDia = reserva.getValor().add(dtoDia.getValorPorHora());
                reserva.establecerValorReserva(valorCalculadoPorHorasDelDia);
            }

            fechaInicio = fechaInicio.plusHours(INCREMENTO_HORAS_RESERVA);
        }

    }

    private void calcularValorReservaPorTipoDeHabitacion(Reserva reserva) {

        DtoHabitacion dtoHabitacion = this.servicioListarHabitacion.obtenerHabitacionPorId(reserva.getHabitacionId());
        DtoTipoHabitacion dtoTipoHabitacion = this.servicioListarTipoHabitacion.obtenerTipoHabitacionPorId(dtoHabitacion.getTipoHabitacionId());

        BigDecimal valorCalculadoPorTipoHabitacion = reserva.getValor().add(dtoTipoHabitacion.getValor());
        reserva.establecerValorReserva(valorCalculadoPorTipoHabitacion);

    }

    private void calcularValorReservaPorTipoDePago(Reserva reserva) {

        DtoTipoPago dtoTipoPago = this.servicioListarTipoPago.obtenerTipoPagoPorId(reserva.getTipoPagoId());
        BigDecimal porcentajeCalculado = new BigDecimal(Double.toString(dtoTipoPago.getPorcentajeImpuesto()))
                .divide(new BigDecimal(FACTOR_DIVISION_PORCENTAJE)).multiply(reserva.getValor());
        BigDecimal valorImpuesto = reserva.getValor().add(porcentajeCalculado);

        reserva.establecerValorReserva(valorImpuesto);

    }
}
