package com.techworld.TiendaComputadores.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TestController.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void handleNotFoundException_ShouldReturnNotFoundStatus() throws Exception {
        mockMvc.perform(get("/test/not-found"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Resource not found!"));
    }

    @Test
    void handleGeneralException_ShouldReturnInternalServerError() throws Exception {
        mockMvc.perform(get("/test/general-error"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string("An unexpected error occurred: Something went wrong"));
    }
}

@RestController
class TestController {

    @GetMapping("/test/not-found")
    public void throwNotFoundException() {
        throw new NotFoundException("Resource not found!");
    }

    @GetMapping("/test/general-error")
    public void throwGeneralException() {
        throw new RuntimeException("Something went wrong");
    }
}
