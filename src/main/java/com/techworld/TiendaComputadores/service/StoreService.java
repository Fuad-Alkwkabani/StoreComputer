package com.techworld.TiendaComputadores.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techworld.TiendaComputadores.dto.StoreDTO;
import com.techworld.TiendaComputadores.exception.NotFoundException;
import com.techworld.TiendaComputadores.model.Store;
import com.techworld.TiendaComputadores.repository.StoreRepository;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Store with ID " + id + " not found"));
    }

    
    public Store addStore(StoreDTO storeDTO) {
        Store store = new Store();
        store.setName(storeDTO.getName());
        store.setOwner(storeDTO.getOwner());
        store.setTaxId(storeDTO.getTaxId());
        return storeRepository.save(store);
    }

   
    public void deleteStore(Long id) {
        Store store = getStoreById(id); 
        storeRepository.delete(store);
    }
}
