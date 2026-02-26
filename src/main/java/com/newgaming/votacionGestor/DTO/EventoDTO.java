package com.newgaming.votacionGestor.DTO;

import java.time.LocalDate;

/**
 * DTO utilizado para representar información resumida de un evento dentro del sistema.
 * Permite transportar datos esenciales sin exponer la entidad completa, facilitando
 * respuestas más ligeras y seguras en los endpoints.
 *
 * <p>El funcionamiento general del DTO se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Incluye el identificador del evento.</li>
 *     <li>Contiene el nombre del evento.</li>
 *     <li>Indica el estado actual del evento (habilitado, deshabilitado, etc.).</li>
 *     <li>Incluye la fecha en la que se llevará a cabo el evento.</li>
 *     <li>Se utiliza para enviar información al cliente sin exponer detalles internos.</li>
 * </ol>
 *
 * <p>Este DTO facilita la comunicación entre backend y frontend, proporcionando
 * únicamente los datos necesarios para mostrar o manipular información del evento.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
public class EventoDTO {

    /**
     * Identificador único del evento.
     *
     * <ol>
     *     <li>Permite distinguir el evento dentro del sistema.</li>
     *     <li>Es utilizado para operaciones de consulta y actualización.</li>
     * </ol>
     *
     * @since 0.1
     */
    private Long id;

    /**
     * Nombre del evento.
     *
     * <ol>
     *     <li>Representa el título o denominación del evento.</li>
     *     <li>Se utiliza para mostrar información al usuario.</li>
     * </ol>
     *
     * @since 0.1
     */
    private String nombre;

    /**
     * Estado actual del evento.
     *
     * <ol>
     *     <li>Indica si el evento está habilitado, deshabilitado u otro estado definido.</li>
     *     <li>Permite controlar su disponibilidad dentro del sistema.</li>
     * </ol>
     *
     * @since 0.1
     */
    private String estado;

    /**
     * Fecha en la que se llevará a cabo el evento.
     *
     * <ol>
     *     <li>Representa la fecha programada del evento.</li>
     *     <li>Se utiliza para mostrar información temporal al usuario.</li>
     * </ol>
     *
     * @since 0.1
     */
    private LocalDate fecha;

    /**
     * Obtiene el identificador del evento.
     *
     * @since 0.1
     * @return ID del evento.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del evento.
     *
     * @since 0.1
     * @param id ID del evento.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del evento.
     *
     * @since 0.1
     * @return nombre del evento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del evento.
     *
     * @since 0.1
     * @param nombre nombre del evento.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el estado del evento.
     *
     * @since 0.1
     * @return estado del evento.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del evento.
     *
     * @since 0.1
     * @param estado estado del evento.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la fecha del evento.
     *
     * @since 0.1
     * @return fecha del evento.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del evento.
     *
     * @since 0.1
     * @param fecha fecha del evento.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
