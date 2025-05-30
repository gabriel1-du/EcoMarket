package com.example.ApiBoleta.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiBoleta.DTO.BoletaRequestDTO;
import com.example.ApiBoleta.DTO.BoletaResponseDTO;
import com.example.ApiBoleta.Model.Boleta;
import com.example.ApiBoleta.Service.BoletaService;

@RequestMapping("/api/boleta")
@RestController
public class BoletaController {

    //Inyeccion de BoletaRepository
    @Autowired
    private BoletaService boletaService;

    //Para obtener todas las boletas
    @GetMapping("/")
    public ResponseEntity<List<Boleta>> getAll(){
        return ResponseEntity.ok(boletaService.getAll());
    }


    //Buscar por id de boleta
    @GetMapping("/{boletaId}")
       public ResponseEntity<?> getById(@PathVariable Integer boletaId) {
        Boleta boleta = boletaService.getById(boletaId);
        if (boleta != null) {
            return ResponseEntity.ok(boleta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Boleta no encontrada");
        }
    }


    // POST: crear nueva boleta usando Request DTO
    @PostMapping
    public ResponseEntity<BoletaResponseDTO> add(@RequestBody BoletaRequestDTO reqDto) {
        BoletaResponseDTO dto = boletaService.addFromDto(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    //Actualizar una boleta ya existente con DTO
    @PutMapping("/{boletaId}")
    public ResponseEntity<?> update(@PathVariable Integer boletaId, @RequestBody BoletaRequestDTO dto) {
        Boleta actualizado = boletaService.update(boletaId, dto);
        if (actualizado != null) {
            BoletaResponseDTO responseDTO = boletaService.convertToDTO(actualizado);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Boleta no encontrada para actualizar");
        }
    }


    //Eliminar una boleta
    @DeleteMapping("/{boletaId}")
    public ResponseEntity<?> delete(@PathVariable Integer boletaId) {
        Boleta eliminado = boletaService.delete(boletaId);
        if (eliminado != null) {
            return ResponseEntity.ok(eliminado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Boleta no encontrada para eliminar");
        }
    }




}
