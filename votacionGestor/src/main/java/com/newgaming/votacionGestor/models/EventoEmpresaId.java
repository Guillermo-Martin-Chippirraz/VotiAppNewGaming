package com.newgaming.votacionGestor.models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 * Clase que modela la clave primaria compuesta de la relación entre un evento
 * y una empresa dentro del sistema. Esta clave está formada por los campos
 * idEvento e idEmpresa, y se utiliza en la entidad {@link EventoEmpresa}.
 *
 * <p>El comportamiento y estructura de la clase se basan en los siguientes puntos:</p>
 * <ol>
 *     <li>Está anotada con {@code @Embeddable}, lo que permite incrustarla en otra entidad.</li>
 *     <li>Implementa {@link Serializable} para garantizar compatibilidad con JPA.</li>
 *     <li>Contiene dos campos Long que representan las claves foráneas del evento y la empresa.</li>
 *     <li>Se utiliza como identificador compuesto en la tabla intermedia evento_empresa.</li>
 *     <li>Permite evitar duplicidades en la relación muchos-a-muchos.</li>
 * </ol>
 *
 * <p>Esta clase es fundamental para mantener integridad referencial y representar
 * correctamente la relación entre eventos y empresas.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Embeddable
public class EventoEmpresaId implements Serializable {

    /**
     * Identificador del evento asociado.
     *
     * <ol>
     *     <li>Corresponde a la clave primaria de la entidad Evento.</li>
     *     <li>Forma parte de la clave compuesta de EventoEmpresa.</li>
     * </ol>
     *
     * @since 0.1
     */
    private Long idEvento;

    /**
     * Identificador de la empresa asociada.
     *
     * <ol>
     *     <li>Corresponde a la clave primaria de la entidad Empresa.</li>
     *     <li>Forma parte de la clave compuesta de EventoEmpresa.</li>
     * </ol>
     *
     * @since 0.1
     */
    private Long idEmpresa;

    /**
     * Constructor por defecto.
     * Inicializa un objeto EventoEmpresaId vacío.
     *
     * <ol>
     *     <li>Requerido por JPA para la creación automática de instancias.</li>
     *     <li>No asigna valores a los atributos.</li>
     * </ol>
     *
     * @since 0.1
     */
    public EventoEmpresaId() {
    }

    /**
     * Constructor por parámetros.
     * Inicializa la clave compuesta con los identificadores del evento y la empresa.
     *
     * <ol>
     *     <li>Asigna el ID del evento recibido.</li>
     *     <li>Asigna el ID de la empresa recibida.</li>
     * </ol>
     *
     * @param idEvento identificador del evento.
     * @param idEmpresa identificador de la empresa.
     * @since 0.1
     */
    public EventoEmpresaId(Long idEvento, Long idEmpresa) {
        this.idEvento = idEvento;
        this.idEmpresa = idEmpresa;
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
     * Obtiene el identificador de la empresa.
     *
     * @return ID de la empresa.
     * @since 0.1
     */
    public Long getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * Establece el identificador de la empresa.
     *
     * @param idEmpresa ID de la empresa.
     * @since 0.1
     */
    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
