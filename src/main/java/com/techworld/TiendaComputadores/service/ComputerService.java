package com.techworld.TiendaComputadores.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techworld.TiendaComputadores.dto.ComputerDTO;
import com.techworld.TiendaComputadores.exception.NotFoundException;
import com.techworld.TiendaComputadores.model.Computer;
import com.techworld.TiendaComputadores.model.Store;
import com.techworld.TiendaComputadores.repository.ComputerRepository;
import com.techworld.TiendaComputadores.repository.StoreRepository;

import java.util.List;

@Service
public class ComputerService {
    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private StoreRepository storeRepository;

    public List<Computer> getComputersByStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new NotFoundException("Store with ID " + storeId + " not found"));
        return store.getInventory();
    }

    public Computer addComputer(ComputerDTO computerDTO, Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new NotFoundException("Store with ID " + storeId + " not found"));

        Computer computer = new Computer();
        computer.setBrand(computerDTO.getBrand());
        computer.setMemory(computerDTO.getMemory());
        computer.setProcessor(computerDTO.getProcessor());
        computer.setOperatingSystem(computerDTO.getOperatingSystem());
        computer.setPrice(computerDTO.getPrice());
        computer.setStore(store);
        return computerRepository.save(computer);
    }

    public void deleteComputer(Long id) {
        Computer computer = computerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Computer with ID " + id + " not found"));
        computerRepository.delete(computer);
    }
}
