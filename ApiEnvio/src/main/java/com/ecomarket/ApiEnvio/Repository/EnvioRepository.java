package com.ecomarket.ApiEnvio.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomarket.ApiEnvio.Model.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Integer>{
    List<Envio> findByEstado(String estado);
    List<Envio> findByPedidoId(Integer pedidoId);

}
