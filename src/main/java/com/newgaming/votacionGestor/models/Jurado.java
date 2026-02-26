package com.newgaming.votacionGestor.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Clase que modela a un jurado dentro del sistema de votación.
 * Un jurado es un usuario especializado encargado de evaluar empresas
 * dentro de los eventos habilitados por los administradores.
 *
 * <p>El comportamiento y estructura de la clase se basan en los siguientes puntos:</p>
 * <ol>
 *     <li>Extiende la clase {@link Usuario}, heredando sus atributos principales.</li>
 *     <li>Incluye una relación ManyToOne con la entidad Administrador.</li>
 *     <li>El administrador asociado representa quién registró al jurado.</li>
 *     <li>Se almacena en la tabla "jurado" mediante herencia JOINED.</li>
 * </ol>
 *
 * <p>Esta clase permite identificar qué administrador creó cada jurado,
 * manteniendo trazabilidad y control dentro del sistema.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Entity
@Table(name = "jurado")
public class Jurado extends Usuario {

    /**
     * Administrador que registró al jurado.
     *
     * <ol>
     *     <li>Relación ManyToOne: un administrador puede registrar varios jurados.</li>
     *     <li>Se almacena en la columna id_admin_registro.</li>
     * </ol>
     *
     * @since 0.1
     */
    @ManyToOne
    @JoinColumn(name = "id_admin_registro")
    private Administrador adminRegistro;

    /**
     * Obtiene el administrador que registró al jurado.
     *
     * @return administrador creador.
     * @since 0.1
     */
    public Administrador getAdminRegistro() {
        return adminRegistro;
    }

    /**
     * Establece el administrador que registró al jurado.
     *
     * @param adminRegistro administrador creador.
     * @since 0.1
     */
    public void setAdminRegistro(Administrador adminRegistro) {
        this.adminRegistro = adminRegistro;
    }
}
