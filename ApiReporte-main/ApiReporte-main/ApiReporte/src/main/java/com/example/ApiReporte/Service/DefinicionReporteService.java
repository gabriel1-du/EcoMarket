package com.example.ApiReporte.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ApiReporte.Model.DefinicionReporte;
import com.example.ApiReporte.Model.HistoricoReporte;
import com.example.ApiReporte.Repository.DefinicionReporteRepository;
import com.example.ApiReporte.Repository.HistoricoReporteRepository;

@Service
public class DefinicionReporteService {
    
    //Inyeccion de repositorios
    @Autowired
    private DefinicionReporteRepository definicionReporteRepository;
    @Autowired
    private HistoricoReporteRepository historicoReporteRepository;


    //Obtener todos los reportes
    public List<HistoricoReporte> getAll(){
        return historicoReporteRepository.findAll();
    }


    //Buscar reporte por id
    public HistoricoReporte getbyId(Integer reporte_id ){
        Optional<HistoricoReporte> HistoricoReporte = historicoReporteRepository.findById(reporte_id); //Busca el id
        return HistoricoReporte.orElse(null);// Devuelve null en caso de que no encuentre o no haya 
    }


    //Crear un nuevo reporte (Crea  un reporte, pero dentro se debe ingresar el tipo de reporte tambien)
    public HistoricoReporte add(HistoricoReporte historicoReporte) {
    return historicoReporteRepository.save(historicoReporte);
    }


    //Crear un nuevo tipo de reporte (El catologo)
    public DefinicionReporte crearTipoReporte(String nombre, String descripcion, String queryBase) {
        DefinicionReporte nuevo = new DefinicionReporte();
        nuevo.setNombre(nombre);
        nuevo.setDescripcion(descripcion);
        nuevo.setQueryBase(queryBase);
        return definicionReporteRepository.save(nuevo);
    }

     //Buscar Tipo reporte por id
    public DefinicionReporte getByIdDefinicionReporte(Integer reporteId) {
        Optional<DefinicionReporte> resultado = definicionReporteRepository.findById(reporteId);
        return resultado.orElse(null);
     }





}
