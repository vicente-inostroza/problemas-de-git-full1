package com.example.favoritos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.favoritos.model.Favorito;
import com.example.favoritos.repository.FavoritoRepository;
import com.example.favoritos.exception.FavoritoAlreadyExistsException;
import com.example.favoritos.exception.JuegoNotFoundException;
import com.example.favoritos.exception.ServiceUnavailableException;
import com.example.favoritos.exception.ResourceNotFoundException;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${catalogo.service.url}")
    private String catalogoServiceUrl;

    public Favorito guardarFavorito(Favorito favorito) {
        // Sanitize input to remove leading/trailing whitespace
        final String trimmedJuegoId = favorito.getJuegoId().trim();
        final String trimmedUsuarioId = favorito.getUsuarioId().trim();

        // 1. Verificar que este favorito no exista ya
        if (favoritoRepository.existsByUsuarioIdAndJuegoId(trimmedUsuarioId, trimmedJuegoId)) {
            throw new FavoritoAlreadyExistsException("El juego con ID: " + trimmedJuegoId + " ya está en los favoritos del usuario.");
        }

        // 2. Verificar que el juegoId existe en el microservicio de catálogo
        String url = catalogoServiceUrl + "/" + trimmedJuegoId;
        try {
            restTemplate.getForEntity(url, Object.class);
        } catch (HttpClientErrorException.NotFound e) {
            // Si el catálogo devuelve 404, lanzamos nuestra excepción personalizada
            throw new JuegoNotFoundException("El juego con ID: " + trimmedJuegoId + " no existe en el catálogo.");
        } catch (ResourceAccessException e) {
            // Si no se puede conectar al servicio de catálogo (está caído)
            throw new ServiceUnavailableException("No se pudo conectar al servicio de Catálogo. Inténtelo de nuevo más tarde.");
        }

        // 3. Si todo está bien, guardamos el favorito con los datos limpios
        favorito.setJuegoId(trimmedJuegoId);
        favorito.setUsuarioId(trimmedUsuarioId);
        return favoritoRepository.save(favorito);
    }

    public Favorito actualizarFavorito(Integer id, Favorito favoritoDetails) {
        // 1. Encontrar el favorito existente. Este método ya lanza ResourceNotFoundException si no lo encuentra.
        Favorito favoritoExistente = obtenerPorId(id);

        // 2. Limpiar los datos de entrada
        final String trimmedJuegoId = favoritoDetails.getJuegoId().trim();
        final String trimmedUsuarioId = favoritoDetails.getUsuarioId().trim();

        // 3. Verificar si los datos han cambiado antes de hacer validaciones costosas
        boolean isJuegoIdChanged = !favoritoExistente.getJuegoId().equals(trimmedJuegoId);
        boolean isUsuarioIdChanged = !favoritoExistente.getUsuarioId().equals(trimmedUsuarioId);

        if (!isJuegoIdChanged && !isUsuarioIdChanged) {
            return favoritoExistente; // No hay cambios, no es necesario actualizar
        }

        // 4. Si el juego o el usuario cambiaron, verificar que la nueva combinación no exista ya en otro registro
        if (favoritoRepository.existsByUsuarioIdAndJuegoId(trimmedUsuarioId, trimmedJuegoId)) {
            throw new FavoritoAlreadyExistsException("La combinación del juego con ID: " + trimmedJuegoId + " y el usuario ya existe.");
        }

        // 5. Si el juegoId cambió, validarlo contra el catálogo
        if (isJuegoIdChanged) {
            String url = catalogoServiceUrl + "/" + trimmedJuegoId;
            try {
                restTemplate.getForEntity(url, Object.class);
            } catch (HttpClientErrorException.NotFound e) {
                throw new JuegoNotFoundException("El juego con ID: " + trimmedJuegoId + " no existe en el catálogo.");
            } catch (ResourceAccessException e) {
                throw new ServiceUnavailableException("No se pudo conectar al servicio de Catálogo. Inténtelo de nuevo más tarde.");
            }
        }

        // 6. Actualizar los datos del objeto existente y guardar
        favoritoExistente.setUsuarioId(trimmedUsuarioId);
        favoritoExistente.setJuegoId(trimmedJuegoId);
        return favoritoRepository.save(favoritoExistente);
    }

    public List<Favorito> obtenerPorUsuario(String usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId);
    }

    public List<Favorito> obtenerTodos() {
        return favoritoRepository.findAll();
    }

    public Favorito obtenerPorId(Integer id) {
        return favoritoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el favorito con ID: " + id));
    }

    public void eliminar(Integer id) {
        if (!favoritoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe un favorito con el ID: " + id);
        }
        favoritoRepository.deleteById(id);
    }
}