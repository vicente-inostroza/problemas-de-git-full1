package com.carrito.carrito.repository;

import com.carrito.carrito.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer> {

    // Método para buscar todos los elementos del carrito que le pertenecen a un usuario específico
    List<CartItem> findByIdUsuario(Integer idUsuario);
}