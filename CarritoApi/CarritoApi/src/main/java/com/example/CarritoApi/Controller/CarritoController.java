package com.example.CarritoApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.CarritoApi.Model.Carrito;
import com.example.CarritoApi.Service.CarritoService;

@RestController
@RequestMapping("/api/carrito") 
public class CarritoController {


    @Autowired
    private CarritoService carritoService; //INYECCION del REpo


    //-------------
    //Metodos GeT
    //------------

    // Obtener un carrito por su ID 
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            Carrito carrito = carritoService.getById(id);
            if (carrito != null) {
            return ResponseEntity.ok(carrito);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Carrito no encontrado");
                        }
        } catch (Exception e) {
            // Imprime en consola el stack completo
            e.printStackTrace();
            // Devuelve el mensaje para ver qué excepción saltó
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Error interno: " + e.getClass().getSimpleName()
                                   + " – " + e.getMessage());
        }
    }



     // Obtener el carrito activo de un usuario 
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> getByUsuario(@PathVariable Integer usuarioId) {
        Carrito carrito = carritoService.getByUsuarioId(usuarioId);
        if (carrito != null) {
            return ResponseEntity.ok(carrito);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrito no encontrado para el usuario");
        }
    }



    //------------
    //Metodos Post
    //---------------

     // Crear un nuevo carrito para un usuario 
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> crearCarrito(@PathVariable Integer usuarioId) {
        Carrito nuevo = carritoService.crearCarrito(usuarioId);
        if (nuevo != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado, no se puede crear carrito");
        }
    }

    // Agregar un producto al carrito 
    @PostMapping("/{carritoId}/producto/{productoId}")
    public ResponseEntity<?> agregarProducto(
        @PathVariable Integer carritoId,
        @PathVariable Integer productoId,
        @RequestParam Integer cantidad
    ) {
        Carrito actualizado = carritoService.agregarProducto(carritoId, productoId, cantidad);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrito no encontrado");
        }
    }

    //------------
    //Metodos Delete
    //-------------
    // Eliminar un producto del carrito 
    @DeleteMapping("/{carritoId}/producto/{productoId}")
    public ResponseEntity<?> eliminarProducto(
        @PathVariable Integer carritoId,
        @PathVariable Integer productoId
    ) {
        Carrito actualizado = carritoService.eliminarProducto(carritoId, productoId);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado en el carrito");
        }
    }

    // Vaciar todos los productos del carrito 
    @DeleteMapping("/{carritoId}")
    public ResponseEntity<?> vaciarCarrito(@PathVariable Integer carritoId) {
        Carrito actualizado = carritoService.vaciarCarrito(carritoId);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrito no encontrado");
        }
    }

}
