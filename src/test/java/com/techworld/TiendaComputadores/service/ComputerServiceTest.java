package com.techworld.TiendaComputadores.service;

import com.techworld.TiendaComputadores.dto.ComputerDTO;
import com.techworld.TiendaComputadores.exception.NotFoundException;
import com.techworld.TiendaComputadores.model.Computer;
import com.techworld.TiendaComputadores.model.Store;
import com.techworld.TiendaComputadores.repository.ComputerRepository;
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

class ComputerServiceTest {

    @InjectMocks
    private ComputerService computerService;

    @Mock
    private ComputerRepository computerRepository;

    @Mock
    private StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getComputersByStore_ShouldReturnListOfComputers_WhenStoreExists() {
        Long storeId = 1L;
        Store store = new Store();
        store.setId(storeId);
        store.setInventory(new ArrayList<>(List.of(new Computer(), new Computer())));

        when(storeRepository.findById(storeId)).thenReturn(Optional.of(store));

        List<Computer> computers = computerService.getComputersByStore(storeId);

        assertNotNull(computers);
        assertEquals(2, computers.size());
        verify(storeRepository, times(1)).findById(storeId);
    }

    @Test
    void getComputersByStore_ShouldThrowNotFoundException_WhenStoreDoesNotExist() {
        Long storeId = 1L;
        when(storeRepository.findById(storeId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> computerService.getComputersByStore(storeId));
        assertEquals("Store with ID " + storeId + " not found", exception.getMessage());
        verify(storeRepository, times(1)).findById(storeId);
    }

    @Test
    void addComputer_ShouldSaveAndReturnComputer_WhenStoreExists() {
        Long storeId = 1L;
        Store store = new Store();
        store.setId(storeId);

        ComputerDTO computerDTO = new ComputerDTO();
        computerDTO.setBrand("Dell");
        computerDTO.setMemory(16);
        computerDTO.setProcessor("Intel i7");
        computerDTO.setOperatingSystem("Windows 11");
        computerDTO.setPrice(1200.00);

        Computer computer = new Computer();
        computer.setBrand(computerDTO.getBrand());
        computer.setMemory(computerDTO.getMemory());
        computer.setProcessor(computerDTO.getProcessor());
        computer.setOperatingSystem(computerDTO.getOperatingSystem());
        computer.setPrice(computerDTO.getPrice());
        computer.setStore(store);

        when(storeRepository.findById(storeId)).thenReturn(Optional.of(store));
        when(computerRepository.save(any(Computer.class))).thenReturn(computer);

        Computer savedComputer = computerService.addComputer(computerDTO, storeId);

        assertNotNull(savedComputer);
        assertEquals("Dell", savedComputer.getBrand());
        assertEquals(16, savedComputer.getMemory());
        assertEquals("Intel i7", savedComputer.getProcessor());
        verify(storeRepository, times(1)).findById(storeId);
        verify(computerRepository, times(1)).save(any(Computer.class));
    }

    @Test
    void addComputer_ShouldThrowNotFoundException_WhenStoreDoesNotExist() {
        Long storeId = 1L;
        ComputerDTO computerDTO = new ComputerDTO();
        when(storeRepository.findById(storeId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> computerService.addComputer(computerDTO, storeId));
        assertEquals("Store with ID " + storeId + " not found", exception.getMessage());
        verify(storeRepository, times(1)).findById(storeId);
    }

    @Test
    void deleteComputer_ShouldDeleteComputer_WhenComputerExists() {
        Long computerId = 1L;
        Computer computer = new Computer();
        computer.setId(computerId);

        when(computerRepository.findById(computerId)).thenReturn(Optional.of(computer));

        computerService.deleteComputer(computerId);

        verify(computerRepository, times(1)).findById(computerId);
        verify(computerRepository, times(1)).delete(computer);
    }

    @Test
    void deleteComputer_ShouldThrowNotFoundException_WhenComputerDoesNotExist() {
        Long computerId = 1L;
        when(computerRepository.findById(computerId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> computerService.deleteComputer(computerId));
        assertEquals("Computer with ID " + computerId + " not found", exception.getMessage());
        verify(computerRepository, times(1)).findById(computerId);
    }
}
