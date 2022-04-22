package com.ceiba.reserva.controlador;


import com.ceiba.reserva.consulta.ManejadorListarReserva;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@Api(tags = {"Controlador consulta reserva"})
@CrossOrigin("http://localhost:4200")
public class ConsultaControladorReserva {

    private final ManejadorListarReserva manejadorListarReserva;
    private final Logger logger = LoggerFactory.getLogger(getClass());

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
    public ResponseEntity<?> obtenerTCRM() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        final String url = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService";
        final LocalDate fecha = LocalDate.now();

        StringBuffer body = new StringBuffer();
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
        final String xmlEnTexto = restTemplate.postForEntity(url, request, String.class).getBody();

        return ResponseEntity.ok(this.getRespuestaXmlEnTexto(xmlEnTexto));
    }

    private String getRespuestaXmlEnTexto(String xml) {

        String res = "";
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode;

        try {
            jsonNode = xmlMapper.readTree(xml.getBytes());
            ObjectMapper objMapper = new ObjectMapper();
            res = objMapper.writeValueAsString(jsonNode);
        } catch (IOException ex) {
            logger.warn(ex.getMessage());
        }
        return res;
    }
}
