package com.techworld.TiendaComputadores.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techworld.TiendaComputadores.dto.StoreDTO;
import com.techworld.TiendaComputadores.model.Store;
import com.techworld.TiendaComputadores.service.StoreService;
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

@WebMvcTest(StoreController.class)
public class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllStores() throws Exception {
        Store store = new Store();
        store.setId(1L);
        store.setName("Tech World");
        store.setOwner("John Doe");
        store.setTaxId("123-456-789");

        List<Store> stores = Arrays.asList(store);

        when(storeService.getAllStores()).thenReturn(stores);

        mockMvc.perform(get("/api/stores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Tech World"))
                .andExpect(jsonPath("$[0].owner").value("John Doe"))
                .andExpect(jsonPath("$[0].taxId").value("123-456-789"));

        verify(storeService, times(1)).getAllStores();
    }

    @Test
    public void testGetStoreById() throws Exception {
        Store store = new Store();
        store.setId(1L);
        store.setName("Tech World");
        store.setOwner("John Doe");
        store.setTaxId("123-456-789");

        when(storeService.getStoreById(1L)).thenReturn(store);

        mockMvc.perform(get("/api/stores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Tech World"))
                .andExpect(jsonPath("$.owner").value("John Doe"))
                .andExpect(jsonPath("$.taxId").value("123-456-789"));

        verify(storeService, times(1)).getStoreById(1L);
    }

    @Test
    public void testAddStore() throws Exception {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName("Tech World");
        storeDTO.setOwner("John Doe");
        storeDTO.setTaxId("123-456-789");

        Store store = new Store();
        store.setId(1L);
        store.setName("Tech World");
        store.setOwner("John Doe");
        store.setTaxId("123-456-789");

        when(storeService.addStore(Mockito.any(StoreDTO.class))).thenReturn(store);

        mockMvc.perform(post("/api/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(storeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Tech World"))
                .andExpect(jsonPath("$.owner").value("John Doe"))
                .andExpect(jsonPath("$.taxId").value("123-456-789"));

        verify(storeService, times(1)).addStore(Mockito.any(StoreDTO.class));
    }

    @Test
    public void testDeleteStore() throws Exception {
        mockMvc.perform(delete("/api/stores/1"))
                .andExpect(status().isNoContent());

        verify(storeService, times(1)).deleteStore(1L);
    }
}
