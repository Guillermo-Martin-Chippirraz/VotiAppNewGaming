package com.newgaming.votacionGestor.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Clase que modela un usuario genérico dentro del sistema de votación.
 * Esta entidad actúa como clase base para Administrador y Jurado mediante
 * herencia JOINED, permitiendo que cada tipo de usuario extienda sus
 * funcionalidades manteniendo una estructura común.
 *
 * <p>Los atributos principales incluyen:</p>
 * <ol>
 *     <li>Identificador único del usuario.</li>
 *     <li>Nombre de usuario único para autenticación.</li>
 *     <li>Email de contacto.</li>
 *     <li>Contraseña (pendiente de hashing).</li>
 *     <li>Rol del usuario dentro del sistema (ADMIN o JURADO).</li>
 *     <li>Datos adicionales almacenados como JSON.</li>
 *     <li>Fechas de creación y actualización.</li>
 * </ol>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.1
 */
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    /**
     * Identificador único del usuario.
     * Se genera automáticamente mediante estrategia IDENTITY.
     *
     * <ol>
     *     <li>Se utiliza como clave primaria en la tabla usuario.</li>
     *     <li>Es heredado por las entidades Administrador y Jurado.</li>
     * </ol>
     *
     * @since v0.1
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    /**
     * Nombre de usuario único utilizado para autenticación.
     *
     * <ol>
     *     <li>Debe ser único en la base de datos.</li>
     *     <li>Se utiliza como identificador principal en el login.</li>
     * </ol>
     *
     * @since v0.1
     */
    @Column(unique = true)
    private String username;

    /**
     * Correo electrónico del usuario.
     *
     * <ol>
     *     <li>Se utiliza para contacto o recuperación de cuenta.</li>
     *     <li>No tiene restricciones de unicidad.</li>
     * </ol>
     *
     * @since v0.1
     */
    private String email;

    /**
     * Contraseña del usuario.
     *
     * <ol>
     *     <li>Actualmente almacenada en texto plano.</li>
     *     <li>Debe ser cifrada en futuras versiones.</li>
     * </ol>
     *
     * @since v0.1
     */
    private String password;

    /**
     * Rol del usuario dentro del sistema.
     *
     * <ol>
     *     <li>Puede ser ADMIN o JURADO.</li>
     *     <li>Determina los permisos y accesos del usuario.</li>
     * </ol>
     *
     * @since v0.1
     */
    private String rol;

    /**
     * Datos adicionales del usuario almacenados en formato JSONB.
     *
     * <ol>
     *     <li>Permite almacenar información flexible.</li>
     *     <li>Se utiliza para datos no estructurados.</li>
     * </ol>
     *
     * @since v0.1
     */
    @Column(columnDefinition = "jsonb")
    private String datosExtra;

    /**
     * Fecha y hora en la que se creó el usuario.
     *
     * <ol>
     *     <li>Se asigna al registrar el usuario.</li>
     *     <li>Permite auditoría temporal.</li>
     * </ol>
     *
     * @since v0.1
     */
    private LocalDateTime creadoEn;

    /**
     * Fecha y hora de la última actualización del usuario.
     *
     * <ol>
     *     <li>Se actualiza cada vez que se modifica el usuario.</li>
     *     <li>Permite control de cambios.</li>
     * </ol>
     *
     * @since v0.1
     */
    private LocalDateTime actualizadoEn;

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return ID del usuario.
     * @since v0.1
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param idUsuario ID del usuario.
     * @since v0.1
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return username del usuario.
     * @since v0.1
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username nombre de usuario.
     * @since v0.1
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene el email del usuario.
     *
     * @return email del usuario.
     * @since v0.1
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del usuario.
     *
     * @param email correo electrónico.
     * @since v0.1
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return contraseña del usuario.
     * @since v0.1
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password contraseña en texto plano.
     * @since v0.1
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return rol del usuario.
     * @since v0.1
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol rol asignado (ADMIN o JURADO).
     * @since v0.1
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Obtiene los datos adicionales del usuario.
     *
     * @return datos en formato JSON.
     * @since v0.1
     */
    public String getDatosExtra() {
        return datosExtra;
    }

    /**
     * Establece los datos adicionales del usuario.
     *
     * @param datosExtra JSON con información adicional.
     * @since v0.1
     */
    public void setDatosExtra(String datosExtra) {
        this.datosExtra = datosExtra;
    }

    /**
     * Obtiene la fecha de creación del usuario.
     *
     * @return fecha de creación.
     * @since v0.1
     */
    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    /**
     * Establece la fecha de creación del usuario.
     *
     * @param creadoEn fecha de creación.
     * @since v0.1
     */
    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    /**
     * Obtiene la fecha de última actualización del usuario.
     *
     * @return fecha de actualización.
     * @since v0.1
     */
    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    /**
     * Establece la fecha de última actualización del usuario.
     *
     * @param actualizadoEn fecha de actualización.
     * @since v0.1
     */
    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }
}
