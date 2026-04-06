package com.newgaming.votacionGestor.repositories;

import com.newgaming.votacionGestor.models.EventoUsuario;
import com.newgaming.votacionGestor.models.EventoUsuarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio encargado de gestionar el acceso a datos de la entidad
 * {@link EventoUsuario}, que representa la relación entre eventos y usuarios
 * dentro del sistema. Esta relación utiliza una clave compuesta definida por
 * {@link EventoUsuarioId}.
 *
 * <p>Al extender {@link JpaRepository}, este repositorio proporciona todas las
 * operaciones CRUD estándar sin necesidad de implementación adicional.</p>
 *
 * <p>Incluye métodos derivados para consultar relaciones específicas según el
 * identificador del evento o del usuario, aprovechando la convención de nombres
 * de Spring Data JPA para generar automáticamente las consultas necesarias.</p>
 *
 * <p>Spring detecta automáticamente este repositorio gracias a la anotación
 * {@link Repository}, permitiendo su inyección en servicios y gestionando su
 * ciclo de vida.</p>
 *
 * <p>Métodos proporcionados:</p>
 * <ul>
 *     <li><strong>findByIdIdEvento(Long idEvento)</strong> — Obtiene todas las relaciones
 *     entre un evento y los usuarios asociados a él.</li>
 *
 *     <li><strong>findByIdIdUsuario(Long idUsuario)</strong> — Obtiene todas las relaciones
 *     entre un usuario y los eventos en los que participa.</li>
 * </ul>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Repository
public interface EventoUsuarioRepository extends JpaRepository<EventoUsuario, EventoUsuarioId> {

    /**
     * Obtiene todas las relaciones Evento-Usuario asociadas a un evento concreto.
     *
     * @param idEvento identificador del evento.
     * @return lista de relaciones correspondientes al evento.
     */
    List<EventoUsuario> findByIdIdEvento(Long idEvento);

    /**
     * Obtiene todas las relaciones Evento-Usuario asociadas a un usuario concreto.
     *
     * @param idUsuario identificador del usuario.
     * @return lista de relaciones correspondientes al usuario.
     */
    List<EventoUsuario> findByIdIdUsuario(Long idUsuario);
}
