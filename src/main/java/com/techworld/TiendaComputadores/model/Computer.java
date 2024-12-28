package com.techworld.TiendaComputadores.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @JoinColumn(name = "store_id")
    @JsonBackReference
    private Store store;
}
