package com.ceiba.reserva.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.testdatabuilder.ComandoReservaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorReserva.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorReservaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    void deberiaCrearLaReserva() throws Exception{

        // arrange
        ComandoReserva comandoReserva = new ComandoReservaTestDataBuilder().build();

        // act - assert
        mocMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReserva)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor':2}"));
    }

    @Test
    void deberiaActualizarLaReserva() throws Exception{

        // arrange
        Long id = 1L;
        ComandoReserva comandoReserva = new ComandoReservaTestDataBuilder().build();

        // act - assert
        mocMvc.perform(put("/reservas/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReserva)))
                .andExpect(status().isOk());
    }

    @Test
    void deberiaEliminarUnUsuario() throws Exception {

        // arrange
        Long id = 1L;

        // act - assert
        mocMvc.perform(delete("/reservas/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
