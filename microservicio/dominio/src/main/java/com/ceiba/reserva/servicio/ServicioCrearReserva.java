package com.ceiba.reserva.servicio;

import com.ceiba.dia.modelo.dto.DtoDia;
import com.ceiba.dia.servicio.ServicioListarDia;
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
import java.util.List;


public class ServicioCrearReserva extends ServicioReserva {

    private final RepositorioReserva repositorioReserva;
    private final ServicioListarUsuario servicioListarUsuario;
    private final ServicioListarDia servicioListarDia;
    private final ServicioListarHabitacion servicioListarHabitacion;
    private final ServicioListarTipoHabitacion servicioListarTipoHabitacion;
    private final ServicioListarTipoPago servicioListarTipoPago;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva, ServicioListarUsuario servicioListarUsuario,
                                ServicioListarDia servicioListarDia, ServicioListarHabitacion servicioListarHabitacion, ServicioListarTipoHabitacion servicioListarTipoHabitacion, ServicioListarTipoPago servicioListarTipoPago) {
        this.repositorioReserva = repositorioReserva;
        this.servicioListarUsuario = servicioListarUsuario;
        this.servicioListarDia = servicioListarDia;
        this.servicioListarHabitacion = servicioListarHabitacion;
        this.servicioListarTipoHabitacion = servicioListarTipoHabitacion;
        this.servicioListarTipoPago = servicioListarTipoPago;
    }

    public Long ejecutar(Reserva reserva) {

        this.servicioListarUsuario.validarExistenciaUsuarioPorId(reserva.getUsuarioId());
        List<DtoDia> dtoDias =this.servicioListarDia.ejecutar();
        DtoHabitacion dtoHabitacion = this.servicioListarHabitacion.obtenerHabitacionPorId(reserva.getHabitacionId());
        this.servicioListarHabitacion.validarHabitacionOcupadaPorId(dtoHabitacion.getId());
        DtoTipoHabitacion dtoTipoHabitacion = this.servicioListarTipoHabitacion.obtenerTipoHabitacionPorId(dtoHabitacion.getTipoHabitacionId());
        DtoTipoPago dtoTipoPago = this.servicioListarTipoPago.obtenerTipoPagoPorId(reserva.getTipoPagoId());

        BigDecimal valor = calcularYEstablecerValorReserva(dtoDias,dtoTipoHabitacion,dtoTipoPago,reserva.getFechaInicio(), reserva.getFechaFin());

        reserva.establecerValorReserva(valor);

        return this.repositorioReserva.crear(reserva);
    }



}
