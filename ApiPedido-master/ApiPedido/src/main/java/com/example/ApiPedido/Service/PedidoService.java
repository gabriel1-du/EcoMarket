package com.example.ApiPedido.Service;

import java.util.List;
//Importar ese optional , no otro
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiPedido.Model.Pedido;
import com.example.ApiPedido.Repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired //Inyeccion de repo
    private PedidoRepository pedidoRepository;


    // OBETNER todas los PEDIDO del dba
    public List<Pedido> getAll() {
        return pedidoRepository.findAll(); 
    }


    // BUSCAR pedido por ID
    public Pedido getById(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id); // Busca por ID
        return pedido.orElse(null); // Si no la encuentra, retorna null
    }

    
    // CREAR PEDIDO
    public Pedido add(Pedido pedido) {
        return pedidoRepository.save(pedido); // GUARDA y RETORNA el PEDIDO
    }

    //ACTUALIZAR una persona existente
    public Pedido update(Integer id, Pedido pedido) {
        if (pedidoRepository.existsById(id)) {
            pedido.setId(id); // Aseguramos que se use el mismo ID
            return pedidoRepository.save(pedido); // Guarda los cambios
        }
        return null; // No se encontró pedido
    }//opcional : Averiguar como dejar un mensaje escrito 


    // ELIMINAR una PEDIDO por ID
    public Pedido delete(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            pedidoRepository.deleteById(id); // Elimina la PEDIDO
            return pedido.get(); // Retorna PEDIDO eliminada
        }
        return null; // No existe El pedido
    }//opcional : Averiguar como dejar un mensaje escrito 
}
