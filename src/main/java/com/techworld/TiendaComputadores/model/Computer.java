package com.techworld.TiendaComputadores.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private int memory; // GB
    private String processor;
    private String operatingSystem;
    private double price;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
