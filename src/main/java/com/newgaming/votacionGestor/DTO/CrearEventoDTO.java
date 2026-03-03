package com.newgaming.votacionGestor.DTO;

import com.newgaming.votacionGestor.models.Evento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * DTO utilizado para la creación de nuevos eventos dentro del sistema.
 * Permite transportar desde el cliente la información necesaria para registrar
 * un evento, aplicando validaciones básicas sobre los campos recibidos.
 *
 * <p>Este DTO se emplea exclusivamente en operaciones de entrada (creación),
 * evitando exponer detalles internos de la entidad <code>Evento</code> y
 * garantizando que los datos proporcionados cumplen los requisitos mínimos
 * antes de ser convertidos en una entidad persistente.</p>
 *
 * <p>Incluye un método <code>toEntity()</code> que transforma el DTO en una
 * instancia de <code>Evento</code>, facilitando la integración con la capa
 * de servicio.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
public class CrearEventoDTO {

    /**
     * Nombre del evento.
     *
     * <ul>
     *     <li>Debe ser un texto no vacío.</li>
     *     <li>Identifica el evento dentro del sistema.</li>
     * </ul>
     */
    @NotBlank
    private String nombre;

    /**
     * Descripción del evento.
     *
     * <ul>
     *     <li>Debe ser un texto no vacío.</li>
     *     <li>Proporciona información adicional sobre el evento.</li>
     * </ul>
     */
    @NotBlank
    private String description;

    /**
     * Fecha en la que se celebrará el evento.
     *
     * <ul>
     *     <li>Debe ser un valor no nulo.</li>
     *     <li>Representa la fecha oficial del evento.</li>
     * </ul>
     */
    @NotNull
    private LocalDate fecha;

    /**
     * Estado inicial del evento.
     *
     * <ul>
     *     <li>Debe ser un valor no nulo.</li>
     *     <li>Generalmente toma valores como "CREADO", "HABILITADO" o "DESHABILITADO".</li>
     * </ul>
     */
    @NotNull
    private String estado;

    /**
     * Obtiene el nombre del evento.
     *
     * @since v0.2
     * @return nombre del evento.
     */
    public @NotBlank String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del evento.
     *
     * @since v0.2
     * @param nombre nombre del evento.
     */
    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del evento.
     *
     * @since v0.2
     * @return descripción del evento.
     */
    public @NotBlank String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del evento.
     *
     * @since v0.2
     * @param description descripción del evento.
     */
    public void setDescription(@NotBlank String description) {
        this.description = description;
    }

    /**
     * Obtiene la fecha del evento.
     *
     * @since v0.2
     * @return fecha del evento.
     */
    public @NotNull LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del evento.
     *
     * @since v0.2
     * @param fecha fecha del evento.
     */
    public void setFecha(@NotNull LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el estado inicial del evento.
     *
     * @since v0.2
     * @return estado del evento.
     */
    public @NotNull String getEstado() {
        return estado;
    }

    /**
     * Establece el estado inicial del evento.
     *
     * @since v0.2
     * @param estado estado del evento.
     */
    public void setEstado(@NotNull String estado) {
        this.estado = estado;
    }

    /**
     * Convierte este DTO en una entidad <code>Evento</code>.
     *
     * <ol>
     *     <li>Crea una nueva instancia de <code>Evento</code>.</li>
     *     <li>Asigna el nombre, descripción, fecha y estado recibidos.</li>
     *     <li>No establece campos internos como creador o fechas de auditoría,
     *         ya que estos se asignan en la capa de servicio.</li>
     * </ol>
     *
     * @since v0.2
     * @return entidad <code>Evento</code> lista para ser procesada por la capa de servicio.
     */
    public Evento toEntity() {
        Evento e = new Evento();
        e.setNombre(nombre);
        e.setDescripcion(description);
        e.setFecha(fecha);
        e.setEstado(estado);
        return e;
    }
}