package com.example.ApiReporte.Controller;

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

import com.example.ApiReporte.Model.DefinicionReporte;
import com.example.ApiReporte.Model.HistoricoReporte;
import com.example.ApiReporte.Service.DefinicionReporteService;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@RestController
@RequestMapping("/api/reportes")
public class DefinicionReporteController {

    //Inyecciones
    
    @Autowired
    private DefinicionReporteService reporteService;



    //----
    //Metodos Get
    //--------

    // Obtener todos los reportes históricos (GET /api/reportes)
    @GetMapping("/")
    public ResponseEntity<List<HistoricoReporte>> getAll() {
        return ResponseEntity.ok(reporteService.getAll());
    }

    // Obtener un historial de ejecución por ID (GET /api/reportes/{id})
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        HistoricoReporte reporte = reporteService.getbyId(id);
        if (reporte != null) {
            return ResponseEntity.ok(reporte);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reporte no encontrado");
        }
    }

       // Obtener un tipo de reporte por ID (GET /api/reportes/tipo/{id})
    @GetMapping("/tipo/{id}")
    public ResponseEntity<?> getDefinicionReporteById(@PathVariable Integer id) {
        DefinicionReporte tipo = reporteService.getByIdDefinicionReporte(id);
        if (tipo != null) {
            return ResponseEntity.ok(tipo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de reporte no encontrado");
        }
    }

    //-----
    //Metodo post
    //---------
    // Crear un nuevo registro de ejecución de reporte (POST /api/reportes)
    @PostMapping  
    public ResponseEntity<?> add(@RequestBody HistoricoReporte historico) {
        HistoricoReporte nuevo = reporteService.add(historico);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    //Crear un nuevo tipo de reporte
    @PostMapping("/tipo")
        public ResponseEntity<?> crearTipoReporte(@RequestBody DefinicionReporte reporte) {
            DefinicionReporte nuevo = reporteService.crearTipoReporte(
            reporte.getNombre(),
            reporte.getDescripcion(),
            reporte.getQueryBase()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }


    //----------
    //Metodos delete
    //-----------

    // Eliminar un tipo de reporte (DELETE /api/reportes/tipo/{id})
    @DeleteMapping("/tipo/{id}")
    public ResponseEntity<?> eliminarTipoReporte(@PathVariable Integer id) {
        DefinicionReporte eliminado = reporteService.eliminarTipoReporte(id);
        if (eliminado != null) {
            return ResponseEntity.ok("Tipo de reporte eliminado: " + eliminado.getNombre());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar el tipo de reporte o no existe");
        }
    }

    //Metodo para eliminar HistoricoReporte
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarHistoricoReporte(@PathVariable Integer id) {
        HistoricoReporte eliminado = reporteService.eliminarHistoricoReporte(id);
        if (eliminado != null) {
            return ResponseEntity.ok("Histórico eliminado con ID: " + eliminado.getHistId());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el histórico con ID: " + id);
        }
    }

    //-----------
    //Metodos put
    //-----------


    // Actualizar un tipo de reporte (PUT /api/reportes/tipo/{id})
    @PutMapping("/tipo/{id}")   
    public ResponseEntity<?> actualizarTipoReporte(@PathVariable Integer id, @RequestBody DefinicionReporte reporte) {
        DefinicionReporte actualizado = reporteService.actualizarTipoReporte(id, reporte);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de reporte no encontrado para actualizar");
        }
    }

    // Actualizar un reporte histórico (PUT /api/reportes/{id})
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarHistoricoReporte(@PathVariable Integer id, @RequestBody HistoricoReporte historico) {
        HistoricoReporte actualizado = reporteService.actualizarHistoricoReporte(id, historico);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Histórico no encontrado para actualizar");
        }   
    }



    //-------------------
    //METODOS HATEOAS
    //_------------------ 
    @GetMapping("/hateoas")
    public ResponseEntity<List<HistoricoReporte>> getAllHateoas() {
          List<HistoricoReporte> lista = reporteService.getAll();

        for (HistoricoReporte hist : lista) {
            Integer id = hist.getHistId();

            hist.add(Link.of("http://localhost:8888/api/proxy/reportes/" + id).withSelfRel());
            hist.add(Link.of("http://localhost:8888/api/proxy/reportes/" + id).withRel("modificar").withType("PUT"));
            hist.add(Link.of("http://localhost:8888/api/proxy/reportes/" + id).withRel("eliminar").withType("DELETE"));
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/hateoas/{histId}")
    public ResponseEntity<?> getReporteHATEOASById(@PathVariable Integer histId) {
        HistoricoReporte hist = reporteService.getbyId(histId); // Asegúrate de que el método esté bien escrito (getById)

        // En caso de que no haya ningún objeto en la base de datos
        if (hist != null) {
            hist.add(linkTo(methodOn(DefinicionReporteController.class).getReporteHATEOASById(histId)).withSelfRel());
            hist.add(linkTo(methodOn(DefinicionReporteController.class).getAllHateoas()).withRel("todos"));
            hist.add(linkTo(methodOn(DefinicionReporteController.class).eliminarHistoricoReporte(histId)).withRel("eliminar"));

            // API Gateway links
            hist.add(Link.of("http://localhost:8888/api/proxy/reporte/" + histId).withSelfRel());
            hist.add(Link.of("http://localhost:8888/api/proxy/reporte/" + histId).withRel("modificar").withType("PUT"));
            hist.add(Link.of("http://localhost:8888/api/proxy/reporte/" + histId).withRel("eliminar").withType("DELETE"));

            return ResponseEntity.ok(hist);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reporte no encontrado");
        }
    }


}
