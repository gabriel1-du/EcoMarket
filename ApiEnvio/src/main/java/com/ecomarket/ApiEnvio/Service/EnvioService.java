package com.ecomarket.ApiEnvio.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarket.ApiEnvio.Model.Envio;
import com.ecomarket.ApiEnvio.Repository.EnvioRepository;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

     public List<Envio> listarTodos() {
        return envioRepository.findAll();
    }

    public Envio obtenerPorId(Integer id) {
        return envioRepository.findById(id).orElse(null);
    }

    public List<Envio> buscarPorEstado(String estado) {
        return envioRepository.findByEstado(estado);
    }

    public Envio crear(Envio envio) {
        envio.setFechaEnvio(LocalDateTime.now());
        return envioRepository.save(envio);
    }

    public Envio actualizar(Integer id, Envio actualizado) {
        Envio actual = envioRepository.findById(id).orElse(null);
        if (actual == null) return null;

        actual.setDireccion(actualizado.getDireccion());
        actual.setEstado(actualizado.getEstado());
        actual.setFechaEntregaEstimada(actualizado.getFechaEntregaEstimada());
        actual.setTrackingNumber(actualizado.getTrackingNumber());

        return envioRepository.save(actual);
    }

    public void eliminar(Integer id) {
        envioRepository.deleteById(id);
    }

}
