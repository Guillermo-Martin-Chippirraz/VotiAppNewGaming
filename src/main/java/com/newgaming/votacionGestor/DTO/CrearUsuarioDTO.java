package com.newgaming.votacionGestor.DTO;

import com.newgaming.votacionGestor.models.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO utilizado para la creación de nuevos usuarios dentro del sistema.
 * Contiene los datos necesarios para registrar un usuario y convertirlos
 * posteriormente en una entidad del modelo.
 *
 * <p>El funcionamiento general del DTO se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Incluye validaciones para garantizar que los datos obligatorios estén presentes.</li>
 *     <li>Permite transformar los datos recibidos en una entidad <code>Usuario</code>.</li>
 *     <li>Incluye username, password y rol, todos obligatorios.</li>
 *     <li>Evita exponer información sensible o innecesaria en la petición.</li>
 * </ol>
 *
 * <p>Este DTO asegura que el proceso de creación de usuarios sea seguro,
 * controlado y consistente.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
public class CrearUsuarioDTO {

    /**
     * Nombre de usuario del nuevo usuario.
     *
     * <ol>
     *     <li>Debe ser un texto no vacío.</li>
     *     <li>Debe tener entre 3 y 50 caracteres.</li>
     *     <li>Es obligatorio para el registro del usuario.</li>
     * </ol>
     *
     * @since 0.1
     */
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 50)
    private String username;

    /**
     * Contraseña del nuevo usuario.
     *
     * <ol>
     *     <li>Debe ser un texto no vacío.</li>
     *     <li>Debe tener al menos 6 caracteres.</li>
     *     <li>Es obligatoria para el registro del usuario.</li>
     * </ol>
     *
     * @since 0.1
     */
    @NotBlank(message = "La contraseña es obligatira")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    /**
     * Rol del nuevo usuario.
     *
     * <ol>
     *     <li>Debe ser un texto no vacío.</li>
     *     <li>Determina los permisos del usuario dentro del sistema.</li>
     * </ol>
     *
     * @since 0.1
     */
    @NotBlank(message = "El rol es obligatorio")
    private String rol;

    /**
     * Obtiene el nombre de usuario.
     *
     * @since 0.1
     * @return nombre de usuario.
     */
    public @NotBlank @Size(min = 3, max = 50) String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @since 0.1
     * @param username nombre de usuario.
     */
    public void setUsername(@NotBlank @Size(min = 3, max = 50) String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @since 0.1
     * @return contraseña del usuario.
     */
    public @NotBlank @Size(min = 6) String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @since 0.1
     * @param password contraseña del usuario.
     */
    public void setPassword(@NotBlank @Size(min = 6) String password) {
        this.password = password;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @since 0.1
     * @return rol del usuario.
     */
    public @NotBlank String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @since 0.1
     * @param rol rol del usuario.
     */
    public void setRol(@NotBlank String rol) {
        this.rol = rol;
    }

    /**
     * Convierte el DTO en una entidad <code>Usuario</code>.
     *
     * <ol>
     *     <li>Crea una nueva instancia de Usuario.</li>
     *     <li>Asigna el username recibido.</li>
     *     <li>Asigna la contraseña recibida.</li>
     *     <li>Asigna el rol recibido.</li>
     * </ol>
     *
     * @since 0.1
     * @return entidad Usuario lista para ser persistida.
     */
    public Usuario toEntity() {
        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPassword(password);
        u.setRol(rol);
        return u;
    }
}