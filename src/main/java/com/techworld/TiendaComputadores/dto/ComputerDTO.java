package com.techworld.TiendaComputadores.dto;

import lombok.Data;

@Data
public class ComputerDTO {
    private String brand;            
    private int memory;              
    private String processor;        
    private String operatingSystem;  
    private double price;            
}
