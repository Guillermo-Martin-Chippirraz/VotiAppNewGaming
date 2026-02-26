package com.newgaming.votacionGestor.DTO;

import com.newgaming.votacionGestor.models.Jurado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
 * @version 0.1
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
     * @since 0.1
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
     * @since 0.1
     */
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6)
    private String password;

    /**
     * Convierte el DTO en una entidad <code>Jurado</code>.
     *
     * <ol>
     *     <li>Crea una nueva instancia de Jurado.</li>
     *     <li>Asigna el username recibido.</li>
     *     <li>Asigna la contraseña recibida.</li>
     *     <li>Establece el rol del usuario como "JURADO".</li>
     * </ol>
     *
     * @since 0.1
     * @return entidad Jurado lista para ser persistida.
     */
    public Jurado toEntity() {
        Jurado j = new Jurado();
        j.setUsername(username);
        j.setPassword(password);
        j.setRol("JURADO");
        return j;
    }
}
