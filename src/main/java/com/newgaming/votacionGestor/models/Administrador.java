package com.newgaming.votacionGestor.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Clase que modela a un administrador dentro del sistema de votación.
 * Esta entidad extiende a {@link Usuario} y representa a los usuarios con
 * privilegios elevados encargados de gestionar el sistema.
 *
 * <p>El comportamiento de la clase se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Hereda todos los atributos de la clase Usuario (username, email, rol, etc.).</li>
 *     <li>No añade nuevos campos, ya que su función es diferenciar el tipo de usuario.</li>
 *     <li>Se almacena en la tabla "administrador" mediante herencia JOINED.</li>
 *     <li>Los administradores pueden registrar jurados y realizar acciones restringidas.</li>
 * </ol>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Entity
@Table(name = "administrador")
public class Administrador extends Usuario {

    /**
     * Constructor por defecto.
     * Inicializa un objeto Administrador sin valores asignados.
     *
     * <ol>
     *     <li>Llama implícitamente al constructor de Usuario.</li>
     *     <li>Permite la creación de instancias vacías para JPA.</li>
     * </ol>
     *
     * @since 0.1
     */
    public Administrador() {
        super();
    }
}
