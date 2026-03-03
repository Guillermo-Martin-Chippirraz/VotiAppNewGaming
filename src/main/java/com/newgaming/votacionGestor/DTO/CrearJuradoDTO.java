package com.newgaming.votacionGestor.DTO;

import com.newgaming.votacionGestor.models.Jurado;
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
 * @version v0.2
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
     * Convierte este DTO en una entidad <code>Jurado</code>.
     *
     * <ol>
     *     <li>Crea una nueva instancia de <code>Jurado</code>.</li>
     *     <li>Asigna el nombre de usuario recibido.</li>
     *     <li>Asigna la contraseña recibida (sin encriptar; la capa de servicio se encarga de ello).</li>
     *     <li>Establece el rol del usuario como <code>"JURADO"</code>.</li>
     *     <li>Inicializa el mapa de datos extra como un HashMap vacío.</li>
     *     <li>Inicializa el email como cadena vacía.</li>
     * </ol>
     *
     * <p>Este método prepara la entidad para ser procesada por la capa de servicio,
     * donde se aplicarán validaciones adicionales y la encriptación de la contraseña.</p>
     *
     * @since v0.1
     * @return entidad <code>Jurado</code> lista para ser persistida.
     */
    public Jurado toEntity() {
        Jurado j = new Jurado();
        j.setUsername(username);
        j.setPassword(password);
        j.setRol("JURADO");
        j.setDatosExtra(new HashMap<>());
        j.setEmail("");
        return j;
    }

}
