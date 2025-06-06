package com.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.models.Administrador;

public interface VendedorRepository extends JpaRepository<Administrador, Integer> {

}