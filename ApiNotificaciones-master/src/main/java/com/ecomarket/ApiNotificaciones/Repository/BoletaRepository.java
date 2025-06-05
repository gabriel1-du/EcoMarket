package com.ecomarket.ApiNotificaciones.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomarket.ApiNotificaciones.Model.Boleta;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Integer> {}
