package com.example.ApiInventario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiInventario.Model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{

}
