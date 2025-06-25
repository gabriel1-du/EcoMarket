package com.example.ApiMedioDePago.Controller;

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

import com.example.ApiMedioDePago.Model.MedioDePago;
import com.example.ApiMedioDePago.Sevice.MedioDePagoService;

    import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
    import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/medio-pago") // Ruta base para esta API
public class MedioDePagoController {

     @Autowired // Inyección del servicio de medio de pago
    private MedioDePagoService medioDePagoService;

    // Obtener todos los métodos de pago (GET /api/medio-pago)
    @GetMapping
    public ResponseEntity<List<MedioDePago>> getAll() {
        return ResponseEntity.ok(medioDePagoService.getAll());
    }

    // Obtener un método de pago por su ID (GET /api/medio-pago/{id})
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        MedioDePago medio = medioDePagoService.getById(id);
        if (medio != null) {
            return ResponseEntity.ok(medio);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Método de pago no encontrado");
        }
    }

    // Crear un nuevo método de pago (POST /api/medio-pago)
    @PostMapping
    public ResponseEntity<?> add(@RequestBody MedioDePago medio) {
        MedioDePago nuevo = medioDePagoService.add(medio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Actualizar un método de pago existente (PUT /api/medio-pago/{id})
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MedioDePago medio) {
        MedioDePago actualizado = medioDePagoService.update(id, medio);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Método de pago no encontrado para actualizar");
        }
    }

    // Eliminar un método de pago por su ID (DELETE /api/medio-pago/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        MedioDePago eliminado = medioDePagoService.delete(id);
        if (eliminado != null) {
            return ResponseEntity.ok(eliminado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Método de pago no encontrado para eliminar");
        }
    }


    //----------------------
    //METODOS HATEOAS
    //----------------------
    
    //OBTENER POR ID
    @GetMapping("/hateoas/{id}")
    public ResponseEntity<?> getHATEOASById(@PathVariable Integer id) {
        MedioDePago medio = medioDePagoService.getById(id);

        //En caso de que no haya ningun objeto en la base de datos
        if (medio != null) {
            medio.add(linkTo(methodOn(MedioDePagoController.class).getHATEOASById(id)).withSelfRel());
            medio.add(linkTo(methodOn(MedioDePagoController.class).getAllHATEOAS()).withRel("todos"));
            medio.add(linkTo(methodOn(MedioDePagoController.class).delete(id)).withRel("eliminar"));
            // API Gateway links
            medio.add(Link.of("http://localhost:8888/api/proxy/medio-pago/" + id).withSelfRel());
            medio.add(Link.of("http://localhost:8888/api/proxy/medio-pago/" + id).withRel("modificar").withType("PUT"));
            medio.add(Link.of("http://localhost:8888/api/proxy/medio-pago/" + id).withRel("eliminar").withType("DELETE"));

            return ResponseEntity.ok(medio);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Método de pago no encontrado");
        }
    }

    //OBTENER TODOS LOS POR HATEOAS
    @GetMapping("/hateoas")
    public ResponseEntity<List<MedioDePago>> getAllHATEOAS() {
        List<MedioDePago> lista = medioDePagoService.getAll();

        for (MedioDePago medio : lista) {
            Integer id = medio.getId_medio_de_pago();

            medio.add(Link.of("http://localhost:8888/api/proxy/medio-pago/" + id).withSelfRel());
            medio.add(Link.of("http://localhost:8888/api/proxy/medio-pago/" + id).withRel("modificar").withType("PUT"));
            medio.add(Link.of("http://localhost:8888/api/proxy/medio-pago/" + id).withRel("eliminar").withType("DELETE"));
        }

        return ResponseEntity.ok(lista);
    }








}
