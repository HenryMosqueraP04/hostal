package com.ceiba.reserva.servicio;

import com.ceiba.dia.modelo.dto.DtoDia;
import com.ceiba.tipo_habitacion.modelo.dto.DtoTipoHabitacion;
import com.ceiba.tipo_pago.modelo.dto.DtoTipoPago;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public abstract class ServicioReserva {


    private static final int VALOR_INICIAL_RESERVA = 0;
    private static final int INCREMENTO_HORAS_RESERVA = 1;
    private static final int FACTOR_DIVISION_PORCENTAJE = 100;

    public BigDecimal calcularYEstablecerValorReserva(
            List<DtoDia> dtoDias,
            DtoTipoHabitacion dtoTipoHabitacion,
            DtoTipoPago dtoTipoPago,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {

        BigDecimal valorInicial = new BigDecimal(VALOR_INICIAL_RESERVA);
        BigDecimal valorPorHora = this.calcularValorReservaPorHoras(valorInicial, dtoDias, fechaInicio, fechaFin);
        BigDecimal valorPorTipoDeHabitacion = this.calcularValorReservaPorTipoDeHabitacion(valorPorHora, dtoTipoHabitacion);
        return this.calcularValorReservaPorTipoDePago(valorPorTipoDeHabitacion, dtoTipoPago);
    }

    private BigDecimal calcularValorReservaPorHoras(BigDecimal valor, List<DtoDia> dtoDias, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        LocalDateTime inicio = fechaInicio;
        LocalDateTime fin = fechaFin;

        while (inicio.isBefore(fin)) {
            DayOfWeek dia = fechaInicio.getDayOfWeek();

            Optional<DtoDia> optionalDtoDia = dtoDias.stream().filter(d -> d.getNombre().equalsIgnoreCase(dia.name())).findFirst();

            if (optionalDtoDia.isPresent()) {
                DtoDia dtoDia = optionalDtoDia.get();
                valor = valor.add(dtoDia.getValorPorHora());
            }

            inicio = inicio.plusHours(INCREMENTO_HORAS_RESERVA);
        }

        return valor;

    }

    private BigDecimal calcularValorReservaPorTipoDeHabitacion(BigDecimal valor, DtoTipoHabitacion dtoTipoHabitacion) {
        valor = valor.add(dtoTipoHabitacion.getValor());
        return valor;
    }

    private BigDecimal calcularValorReservaPorTipoDePago(BigDecimal valor, DtoTipoPago dtoTipoPago) {

        BigDecimal porcentajeCalculado = new BigDecimal(Double.toString(dtoTipoPago.getPorcentajeImpuesto()))
                .divide(new BigDecimal(FACTOR_DIVISION_PORCENTAJE)).multiply(valor);
        valor = valor.add(porcentajeCalculado);
        return valor;
    }

}
