package com.newgaming.votacionGestor.DTO;

import com.newgaming.votacionGestor.models.Usuario;

/**
 * DTO utilizado para representar información esencial de un usuario dentro del sistema.
 * Permite transportar datos seguros hacia el cliente sin exponer información sensible
 * como contraseñas u otros atributos internos.
 *
 * <p>Este DTO se emplea principalmente en respuestas de controladores cuando se requiere
 * mostrar información básica del usuario, ya sea tras su creación o durante consultas.</p>
 *
 * <p>Incluye un método estático para convertir una entidad <code>Usuario</code> en un
 * objeto <code>UsuarioDTO</code>, facilitando la transformación entre capas.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
public class UsuarioDTO {

    /**
     * Identificador único del usuario.
     *
     * <p>Corresponde al ID almacenado en la base de datos y permite identificar
     * al usuario en operaciones de consulta.</p>
     *
     * @since 0.1
     */
    private Long id;

    /**
     * Nombre de usuario utilizado para autenticación.
     *
     * <p>Es un dato seguro para exponer en respuestas y permite identificar al usuario
     * dentro del sistema.</p>
     *
     * @since 0.1
     */
    private String username;

    /**
     * Rol asignado al usuario.
     *
     * <p>Determina los permisos y accesos del usuario dentro del sistema
     * (por ejemplo: ADMIN, JURADO, etc.).</p>
     *
     * @since 0.1
     */
    private String rol;

    /**
     * Obtiene el identificador del usuario.
     *
     * @since 0.1
     * @return ID del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @since 0.1
     * @param id ID del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @since 0.1
     * @return nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @since 0.1
     * @param username nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @since 0.1
     * @return rol del usuario.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @since 0.1
     * @param rol rol del usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Convierte una entidad <code>Usuario</code> en un objeto <code>UsuarioDTO</code>.
     *
     * <p>Este método facilita la transformación entre la capa de persistencia y la capa
     * de presentación, asegurando que solo se expongan datos seguros.</p>
     *
     * <ol>
     *     <li>Crea una nueva instancia del DTO.</li>
     *     <li>Asigna el ID del usuario.</li>
     *     <li>Asigna el nombre de usuario.</li>
     *     <li>Asigna el rol del usuario.</li>
     * </ol>
     *
     * @since 0.1
     * @param usuario entidad Usuario a convertir.
     * @return DTO con la información del usuario.
     */
    public static UsuarioDTO fromEntity(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getIdUsuario());
        dto.setUsername(usuario.getUsername());
        dto.setRol(usuario.getRol());
        return dto;
    }
}
