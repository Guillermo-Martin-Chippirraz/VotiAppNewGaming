package com.newgaming.votacionGestor.repositories;

import com.newgaming.votacionGestor.models.Votacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio encargado de gestionar el acceso a datos de la entidad {@link Votacion}.
 * Proporciona todas las operaciones CRUD estándar gracias a la herencia de
 * {@link JpaRepository}, permitiendo interactuar con la base de datos sin necesidad
 * de implementar consultas manuales.
 *
 * <p>Este repositorio permite:</p>
 * <ul>
 *     <li>Buscar votaciones por su ID.</li>
 *     <li>Registrar nuevas votaciones.</li>
 *     <li>Actualizar votaciones existentes.</li>
 *     <li>Eliminar votaciones.</li>
 *     <li>Listar todas las votaciones registradas.</li>
 * </ul>
 *
 * <p>Además, incluye métodos derivados para consultar votaciones según el evento,
 * la empresa o el jurado asociado, aprovechando la convención de nombres de
 * Spring Data JPA para generar automáticamente las consultas necesarias.</p>
 *
 * <p>La anotación {@link Repository} permite que Spring detecte esta interfaz como
 * un componente de acceso a datos y gestione su ciclo de vida.</p>
 *
 * <p>Métodos adicionales:</p>
 * <ul>
 *     <li><strong>findByEventoIdEvento(Long idEvento)</strong> — Obtiene todas las votaciones
 *     asociadas a un evento concreto.</li>
 *
 *     <li><strong>findByEmpresaIdEmpresa(Long idEmpresa)</strong> — Obtiene todas las votaciones
 *     realizadas sobre una empresa específica.</li>
 *
 *     <li><strong>findByJuradoIdUsuario(Long idUsuario)</strong> — Obtiene todas las votaciones
 *     realizadas por un jurado concreto.</li>
 * </ul>
 *
 * @version 0.1
 * @author Guillermo Martín Chippirraz
 */
@Repository
public interface VotacionRepository extends JpaRepository<Votacion, Long> {

    /**
     * Obtiene todas las votaciones asociadas a un evento concreto.
     *
     * @param idEvento identificador del evento.
     * @return lista de votaciones correspondientes al evento.
     */
    List<Votacion> findByEventoIdEvento(Long idEvento);

    /**
     * Obtiene todas las votaciones asociadas a una empresa concreta.
     *
     * @param idEmpresa identificador de la empresa.
     * @return lista de votaciones correspondientes a la empresa.
     */
    List<Votacion> findByEmpresaIdEmpresa(Long idEmpresa);

    /**
     * Obtiene todas las votaciones realizadas por un jurado concreto.
     *
     * @param idUsuario identificador del jurado.
     * @return lista de votaciones realizadas por el jurado.
     */
    List<Votacion> findByJuradoIdUsuario(Long idUsuario);
}
