package com.newgaming.votacionGestor.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

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
 * @version 0.1
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
     * @since 0.1
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
     * @since 0.1
     */
    @NotNull(message = "Debe indicar el formulario")
    private Long idFormulario;

    /**
     * Puntuación total obtenida en la votación.
     *
     * <ol>
     *     <li>Debe ser un valor no nulo.</li>
     *     <li>Representa la valoración final otorgada por el jurado.</li>
     * </ol>
     *
     * @since 0.1
     */
    @NotNull(message = "Debe indicar la puntuación total")
    private Double puntuacionTotal;

    /**
     * Respuestas del jurado a cada pregunta del formulario.
     *
     * <ol>
     *     <li>Debe ser un valor no nulo.</li>
     *     <li>Se almacena como un mapa clave-valor para mayor flexibilidad.</li>
     *     <li>Permite representar respuestas de distintos tipos.</li>
     * </ol>
     *
     * @since 0.1
     */
    @NotNull(message = "Debe incluir las respuestas")
    private Map<String, Object> respuetas;

    /**
     * Obtiene el ID de la empresa evaluada.
     *
     * @since 0.1
     * @return identificador de la empresa.
     */
    public @NotNull Long getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * Establece el ID de la empresa evaluada.
     *
     * @since 0.1
     * @param idEmpresa identificador de la empresa.
     */
    public void setIdEmpresa(@NotNull Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * Obtiene el ID del formulario utilizado.
     *
     * @since 0.1
     * @return identificador del formulario.
     */
    public @NotNull Long getIdFormulario() {
        return idFormulario;
    }

    /**
     * Establece el ID del formulario utilizado.
     *
     * @since 0.1
     * @param idFormulario identificador del formulario.
     */
    public void setIdFormulario(@NotNull Long idFormulario) {
        this.idFormulario = idFormulario;
    }

    /**
     * Obtiene la puntuación total otorgada en la votación.
     *
     * @since 0.1
     * @return puntuación total.
     */
    public @NotNull Double getPuntuacionTotal() {
        return puntuacionTotal;
    }

    /**
     * Establece la puntuación total otorgada en la votación.
     *
     * @since 0.1
     * @param puntuacionTotal puntuación total.
     */
    public void setPuntuacionTotal(@NotNull Double puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    /**
     * Obtiene el mapa de respuestas del jurado.
     *
     * @since 0.1
     * @return mapa con las respuestas del formulario.
     */
    public @NotNull Map<String, Object> getRespuetas() {
        return respuetas;
    }

    /**
     * Establece el mapa de respuestas del jurado.
     *
     * @since 0.1
     * @param respuetas mapa con las respuestas del formulario.
     */
    public void setRespuetas(@NotNull Map<String, Object> respuetas) {
        this.respuetas = respuetas;
    }
}
