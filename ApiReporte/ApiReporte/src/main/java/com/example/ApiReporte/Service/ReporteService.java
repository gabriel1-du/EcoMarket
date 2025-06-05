package com.example.ApiReporte.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiReporte.Model.TipoReporte;
import com.example.ApiReporte.Model.Reporte;
import com.example.ApiReporte.Repository.TipoReporteRepository;
import com.example.ApiReporte.Repository.ReporteRepository;

@Service
public class ReporteService {
    
    //Inyeccion de repositorios

    @Autowired//ReporteRepository 
    private ReporteRepository reporteRepository;

    @Autowired
    private TipoReporteRepository tipoReporteRepository; // con minúscula


    //----------------
    //METODOS DE BUSQUEDA/GET
    //---------------

    //Obtener todos los reportes
    public List<Reporte> getAll(){ 
        return reporteRepository.findAll();
    }
   

    //Buscar REPORTE por Id
    public Reporte getById(Integer reporteId  ){
        Optional<Reporte> Reporte = reporteRepository.findById(reporteId); //Busca el id
        return Reporte.orElse(null);// Devuelve null en caso de que no encuentre o no haya 
    }

    //Buscar EJECUCION DE REPORTE por Id
     public TipoReporte getbyIdEjecucionReporte(Integer ejecucion_id  ){
        Optional<TipoReporte> TipoReporte= tipoReporteRepository.findById(ejecucion_id); //Busca el id
        return TipoReporte.orElse(null);// Devuelve null en caso de que no encuentre o no haya 
    }


    //Buscar Tipo reporte por id
    public TipoReporte getByIdTipoReporte(Integer id) {
        return tipoReporteRepository.findById(id).orElse(null);
    }


    //------------------
    //METODOS DE CREACION/POST
    //------------------

    //Crear un nuevo Reporte(Crea  un reporte, Debe ingresarse la ejecucion de reporte )
    public Reporte add(Reporte reporte) {
    return reporteRepository.save(reporte);
    }


    public TipoReporte crearTipoReporte(Reporte reporte, Integer usuarioId, String tipo_reporte) {
    // Guardar primero el reporte
    Reporte reporteGuardado = reporteRepository.save(reporte);

    TipoReporte nuevo = new TipoReporte();
    nuevo.setUsuarioId(usuarioId);
    nuevo.setTipo_reporte(tipo_reporte);

    return tipoReporteRepository.save(nuevo);
}


}
