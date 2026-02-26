package com.newgaming.votacionGestor.repositories;

import com.newgaming.votacionGestor.models.Jurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio encargado de gestionar el acceso a datos de la entidad {@link Jurado}.
 * Proporciona todas las operaciones CRUD estándar gracias a la herencia de
 * {@link JpaRepository}, permitiendo interactuar con la base de datos sin necesidad
 * de implementar consultas manuales.
 *
 * <p>Este repositorio permite:</p>
 * <ul>
 *     <li>Buscar jurados por su ID.</li>
 *     <li>Registrar nuevos jurados.</li>
 *     <li>Actualizar jurados existentes.</li>
 *     <li>Eliminar jurados.</li>
 *     <li>Listar todos los jurados registrados.</li>
 * </ul>
 *
 * <p>La anotación {@link Repository} permite que Spring detecte esta interfaz como
 * un componente de acceso a datos y gestione su ciclo de vida automáticamente.</p>
 *
 * @version 0.1
 * @author Guillermo Martín Chippirraz
 */
@Repository
public interface JuradoRepository extends JpaRepository<Jurado, Long> {
}
