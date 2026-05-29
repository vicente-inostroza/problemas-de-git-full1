package com.notificaciones.notificaciones.repository;

import com.notificaciones.notificaciones.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {

    List<Notificacion> findByUsuarioIdAndLeidoFalseOrderByIdNotificacionDesc(Integer usuarioId);
}
