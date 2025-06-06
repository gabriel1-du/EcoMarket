package com.gestion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorDTO {
    private Integer idVendedor;
    private Integer idUsuario;
    private String nombreCompleto;
}