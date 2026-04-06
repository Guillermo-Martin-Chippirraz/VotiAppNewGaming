package com.newgaming.votacionGestor.DTO;

import com.newgaming.votacionGestor.models.Jurado;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashMap;

/**
 * DTO utilizado para la creación de nuevos jurados dentro del sistema.
 * Contiene únicamente los datos necesarios para registrar un jurado y
 * convertirlos posteriormente en una entidad del modelo.
 *
 * <p>El funcionamiento general del DTO se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Incluye validaciones para garantizar que los datos obligatorios estén presentes.</li>
 *     <li>Permite transformar los datos recibidos en una entidad <code>Jurado</code>.</li>
 *     <li>Asigna automáticamente el rol "JURADO" al crear la entidad.</li>
 *     <li>Evita exponer información sensible o innecesaria en la petición.</li>
 * </ol>
 *
 * <p>Este DTO asegura que el proceso de creación de jurados sea seguro,
 * controlado y consistente.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.4
 */
public class CrearJuradoDTO {

    /**
     * Nombre de usuario del jurado.
     *
     * <ol>
     *     <li>Debe ser un texto no vacío.</li>
     *     <li>Es obligatorio para el registro del jurado.</li>
     * </ol>
     *
     * @since v0.1
     */
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    /**
     * Contraseña del jurado.
     *
     * <ol>
     *     <li>Debe ser un texto no vacío.</li>
     *     <li>Debe tener al menos 6 caracteres.</li>
     *     <li>Es obligatoria para el registro del jurado.</li>
     * </ol>
     *
     * @since v0.1
     */
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6)
    private String password;

    /**
     * Dirección de correo electrónico del usuario.
     *
     * <p>Este campo es obligatorio y debe cumplir con el formato de un email válido.
     * Además, está marcado como único en la base de datos, por lo que no pueden
     * existir dos usuarios con la misma dirección de correo.</p>
     *
     * <p>Las validaciones aplicadas garantizan que:</p>
     * <ul>
     *     <li>El valor no sea nulo ni una cadena en blanco ({@link NotBlank}).</li>
     *     <li>El formato corresponda a una dirección de correo válida ({@link Email}).</li>
     *     <li>La base de datos impida duplicados mediante la restricción {@code unique = true}.</li>
     * </ul>
     *
     * @since v0.4
     */
    @Column(nullable = false, unique = true)
    @NotBlank(message = "El email es obligatorio")
    @Email
    private String email;

    /**
     * Obtiene el nombre de usuario asignado al nuevo jurado.
     *
     * <p>Este valor es obligatorio y debe contener texto no vacío. Se utiliza como
     * identificador principal del jurado dentro del sistema.</p>
     *
     * @since v0.2
     * @return nombre de usuario del jurado.
     */
    public @NotBlank(message = "El nombre de usuario es obligatorio") String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario del nuevo jurado.
     *
     * <p>El valor proporcionado debe ser un texto no vacío. Este campo será utilizado
     * como identificador único del jurado en el sistema.</p>
     *
     * @since v0.2
     * @param username nombre de usuario del jurado.
     */
    public void setUsername(@NotBlank(message = "El nombre de usuario es obligatorio") String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña asignada al nuevo jurado.
     *
     * <p>La contraseña es obligatoria y debe tener una longitud mínima de 6 caracteres.
     * Este valor será posteriormente encriptado antes de almacenarse en la base de datos.</p>
     *
     * @since v0.2
     * @return contraseña del jurado.
     */
    public @NotBlank(message = "La contraseña es obligatoria") @Size(min = 6) String getPassword() {
        return password;
    }

    /**
     * Obtiene la dirección de correo electrónico del usuario.
     *
     * <p>Este valor es obligatorio y debe cumplir con el formato de un email válido.
     * La anotación {@link NotBlank} garantiza que no sea una cadena vacía, mientras
     * que la validación {@link Email} asegura que tenga un formato correcto.</p>
     *
     * @since v0.4
     * @return el email del usuario.
     */
    public @NotBlank(message = "El email es obligatorio") String getEmail() {
        return email;
    }

    /**
     * Establece la dirección de correo electrónico del usuario.
     *
     * <p>El valor proporcionado debe ser un email válido y no puede estar vacío.
     * Las anotaciones de validación {@link NotBlank} y {@link Email} se aplican
     * para garantizar que el dato sea correcto antes de ser persistido.</p>
     *
     * @since v0.4
     * @param email dirección de correo electrónico que se asignará al usuario.
     */
    public void setEmail(@NotBlank(message = "El email es obligatorio") String email) {
        this.email = email;
    }

    /**
     * Establece la contraseña del nuevo jurado.
     *
     * <p>Debe ser un texto no vacío y cumplir con la longitud mínima requerida.
     * La contraseña será encriptada por la capa de servicio antes de persistirse.</p>
     *
     * @since v0.2
     * @param password contraseña del jurado.
     */
    public void setPassword(@NotBlank(message = "La contraseña es obligatoria") @Size(min = 6) String password) {
        this.password = password;
    }

    /**
     * Convierte este DTO en una entidad {@link Jurado}.
     *
     * <p>Este método construye una nueva instancia de {@code Jurado} utilizando los
     * datos proporcionados en el DTO. La conversión incluye:</p>
     *
     * <ul>
     *     <li>Asignar el nombre de usuario recibido.</li>
     *     <li>Asignar la contraseña tal como llega (la capa de servicio se encargará de encriptarla).</li>
     *     <li>Establecer el rol del usuario como {@code "JURADO"}.</li>
     *     <li>Inicializar el mapa de datos extra como un {@link HashMap} vacío.</li>
     *     <li>Asignar el email recibido.</li>
     * </ul>
     *
     * <p>La entidad resultante queda lista para ser procesada por la capa de servicio,
     * donde se aplicarán validaciones adicionales y la encriptación de la contraseña.</p>
     *
     * @since v0.1
     * @return una entidad {@code Jurado} construida a partir de este DTO.
     */
    public Jurado toEntity() {
        Jurado j = new Jurado();
        j.setUsername(username);
        j.setPassword(password);
        j.setRol("JURADO");
        j.setDatosExtra(new HashMap<>());
        j.setEmail(email);
        return j;
    }

}
