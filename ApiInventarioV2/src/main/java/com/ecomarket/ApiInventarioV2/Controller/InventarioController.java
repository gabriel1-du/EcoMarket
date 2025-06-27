package com.ecomarket.ApiInventarioV2.Controller;

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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.ecomarket.ApiInventarioV2.Model.Inventario;
import com.ecomarket.ApiInventarioV2.Service.InventarioService;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<Inventario> listar() {
        return inventarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerPorId(@PathVariable Integer id) {
        Inventario inv = inventarioService.obtenerPorId(id);
        return inv != null ? ResponseEntity.ok(inv) : ResponseEntity.notFound().build();
    }

    @GetMapping("/producto/{productoId}")
    public List<Inventario> obtenerPorProducto(@PathVariable Integer productoId) {
        return inventarioService.obtenerPorProductoId(productoId);
    }

    @PostMapping
    public ResponseEntity<Inventario> crear(@RequestBody Inventario inventario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.crearInventario(inventario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizar(@PathVariable Integer id, @RequestBody Inventario nuevo) {
        Inventario actualizado = inventarioService.actualizarInventario(id, nuevo);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.noContent().build();
    }

    //METODOS HATEOAS

    //METODO HATEOAS para buscar por ID
    @GetMapping("/hateoas/{id}")
    public Inventario obtenerHATEOAS(@PathVariable Integer id) {
        Inventario inv = inventarioService.obtenerPorId(id);
         
        //link HATEOAS para API Gateway "A mano"
        inv.add(Link.of("http://localhost:8888/api/proxy/inventario" + inv.getId()).withSelfRel());
        inv.add(Link.of("http://localhost:8888/api/proxy/inventario" + inv.getId()).withRel("Modificar HATEOAS").withType("PUT"));
        inv.add(Link.of("http://localhost:8888/api/proxy/inventario" + inv.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));

        return inv;
    }

    //METODO HATEOAS para listar todos los productos utilizando HATEOAS
    @GetMapping("/hateoas")
    public List<Inventario> obtenerTodosHATEOAS() {
        List<Inventario> lista = inventarioService.obtenerTodos();

        for (Inventario inv : lista) {
            //link url de la misma API
            inv.add(linkTo(methodOn(InventarioController.class).obtenerHATEOAS(inv.getId())).withSelfRel());

            //link HATEOAS para API Gateway "A mano"
            inv.add(Link.of("http://localhost:8888/api/proxy/inventario").withRel("Get todos HATEOAS"));
            inv.add(Link.of("http://localhost:8888/api/proxy/inventario" + inv.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }

}
