package com.example.ApiBoleta.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoletaRequestDTO {

    private Integer subtotal;
    private Integer impuesto;
    private Integer total;
    private LocalDateTime fechaEmision;
    private Integer pedidoId;
    private Integer usuarioId;
}

