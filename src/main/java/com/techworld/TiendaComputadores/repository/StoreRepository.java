package com.techworld.TiendaComputadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techworld.TiendaComputadores.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
