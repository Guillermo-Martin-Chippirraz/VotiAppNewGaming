package com.newgaming.votacionGestor.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

/**
 * Clase que modela una votación realizada por un jurado dentro del sistema.
 * Una votación representa la evaluación que un jurado hace sobre una empresa
 * en un evento concreto, utilizando un formulario asociado.
 *
 * <p>El comportamiento y estructura de la clase se basan en los siguientes puntos:</p>
 * <ol>
 *     <li>Incluye un identificador único generado automáticamente.</li>
 *     <li>Registra qué jurado realizó la votación mediante una relación ManyToOne.</li>
 *     <li>Asocia la votación a una empresa evaluada.</li>
 *     <li>Incluye el formulario utilizado para responder las preguntas.</li>
 *     <li>Registra el evento al que pertenece la votación.</li>
 *     <li>Almacena las respuestas en formato JSONB para mayor flexibilidad.</li>
 *     <li>Incluye la puntuación total calculada a partir de las respuestas.</li>
 *     <li>Registra la fecha y hora en la que se realizó la votación.</li>
 * </ol>
 *
 * <p>Esta clase es fundamental para almacenar las evaluaciones realizadas por los jurados
 * y permitir el análisis posterior de resultados.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
@Entity
@Table(name = "votacion")
public class Votacion {

    /**
     * Identificador único de la votación.
     *
     * <ol>
     *     <li>Clave primaria de la tabla votacion.</li>
     *     <li>Generado automáticamente mediante estrategia IDENTITY.</li>
     * </ol>
     *
     * @since v0.1
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVotacion;

    /**
     * Jurado que realizó la votación.
     *
     * <ol>
     *     <li>Relación ManyToOne: un jurado puede realizar múltiples votaciones.</li>
     *     <li>Se almacena en la columna id_jurado.</li>
     * </ol>
     *
     * @since v0.1
     */
    @ManyToOne
    @JoinColumn(name = "id_jurado")
    private Jurado jurado;

    /**
     * Empresa evaluada en la votación.
     *
     * <ol>
     *     <li>Relación ManyToOne: una empresa puede ser evaluada muchas veces.</li>
     *     <li>Se almacena en la columna id_empresa.</li>
     * </ol>
     *
     * @since v0.1
     */
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    /**
     * Formulario utilizado para realizar la votación.
     *
     * <ol>
     *     <li>Relación ManyToOne: un formulario puede ser usado en varias votaciones.</li>
     *     <li>Se almacena en la columna id_formulario.</li>
     * </ol>
     *
     * @since v0.1
     */
    @ManyToOne
    @JoinColumn(name = "id_formulario")
    private Formulario formulario;

    /**
     * Evento al que pertenece la votación.
     *
     * <ol>
     *     <li>Relación ManyToOne: un evento puede contener múltiples votaciones.</li>
     *     <li>Se almacena en la columna id_evento.</li>
     * </ol>
     *
     * @since v0.1
     */
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    /**
     * Respuestas del jurado almacenadas en formato JSONB.
     *
     * <ul>
     *     <li>Contiene todas las respuestas del formulario asociadas a la votación.</li>
     *     <li>El formato JSONB permite almacenar estructuras dinámicas y complejas sin un esquema fijo.</li>
     *     <li>Facilita la lectura y procesamiento tanto desde el backend como desde el frontend.</li>
     *     <li>Permite realizar consultas eficientes sobre su contenido en bases de datos como PostgreSQL.</li>
     * </ul>
     *
     * @since v0.1
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String respuestasJson;


    /**
     * Puntuación total obtenida a partir de las respuestas.
     *
     * <ol>
     *     <li>Representa la valoración final del jurado.</li>
     *     <li>Puede ser calculada automáticamente según el formulario.</li>
     * </ol>
     *
     * @since v0.1
     */
    private Double puntuacionTotal;

    /**
     * Fecha y hora en la que se realizó la votación.
     *
     * <ol>
     *     <li>Permite ordenar y auditar votaciones.</li>
     *     <li>Se asigna en el momento de registrar la votación.</li>
     * </ol>
     *
     * @since v0.1
     */
    private LocalDateTime fechaVotacion;

    /**
     * Obtiene el identificador único de la votación.
     *
     * @since v0.1
     * @return ID de la votación.
     */
    public Long getIdVotacion() {
        return idVotacion;
    }

    /**
     * Establece el identificador único de la votación.
     *
     * @since v0.1
     * @param idVotacion ID de la votación.
     */
    public void setIdVotacion(Long idVotacion) {
        this.idVotacion = idVotacion;
    }

    /**
     * Obtiene el jurado que realizó la votación.
     *
     * @since v0.1
     * @return jurado evaluador.
     */
    public Jurado getJurado() {
        return jurado;
    }

    /**
     * Establece el jurado que realizó la votación.
     *
     * @since v0.1
     * @param jurado jurado evaluador.
     */
    public void setJurado(Jurado jurado) {
        this.jurado = jurado;
    }

    /**
     * Obtiene la empresa evaluada.
     *
     * @since v0.1
     * @return empresa evaluada.
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * Establece la empresa evaluada.
     *
     * @since v0.1
     * @param empresa empresa evaluada.
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * Obtiene el formulario utilizado en la votación.
     *
     * @since v0.1
     * @return formulario asociado.
     */
    public Formulario getFormulario() {
        return formulario;
    }

    /**
     * Establece el formulario utilizado en la votación.
     *
     * @since v0.1
     * @param formulario formulario asociado.
     */
    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    /**
     * Obtiene el evento al que pertenece la votación.
     *
     * @since v0.1
     * @return evento asociado.
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Establece el evento al que pertenece la votación.
     *
     * @since v0.1
     * @param evento evento asociado.
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * Obtiene las respuestas del jurado en formato JSON.
     *
     * @since v0.1
     * @return respuestas en JSON.
     */
    public String getRespuestasJson() {
        return respuestasJson;
    }

    /**
     * Establece las respuestas del jurado en formato JSON.
     *
     * @since v0.1
     * @param respuestasJson respuestas en JSON.
     */
    public void setRespuestasJson(String respuestasJson) {
        this.respuestasJson = respuestasJson;
    }

    /**
     * Obtiene la puntuación total de la votación.
     *
     * @since v0.1
     * @return puntuación total.
     */
    public Double getPuntuacionTotal() {
        return puntuacionTotal;
    }

    /**
     * Establece la puntuación total de la votación.
     *
     * @since v0.1
     * @param puntuacionTotal puntuación total.
     */
    public void setPuntuacionTotal(Double puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    /**
     * Obtiene la fecha en la que se realizó la votación.
     *
     * @since v0.1
     * @return fecha de votación.
     */
    public LocalDateTime getFechaVotacion() {
        return fechaVotacion;
    }

    /**
     * Establece la fecha en la que se realizó la votación.
     *
     * @since v0.1
     * @param fechaVotacion fecha de votación.
     */
    public void setFechaVotacion(LocalDateTime fechaVotacion) {
        this.fechaVotacion = fechaVotacion;
    }
}