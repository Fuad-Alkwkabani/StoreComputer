package com.techworld.TiendaComputadores.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techworld.TiendaComputadores.dto.ComputerDTO;
import com.techworld.TiendaComputadores.model.Computer;
import com.techworld.TiendaComputadores.service.ComputerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ComputerController.class)
public class ComputerControllerTest {

    @Autowired
    private MockMvc mockMvc;

   @MockBean
    private ComputerService computerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetComputersByStore() throws Exception {
        Computer computer = new Computer();
        computer.setId(1L);
        computer.setBrand("Dell");
        computer.setMemory(16);
        computer.setProcessor("Intel i7");
        computer.setOperatingSystem("Windows 11");
        computer.setPrice(1200.50);

        List<Computer> computers = Arrays.asList(computer);

        when(computerService.getComputersByStore(1L)).thenReturn(computers);

        mockMvc.perform(get("/api/computers/store/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].brand").value("Dell"))
                .andExpect(jsonPath("$[0].memory").value(16))
                .andExpect(jsonPath("$[0].processor").value("Intel i7"))
                .andExpect(jsonPath("$[0].operatingSystem").value("Windows 11"))
                .andExpect(jsonPath("$[0].price").value(1200.50));

        verify(computerService, times(1)).getComputersByStore(1L);
    }

    @Test
    public void testAddComputer() throws Exception {
        ComputerDTO computerDTO = new ComputerDTO();
        computerDTO.setBrand("Dell");
        computerDTO.setMemory(16);
        computerDTO.setProcessor("Intel i7");
        computerDTO.setOperatingSystem("Windows 11");
        computerDTO.setPrice(1200.50);

        Computer computer = new Computer();
        computer.setId(1L);
        computer.setBrand("Dell");
        computer.setMemory(16);
        computer.setProcessor("Intel i7");
        computer.setOperatingSystem("Windows 11");
        computer.setPrice(1200.50);

        when(computerService.addComputer(Mockito.any(ComputerDTO.class), eq(1L))).thenReturn(computer);

        mockMvc.perform(post("/api/computers/store/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(computerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.brand").value("Dell"))
                .andExpect(jsonPath("$.memory").value(16))
                .andExpect(jsonPath("$.processor").value("Intel i7"))
                .andExpect(jsonPath("$.operatingSystem").value("Windows 11"))
                .andExpect(jsonPath("$.price").value(1200.50));

        verify(computerService, times(1)).addComputer(Mockito.any(ComputerDTO.class), eq(1L));
    }

    @Test
    public void testDeleteComputer() throws Exception {
        mockMvc.perform(delete("/api/computers/1"))
                .andExpect(status().isNoContent());

        
        verify(computerService, times(1)).deleteComputer(1L);
    }
}
