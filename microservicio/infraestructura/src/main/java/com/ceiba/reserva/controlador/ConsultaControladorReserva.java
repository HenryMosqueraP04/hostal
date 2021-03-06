package com.ceiba.reserva.controlador;


import com.ceiba.infraestructura.excepcion.ExcepcionTecnica;
import com.ceiba.reserva.consulta.ManejadorListarReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
@Api(tags = {"Controlador consulta reserva"})
@CrossOrigin("http://localhost:4200")
public class ConsultaControladorReserva {

    private final ManejadorListarReserva manejadorListarReserva;

    public ConsultaControladorReserva(ManejadorListarReserva manejadorListarReserva) {
        this.manejadorListarReserva = manejadorListarReserva;
    }

    @GetMapping("/{id}")
    @ApiOperation("Encontrar reserva por id")
    public DtoReserva obtenerPorId(@PathVariable Long id) {
        return this.manejadorListarReserva.obtenerPorId(id);
    }

    @GetMapping("/por-usuario-id/{usuarioId}")
    @ApiOperation("Listar reservas por id de usuario")
    public List<DtoReserva> listarPorUsuarioId(@PathVariable Long usuarioId) {
        return this.manejadorListarReserva.listarPorUsuarioId(usuarioId);
    }

    @GetMapping
    @ApiOperation("Listar reservas")
    public List<DtoReserva> listar() {
        return this.manejadorListarReserva.ejecutar();
    }

    @GetMapping("/tcrm")
    public ResponseEntity<String> obtenerTCRM() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        final String url = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService";
        final LocalDate fecha = LocalDate.now();

        StringBuilder body = new StringBuilder();
        body.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" \n");
        body.append("xmlns:act=\"http://action.trm.services.generic.action.superfinanciera.nexura.sc.com.co/\"> \n");
        body.append("<soapenv:Header/>\n");
        body.append("<soapenv:Body>\n");
        body.append("<act:queryTCRM>\n");
        body.append("<tcrmQueryAssociatedDate>");
        body.append(fecha);
        body.append("</tcrmQueryAssociatedDate>\n");
        body.append("</act:queryTCRM>\n");
        body.append("</soapenv:Body>\n");
        body.append("</soapenv:Envelope>");

        HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        final Optional<String> xmlEnTexto = Optional.of(restTemplate.postForEntity(url, request, String.class).getBody());

        return ResponseEntity.ok(this.obtenerTCRM(xmlEnTexto));
    }

    private String obtenerTCRM(Optional<String> xml) throws IOException {

        if (!xml.isPresent()) {
            throw new ExcepcionTecnica("TCMR no encontrado");
        }

        XmlMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(xml.get().getBytes());
        ObjectMapper objMapper = new ObjectMapper();
        return objMapper.writeValueAsString(jsonNode);

    }

}
