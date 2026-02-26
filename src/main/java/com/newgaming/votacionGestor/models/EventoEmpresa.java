package com.newgaming.votacionGestor.models;

import jakarta.persistence.*;

/**
 * Clase que modela la relación entre un evento y una empresa dentro del sistema.
 * Representa la asociación de tipo muchos-a-muchos entre {@link Evento} y {@link Empresa},
 * utilizando una clave primaria compuesta definida en {@link EventoEmpresaId}.
 *
 * <p>El comportamiento y estructura de la clase se basan en los siguientes puntos:</p>
 * <ol>
 *     <li>Utiliza una clave compuesta mediante la anotación {@code @EmbeddedId}.</li>
 *     <li>Asocia un evento con una empresa mediante dos relaciones ManyToOne.</li>
 *     <li>La anotación {@code @MapsId} sincroniza los IDs de la clave compuesta con las entidades relacionadas.</li>
 *     <li>Se almacena en la tabla "evento_empresa", que actúa como tabla intermedia.</li>
 *     <li>Permite registrar qué empresas participan en qué eventos.</li>
 * </ol>
 *
 * <p>Esta clase es fundamental para gestionar la participación de empresas en eventos
 * sin duplicar información y manteniendo integridad referencial.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Entity
@Table(name = "evento_empresa")
public class EventoEmpresa {

    /**
     * Clave primaria compuesta que identifica la relación evento-empresa.
     *
     * <ol>
     *     <li>Incluye los campos idEvento e idEmpresa.</li>
     *     <li>Permite evitar duplicidades en la relación.</li>
     * </ol>
     *
     * @since 0.1
     */
    @EmbeddedId
    private EventoEmpresaId id;

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
     * Empresa asociada a la relación.
     *
     * <ol>
     *     <li>Relación ManyToOne con la entidad Empresa.</li>
     *     <li>{@code @MapsId("idEmpresa")} sincroniza el ID de la empresa con la clave compuesta.</li>
     * </ol>
     *
     * @since 0.1
     */
    @ManyToOne
    @MapsId("idEmpresa")
    private Empresa empresa;

    /**
     * Constructor por defecto.
     * Inicializa un objeto EventoEmpresa vacío.
     *
     * <ol>
     *     <li>Requerido por JPA para instanciación automática.</li>
     *     <li>No asigna valores a los atributos.</li>
     * </ol>
     *
     * @since 0.1
     */
    public EventoEmpresa() {
    }

    /**
     * Constructor por parámetros.
     * Inicializa la relación entre un evento y una empresa mediante una clave compuesta.
     *
     * <ol>
     *     <li>Asigna la clave primaria compuesta recibida.</li>
     *     <li>Asigna el evento asociado.</li>
     *     <li>Asigna la empresa asociada.</li>
     * </ol>
     *
     * @param id clave primaria compuesta.
     * @param evento evento asociado.
     * @param empresa empresa asociada.
     * @since 0.1
     */
    public EventoEmpresa(EventoEmpresaId id, Evento evento, Empresa empresa) {
        this.id = id;
        this.evento = evento;
        this.empresa = empresa;
    }

    /**
     * Obtiene la clave primaria compuesta de la relación.
     *
     * @return objeto EventoEmpresaId.
     * @since 0.1
     */
    public EventoEmpresaId getId() {
        return id;
    }

    /**
     * Establece la clave primaria compuesta de la relación.
     *
     * @param id clave compuesta.
     * @since 0.1
     */
    public void setId(EventoEmpresaId id) {
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
     * Obtiene la empresa asociada.
     *
     * @return empresa vinculada.
     * @since 0.1
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * Establece la empresa asociada.
     *
     * @param empresa empresa vinculada.
     * @since 0.1
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
