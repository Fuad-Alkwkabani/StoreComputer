package com.techworld.TiendaComputadores.service;

import com.techworld.TiendaComputadores.dto.StoreDTO;
import com.techworld.TiendaComputadores.exception.NotFoundException;
import com.techworld.TiendaComputadores.model.Store;
import com.techworld.TiendaComputadores.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoreServiceTest {

    @InjectMocks
    private StoreService storeService;

    @Mock
    private StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllStores_ShouldReturnListOfStores() {
        List<Store> stores = new ArrayList<>();
        stores.add(new Store());
        stores.add(new Store());

        when(storeRepository.findAll()).thenReturn(stores);

        List<Store> result = storeService.getAllStores();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(storeRepository, times(1)).findAll();
    }

    @Test
    void getStoreById_ShouldReturnStore_WhenStoreExists() {
        Long storeId = 1L;
        Store store = new Store();
        store.setId(storeId);

        when(storeRepository.findById(storeId)).thenReturn(Optional.of(store));

        Store result = storeService.getStoreById(storeId);

        assertNotNull(result);
        assertEquals(storeId, result.getId());
        verify(storeRepository, times(1)).findById(storeId);
    }

    @Test
    void getStoreById_ShouldThrowNotFoundException_WhenStoreDoesNotExist() {
        Long storeId = 1L;

        when(storeRepository.findById(storeId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> storeService.getStoreById(storeId));
        assertEquals("Store with ID " + storeId + " not found", exception.getMessage());
        verify(storeRepository, times(1)).findById(storeId);
    }

    @Test
    void addStore_ShouldSaveAndReturnStore() {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName("Tech World");
        storeDTO.setOwner("John Doe");
        storeDTO.setTaxId("123456789");

        Store store = new Store();
        store.setName(storeDTO.getName());
        store.setOwner(storeDTO.getOwner());
        store.setTaxId(storeDTO.getTaxId());

        when(storeRepository.save(any(Store.class))).thenReturn(store);

        Store result = storeService.addStore(storeDTO);

        assertNotNull(result);
        assertEquals("Tech World", result.getName());
        assertEquals("John Doe", result.getOwner());
        assertEquals("123456789", result.getTaxId());
        verify(storeRepository, times(1)).save(any(Store.class));
    }

    @Test
    void deleteStore_ShouldDeleteStore_WhenStoreExists() {
        Long storeId = 1L;
        Store store = new Store();
        store.setId(storeId);

        when(storeRepository.findById(storeId)).thenReturn(Optional.of(store));

        storeService.deleteStore(storeId);

        verify(storeRepository, times(1)).findById(storeId);
        verify(storeRepository, times(1)).delete(store);
    }

    @Test
    void deleteStore_ShouldThrowNotFoundException_WhenStoreDoesNotExist() {
        Long storeId = 1L;

        when(storeRepository.findById(storeId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> storeService.deleteStore(storeId));
        assertEquals("Store with ID " + storeId + " not found", exception.getMessage());
        verify(storeRepository, times(1)).findById(storeId);
    }
}
