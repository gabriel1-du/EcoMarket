package com.example.ApiReporte.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiReporte.Model.Reporte;
import com.example.ApiReporte.Model.TipoReporte;
import com.example.ApiReporte.Service.ReporteService;


@RestController
@RequestMapping("/api/reporte")
public class ReporteController {
    
    //------------------
    //iNYECCION DE SERVICE
    //--------------------
    @Autowired
    private ReporteService reporteService;

    //----------
    //Metodos GET
    //----------

    // Obtener un tipo de reporte por ID (GET /api/reportes/tipo/{id})
    @GetMapping("/tipo/{id}")
    public ResponseEntity<?> getDefinicionReporteById(@PathVariable("id") Integer tipo_reporte_id) {
        TipoReporte tipo = reporteService.getByIdTipoReporte(tipo_reporte_id);
        if (tipo != null) {
            return ResponseEntity.ok(tipo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de reporte no encontrado");
        }
    }



    // Obtener todos los REPORTES
    @GetMapping("/")
    public ResponseEntity<List<Reporte>> getAll() {
        return ResponseEntity.ok(reporteService.getAll());
    }

    // Obtener un REPORTE por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer reporteId) {
        Reporte reporte = reporteService.getById(reporteId);
        if (reporte != null) {
            return ResponseEntity.ok(reporte);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reporte no encontrado");
        }
    }

    //---------------------
    //Obtener metodos POST
    //---------------------
    // Crear Reporte
    @PostMapping  
    public ResponseEntity<?> add(@RequestBody Reporte reporte) {
        Reporte nuevo = reporteService.add(reporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    //CREAR un nuevo TIPO REPORTE
    @PostMapping("/tipo")
    public ResponseEntity<?> crearTipoReporte(@RequestBody TipoReporte tipoReporte) {
        TipoReporte nuevo = reporteService.crearTipoReporte(           
            tipoReporte.getUsuarioId(),             
            tipoReporte.getTipo_reporte()            
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }


    
}
