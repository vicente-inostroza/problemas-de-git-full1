package com.carrito.carrito.service;

import com.carrito.carrito.model.CartItem;
import com.carrito.carrito.model.CartResponse;
import com.carrito.carrito.model.GameData;
import com.carrito.carrito.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;

    // Obtiene los artículos del carrito de un usuario y los cruza con los datos de catálogo usando llamadas HTTP
    public List<CartResponse> obtenerCarrito(Integer idUsuario) {
        
        List<CartItem> items = repository.findByIdUsuario(idUsuario);
        List<CartResponse> respuestas = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        
        for (CartItem item : items) {
            String url = "http://localhost:8081/catalogo/buscar-id/" + item.getIdGame();
            
            GameData juego = restTemplate.getForObject(url, GameData.class);
            
            CartResponse respuesta = new CartResponse();
            respuesta.setIdCartItem(item.getIdCartItem());
            respuesta.setIdUsuario(item.getIdUsuario());
            respuesta.setCantidad(item.getCantidad());
            respuesta.setJuego(juego);
            
            respuestas.add(respuesta);
        }
        
        return respuestas;
    }

    // Almacena un nuevo artículo o actualización en la base de datos local del carrito
    public CartItem agregar(CartItem item) {
        return repository.save(item);
    }

    // Verifica la existencia de un artículo por su ID y lo remueve de la base de datos si existe
    public boolean delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}