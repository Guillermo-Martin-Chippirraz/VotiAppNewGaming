package com.newgaming.votacionGestor.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO utilizado para la creación de nuevas votaciones dentro del sistema.
 * Contiene los datos necesarios para registrar una votación realizada por un jurado,
 * incluyendo la empresa evaluada, el formulario utilizado, la puntuación total
 * y las respuestas detalladas.
 *
 * <p>El funcionamiento general del DTO se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Incluye validaciones para garantizar que todos los campos obligatorios estén presentes.</li>
 *     <li>Permite transportar la información necesaria desde el cliente hacia el backend.</li>
 *     <li>Incluye un mapa flexible para almacenar las respuestas del formulario.</li>
 *     <li>Evita exponer información innecesaria o sensible en la petición.</li>
 * </ol>
 *
 * <p>Este DTO asegura que el proceso de creación de votaciones sea seguro,
 * estructurado y consistente.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
public class CrearVotacionDTO {

    /**
     * Identificador de la empresa evaluada.
     *
     * <ol>
     *     <li>Debe ser un valor no nulo.</li>
     *     <li>Permite asociar la votación a una empresa concreta.</li>
     * </ol>
     *
     * @since v0.1
     */
    @NotNull(message = "Debe indicar la empresa")
    private Long idEmpresa;

    /**
     * Identificador del formulario utilizado en la votación.
     *
     * <ol>
     *     <li>Debe ser un valor no nulo.</li>
     *     <li>Permite saber qué conjunto de preguntas respondió el jurado.</li>
     * </ol>
     *
     * @since v0.1
     */
    @NotNull(message = "Debe indicar el formulario")
    private Long idFormulario;

    /**
     * Identificador del evento al que pertenece la votación.
     *
     * <ul>
     *     <li>Debe ser un valor no nulo.</li>
     *     <li>Permite asociar la votación al evento correspondiente.</li>
     *     <li>Es indispensable para validar que el jurado está votando dentro del evento correcto.</li>
     * </ul>
     *
     * @since v0.2
     */
    @NotNull(message = "Debe indicar el evento")
    private Long idEvento;


    /**
     * Puntuación total obtenida en la votación.
     *
     * <ol>
     *     <li>Debe ser un valor no nulo.</li>
     *     <li>Representa la valoración final otorgada por el jurado.</li>
     * </ol>
     *
     * @since v0.1
     */
    @NotNull(message = "Debe indicar la puntuación total")
    private Double puntuacionTotal;


    /**
     * Respuestas del jurado a cada pregunta del formulario.
     *
     * <ul>
     *     <li>Debe ser un valor no vacío.</li>
     *     <li>Se almacena como una cadena JSON para permitir flexibilidad en la estructura.</li>
     *     <li>Representa las respuestas completas del jurado al formulario asignado.</li>
     * </ul>
     *
     * @since v0.1
     */
    @NotBlank(message = "Debe incluir las respuestas")
    private String respuestasJson;

    /**
     * Obtiene el ID de la empresa evaluada.
     *
     * @since v0.1
     * @return identificador de la empresa.
     */
    public @NotNull Long getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * Establece el ID de la empresa evaluada.
     *
     * @since v0.1
     * @param idEmpresa identificador de la empresa.
     */
    public void setIdEmpresa(@NotNull Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * Obtiene el ID del formulario utilizado.
     *
     * @since v0.1
     * @return identificador del formulario.
     */
    public @NotNull Long getIdFormulario() {
        return idFormulario;
    }

    /**
     * Establece el ID del formulario utilizado.
     *
     * @since v0.1
     * @param idFormulario identificador del formulario.
     */
    public void setIdFormulario(@NotNull Long idFormulario) {
        this.idFormulario = idFormulario;
    }

    /**
     * Obtiene el identificador del evento al que pertenece la votación.
     *
     * @since v0.2
     * @return ID del evento.
     */
    public @NotNull(message = "Debe indicar el evento") Long getIdEvento() {
        return idEvento;
    }

    /**
     * Establece el identificador del evento al que pertenece la votación.
     *
     * @since v0.2
     * @param idEvento ID del evento.
     */
    public void setIdEvento(@NotNull(message = "Debe indicar el evento") Long idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * Obtiene la puntuación total otorgada en la votación.
     *
     * @since v0.1
     * @return puntuación total.
     */
    public @NotNull Double getPuntuacionTotal() {
        return puntuacionTotal;
    }

    /**
     * Establece la puntuación total otorgada en la votación.
     *
     * @since v0.1
     * @param puntuacionTotal puntuación total.
     */
    public void setPuntuacionTotal(@NotNull Double puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    /**
     * Obtiene la cadena JSON que contiene las respuestas del jurado.
     *
     * <p>Este valor representa todas las respuestas del formulario en formato JSON,
     * permitiendo almacenar estructuras dinámicas y flexibles.</p>
     *
     * @since v0.1
     * @return respuestas del formulario en formato JSON.
     */
    public @NotBlank String getRespuestasJson() {
        return respuestasJson;
    }

    /**
     * Establece la cadena JSON con las respuestas del jurado.
     *
     * <p>Debe ser un texto no vacío que represente un JSON válido con las respuestas
     * del formulario.</p>
     *
     * @since v0.1
     * @param respuestasJson respuestas del formulario en formato JSON.
     */
    public void setRespuestasJson(@NotBlank String respuestasJson) {
        this.respuestasJson = respuestasJson;
    }
}
