package com.newgaming.votacionGestor.repositories;

import com.newgaming.votacionGestor.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio encargado de gestionar el acceso a datos de la entidad {@link Usuario}.
 * Proporciona todas las operaciones CRUD estándar gracias a la herencia de
 * {@link JpaRepository}, permitiendo interactuar con la base de datos sin necesidad
 * de implementar consultas manuales.
 *
 * <p>Este repositorio permite:</p>
 * <ul>
 *     <li>Buscar usuarios por su ID.</li>
 *     <li>Registrar nuevos usuarios.</li>
 *     <li>Actualizar usuarios existentes.</li>
 *     <li>Eliminar usuarios.</li>
 *     <li>Listar todos los usuarios registrados.</li>
 * </ul>
 *
 * <p>Además, incluye un método derivado para buscar usuarios por su nombre de usuario,
 * aprovechando la convención de nombres de Spring Data JPA para generar automáticamente
 * la consulta correspondiente.</p>
 *
 * <p>La anotación {@link Repository} permite que Spring detecte esta interfaz como
 * un componente de acceso a datos y gestione su ciclo de vida.</p>
 *
 * <p>Método adicional:</p>
 * <ul>
 *     <li><strong>findByUsername(String username)</strong> — Busca un usuario cuyo nombre
 *     de usuario coincida con el proporcionado.</li>
 * </ul>
 *
 * @version 0.1
 * @author Guillermo Martín Chippirraz
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username nombre de usuario.
     * @return un {@link Optional} que contiene el usuario si existe.
     */
    Optional<Usuario> findByUsername(String username);
}
