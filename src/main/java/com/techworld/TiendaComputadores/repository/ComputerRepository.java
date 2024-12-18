package com.techworld.TiendaComputadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techworld.TiendaComputadores.model.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
}
