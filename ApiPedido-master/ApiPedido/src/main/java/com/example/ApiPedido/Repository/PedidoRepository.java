package com.example.ApiPedido.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ApiPedido.Model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
