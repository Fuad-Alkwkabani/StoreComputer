package com.techworld.TiendaComputadores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techworld.TiendaComputadores.dto.StoreDTO;
import com.techworld.TiendaComputadores.model.Store;
import com.techworld.TiendaComputadores.service.StoreService;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @PostMapping
    public ResponseEntity<Store> addStore(@RequestBody StoreDTO storeDTO) {
        Store store = storeService.addStore(storeDTO);
        return ResponseEntity.ok(store);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }
}
