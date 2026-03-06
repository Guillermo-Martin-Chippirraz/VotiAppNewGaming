package com.newgaming.votacionGestor.repositories;

import com.newgaming.votacionGestor.models.EventoEmpresa;
import com.newgaming.votacionGestor.models.EventoEmpresaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio encargado de gestionar el acceso a datos de la entidad
 * {@link EventoEmpresa}, la cual representa la relación entre eventos y empresas
 * dentro del sistema. Esta entidad utiliza una clave compuesta representada por
 * {@link EventoEmpresaId}.
 *
 * <p>Al extender {@link JpaRepository}, este repositorio proporciona todas las
 * operaciones CRUD estándar sin necesidad de implementación adicional.</p>
 *
 * <p>Además, incluye métodos derivados para consultar relaciones específicas
 * según el identificador del evento o de la empresa.</p>
 *
 * <p>Spring detecta automáticamente este repositorio gracias a la anotación
 * {@link Repository}, gestionando su ciclo de vida y permitiendo su inyección
 * en servicios.</p>
 *
 * <p>Consultas proporcionadas:</p>
 * <ul>
 *     <li><strong>findByIdIdEvento(Long idEvento)</strong> — Obtiene todas las relaciones
 *     entre un evento y sus empresas asociadas.</li>
 *
 *     <li><strong>findByIdIdEmpresa(Long idEmpresa)</strong> — Obtiene todas las relaciones
 *     entre una empresa y los eventos en los que participa.</li>
 * </ul>
 *
 * <p>Estos métodos aprovechan la convención de nombres de Spring Data JPA para
 * generar automáticamente las consultas necesarias.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.3
 */
@Repository
public interface EventoEmpresaRepository extends JpaRepository<EventoEmpresa, EventoEmpresaId> {

    /**
     * Obtiene todas las relaciones Evento-Empresa asociadas a un evento concreto.
     *
     * @since v0.1
     * @param idEvento identificador del evento.
     * @return lista de relaciones correspondientes al evento.
     */
    List<EventoEmpresa> findByIdIdEvento(Long idEvento);

    /**
     * Obtiene todas las relaciones Evento-Empresa asociadas a una empresa concreta.
     *
     * @since v0.1
     * @param idEmpresa identificador de la empresa.
     * @return lista de relaciones correspondientes a la empresa.
     */
    List<EventoEmpresa> findByIdIdEmpresa(Long idEmpresa);

    /**
     * Elimina todas las entidades asociadas a una empresa específica.
     *
     * <p>Este método elimina los registros cuya relación apunte a la empresa
     * identificada por {@code idEmpresa}. Se utiliza normalmente cuando una empresa
     * es eliminada del sistema y es necesario limpiar sus dependencias.</p>
     *
     * @since v0.3
     * @param idEmpresa identificador de la empresa cuyos registros asociados deben eliminarse.
     */
    void deleteByEmpresaIdEmpresa(Long idEmpresa);

    /**
     * Elimina todas las entidades asociadas a un evento específico.
     *
     * <p>Este método elimina los registros cuya relación apunte al evento
     * identificado por {@code idEvento}. Es útil cuando un evento se elimina y se
     * requiere borrar todas las dependencias vinculadas a él.</p>
     *
     * @since v0.3
     * @param idEvento identificador del evento cuyos registros asociados deben eliminarse.
     */
    void deleteByEventoIdEvento(Long idEvento);

}
