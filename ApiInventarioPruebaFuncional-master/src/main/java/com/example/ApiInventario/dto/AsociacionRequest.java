package com.example.ApiInventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AsociacionRequest {

    private Integer productoId;
    private Integer categoriaId;

}
