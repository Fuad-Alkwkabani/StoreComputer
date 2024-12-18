package com.techworld.TiendaComputadores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techworld.TiendaComputadores.dto.ComputerDTO;
import com.techworld.TiendaComputadores.model.Computer;
import com.techworld.TiendaComputadores.service.ComputerService;

import java.util.List;

@RestController
@RequestMapping("/api/computers")
public class ComputerController {
    @Autowired
    private ComputerService computerService;

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Computer>> getComputersByStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(computerService.getComputersByStore(storeId));
    }

    @PostMapping("/store/{storeId}")
    public ResponseEntity<Computer> addComputer(@RequestBody ComputerDTO computerDTO, @PathVariable Long storeId) {
        Computer computer = computerService.addComputer(computerDTO, storeId);
        return ResponseEntity.ok(computer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComputer(@PathVariable Long id) {
        computerService.deleteComputer(id);
        return ResponseEntity.noContent().build();
    }
}
