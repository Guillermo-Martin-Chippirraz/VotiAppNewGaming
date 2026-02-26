package com.newgaming.votacionGestor.repositories;

import com.newgaming.votacionGestor.models.Formulario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio encargado de gestionar el acceso a datos de la entidad {@link Formulario}.
 * Proporciona todas las operaciones CRUD estándar gracias a la herencia de
 * {@link JpaRepository}, permitiendo interactuar con la base de datos sin necesidad
 * de implementar consultas manuales.
 *
 * <p>Este repositorio permite:</p>
 * <ul>
 *     <li>Buscar formularios por su ID.</li>
 *     <li>Registrar nuevos formularios.</li>
 *     <li>Actualizar formularios existentes.</li>
 *     <li>Eliminar formularios.</li>
 *     <li>Listar todos los formularios registrados.</li>
 * </ul>
 *
 * <p>Además, incluye un método derivado para obtener el formulario asociado a un evento
 * concreto, aprovechando la convención de nombres de Spring Data JPA para generar
 * automáticamente la consulta.</p>
 *
 * <p>La anotación {@link Repository} permite que Spring detecte esta interfaz como
 * un componente de acceso a datos y gestione su ciclo de vida.</p>
 *
 * <p>Método adicional:</p>
 * <ul>
 *     <li><strong>findByEventoIdEvento(Long idEvento)</strong> — Obtiene el formulario
 *     asociado a un evento específico, si existe.</li>
 * </ul>
 *
 * @version 0.1
 * @author Guillermo Martín Chippirraz
 */
@Repository
public interface FormularioRepository extends JpaRepository<Formulario, Long> {

    /**
     * Busca el formulario asociado a un evento concreto.
     *
     * @param idEvento identificador del evento.
     * @return un {@link Optional} que contiene el formulario si existe.
     */
    Optional<Formulario> findByEventoIdEvento(Long idEvento);
}
