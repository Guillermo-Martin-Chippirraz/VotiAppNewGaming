package com.newgaming.votacionGestor.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO utilizado para la creación de nuevos formularios dentro del sistema.
 * Permite transportar desde el cliente la información necesaria para registrar
 * un formulario asociado a un evento, aplicando validaciones sobre los campos
 * recibidos antes de ser procesados por la capa de servicio.
 *
 * <p>Este DTO se emplea exclusivamente en operaciones de entrada (creación),
 * evitando exponer detalles internos de la entidad <code>Formulario</code>.
 * Su función principal es garantizar que los datos enviados por el cliente
 * cumplen los requisitos mínimos para generar un formulario válido.</p>
 *
 * <p>Incluye los campos esenciales para la creación del formulario:
 * el identificador del evento al que pertenece y la lista de preguntas
 * representada como un JSON en formato texto.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
public class CrearFormularioDTO {

    /**
     * Identificador del evento al que pertenece el formulario.
     *
     * <ul>
     *     <li>Debe ser un valor no nulo.</li>
     *     <li>Permite asociar el formulario a un evento concreto.</li>
     * </ul>
     */
    @NotNull
    private Long idEvento;

    /**
     * Lista de preguntas del formulario en formato JSON.
     *
     * <ul>
     *     <li>Debe ser un texto no vacío.</li>
     *     <li>Representa la estructura completa del formulario.</li>
     *     <li>Se almacena como cadena para permitir flexibilidad en el contenido.</li>
     * </ul>
     */
    @NotBlank
    private String listaDePreguntas;

    /**
     * Obtiene el identificador del evento asociado al formulario.
     *
     * @since v0.2
     * @return ID del evento.
     */
    public @NotNull Long getIdEvento() {
        return idEvento;
    }

    /**
     * Establece el identificador del evento asociado al formulario.
     *
     * @since v0.2
     * @param idEvento ID del evento.
     */
    public void setIdEvento(@NotNull Long idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * Obtiene la lista de preguntas del formulario en formato JSON.
     *
     * @since v0.2
     * @return lista de preguntas como cadena JSON.
     */
    public @NotBlank String getListaDePreguntas() {
        return listaDePreguntas;
    }

    /**
     * Establece la lista de preguntas del formulario en formato JSON.
     *
     * @since v0.2
     * @param listaDePreguntas cadena JSON con la estructura del formulario.
     */
    public void setListaDePreguntas(@NotBlank String listaDePreguntas) {
        this.listaDePreguntas = listaDePreguntas;
    }
}