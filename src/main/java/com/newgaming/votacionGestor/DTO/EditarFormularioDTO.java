package com.newgaming.votacionGestor.DTO;

/**
 * DTO utilizado para la edición del contenido de un formulario.
 *
 * <p>Permite actualizar la lista de preguntas asociadas a un formulario
 * existente. El contenido se almacena como una cadena, normalmente en formato
 * JSON, representando la estructura del formulario.</p>
 *
 * <p>El campo es opcional; únicamente se modificará si se proporciona en la
 * solicitud.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.3
 */
public class EditarFormularioDTO {

    /** Lista de preguntas del formulario, generalmente en formato JSON. */
    private String listaDePreguntas;

    /**
     * Obtiene la lista de preguntas propuesta para el formulario.
     *
     * @since v0.3
     * @return lista de preguntas en formato texto, o {@code null} si no se ha establecido.
     */
    public String getListaDePreguntas() {
        return listaDePreguntas;
    }

    /**
     * Establece la lista de preguntas que se desea asignar al formulario.
     *
     * @since v0.3
     * @param listaDePreguntas nueva lista de preguntas en formato texto.
     */
    public void setListaDePreguntas(String listaDePreguntas) {
        this.listaDePreguntas = listaDePreguntas;
    }
}