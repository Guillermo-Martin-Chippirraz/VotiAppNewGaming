package com.newgaming.votacionGestor.repositories;

import com.newgaming.votacionGestor.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio encargado de gestionar el acceso a datos de la entidad
 * {@link Empresa}. Proporciona todas las operaciones CRUD estándar gracias
 * a la herencia de {@link JpaRepository}, permitiendo interactuar con la base
 * de datos sin necesidad de implementar consultas manuales.
 *
 * <p>Este repositorio permite:</p>
 * <ul>
 *     <li>Buscar empresas por su ID.</li>
 *     <li>Registrar nuevas empresas.</li>
 *     <li>Actualizar empresas existentes.</li>
 *     <li>Eliminar empresas.</li>
 *     <li>Listar todas las empresas registradas.</li>
 * </ul>
 *
 * <p>La anotación {@link Repository} permite que Spring lo detecte como un
 * componente de acceso a datos y gestione su ciclo de vida automáticamente.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
