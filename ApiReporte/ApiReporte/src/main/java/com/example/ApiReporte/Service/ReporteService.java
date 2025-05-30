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
    public Reporte getbyId(Integer reporteId  ){
        Optional<Reporte> Reporte = reporteRepository.findById(reporteId); //Busca el id
        return Reporte.orElse(null);// Devuelve null en caso de que no encuentre o no haya 
    }

    //Buscar EJECUCION DE REPORTE por Id
     public TipoReporte getbyIdEjecucionReporte(Integer ejecucion_id  ){
        Optional<TipoReporte> TipoReporte= tipoReporteRepository.findById(ejecucion_id); //Busca el id
        return TipoReporte.orElse(null);// Devuelve null en caso de que no encuentre o no haya 
    }


    //Buscar Tipo reporte por id
    public TipoReporte getByIdTipoReporte(Integer tipo_reporte_id) {
        Optional<TipoReporte> resultado = tipoReporteRepository.findById(tipo_reporte_id);
        return resultado.orElse(null);
    }



    //------------------
    //METODOS DE CREACION/POST
    //------------------

    //Crear un nuevo Reporte(Crea  un reporte, Debe ingresarse la ejecucion de reporte )
    public Reporte add(Reporte reporte) {
    return reporteRepository.save(reporte);
    }


    //Crear un nuevo tipo de reporte (El catologo)
    public TipoReporte crearTipoReporte(Reporte reporte, Integer usuarioId, String tipo_reporte) {
        TipoReporte nuevo = new TipoReporte();

        nuevo.setReporte(reporte); // Relación con reporte
        nuevo.setUsuarioId(usuarioId); // ID del usuario
        nuevo.setTipo_reporte(tipo_reporte); // El tipo o nombre

        // La fecha se autogenera si ya lo definiste como: = LocalDateTime.now()
        return tipoReporteRepository.save(nuevo);
    }


}
