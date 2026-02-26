package com.newgaming.votacionGestor.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase que modela un evento dentro del sistema de votación.
 * Un evento representa una edición o convocatoria en la que los jurados
 * evaluarán empresas registradas.
 *
 * <p>El comportamiento y estructura de la clase se basan en los siguientes puntos:</p>
 * <ol>
 *     <li>Incluye un identificador único generado automáticamente.</li>
 *     <li>Almacena información básica del evento: nombre, fecha, descripción y estado.</li>
 *     <li>Registra qué usuario creó el evento mediante una relación ManyToOne.</li>
 *     <li>Permite almacenar qué administrador habilita o deshabilita el evento.</li>
 *     <li>Incluye marcas temporales de creación y actualización para auditoría.</li>
 *     <li>Se almacena en la tabla "evento" dentro de la base de datos.</li>
 * </ol>
 *
 * <p>Los eventos son gestionados por administradores y pueden estar en distintos
 * estados según su ciclo de vida (por ejemplo: creado, habilitado, deshabilitado).</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Entity
@Table(name = "evento")
public class Evento {

    /**
     * Identificador único del evento.
     *
     * <ol>
     *     <li>Clave primaria de la tabla evento.</li>
     *     <li>Generado automáticamente mediante estrategia IDENTITY.</li>
     * </ol>
     *
     * @since 0.1
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    /**
     * Nombre del evento.
     *
     * <ol>
     *     <li>Identifica la edición o convocatoria.</li>
     *     <li>Debe ser claro y representativo.</li>
     * </ol>
     *
     * @since 0.1
     */
    private String nombre;

    /**
     * Fecha en la que se celebra el evento.
     *
     * <ol>
     *     <li>Representa el día oficial del evento.</li>
     *     <li>Se utiliza para organizar votaciones y actividades.</li>
     * </ol>
     *
     * @since 0.1
     */
    private LocalDate fecha;

    /**
     * Descripción general del evento.
     *
     * <ol>
     *     <li>Proporciona información contextual para jurados y administradores.</li>
     *     <li>Puede incluir detalles sobre la temática o propósito del evento.</li>
     * </ol>
     *
     * @since 0.1
     */
    private String descripcion;

    /**
     * Estado actual del evento.
     *
     * <ol>
     *     <li>Puede representar estados como: creado, habilitado o deshabilitado.</li>
     *     <li>Determina si el evento está disponible para votaciones.</li>
     * </ol>
     *
     * @since 0.1
     */
    private String estado;

    /**
     * Usuario que creó el evento.
     *
     * <ol>
     *     <li>Relación ManyToOne: un usuario puede crear varios eventos.</li>
     *     <li>Se almacena en la columna id_usuario.</li>
     * </ol>
     *
     * @since 0.1
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioCreador;

    /**
     * Administrador que habilita el evento.
     *
     * <ol>
     *     <li>Relación ManyToOne: un administrador puede habilitar varios eventos.</li>
     *     <li>Se almacena en la columna id_admin_habilita.</li>
     * </ol>
     *
     * @since 0.1
     */
    @ManyToOne
    @JoinColumn(name = "id_admin_habilita")
    private Administrador adminHabilita;

    /**
     * Administrador que deshabilita el evento.
     *
     * <ol>
     *     <li>Relación ManyToOne: un administrador puede deshabilitar varios eventos.</li>
     *     <li>Se almacena en la columna id_admin_deshabilita.</li>
     * </ol>
     *
     * @since 0.1
     */
    @ManyToOne
    @JoinColumn(name = "id_admin_deshabilita")
    private Administrador adminDeshabilita;

    /**
     * Fecha y hora en la que se creó el evento.
     *
     * <ol>
     *     <li>Asignada al registrar el evento.</li>
     *     <li>Permite auditoría temporal.</li>
     * </ol>
     *
     * @since 0.1
     */
    private LocalDateTime creadoEn;

    /**
     * Fecha y hora de la última actualización del evento.
     *
     * <ol>
     *     <li>Actualizada cada vez que se modifica el evento.</li>
     *     <li>Permite control de cambios.</li>
     * </ol>
     *
     * @since 0.1
     */
    private LocalDateTime actualizadoEn;

    /**
     * Obtiene el identificador único del evento.
     *
     * @return ID del evento.
     * @since 0.1
     */
    public Long getIdEvento() {
        return idEvento;
    }

    /**
     * Establece el identificador único del evento.
     *
     * @param idEvento ID del evento.
     * @since 0.1
     */
    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * Obtiene el nombre del evento.
     *
     * @return nombre del evento.
     * @since 0.1
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del evento.
     *
     * @param nombre nombre del evento.
     * @since 0.1
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la fecha del evento.
     *
     * @return fecha del evento.
     * @since 0.1
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del evento.
     *
     * @param fecha fecha del evento.
     * @since 0.1
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la descripción del evento.
     *
     * @return descripción del evento.
     * @since 0.1
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del evento.
     *
     * @param descripcion texto descriptivo.
     * @since 0.1
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el estado del evento.
     *
     * @return estado del evento.
     * @since 0.1
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del evento.
     *
     * @param estado estado del evento.
     * @since 0.1
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el usuario creador del evento.
     *
     * @return usuario creador.
     * @since 0.1
     */
    public Usuario getUsuarioCreador() {
        return usuarioCreador;
    }

    /**
     * Establece el usuario creador del evento.
     *
     * @param usuarioCreador usuario creador.
     * @since 0.1
     */
    public void setUsuarioCreador(Usuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    /**
     * Obtiene el administrador que habilitó el evento.
     *
     * @return administrador habilitador.
     * @since 0.1
     */
    public Administrador getAdminHabilita() {
        return adminHabilita;
    }

    /**
     * Establece el administrador que habilita el evento.
     *
     * @param adminHabilita administrador habilitador.
     * @since 0.1
     */
    public void setAdminHabilita(Administrador adminHabilita) {
        this.adminHabilita = adminHabilita;
    }

    /**
     * Obtiene el administrador que deshabilitó el evento.
     *
     * @return administrador deshabilitador.
     * @since 0.1
     */
    public Administrador getAdminDeshabilita() {
        return adminDeshabilita;
    }

    /**
     * Establece el administrador que deshabilita el evento.
     *
     * @param adminDeshabilita administrador deshabilitador.
     * @since 0.1
     */
    public void setAdminDeshabilita(Administrador adminDeshabilita) {
        this.adminDeshabilita = adminDeshabilita;
    }

    /**
     * Obtiene la fecha de creación del evento.
     *
     * @return fecha de creación.
     * @since 0.1
     */
    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    /**
     * Establece la fecha de creación del evento.
     *
     * @param creadoEn fecha de creación.
     * @since 0.1
     */
    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    /**
     * Obtiene la fecha de última actualización del evento.
     *
     * @return fecha de actualización.
     * @since 0.1
     */
    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    /**
     * Establece la fecha de última actualización del evento.
     *
     * @param actualizadoEn fecha de actualización.
     * @since 0.1
     */
    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }
}

