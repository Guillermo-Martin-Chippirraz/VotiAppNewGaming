package com.newgaming.votacionGestor.DTO;

import java.time.LocalDate;

/**
 * DTO utilizado para la edición de datos de un evento.
 *
 * <p>Permite actualizar información relevante de un evento existente, incluyendo
 * su nombre, descripción, fecha y estado. Este objeto se emplea en las
 * operaciones de actualización realizadas por administradores dentro del
 * sistema.</p>
 *
 * <p>Todos los campos son opcionales; únicamente se modificarán aquellos que
 * sean proporcionados en la solicitud.</p>
 *
 * <p>El campo {@code estado} debe corresponder con los valores válidos definidos
 * por la lógica del sistema (por ejemplo: HABILITADO, FINALIZADO, etc.).</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.3
 */
public class EditarEventoDTO {

    /** Nombre actualizado del evento. */
    private String nombre;

    /** Descripción o información adicional del evento. */
    private String descripcion;

    /** Fecha programada del evento. */
    private LocalDate fecha;

    /** Estado actual del evento. */
    private String estado;

    /**
     * Obtiene el nombre propuesto para el evento.
     *
     * @since v0.3
     * @return nombre del evento, o {@code null} si no se ha establecido.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre que se desea asignar al evento.
     *
     * @since v0.3
     * @param nombre nuevo nombre del evento.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción propuesta para el evento.
     *
     * @since v0.3
     * @return descripción del evento, o {@code null} si no se ha establecido.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción que se desea asignar al evento.
     *
     * @since v0.3
     * @param descripcion nueva descripción del evento.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha propuesta para el evento.
     *
     * @since v0.3
     * @return fecha del evento, o {@code null} si no se ha establecido.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha que se desea asignar al evento.
     *
     * @since v0.3
     * @param fecha nueva fecha del evento.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el estado propuesto para el evento.
     *
     * @since v0.3
     * @return estado del evento, o {@code null} si no se ha establecido.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado que se desea asignar al evento.
     *
     * @since v0.3
     * @param estado nuevo estado del evento.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}