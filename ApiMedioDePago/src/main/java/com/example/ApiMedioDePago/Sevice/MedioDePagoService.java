package com.example.ApiMedioDePago.Sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiMedioDePago.Model.MedioDePago;
import com.example.ApiMedioDePago.Repository.MedioDePagoRepository;

@Service
public class MedioDePagoService {

    
    @Autowired
    private MedioDePagoRepository metodoPagoRepository;

    // Obtener todos los métodos de pago
    public List<MedioDePago> getAll() {
        return metodoPagoRepository.findAll();
    }

    // Obtener un método de pago por su ID
    public MedioDePago getById(Integer id_medio_de_pago) {
        Optional<MedioDePago> metodo = metodoPagoRepository.findById(id_medio_de_pago);
        return metodo.orElse(null);
    }

    // Crear un nuevo método de pago
    public MedioDePago add(MedioDePago medio) {
        return metodoPagoRepository.save(medio);
    }

    // Actualizar un método de pago existente
    public MedioDePago update(Integer id_medio_de_pago, MedioDePago nuevoMetodo) {
        if (metodoPagoRepository.existsById(id_medio_de_pago)) {
            nuevoMetodo.setId_medio_de_pago(id_medio_de_pago); // Asegura que se use el mismo ID
            return metodoPagoRepository.save(nuevoMetodo);
        }
        return null;
    }

    // Eliminar un método de pago por su ID
    public MedioDePago delete(Integer id_medio_de_pago) {
        Optional<MedioDePago> metodo = metodoPagoRepository.findById(id_medio_de_pago);
        if (metodo.isPresent()) {
            metodoPagoRepository.deleteById(id_medio_de_pago);
            return metodo.get(); // Devuelve el método eliminado
        }
        return null;
    }


}
