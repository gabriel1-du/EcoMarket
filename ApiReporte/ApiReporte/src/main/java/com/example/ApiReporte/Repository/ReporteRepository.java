package com.example.ApiReporte.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ApiReporte.Model.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {

}
