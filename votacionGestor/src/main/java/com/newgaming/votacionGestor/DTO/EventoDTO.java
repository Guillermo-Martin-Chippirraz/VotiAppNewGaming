package com.newgaming.votacionGestor.DTO;

import com.newgaming.votacionGestor.models.Evento;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
 * @version v0.2
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
     * @since v0.1
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
     * @since v0.1
     */
    private String nombre;


    /**
     * Descripción detallada del evento.
     *
     * <ul>
     *     <li>Puede contener texto libre.</li>
     *     <li>Proporciona información adicional sobre el evento.</li>
     *     <li>No posee restricciones de validación en este DTO.</li>
     * </ul>
     *
     * @since v0.2
     */
    private String descripcion;

    /**
     * Estado actual del evento.
     *
     * <ol>
     *     <li>Indica si el evento está habilitado, deshabilitado u otro estado definido.</li>
     *     <li>Permite controlar su disponibilidad dentro del sistema.</li>
     * </ol>
     *
     * @since v0.1
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
     * @since v0.1
     */
    private LocalDate fecha;

    /**
     * Identificador del usuario que creó el evento.
     *
     * <ul>
     *     <li>Corresponde al administrador que registró el evento.</li>
     *     <li>Se utiliza para auditoría y trazabilidad.</li>
     * </ul>
     *
     * @since v0.2
     */
    private Long idUsuarioCreador;

    /**
     * Fecha y hora en la que el evento fue creado.
     *
     * <ul>
     *     <li>Se asigna automáticamente al registrar el evento.</li>
     *     <li>Permite llevar control temporal de la creación.</li>
     * </ul>
     *
     * @since v0.2
     */
    private LocalDateTime creadoEn;

    /**
     * Fecha y hora de la última actualización del evento.
     *
     * <ul>
     *     <li>Se actualiza cada vez que se modifica el evento.</li>
     *     <li>Permite mantener un historial de cambios.</li>
     * </ul>
     *
     * @since v0.2
     */
    private LocalDateTime actualizadoEn;


    /**
     * Obtiene el identificador del evento.
     *
     * @since v0.1
     * @return ID del evento.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del evento.
     *
     * @since v0.1
     * @param id ID del evento.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del evento.
     *
     * @since v0.1
     * @return nombre del evento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del evento.
     *
     * @since v0.1
     * @param nombre nombre del evento.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del evento.
     *
     * @since v0.2
     * @return descripción del evento.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del evento.
     *
     * @since v0.2
     * @param descripcion texto descriptivo del evento.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el estado del evento.
     *
     * @since v0.1
     * @return estado del evento.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del evento.
     *
     * @since v0.1
     * @param estado estado del evento.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la fecha del evento.
     *
     * @since v0.1
     * @return fecha del evento.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del evento.
     *
     * @since v0.1
     * @param fecha fecha del evento.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el ID del usuario que creó el evento.
     *
     * @since v0.2
     * @return identificador del usuario creador.
     */
    public Long getIdUsuarioCreador() {
        return idUsuarioCreador;
    }

    /**
     * Establece el ID del usuario que creó el evento.
     *
     * @since v0.2
     * @param idUsuarioCreador identificador del usuario creador.
     */
    public void setIdUsuarioCreador(Long idUsuarioCreador) {
        this.idUsuarioCreador = idUsuarioCreador;
    }

    /**
     * Obtiene la fecha y hora de creación del evento.
     *
     * @since v0.2
     * @return fecha de creación.
     */
    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    /**
     * Establece la fecha y hora de creación del evento.
     *
     * @since v0.2
     * @param creadoEn fecha de creación.
     */
    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    /**
     * Obtiene la fecha y hora de la última actualización del evento.
     *
     * @since v0.2
     * @return fecha de última actualización.
     */
    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    /**
     * Establece la fecha y hora de la última actualización del evento.
     *
     * @since v0.2
     * @param actualizadoEn fecha de última actualización.
     */
    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }

    /**
     * Convierte una entidad <code>Evento</code> en un objeto <code>EventoDTO</code>.
     *
     * <ol>
     *     <li>Crea una nueva instancia de <code>EventoDTO</code>.</li>
     *     <li>Asigna el ID del evento.</li>
     *     <li>Asigna el nombre, descripción, fecha y estado.</li>
     *     <li>Asigna el ID del usuario creador.</li>
     *     <li>Asigna las fechas de creación y actualización.</li>
     * </ol>
     *
     * <p>Este método permite transformar la entidad completa en un DTO seguro
     * para su exposición en respuestas HTTP.</p>
     *
     * @since v0.1
     * @param evento entidad <code>Evento</code> a convertir.
     * @return instancia de <code>EventoDTO</code> con los datos del evento.
     */
    public static EventoDTO fromEntity(Evento evento) {
        EventoDTO dto = new EventoDTO();
        dto.setId(evento.getIdEvento());
        dto.setNombre(evento.getNombre());
        dto.setDescripcion(evento.getDescripcion());
        dto.setFecha(evento.getFecha());
        dto.setEstado(evento.getEstado());
        dto.setIdUsuarioCreador(evento.getUsuarioCreador().getIdUsuario());
        dto.setCreadoEn(evento.getCreadoEn());
        dto.setActualizadoEn(evento.getActualizadoEn());
        return dto;
    }

}
