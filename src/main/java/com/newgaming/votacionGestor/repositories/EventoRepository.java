package com.newgaming.votacionGestor.repositories;

import com.newgaming.votacionGestor.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio encargado de gestionar el acceso a datos de la entidad {@link Evento}.
 * Proporciona todas las operaciones CRUD estándar gracias a la herencia de
 * {@link JpaRepository}, permitiendo interactuar con la base de datos sin necesidad
 * de implementar consultas manuales.
 *
 * <p>Este repositorio permite:</p>
 * <ul>
 *     <li>Buscar eventos por su ID.</li>
 *     <li>Registrar nuevos eventos.</li>
 *     <li>Actualizar eventos existentes.</li>
 *     <li>Eliminar eventos.</li>
 *     <li>Listar todos los eventos registrados.</li>
 * </ul>
 *
 * <p>Además, incluye un método derivado para consultar eventos según su estado,
 * aprovechando la convención de nombres de Spring Data JPA para generar la consulta
 * automáticamente.</p>
 *
 * <p>La anotación {@link Repository} permite que Spring detecte esta interfaz como
 * un componente de acceso a datos y gestione su ciclo de vida.</p>
 *
 * <p>Método adicional:</p>
 * <ul>
 *     <li><strong>findByEstado(String estado)</strong> — Obtiene todos los eventos cuyo
 *     estado coincida con el valor proporcionado.</li>
 * </ul>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    /**
     * Obtiene todos los eventos que coinciden con un estado específico.
     *
     * @param estado estado del evento (por ejemplo: "HABILITADO", "DESHABILITADO").
     * @return lista de eventos con el estado indicado.
     */
    List<Evento> findByEstado(String estado);
}
