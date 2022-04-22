package com.ceiba.reserva.controlador;

import com.ceiba.ApplicationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorReserva.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorReservaTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    void deberiaObtenerLaReservaPorId() throws Exception {
        // arrange
        Long id = 1L;

        // act - assert
        mocMvc.perform(get("/reservas/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

    }

    @Test
    void deberiaFallarNoEncuentraLaReservaPorId() throws Exception {
        // arrange
        Long id = 0L;

        // act - assert
        mocMvc.perform(get("/reservas/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());


    }

    @Test
    void deberiaObtenerLasReservasPorUsuarioId() throws Exception {
        // arrange
        Long usuarioId = 1L;

        // act - assert
        mocMvc.perform(get("/reservas/por-usuario-id/{usuarioId}", usuarioId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").exists());

    }

    @Test
    void deberiaObtenerLasReservas() throws Exception {

        // act - assert
        mocMvc.perform(get("/reservas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").exists());

    }

    @Test
    void deberiaObtenerElTcrm() throws Exception {

        // act - assert
        mocMvc.perform(get("/reservas/tcrm")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

    }

    @Test
    void deberiaFallarElTcrm() throws Exception {

        // act - assert
        mocMvc.perform(post("https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService")
                        .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is4xxClientError());
    }


}
