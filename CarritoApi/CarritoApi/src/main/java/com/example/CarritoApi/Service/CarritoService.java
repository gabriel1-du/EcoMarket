package com.example.CarritoApi.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarritoApi.Model.Carrito;
import com.example.CarritoApi.Model.ItemCarrito;
import com.example.CarritoApi.Model.Usuario;
import com.example.CarritoApi.Repository.CarritoRepository;
import com.example.CarritoApi.Repository.ItemCarritoRepository;
import com.example.CarritoApi.Repository.usuarioRepository;

@Service
public class CarritoService {

    //Inyeccion de ambos repos
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private ItemCarritoRepository itemCarritoRepository;
    @Autowired
    private usuarioRepository usuarioRepository;
    

    // Obtener un carrito por ID
    public Carrito getById(Integer id) {
        Optional<Carrito> carrito = carritoRepository.findById(id);
        return carrito.orElse(null);
    }

    // Obtener el carrito activo de un usuario (ejemplo: para mostrarlo en el frontend)
    public Carrito getByUsuarioId(Integer usuarioId) {
        return carritoRepository.findByUsuarioIdAndActivoTrue(usuarioId);
    }

    // Crear un carrito nuevo para un usuario (si no tiene uno activo)
    public Carrito crearCarrito(Integer usuarioId) {
    Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
    if (usuario == null) return null;

    // Revisa si ya tiene un carrito activo
    Carrito existente = carritoRepository.findByUsuarioIdAndActivoTrue(usuarioId);
    if (existente != null) {
        return existente; // Retorna el carrito actual si ya existe uno activo
    }

    // Si no existe, crea uno nuevo
    Carrito nuevo = new Carrito();
    nuevo.setUsuario(usuario);
    nuevo.setTotal(BigDecimal.ZERO);
    nuevo.setActivo(true);
    return carritoRepository.save(nuevo);
}

    // Agregar un producto al carrito (nuevo ítem o sumar cantidad si ya existe)
    public Carrito agregarProducto(Integer carritoId, Integer productoId, int cantidad) {
        Carrito carrito = getById(carritoId);
        if (carrito == null) return null;

        Optional<ItemCarrito> existente = itemCarritoRepository.findByCarritoIdAndProductoId(carritoId, productoId);

        if (existente.isPresent()) {
            ItemCarrito item = existente.get();
            item.setCantidad(item.getCantidad() + cantidad);
            itemCarritoRepository.save(item);
        } else {
            ItemCarrito nuevo = new ItemCarrito();
            nuevo.setCarrito(carrito);
            nuevo.setProductoId(productoId);
            nuevo.setCantidad(cantidad);
            itemCarritoRepository.save(nuevo);
        }

        recalcularTotal(carritoId); // actualizar el total del carrito
        return getById(carritoId);
    }

    // Eliminar un producto del carrito
    public Carrito eliminarProducto(Integer carritoId, Integer productoId) {
        Optional<ItemCarrito> item = itemCarritoRepository.findByCarritoIdAndProductoId(carritoId, productoId);
        if (item.isPresent()) {
            itemCarritoRepository.deleteById(item.get().getId());
            recalcularTotal(carritoId);
            return getById(carritoId);
        }
        return null;
    }

    // Vaciar todos los productos del carrito
    public Carrito vaciarCarrito(Integer carritoId) {
        List<ItemCarrito> items = itemCarritoRepository.findByCarritoId(carritoId);
        itemCarritoRepository.deleteAll(items);
        recalcularTotal(carritoId);
        return getById(carritoId);
    }

    // Actualizar la cantidad de un producto
    public Carrito actualizarCantidad(Integer carritoId, Integer productoId, int nuevaCantidad) {
        Optional<ItemCarrito> item = itemCarritoRepository.findByCarritoIdAndProductoId(carritoId, productoId);
        if (item.isPresent()) {
            ItemCarrito existente = item.get();
            existente.setCantidad(nuevaCantidad);
            itemCarritoRepository.save(existente);
            recalcularTotal(carritoId);
            return getById(carritoId);
        }
        return null;
    }

    // Método interno para recalcular el total del carrito (aquí usarías precio si lo tienes)
    private void recalcularTotal(Integer carritoId) {
        Carrito carrito = carritoRepository.findById(carritoId).orElse(null);
        if (carrito != null) {
            List<ItemCarrito> items = itemCarritoRepository.findByCarritoId(carritoId);

            // Lógica temporal: como no tienes precio en ItemCarrito, asumimos 0
            BigDecimal total = BigDecimal.ZERO;
            for (ItemCarrito item : items) {
                BigDecimal precioUnitario = BigDecimal.valueOf(1000); // TODO: Obtener desde InventarioService
                total = total.add(precioUnitario.multiply(BigDecimal.valueOf(item.getCantidad())));
            }

            carrito.setTotal(total);
            carritoRepository.save(carrito);
        }
    }
}
