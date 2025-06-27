package com.ecomarket.ApiEnvio.Controller;

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
import org.springframework.hateoas.Link;

import com.ecomarket.ApiEnvio.Model.Envio;
import com.ecomarket.ApiEnvio.Service.EnvioService;


@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> listar() {
        return envioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerPorId(@PathVariable Integer id) {
        Envio envio = envioService.obtenerPorId(id);
        return envio != null ? ResponseEntity.ok(envio) : ResponseEntity.notFound().build();
    }

    @GetMapping("/estado/{estado}")
    public List<Envio> buscarPorEstado(@PathVariable String estado) {
        return envioService.buscarPorEstado(estado);
    }

    @PostMapping
    public ResponseEntity<Envio> crear(@RequestBody Envio envio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(envioService.crear(envio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizar(@PathVariable Integer id, @RequestBody Envio envio) {
        Envio actualizado = envioService.actualizar(id, envio);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        envioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //METODOS HATEOAS

    //METODO HATEOAS para buscar por ID
    @GetMapping("/hateoas/{id}")
    public Envio obtenerHATEOAS(@PathVariable Integer id) {
        Envio env = envioService.obtenerPorId(id);
        
        //link HATEOAS para API Gateway "A mano"
        env.add(Link.of("http://localhost:8888/api/proxy/envios" + env.getId()).withSelfRel());
        env.add(Link.of("http://localhost:8888/api/proxy/envios" + env.getId()).withRel("Modificar HATEOAS").withType("PUT"));
        env.add(Link.of("http://localhost:8888/api/proxy/envios" + env.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));

        return env;
    }

    //METODO HATEOAS para listar todos los productos utilizando HATEOAS
    @GetMapping("/hateoas")
    public List<Envio> obtenerTodosHATEOAS() {
        List<Envio> lista = envioService.listarTodos();

        for (Envio env : lista) {
            

            //link HATEOAS para API Gateway "A mano"
            env.add(Link.of("http://localhost:8888//api/proxy/envios").withRel("Get todos HATEOAS"));
            env.add(Link.of("http://localhost:8888/api/proxy/envios" + env.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }

}
