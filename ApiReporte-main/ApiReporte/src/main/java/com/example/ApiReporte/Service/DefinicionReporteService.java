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

    //------------
    //Metodos para buscar
    //--------------

    //Buscar reporte por id
    public HistoricoReporte getbyId(Integer reporte_id ){
        Optional<HistoricoReporte> HistoricoReporte = historicoReporteRepository.findById(reporte_id); //Busca el id
        return HistoricoReporte.orElse(null);// Devuelve null en caso de que no encuentre o no haya 
    }

    //Buscar Tipo reporte por id
    public DefinicionReporte getByIdDefinicionReporte(Integer reporteId) {
        Optional<DefinicionReporte> resultado = definicionReporteRepository.findById(reporteId);
        return resultado.orElse(null);
    }

    //----------
    //Metodos post
    //--------------
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

    

    //-----
    //Metodos Delete
    //------------

    //Metodo para eliminar Reporte
    public DefinicionReporte eliminarTipoReporte(Integer reporteId) {
        Optional<DefinicionReporte> reporteOpt = definicionReporteRepository.findById(reporteId);

        if (reporteOpt.isPresent()) {
            definicionReporteRepository.deleteById(reporteId);
            return reporteOpt.get();  // Retorna la definición eliminada
        }

        return null;  // No se encontró la definición
    }

    //Eliminar por HsitoricoRPeorte
    public HistoricoReporte eliminarHistoricoReporte(Integer histId) {
        Optional<HistoricoReporte> historicoOpt = historicoReporteRepository.findById(histId);

        if (historicoOpt.isPresent()) {
            historicoReporteRepository.deleteById(histId);
            return historicoOpt.get();  // Retorna el histórico eliminado
        }

        return null;  // No se encontró el histórico
    }

    //-------
    //Metodos Put
    //----------

    public DefinicionReporte actualizarTipoReporte(Integer reporteId, DefinicionReporte datosActualizados) {
        Optional<DefinicionReporte> reporteOpt = definicionReporteRepository.findById(reporteId);

        if (reporteOpt.isPresent()) {
            DefinicionReporte reporteExistente = reporteOpt.get();

            reporteExistente.setNombre(datosActualizados.getNombre());
            reporteExistente.setDescripcion(datosActualizados.getDescripcion());
            reporteExistente.setQueryBase(datosActualizados.getQueryBase());

            return definicionReporteRepository.save(reporteExistente);
        }

        return null; // No existe el reporte para actualizar
    }


    public HistoricoReporte actualizarHistoricoReporte(Integer histId, HistoricoReporte datosActualizados) {
    Optional<HistoricoReporte> historicoOpt = historicoReporteRepository.findById(histId);

    if (historicoOpt.isPresent()) {
        HistoricoReporte historicoExistente = historicoOpt.get();

        // Aquí actualizas los campos que quieras permitir modificar
        historicoExistente.setReporte(datosActualizados.getReporte());
        historicoExistente.setUsuarioId(datosActualizados.getUsuarioId());
        historicoExistente.setEjecutadoEn(datosActualizados.getEjecutadoEn());

        return historicoReporteRepository.save(historicoExistente);
    }

    return null; // No existe el histórico para actualizar
}



}
