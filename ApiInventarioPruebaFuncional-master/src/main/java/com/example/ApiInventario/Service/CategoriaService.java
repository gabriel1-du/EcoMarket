package com.example.ApiInventario.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiInventario.Model.Categoria;

import com.example.ApiInventario.Repository.CategoriaRepository;


@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;


    // Obtener todas las categorias 
    public List<Categoria> getAll() {
        return categoriaRepository.findAll();

    }

    public Categoria getById(Integer categoria_id){
        Optional<Categoria> categoria = categoriaRepository.findById(categoria_id);
        return categoria.orElse(null);
    }

    public Categoria add(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
    //Actualizar Categoria Existente
    public Categoria update(Integer categoria_id, Categoria categoria){
        if (categoriaRepository.existsById(categoria_id)) {
            categoria.setCategoria_id(categoria_id); //Aseguramos que se use el mismo ID
            return categoriaRepository.save(categoria); //guarda los cambios
            
        }
        return null; // no se encontro a la persona
    }
    
    public Categoria delete(Integer categoria_id){
        Optional<Categoria> categoria = categoriaRepository.findById(categoria_id);
        if (categoria.isPresent()) {
            categoriaRepository.deleteById(categoria_id);
            return categoria.get();
            
        }
        return null;
    }
    
}
