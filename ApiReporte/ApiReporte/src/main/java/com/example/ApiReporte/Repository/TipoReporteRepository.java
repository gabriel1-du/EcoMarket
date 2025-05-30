package com.example.ApiReporte.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ApiReporte.Model.TipoReporte;

@Repository
public interface TipoReporteRepository extends JpaRepository<TipoReporte, Integer>{

}
