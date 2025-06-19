package com.example.ApiBoleta.Config;

import org.springframework.stereotype.Component;

import com.example.ApiBoleta.DTO.BoletaDTO;
import com.example.ApiBoleta.Model.Boleta;

//Clase para convertir boleta a boletaDTO
@Component
public class BoletaMapper {

    public BoletaDTO boletaToBoletaDTO(Boleta boleta){


        //Creacion de nuevo objeto
        BoletaDTO dto = new BoletaDTO();
        //Primero set y despues get
        //Atributo de boleta  
        dto.setBoletaId(boleta.getBoletaId());
        dto.setFechaEmision(boleta.getFechaEmision());
        dto.setImpuesto(boleta.getImpuesto());
        dto.setSubtotal(boleta.getSubtotal());
        dto.setTotal(boleta.getTotal());
        //ID
        dto.setPedidoId(boleta.getPedido().getId());
        dto.setUsuarioId(boleta.getUsuario().getId());
        return dto;
        
    }
}
