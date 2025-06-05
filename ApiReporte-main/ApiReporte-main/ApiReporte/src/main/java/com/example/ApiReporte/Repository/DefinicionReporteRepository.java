package com.example.ApiReporte.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ApiReporte.Model.DefinicionReporte;
@Repository
public interface DefinicionReporteRepository extends JpaRepository <DefinicionReporte, Integer> {

}
