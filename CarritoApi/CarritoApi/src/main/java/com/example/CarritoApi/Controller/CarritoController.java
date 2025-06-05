package com.example.CarritoApi.Controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.CarritoApi.Model.Carrito;
import com.example.CarritoApi.Model.ItemCarrito;
import com.example.CarritoApi.Service.CarritoService;

@RestController
@RequestMapping("/api/carrito") 
public class CarritoController {


    @Autowired
    private CarritoService carritoService; //INYECCION del REpo


    //-------------
    //Metodos GeT
    //------------

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Carrito> lista = carritoService.getAll();
        return ResponseEntity.ok(lista);
    }

    // Obtener un carrito por su ID 
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Carrito carrito = carritoService.getById(id);
        if (carrito != null) {
            return ResponseEntity.ok(carrito);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrito no encontrado");
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

    //------------
    //METODO PUT
    //-----------
    
    //Método PUT para actualizar cantidad de un item

    @PutMapping("/{id}")
        public ResponseEntity<?> actualizarCantidadesVarias(
        @PathVariable Integer id,
        @RequestBody Carrito carritoData
        ) {
       // 1) Validar que exista el carrito con ese ID
        Carrito existe = carritoService.getById(id);
        if (existe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Carrito no encontrado para el ID: " + id);
            }

        // 2) Recorrer la lista de items que llegó en el JSON
        if (carritoData.getItems() != null) {
            for (ItemCarrito it : carritoData.getItems()) {
                Integer productoId = it.getProductoId();
                Integer nuevaCant  = it.getCantidad();
                // Llamamos a tu servicio existente para cada item
                carritoService.actualizarCantidad(id, productoId, nuevaCant);
            }
        }

        // 3) Después de actualizar las cantidades, volvemos a leer el carrito completo
        Carrito actualizado = carritoService.getById(id);
        return ResponseEntity.ok(actualizado);
    }



}
