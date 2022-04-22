package com.ceiba.reserva.modelo.entidad;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarValorMenorMatematico;

@Getter
public class Reserva {

    private static final String MENSAJE_USUARIO_ID_REQUERIDO = "Se debe ingresar el id del usuario";
    private static final String MENSAJE_HABITACION_ID_REQUERIDO = "Se debe ingresar el id de la habitación";
    private static final String MENSAJE_TIPO_PAGO_ID_REQUERIDO = "Se debe ingresar el id del tipo de pago";
    private static final String MENSAJE_FECHA_INICIO_REQUERIDO = "Se debe ingresar la fecha de inicio";
    private static final String MENSAJE_FECHA_FIN_REQUERIDO = "Se debe ingresar la fecha de fin";
    private static final String MENSAJE_FECHA_INICIO_MAYOR_QUE_FECHA_ACTUAL = "La fecha de inicio tiene que ser mayor a la actual";
    private static final String MENSAJE_HORA_MINIMA = "La fecha de fin debe ser mínimo %s hora mayor a la fecha de inicio";
    private static final String MENSAJE_VALOR_REQUERIDO = "Se debe ingresar el valor de la reserva";
    private static final String MENSAJE_MINIMO_VALOR_RESERVA = "El valor mínimo de la reserva es $%s";

    private static final String MINIMO_VALOR_RESERVA= "0";
    private static final int HORA_MINIMA= 1;
    private static final int HORA_VALIDACION = 1;

    private Long id;
    private Long usuarioId;
    private Long habitacionId;
    private Long tipoPagoId;
    private BigDecimal valor;
    private boolean estado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public Reserva(Long id, Long usuarioId, Long habitacionId, Long tipoPagoId,
                   boolean estado, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

        validarObligatorio(usuarioId,MENSAJE_USUARIO_ID_REQUERIDO);
        validarObligatorio(habitacionId,MENSAJE_HABITACION_ID_REQUERIDO);
        validarObligatorio(tipoPagoId,MENSAJE_TIPO_PAGO_ID_REQUERIDO);
        validarObligatorio(fechaInicio, MENSAJE_FECHA_INICIO_REQUERIDO);
        validarObligatorio(fechaFin, MENSAJE_FECHA_FIN_REQUERIDO);

        LocalDateTime fechaActual = this.normalizarFecha(LocalDateTime.now());
        fechaInicio = this.normalizarFecha(fechaInicio);
        fechaFin = this.normalizarFecha(fechaFin);

        this.validarFechaInicialMayorQueActual(fechaActual, fechaInicio,MENSAJE_FECHA_INICIO_MAYOR_QUE_FECHA_ACTUAL);
        this.validarReservaPorHoraMinima(fechaInicio, fechaFin, String.format(MENSAJE_HORA_MINIMA, String.valueOf(HORA_MINIMA)));

        this.id = id;
        this.usuarioId = usuarioId;
        this.habitacionId = habitacionId;
        this.tipoPagoId = tipoPagoId;
        this.valor = new BigDecimal(MINIMO_VALOR_RESERVA);
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public void establecerValorReserva(BigDecimal valor){
        validarObligatorio(valor,MENSAJE_VALOR_REQUERIDO);
        validarValorMenorMatematico(valor, new BigDecimal(MINIMO_VALOR_RESERVA), String.format(MENSAJE_MINIMO_VALOR_RESERVA, MINIMO_VALOR_RESERVA));
        this.valor = valor;
    }

    private void validarReservaPorHoraMinima(LocalDateTime fechaInicial, LocalDateTime fechaFinal, String mensaje){
        long horas = fechaInicial.until(fechaFinal, ChronoUnit.HOURS);
        if(horas < HORA_MINIMA){
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    private void validarFechaInicialMayorQueActual(LocalDateTime fechaActual, LocalDateTime fechaInicial, String mensaje){
        fechaActual = fechaActual.minusMinutes(5);
        if(fechaActual.isAfter(fechaInicial)){
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    private LocalDateTime normalizarFecha(LocalDateTime fecha){
        return LocalDateTime.of(fecha.getYear(), fecha.getMonthValue(), fecha.getDayOfMonth(), fecha.getHour(), fecha.getMinute());
    }

}
