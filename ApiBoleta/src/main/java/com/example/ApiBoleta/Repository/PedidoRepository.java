package com.example.ApiBoleta.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiBoleta.Model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
