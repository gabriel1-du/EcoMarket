package com.example.ApiBoleta.DTO;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoletaDTO extends RepresentationModel<BoletaDTO> {
    
    private Integer boletaId;
    private Integer subtotal;
    private Integer impuesto;
    private Integer total;
    private LocalDateTime fechaEmision;

    // Atributos relacionados representados como IDs
    private Integer pedidoId;
    private Integer usuarioId;


}
