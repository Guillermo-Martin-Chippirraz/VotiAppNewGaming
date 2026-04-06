package com.newgaming.votacionGestor.DTO;

import java.util.Map;

/**
 * DTO utilizado para la edición de datos de un jurado.
 *
 * <p>Permite actualizar información básica del jurado, como su correo
 * electrónico, así como un conjunto de datos adicionales representados en un
 * mapa genérico. Este objeto se emplea en operaciones donde el propio jurado
 * o un administrador pueden modificar información del perfil.</p>
 *
 * <p>Todos los campos son opcionales; únicamente se modificarán aquellos que
 * sean proporcionados en la solicitud.</p>
 *
 * <p>El campo {@code datosExtra} permite almacenar información flexible y
 * extensible, como preferencias, configuraciones o metadatos asociados al
 * jurado.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.3
 */
public class EditarJuradoDTO {

    /** Correo electrónico actualizado del jurado. */
    private String email;

    /** Datos adicionales del jurado representados como pares clave-valor. */
    private Map<String, Object> datosExtra;

    /**
     * Obtiene el correo electrónico propuesto para el jurado.
     *
     * @since v0.3
     * @return correo electrónico del jurado, o {@code null} si no se ha establecido.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico que se desea asignar al jurado.
     *
     * @since v0.3
     * @param email nuevo correo electrónico del jurado.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el mapa de datos adicionales propuestos para el jurado.
     *
     * @since v0.3
     * @return mapa de datos extra, o {@code null} si no se ha establecido.
     */
    public Map<String, Object> getDatosExtra() {
        return datosExtra;
    }

    /**
     * Establece los datos adicionales que se desean asignar al jurado.
     *
     * @since v0.3
     * @param datosExtra mapa de datos extra en formato clave-valor.
     */
    public void setDatosExtra(Map<String, Object> datosExtra) {
        this.datosExtra = datosExtra;
    }
}