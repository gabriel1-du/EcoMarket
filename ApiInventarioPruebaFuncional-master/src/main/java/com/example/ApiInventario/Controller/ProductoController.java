package com.example.ApiInventario.Controller;

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

import com.example.ApiInventario.Model.Producto;
import com.example.ApiInventario.Repository.CategoriaRepository;
import com.example.ApiInventario.Repository.ProductoRepository;
import com.example.ApiInventario.Service.ProductoService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/producto")
@RequiredArgsConstructor
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    //Llamar a todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> getAll(){
        return ResponseEntity.ok(productoService.getAll());
    }

    // Obtener persona por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Producto producto = productoService.getById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new z_Mensaje("Persona no encontrada\""));
        }
    }
    
    // Crear nueva persona (POST)
    @PostMapping
    public ResponseEntity<?> add(@RequestBody Producto producto) {
        Producto nueva = productoService.add(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // Actualizar persona existente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Producto producto) {
        Producto actualizada = productoService.update(id, producto);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new z_Mensaje("Persona no encontrada"));
        }
    }

    // Eliminar persona (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Producto eliminada = productoService.delete(id);
        if (eliminada != null) {
            return ResponseEntity.ok(eliminada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new z_Mensaje("Persona no encontrada"));
        }
    }


}
