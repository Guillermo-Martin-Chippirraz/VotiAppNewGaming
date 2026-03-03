package com.newgaming.votacionGestor.DTO;

import com.newgaming.votacionGestor.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
 * @version v0.2
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
     * @since v0.1
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
     * @since v0.1
     */
    @NotBlank(message = "La contraseña es obligatira")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    /**
     * Dirección de correo electrónico del nuevo usuario.
     *
     * <ul>
     *     <li>Debe ser un texto no vacío.</li>
     *     <li>Debe cumplir el formato de email válido.</li>
     *     <li>Se utiliza como dato de contacto y como información adicional del usuario.</li>
     * </ul>
     *
     * @since v0.2
     */
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    /**
     * Rol asignado al nuevo usuario.
     *
     * <ul>
     *     <li>Debe ser un texto no vacío.</li>
     *     <li>Debe coincidir con uno de los valores permitidos: ADMIN, JURADO o USUARIO.</li>
     *     <li>Determina los permisos y el nivel de acceso del usuario dentro del sistema.</li>
     * </ul>
     *
     * @since v0.2
     */
    @NotBlank(message = "El rol es obligatorio")
    @Pattern(regexp = "ADMIN|JURADO|USUARIO", message = "Rol inválido")
    private String rol;

    /**
     * Obtiene el nombre de usuario.
     *
     * @since v0.1
     * @return nombre de usuario.
     */
    public @NotBlank @Size(min = 3, max = 50) String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @since v0.1
     * @param username nombre de usuario.
     */
    public void setUsername(@NotBlank @Size(min = 3, max = 50) String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @since v0.1
     * @return contraseña del usuario.
     */
    public @NotBlank @Size(min = 6) String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @since v0.1
     * @param password contraseña del usuario.
     */
    public void setPassword(@NotBlank @Size(min = 6) String password) {
        this.password = password;
    }

    /**
     * Obtiene el email del usuario.
     *
     * <p>El valor devuelto debe ser un email válido y no puede estar vacío.
     * Este campo se utiliza como información de contacto del usuario.</p>
     *
     * @since v0.2
     * @return email del usuario.
     */
    public @NotBlank(message = "El email es obligatorio") @Email(message = "El email debe ser válido") String getEmail() {
        return email;
    }

    /**
     * Establece el email del usuario.
     *
     * <p>El valor proporcionado debe ser un email válido y no puede estar vacío.
     * Este dato será almacenado junto al resto de información del usuario.</p>
     *
     * @since v0.2
     * @param email dirección de correo electrónico del usuario.
     */
    public void setEmail(@NotBlank(message = "El email es obligatorio") @Email(message = "El email debe ser válido") String email) {
        this.email = email;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @since v0.1
     * @return rol del usuario.
     */
    public @NotBlank String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @since v0.1
     * @param rol rol del usuario.
     */
    public void setRol(@NotBlank String rol) {
        this.rol = rol;
    }

    /**
     * Convierte este DTO en una entidad <code>Usuario</code>.
     *
     * <ol>
     *     <li>Crea una nueva instancia de <code>Usuario</code>.</li>
     *     <li>Asigna el nombre de usuario recibido.</li>
     *     <li>Asigna la contraseña recibida (sin encriptar; la capa de servicio se encarga de ello).</li>
     *     <li>Asigna el email proporcionado.</li>
     *     <li>Asigna el rol indicado, validado previamente mediante anotaciones.</li>
     * </ol>
     *
     * <p>Este método prepara la entidad para ser procesada por la capa de servicio,
     * donde se aplicarán validaciones adicionales y la encriptación de la contraseña.</p>
     *
     * @since v0.1
     * @return entidad <code>Usuario</code> lista para ser persistida.
     */
    public Usuario toEntity() {
        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);
        u.setRol(rol);
        return u;
    }
}