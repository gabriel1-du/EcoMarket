package com.example.ApiBoleta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ApiBoleta.DTO.BoletaDTO;
import com.example.ApiBoleta.Model.Boleta;
import com.example.ApiBoleta.Model.Pedido;
import com.example.ApiBoleta.Model.Usuario;

@SpringBootTest
public class BoletaTest {

    @Test
    void testCrearBoletaDTODesdeModelo() {
        // Crear objetos relacionados
        Pedido pedido = new Pedido();
        pedido.setId(10); // Asegúrate de tener setters en Pedido

        Usuario usuario = new Usuario();
        usuario.setId(20); // Asegúrate de tener setters en Usuario

        // Crear objeto del modelo
        Boleta boleta = new Boleta();
        boleta.setBoletaId(1);
        boleta.setSubtotal(5000);
        boleta.setImpuesto(950);
        boleta.setTotal(5950);
        boleta.setFechaEmision(LocalDateTime.of(2025, 6, 23, 14, 30));
        boleta.setPedido(pedido);
        boleta.setUsuario(usuario);

        // Crear DTO a partir del modelo
        BoletaDTO dto = new BoletaDTO();
        dto.setBoletaId(boleta.getBoletaId());
        dto.setSubtotal(boleta.getSubtotal());
        dto.setImpuesto(boleta.getImpuesto());
        dto.setTotal(boleta.getTotal());
        dto.setFechaEmision(boleta.getFechaEmision());
        dto.setPedidoId(boleta.getPedido().getId());
        dto.setUsuarioId(boleta.getUsuario().getId());

        // Verificaciones
        assertEquals(1, dto.getBoletaId());
        assertEquals(5000, dto.getSubtotal());
        assertEquals(950, dto.getImpuesto());
        assertEquals(5950, dto.getTotal());
        assertEquals(LocalDateTime.of(2025, 6, 23, 14, 30), dto.getFechaEmision());
        assertEquals(10, dto.getPedidoId());
        assertEquals(20, dto.getUsuarioId());

        System.out.println("Test de BoletaDTO desde Boleta realizado exitosamente.");
    }

}
