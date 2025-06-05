package com.example.ApiReporte.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ApiReporte.Model.HistoricoReporte;

@Repository
public interface HistoricoReporteRepository extends JpaRepository<HistoricoReporte, Integer>{

}
