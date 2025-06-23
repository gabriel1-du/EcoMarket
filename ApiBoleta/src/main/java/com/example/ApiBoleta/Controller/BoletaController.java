package com.example.ApiBoleta.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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

import com.example.ApiBoleta.DTO.BoletaDTO;
import com.example.ApiBoleta.DTO.BoletaRequestDTO;
import com.example.ApiBoleta.DTO.BoletaResponseDTO;
import com.example.ApiBoleta.Model.Boleta;
import com.example.ApiBoleta.Service.BoletaService;

//Metodos Hateoas
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RequestMapping("/api/boleta")
@RestController
public class BoletaController {

    //Inyeccion de BoletaRepository
    @Autowired
    private BoletaService boletaService;

    //Para obtener todas las boletas
    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<BoletaDTO>> getAll(){
        return ResponseEntity.ok(boletaService.getAll());
    }


    //Buscar por id de boleta get
    @GetMapping("/{boletaId}")
       public ResponseEntity<?> getById(@PathVariable Integer boletaId) {
        BoletaDTO boleta = boletaService.getbyIDDTO(boletaId);
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






    //--------------
    //Metodo Hateos
    //--------------

    //OBTENER TODAS LAS BOLETAS
    @GetMapping("/hateoas")
    public ResponseEntity<List<BoletaDTO>> getAllHATEOAS() {
        List<BoletaDTO> lista = boletaService.getAll();

        for (BoletaDTO dto : lista) {
            // Enlace a este mismo recurso individual
            dto.add(linkTo(methodOn(BoletaController.class).getById(dto.getBoletaId())).withSelfRel());

            // Enlace a todos los boletas (GET general)
            dto.add(Link.of("http://localhost:8888/api/proxy/boleta").withRel("Get todas las boletas"));

            // Enlace para crear boleta (POST)
            dto.add(Link.of("http://localhost:8888/api/proxy/boleta").withRel("Crear nueva boleta").withType("POST"));
        }

        return ResponseEntity.ok(lista);
    }

    //OBTENER BOLETA POR ID
    @GetMapping("/hateoas/{id}")
    public BoletaDTO getHATEOASById(@PathVariable Integer id) {
        BoletaDTO dto = boletaService.getbyIDDTO(id);

        // Enlaces internos (misma API)
        dto.add(linkTo(methodOn(BoletaController.class).getHATEOASById(id)).withSelfRel());
        dto.add(linkTo(methodOn(BoletaController.class).getAllHATEOAS()).withRel("todas"));
        dto.add(linkTo(methodOn(BoletaController.class).delete(id)).withRel("eliminar"));

        // Enlaces hacia el API Gateway (manuales)
        dto.add(Link.of("http://localhost:8888/api/proxy/boleta/" + dto.getBoletaId()).withSelfRel());
        dto.add(Link.of("http://localhost:8888/api/proxy/boleta/" + dto.getBoletaId()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8888/api/proxy/boleta/" + dto.getBoletaId()).withRel("Eliminar HATEOAS").withType("DELETE"));

        return dto;
    }

}
