package com.newgaming.votacionGestor.models;

import jakarta.persistence.*;

/**
 * Clase que modela la relación entre un evento y un usuario dentro del sistema.
 * Representa la asociación de tipo muchos-a-muchos entre {@link Evento} y {@link Usuario},
 * utilizando una clave primaria compuesta definida en {@link EventoUsuarioId}.
 *
 * <p>El comportamiento y estructura de la clase se basan en los siguientes puntos:</p>
 * <ol>
 *     <li>Utiliza una clave compuesta mediante la anotación {@code @EmbeddedId}.</li>
 *     <li>Asocia un evento con un usuario mediante dos relaciones ManyToOne.</li>
 *     <li>La anotación {@code @MapsId} sincroniza los IDs de la clave compuesta con las entidades relacionadas.</li>
 *     <li>Se almacena en la tabla "evento_usuario", que actúa como tabla intermedia.</li>
 *     <li>Permite registrar qué usuarios participan o están vinculados a qué eventos.</li>
 * </ol>
 *
 * <p>Esta clase es fundamental para gestionar la participación de usuarios en eventos
 * sin duplicar información y manteniendo integridad referencial.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Entity
@Table(name = "evento_usuario")
public class EventoUsuario {

    /**
     * Clave primaria compuesta que identifica la relación evento-usuario.
     *
     * <ol>
     *     <li>Incluye los campos idEvento e idUsuario.</li>
     *     <li>Permite evitar duplicidades en la relación.</li>
     * </ol>
     *
     * @since 0.1
     */
    @EmbeddedId
    private EventoUsuarioId id;

    /**
     * Evento asociado a la relación.
     *
     * <ol>
     *     <li>Relación ManyToOne con la entidad Evento.</li>
     *     <li>{@code @MapsId("idEvento")} sincroniza el ID del evento con la clave compuesta.</li>
     * </ol>
     *
     * @since 0.1
     */
    @ManyToOne
    @MapsId("idEvento")
    private Evento evento;

    /**
     * Usuario asociado a la relación.
     *
     * <ol>
     *     <li>Relación ManyToOne con la entidad Usuario.</li>
     *     <li>{@code @MapsId("idUsuario")} sincroniza el ID del usuario con la clave compuesta.</li>
     * </ol>
     *
     * @since 0.1
     */
    @ManyToOne
    @MapsId("idUsuario")
    private Usuario usuario;

    /**
     * Constructor por defecto.
     * Inicializa un objeto EventoUsuario vacío.
     *
     * <ol>
     *     <li>Requerido por JPA para la creación automática de instancias.</li>
     *     <li>No asigna valores a los atributos.</li>
     * </ol>
     *
     * @since 0.1
     */
    public EventoUsuario() {
    }

    /**
     * Constructor por parámetros.
     * Inicializa la relación entre un evento y un usuario mediante una clave compuesta.
     *
     * <ol>
     *     <li>Asigna la clave primaria compuesta recibida.</li>
     *     <li>Asigna el evento asociado.</li>
     *     <li>Asigna el usuario asociado.</li>
     * </ol>
     *
     * @param id clave primaria compuesta.
     * @param evento evento asociado.
     * @param usuario usuario asociado.
     * @since 0.1
     */
    public EventoUsuario(EventoUsuarioId id, Evento evento, Usuario usuario) {
        this.id = id;
        this.evento = evento;
        this.usuario = usuario;
    }

    /**
     * Obtiene la clave primaria compuesta de la relación.
     *
     * @return objeto EventoUsuarioId.
     * @since 0.1
     */
    public EventoUsuarioId getId() {
        return id;
    }

    /**
     * Establece la clave primaria compuesta de la relación.
     *
     * @param id clave compuesta.
     * @since 0.1
     */
    public void setId(EventoUsuarioId id) {
        this.id = id;
    }

    /**
     * Obtiene el evento asociado.
     *
     * @return evento vinculado.
     * @since 0.1
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Establece el evento asociado.
     *
     * @param evento evento vinculado.
     * @since 0.1
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * Obtiene el usuario asociado.
     *
     * @return usuario vinculado.
     * @since 0.1
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado.
     *
     * @param usuario usuario vinculado.
     * @since 0.1
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
