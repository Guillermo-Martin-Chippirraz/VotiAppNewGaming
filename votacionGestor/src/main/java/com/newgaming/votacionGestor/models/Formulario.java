package com.newgaming.votacionGestor.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

/**
 * Clase que modela un formulario asociado a un evento dentro del sistema de votación.
 * Un formulario contiene la lista de preguntas que los jurados deberán responder
 * al evaluar a las empresas participantes.
 *
 * <p>El comportamiento y estructura de la clase se basan en los siguientes puntos:</p>
 * <ol>
 *     <li>Incluye un identificador único generado automáticamente.</li>
 *     <li>Está vinculado a un único evento mediante una relación OneToOne.</li>
 *     <li>Almacena la lista de preguntas en formato JSONB para mayor flexibilidad.</li>
 *     <li>Incluye marcas temporales de creación y actualización para auditoría.</li>
 *     <li>Se almacena en la tabla "formulario" dentro de la base de datos.</li>
 * </ol>
 *
 * <p>Esta clase permite que cada evento tenga su propio conjunto de preguntas,
 * evitando duplicidades y manteniendo independencia entre ediciones.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
@Entity
@Table(name = "formulario")
public class Formulario {

    /**
     * Identificador único del formulario.
     *
     * <ol>
     *     <li>Clave primaria de la tabla formulario.</li>
     *     <li>Generado automáticamente mediante estrategia IDENTITY.</li>
     * </ol>
     *
     * @since v0.1
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormulario;

    /**
     * Evento al que pertenece el formulario.
     *
     * <ol>
     *     <li>Relación OneToOne: cada evento tiene un único formulario.</li>
     *     <li>Se almacena en la columna id_evento con restricción de unicidad.</li>
     * </ol>
     *
     * @since v0.1
     */
    @OneToOne
    @JoinColumn(name = "id_evento", unique = true)
    private Evento evento;

    /**
     * Lista de preguntas del formulario almacenada en formato JSONB.
     *
     * <ul>
     *     <li>Permite guardar estructuras dinámicas de preguntas sin necesidad de un esquema rígido.</li>
     *     <li>El uso de JSONB facilita búsquedas eficientes y operaciones avanzadas en PostgreSQL.</li>
     *     <li>Se utiliza para representar el contenido completo del formulario tal como lo consume el frontend.</li>
     * </ul>
     *
     * @since v0.1
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String listaDePreguntas;

    /**
     * Fecha y hora en la que se creó el formulario.
     *
     * <ol>
     *     <li>Asignada al registrar el formulario.</li>
     *     <li>Permite auditoría temporal.</li>
     * </ol>
     *
     * @since v0.1
     */
    private LocalDateTime creadoEn;

    /**
     * Fecha y hora de la última actualización del formulario.
     *
     * <ol>
     *     <li>Actualizada cada vez que se modifica el formulario.</li>
     *     <li>Permite control de cambios.</li>
     * </ol>
     *
     * @since v0.1
     */
    private LocalDateTime actualizadoEn;

    /**
     * Obtiene el identificador único del formulario.
     *
     * @return ID del formulario.
     * @since v0.1
     */
    public Long getIdFormulario() {
        return idFormulario;
    }

    /**
     * Establece el identificador único del formulario.
     *
     * @param idFormulario ID del formulario.
     * @since v0.1
     */
    public void setIdFormulario(Long idFormulario) {
        this.idFormulario = idFormulario;
    }

    /**
     * Obtiene el evento asociado al formulario.
     *
     * @return evento vinculado.
     * @since v0.1
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Establece el evento asociado al formulario.
     *
     * @param evento evento vinculado.
     * @since v0.1
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * Obtiene la lista de preguntas del formulario.
     *
     * @return preguntas en formato JSON.
     * @since v0.1
     */
    public String getListaDePreguntas() {
        return listaDePreguntas;
    }

    /**
     * Establece la lista de preguntas del formulario.
     *
     * @param listaDePreguntas JSON con las preguntas.
     * @since v0.1
     */
    public void setListaDePreguntas(String listaDePreguntas) {
        this.listaDePreguntas = listaDePreguntas;
    }

    /**
     * Obtiene la fecha de creación del formulario.
     *
     * @return fecha de creación.
     * @since v0.1
     */
    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    /**
     * Establece la fecha de creación del formulario.
     *
     * @param creadoEn fecha de creación.
     * @since v0.1
     */
    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    /**
     * Obtiene la fecha de última actualización del formulario.
     *
     * @return fecha de actualización.
     * @since v0.1
     */
    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    /**
     * Establece la fecha de última actualización del formulario.
     *
     * @param actualizadoEn fecha de actualización.
     * @since v0.1
     */
    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }
}
