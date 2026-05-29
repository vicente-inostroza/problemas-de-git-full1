package com.notificaciones.notificaciones.service;

import com.notificaciones.notificaciones.model.Notificacion;
import com.notificaciones.notificaciones.repository.NotificacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository repository;

    public List<Notificacion> findAll() {
        return repository.findAll();
    }

    public Optional<Notificacion> findById(Integer id) {
        return repository.findById(id);
    }

    public Notificacion save(Notificacion notificacion) {
        return repository.save(notificacion);
    }

    public Optional<Notificacion> update(Integer id, Notificacion notificacionActualizada) {
        return repository.findById(id)
                .map(notificacionExistente -> {
                    notificacionExistente.setUsuarioId(notificacionActualizada.getUsuarioId());
                    notificacionExistente.setMensaje(notificacionActualizada.getMensaje());
                    notificacionExistente.setLeido(notificacionActualizada.isLeido());
                    return repository.save(notificacionExistente);
                });
    }

    public boolean delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Notificacion> obtenerNotificacionesDeUsuario(Integer usuarioId) {
        return repository.findByUsuarioIdAndLeidoFalseOrderByIdNotificacionDesc(usuarioId);
    }

    public boolean marcarComoLeida(Integer id) {
        if (repository.existsById(id)) {
            Notificacion notificacion = repository.findById(id).orElseThrow();
            notificacion.setLeido(true);
            repository.save(notificacion);
            return true;
        } else {
            return false;
        }
    }

    public Integer count() {
        return (int) repository.count();
    }

    public void crearNotificacion(Notificacion notificacion) {
        repository.save(notificacion);
    }
}
