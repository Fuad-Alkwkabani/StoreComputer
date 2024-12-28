package com.techworld.TiendaComputadores.dto;

import java.util.List;

import lombok.Data;

@Data
public class StoreDTO {
    private String name;       
    private String owner;      
    private String taxId;   
    private List<ComputerDTO> inventory;   
}
