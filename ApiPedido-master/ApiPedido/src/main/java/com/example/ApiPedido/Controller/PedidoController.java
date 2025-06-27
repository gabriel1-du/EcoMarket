package com.example.ApiPedido.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiPedido.Model.Pedido;
import com.example.ApiPedido.Service.PedidoService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Controller
@RestController
@RequestMapping("/api/pedido") // Ruta base para esta API
public class PedidoController {


 @Autowired // Inyección del servicio de pedido
    private PedidoService pedidoService;

    // Obtener todos los pedidos (GET /api/pedido)
    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        return ResponseEntity.ok(pedidoService.getAll());
    }
    
    // Obtener un pedido por su ID (GET /api/pedido/{id})
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Pedido pedido = pedidoService.getById(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado");
        }
    }

    // Crear un nuevo pedido (POST /api/pedido)
    @PostMapping
    public ResponseEntity<?> add(@RequestBody Pedido pedido) {
        Pedido nuevo = pedidoService.add(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Actualizar un pedido existente (PUT /api/pedido/{id})
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Pedido pedido) {
        Pedido actualizado = pedidoService.update(id, pedido);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado para actualizar");
        }
    }

    // Eliminar un pedido por su ID (DELETE /api/pedido/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Pedido eliminado = pedidoService.delete(id);
        if (eliminado != null) {
            return ResponseEntity.ok(eliminado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado para eliminar");
        }
    }


    //-------
    //METODOS HATEOAS
    //--------

    //OBTENER POR ID
    @GetMapping("/hateoas/{id}")
    public ResponseEntity<?> getHATEOASById(@PathVariable Integer id) {
        Pedido pedido = pedidoService.getById(id);

        //En caso de que no haya ningun objeto en la base de datos
        if (pedido != null) {
            pedido.add(linkTo(methodOn(PedidoController.class).getHATEOASById(id)).withSelfRel());
            pedido.add(linkTo(methodOn(PedidoController.class).getAll()).withRel("todos"));
            pedido.add(linkTo(methodOn(PedidoController.class).delete(id)).withRel("eliminar"));
            // API Gateway links
            pedido.add(Link.of("http://localhost:8888/api/proxy/medio-pago/" + id).withSelfRel());
            pedido.add(Link.of("http://localhost:8888/api/proxy/medio-pago/" + id).withRel("modificar").withType("PUT"));
            pedido.add(Link.of("http://localhost:8888/api/proxy/medio-pago/" + id).withRel("eliminar").withType("DELETE"));

            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Método de pago no encontrado");
        }
    }

}
