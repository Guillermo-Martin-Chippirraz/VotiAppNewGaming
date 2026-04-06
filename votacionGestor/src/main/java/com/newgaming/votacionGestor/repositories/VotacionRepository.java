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
 * @version v0.3
 * @author Guillermo Martín Chippirraz
 */
@Repository
public interface VotacionRepository extends JpaRepository<Votacion, Long> {

    /**
     * Obtiene todas las votaciones asociadas a un evento concreto.
     *
     * @since v0.1
     * @param idEvento identificador del evento.
     * @return lista de votaciones correspondientes al evento.
     */
    List<Votacion> findByEventoIdEvento(Long idEvento);

    /**
     * Obtiene todas las votaciones asociadas a una empresa concreta.
     *
     * @since v0.1
     * @param idEmpresa identificador de la empresa.
     * @return lista de votaciones correspondientes a la empresa.
     */
    List<Votacion> findByEmpresaIdEmpresa(Long idEmpresa);

    /**
     * Obtiene todas las votaciones realizadas por un jurado concreto.
     *
     * @since v0.1
     * @param idUsuario identificador del jurado.
     * @return lista de votaciones realizadas por el jurado.
     */
    List<Votacion> findByJuradoIdUsuario(Long idUsuario);

    /**
     * Elimina todas las entidades asociadas a una empresa específica.
     *
     * <p>Este método borra todos los registros cuya relación apunte a la empresa
     * identificada por {@code idEmpresa}. Se utiliza normalmente cuando una empresa
     * es eliminada del sistema y es necesario limpiar sus dependencias para
     * mantener la integridad referencial.</p>
     *
     * @since v0.3
     * @param idEmpresa identificador de la empresa cuyos registros asociados deben eliminarse.
     */
    void deleteByEmpresaIdEmpresa(Long idEmpresa);

    /**
     * Elimina todas las entidades asociadas a un evento específico.
     *
     * <p>Este método elimina los registros cuya relación apunte al evento
     * identificado por {@code idEvento}. Es útil cuando un evento se elimina del
     * sistema y se requiere borrar todas las dependencias vinculadas a él.</p>
     *
     * @since v0.3
     * @param idEvento identificador del evento cuyos registros asociados deben eliminarse.
     */
    void deleteByEventoIdEvento(Long idEvento);

    /**
     * Verifica si existe una entidad asociada simultáneamente a un evento y a una empresa.
     *
     * <p>Este método permite comprobar si ya existe un registro que vincule el evento
     * identificado por {@code idEvento} con la empresa identificada por {@code idEmpresa}.
     * Es útil para evitar duplicidades o validar relaciones antes de crear nuevas
     * asociaciones.</p>
     *
     * @since v0.3
     * @param idEvento identificador del evento.
     * @param idEmpresa identificador de la empresa.
     * @return {@code true} si existe una entidad que relacione ambos identificadores;
     *         {@code false} en caso contrario.
     */
    boolean existsByEventoIdEventoAndEmpresaIdEmpresa(Long idEvento, Long idEmpresa);

}
