package com.newgaming.votacionGestor.repositories;

import com.newgaming.votacionGestor.models.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio encargado de gestionar el acceso a datos de la entidad
 * {@link Administrador}. Proporciona operaciones CRUD completas gracias a
 * la herencia de {@link JpaRepository}, permitiendo interactuar con la base
 * de datos sin necesidad de implementar consultas manuales.
 *
 * <p>Este repositorio permite:</p>
 * <ul>
 *     <li>Buscar administradores por su ID.</li>
 *     <li>Guardar nuevos administradores.</li>
 *     <li>Actualizar administradores existentes.</li>
 *     <li>Eliminar administradores.</li>
 *     <li>Listar todos los administradores registrados.</li>
 * </ul>
 *
 * <p>Al estar anotado con {@link Repository}, Spring lo detecta automáticamente
 * como un componente de acceso a datos y gestiona su ciclo de vida.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}
