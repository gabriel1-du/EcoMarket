package com.example.ApiBoleta.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoletaResponseDTO {

    private Integer boletaId;
    private Integer subtotal;
    private Integer impuesto;
    private Integer total;
    private LocalDateTime fechaEmision;

    private Integer pedidoId;
    private Integer usuarioId;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String emailUsuario;
}
