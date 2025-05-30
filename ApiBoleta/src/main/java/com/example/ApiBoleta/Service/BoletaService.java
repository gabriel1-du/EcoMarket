package com.example.ApiBoleta.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiBoleta.DTO.BoletaRequestDTO;
import com.example.ApiBoleta.DTO.BoletaResponseDTO;
import com.example.ApiBoleta.Model.Boleta;
import com.example.ApiBoleta.Model.Pedido;
import com.example.ApiBoleta.Model.Usuario;
import com.example.ApiBoleta.Repository.BoletaRepository;
import com.example.ApiBoleta.Repository.PedidoRepository;
import com.example.ApiBoleta.Repository.UsuarioRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class BoletaService {

        // Inyección del repositorio
    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    // --------------------
    // MÉTODOS ENTIDAD BOLETA
    // --------------------
    // Obtener todas las boletas
    public List<Boleta> getAll() {
        return boletaRepository.findAll();
    }

    // Buscar boleta por ID
    public Boleta getById(Integer boletaId) {
        Optional<Boleta> boleta = boletaRepository.findById(boletaId);
        return boleta.orElse(null);
    }

    // Actualizar una boleta existente
    public Boleta update(Integer boletaId, Boleta boleta) {
        if (boletaRepository.existsById(boletaId)) {
            boleta.setBoletaId(boletaId); // Asegurar el ID
            return boletaRepository.save(boleta);
        }
        return null;
    }

    // Eliminar una boleta
    public Boleta delete(Integer boletaId) {
        Optional<Boleta> boleta = boletaRepository.findById(boletaId);
        if (boleta.isPresent()) {
            boletaRepository.deleteById(boletaId);
            return boleta.get();
        }
        return null;
    }

    // =============================
    // MÉTODOS CONVERSIÓN DTO
    // =============================

    // Crear boleta y devolver DTO
    public BoletaResponseDTO add(Boleta boleta) {
        Boleta nueva = boletaRepository.save(boleta);
        return convertToDTO(nueva);
    }

    // Convertir entidad Boleta a BoletaResponseDTO
    public BoletaResponseDTO convertToDTO(Boleta boleta) {
        BoletaResponseDTO dto = new BoletaResponseDTO();
        dto.setBoletaId(boleta.getBoletaId());
        dto.setSubtotal(boleta.getSubtotal());
        dto.setImpuesto(boleta.getImpuesto());
        dto.setTotal(boleta.getTotal());
        dto.setFechaEmision(boleta.getFechaEmision());

        if (boleta.getPedido() != null) {
            dto.setPedidoId(boleta.getPedido().getId());
        }

        if (boleta.getUsuario() != null) {
            dto.setUsuarioId(boleta.getUsuario().getId());
            dto.setNombreUsuario(boleta.getUsuario().getNombre());
            dto.setApellidoUsuario(boleta.getUsuario().getApellido());
            dto.setEmailUsuario(boleta.getUsuario().getEmail());
        }

        return dto;
    }

    public BoletaResponseDTO addFromDto(BoletaRequestDTO req) {
        // 1) Carga Pedido
        Pedido pedido = pedidoRepository.findById(req.getPedidoId())
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID " + req.getPedidoId()));

        // 2) Carga Usuario
        Usuario usuario = usuarioRepository.findById(req.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID " + req.getUsuarioId()));

        // 3) Arma la entidad Boleta
        Boleta boleta = new Boleta();
        boleta.setSubtotal(req.getSubtotal());
        boleta.setImpuesto(req.getImpuesto());
        boleta.setTotal(req.getTotal());
        boleta.setFechaEmision(req.getFechaEmision());
        boleta.setPedido(pedido);
        boleta.setUsuario(usuario);

        // 4) Guarda y convierte a DTO de respuesta
        Boleta nueva = boletaRepository.save(boleta);
        return convertToDTO(nueva);
    }

    //Metodo para actualizar una boleta
    public Boleta update(Integer boletaId, BoletaRequestDTO dto) {
        Optional<Boleta> optionalBoleta = boletaRepository.findById(boletaId);
        if (optionalBoleta.isPresent()) {
            Boleta boleta = optionalBoleta.get();
            boleta.setSubtotal(dto.getSubtotal());
            boleta.setImpuesto(dto.getImpuesto());
            boleta.setTotal(dto.getTotal());
            boleta.setFechaEmision(dto.getFechaEmision());

        // Cargar entidades relacionadas por ID
        Pedido pedido = pedidoRepository.findById(dto.getPedidoId()).orElse(null);
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElse(null);

        if (pedido == null || usuario == null) return null;

        boleta.setPedido(pedido);
        boleta.setUsuario(usuario);

        return boletaRepository.save(boleta);
    }
    return null;
}


        

} 


     