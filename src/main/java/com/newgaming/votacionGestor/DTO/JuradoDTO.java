package com.newgaming.votacionGestor.DTO;

import com.newgaming.votacionGestor.models.Jurado;

/**
 * DTO utilizado para representar información resumida de un jurado dentro del sistema.
 * Permite transportar datos esenciales sin exponer la entidad completa, facilitando
 * respuestas más ligeras y seguras en los endpoints.
 *
 * <p>El funcionamiento general del DTO se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Incluye el identificador del jurado.</li>
 *     <li>Contiene el nombre de usuario asociado al jurado.</li>
 *     <li>Evita exponer información sensible como contraseñas o datos internos.</li>
 *     <li>Incluye un método estático para convertir una entidad <code>Jurado</code> en un DTO.</li>
 * </ol>
 *
 * <p>Este DTO facilita la comunicación entre backend y frontend, proporcionando
 * únicamente los datos necesarios para mostrar información del jurado.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
public class JuradoDTO {

    /**
     * Identificador único del jurado.
     *
     * <ol>
     *     <li>Corresponde al ID del usuario en la base de datos.</li>
     *     <li>Permite identificar al jurado en consultas y respuestas.</li>
     * </ol>
     *
     * @since 0.1
     */
    private Long id;

    /**
     * Nombre de usuario del jurado.
     *
     * <ol>
     *     <li>Representa el username utilizado para autenticación.</li>
     *     <li>Es un dato visible y seguro para ser expuesto en respuestas.</li>
     * </ol>
     *
     * @since 0.1
     */
    private String username;

    /**
     * Obtiene el identificador del jurado.
     *
     * @since 0.1
     * @return ID del jurado.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del jurado.
     *
     * @since 0.1
     * @param id ID del jurado.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario del jurado.
     *
     * @since 0.1
     * @return nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario del jurado.
     *
     * @since 0.1
     * @param username nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Convierte una entidad <code>Jurado</code> en un objeto <code>JuradoDTO</code>.
     *
     * <ol>
     *     <li>Crea una nueva instancia del DTO.</li>
     *     <li>Asigna el ID del jurado utilizando su ID de usuario.</li>
     *     <li>Asigna el nombre de usuario del jurado.</li>
     *     <li>Devuelve el DTO completamente construido.</li>
     * </ol>
     *
     * @since 0.1
     * @param j entidad Jurado a convertir.
     * @return DTO con la información del jurado.
     */
    public static JuradoDTO fromEntity(Jurado j) {
        JuradoDTO dto = new JuradoDTO();
        dto.setId(j.getIdUsuario());
        dto.setUsername(j.getUsername());
        return dto;
    }
}
