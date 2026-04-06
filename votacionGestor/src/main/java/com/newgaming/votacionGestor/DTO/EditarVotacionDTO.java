package com.newgaming.votacionGestor.DTO;

/**
 * DTO utilizado para la edición de una votación realizada por un jurado.
 *
 * <p>Permite actualizar la puntuación total otorgada a una empresa y el
 * contenido detallado de las respuestas del formulario asociado, normalmente
 * representado en formato JSON.</p>
 *
 * <p>Todos los campos son opcionales; únicamente se modificarán aquellos que
 * sean proporcionados en la solicitud.</p>
 *
 * <p>Este DTO es utilizado por los jurados para modificar su votación mientras
 * el evento se encuentre en estado válido para edición.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.3
 */
public class EditarVotacionDTO {

    /** Puntuación total asignada por el jurado a la empresa. */
    private Double puntuacionTotal;

    /** Respuestas del formulario en formato JSON. */
    private String respuestasJson;

    /**
     * Obtiene la puntuación total propuesta para la votación.
     *
     * @since v0.3
     * @return puntuación total, o {@code null} si no se ha establecido.
     */
    public Double getPuntuacionTotal() {
        return puntuacionTotal;
    }

    /**
     * Establece la puntuación total que se desea asignar a la votación.
     *
     * @since v0.3
     * @param puntuacionTotal nueva puntuación total.
     */
    public void setPuntuacionTotal(Double puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    /**
     * Obtiene las respuestas del formulario asociadas a la votación.
     *
     * @since v0.3
     * @return respuestas en formato JSON, o {@code null} si no se ha establecido.
     */
    public String getRespuestasJson() {
        return respuestasJson;
    }

    /**
     * Establece las respuestas del formulario asociadas a la votación.
     *
     * @since v0.3
     * @param respuestasJson contenido del formulario en formato JSON.
     */
    public void setRespuestasJson(String respuestasJson) {
        this.respuestasJson = respuestasJson;
    }
}