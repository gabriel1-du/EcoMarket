package com.example.ApiBoleta.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ApiBoleta.Model.Boleta;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Integer>{

}
