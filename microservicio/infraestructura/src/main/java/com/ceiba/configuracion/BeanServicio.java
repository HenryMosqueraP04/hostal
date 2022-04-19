package com.ceiba.configuracion;

import com.ceiba.dia.puerto.dao.DaoDia;
import com.ceiba.dia.servicio.ServicioListarDia;
import com.ceiba.habitacion.puerto.dao.DaoHabitacion;
import com.ceiba.habitacion.puerto.repositorio.RepositorioHabitacion;
import com.ceiba.habitacion.servicio.ServicioListarHabitacion;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioActualizarReserva;
import com.ceiba.reserva.servicio.ServicioCrearReserva;
import com.ceiba.reserva.servicio.ServicioEliminarReserva;
import com.ceiba.reserva.servicio.ServicioListarReserva;
import com.ceiba.tipo_habitacion.puerto.dao.DaoTipoHabitacion;
import com.ceiba.tipo_habitacion.puerto.repositorio.RepositorioTipoHabitacion;
import com.ceiba.tipo_habitacion.servicio.ServicioListarTipoHabitacion;
import com.ceiba.tipo_pago.puerto.dao.DaoTipoPago;
import com.ceiba.tipo_pago.puerto.repositorio.RepositorioTipoPago;
import com.ceiba.tipo_pago.servicio.ServicioListarTipoPago;
import com.ceiba.usuario.puerto.dao.DaoUsuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioListarUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioListarUsuario servicioListarUsuario(DaoUsuario daoUsuario, RepositorioUsuario repositorioUsuario) {
        return new ServicioListarUsuario(daoUsuario, repositorioUsuario);
    }

    @Bean
    public ServicioListarDia servicioListarDia(DaoDia daoDia) {
        return new ServicioListarDia(daoDia);
    }

    @Bean
    public ServicioListarHabitacion servicioListarHabitacion(DaoHabitacion daoHabitacion, RepositorioHabitacion repositorioHabitacion) {
        return new ServicioListarHabitacion(daoHabitacion, repositorioHabitacion);
    }

    @Bean
    public ServicioActualizarReserva servicioActualizarReserva(RepositorioReserva repositorioReserva, ServicioListarUsuario servicioListarUsuario, ServicioListarDia servicioListarDia, ServicioListarHabitacion servicioListarHabitacion, ServicioListarTipoHabitacion servicioListarTipoHabitacion, ServicioListarTipoPago servicioListarTipoPago) {
        return new ServicioActualizarReserva(repositorioReserva, servicioListarUsuario, servicioListarDia, servicioListarHabitacion, servicioListarTipoHabitacion, servicioListarTipoPago);
    }

    @Bean
    public ServicioCrearReserva servicioCrearReserva(RepositorioReserva repositorioReserva, ServicioListarUsuario servicioListarUsuario, ServicioListarDia servicioListarDia, ServicioListarHabitacion servicioListarHabitacion, ServicioListarTipoHabitacion servicioListarTipoHabitacion, ServicioListarTipoPago servicioListarTipoPago) {
        return new ServicioCrearReserva(repositorioReserva, servicioListarUsuario, servicioListarDia, servicioListarHabitacion, servicioListarTipoHabitacion, servicioListarTipoPago);
    }

    @Bean
    public ServicioEliminarReserva servicioEliminarReserva(RepositorioReserva repositorioReserva) {
        return new ServicioEliminarReserva(repositorioReserva);
    }

    @Bean
    public ServicioListarReserva servicioListarReserva(DaoReserva daoReserva, RepositorioReserva repositorioReserva) {
        return new ServicioListarReserva(daoReserva, repositorioReserva);
    }

    @Bean
    public ServicioListarTipoHabitacion servicioListarTipoHabitacion(DaoTipoHabitacion daoTipoHabitacion, RepositorioTipoHabitacion repositorioTipoHabitacion) {
        return new ServicioListarTipoHabitacion(daoTipoHabitacion, repositorioTipoHabitacion);
    }

    @Bean
    public ServicioListarTipoPago servicioListarTipoPago(DaoTipoPago daoTipoPago, RepositorioTipoPago repositorioTipoPago) {
        return new ServicioListarTipoPago(daoTipoPago,repositorioTipoPago);
    }

}



