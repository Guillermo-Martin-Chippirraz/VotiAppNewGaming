package com.newgaming.votacionGestor.models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 * Clase que modela la clave primaria compuesta de la relación entre un evento
 * y un usuario dentro del sistema. Esta clave está formada por los campos
 * idEvento e idUsuario, y se utiliza en la entidad {@link EventoUsuario}.
 *
 * <p>El comportamiento y estructura de la clase se basan en los siguientes puntos:</p>
 * <ol>
 *     <li>Está anotada con {@code @Embeddable}, lo que permite incrustarla en otra entidad.</li>
 *     <li>Implementa {@link Serializable} para garantizar compatibilidad con JPA.</li>
 *     <li>Contiene dos campos Long que representan las claves foráneas del evento y el usuario.</li>
 *     <li>Se utiliza como identificador compuesto en la tabla intermedia evento_usuario.</li>
 *     <li>Permite evitar duplicidades en la relación muchos-a-muchos.</li>
 * </ol>
 *
 * <p>Esta clase es fundamental para mantener integridad referencial y representar
 * correctamente la relación entre eventos y usuarios.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Embeddable
public class EventoUsuarioId implements Serializable {

    /**
     * Identificador del evento asociado.
     *
     * <ol>
     *     <li>Corresponde a la clave primaria de la entidad Evento.</li>
     *     <li>Forma parte de la clave compuesta de EventoUsuario.</li>
     * </ol>
     *
     * @since 0.1
     */
    private Long idEvento;

    /**
     * Identificador del usuario asociado.
     *
     * <ol>
     *     <li>Corresponde a la clave primaria de la entidad Usuario.</li>
     *     <li>Forma parte de la clave compuesta de EventoUsuario.</li>
     * </ol>
     *
     * @since 0.1
     */
    private Long idUsuario;

    /**
     * Constructor por defecto.
     * Inicializa un objeto EventoUsuarioId vacío.
     *
     * <ol>
     *     <li>Requerido por JPA para la creación automática de instancias.</li>
     *     <li>No asigna valores a los atributos.</li>
     * </ol>
     *
     * @since 0.1
     */
    public EventoUsuarioId() {
    }

    /**
     * Constructor por parámetros.
     * Inicializa la clave compuesta con los identificadores del evento y el usuario.
     *
     * <ol>
     *     <li>Asigna el ID del evento recibido.</li>
     *     <li>Asigna el ID del usuario recibido.</li>
     * </ol>
     *
     * @param idEvento identificador del evento.
     * @param idUsuario identificador del usuario.
     * @since 0.1
     */
    public EventoUsuarioId(Long idEvento, Long idUsuario) {
        this.idEvento = idEvento;
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el identificador del evento.
     *
     * @return ID del evento.
     * @since 0.1
     */
    public Long getIdEvento() {
        return idEvento;
    }

    /**
     * Establece el identificador del evento.
     *
     * @param idEvento ID del evento.
     * @since 0.1
     */
    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * Obtiene el identificador del usuario.
     *
     * @return ID del usuario.
     * @since 0.1
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @param idUsuario ID del usuario.
     * @since 0.1
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
