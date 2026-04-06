package com.newgaming.votacionGestor.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que modela una empresa dentro del sistema de votación.
 * Representa a las entidades que serán evaluadas por los jurados en los eventos.
 *
 * <p>El comportamiento y estructura de la clase se basan en los siguientes puntos:</p>
 * <ol>
 *     <li>Incluye un identificador único generado automáticamente.</li>
 *     <li>Almacena información básica de la empresa: nombre, descripción y URL del logo.</li>
 *     <li>Registra qué administrador creó la empresa mediante una relación ManyToOne.</li>
 *     <li>Incluye marcas temporales de creación y actualización para auditoría.</li>
 *     <li>Se almacena en la tabla "empresa" dentro de la base de datos.</li>
 * </ol>
 *
 * <p>Esta clase es utilizada por administradores para registrar empresas que
 * posteriormente serán evaluadas por los jurados.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
@Entity
@Table(name = "empresa")
public class Empresa {

    /**
     * Identificador único de la empresa.
     *
     * <ol>
     *     <li>Clave primaria de la tabla empresa.</li>
     *     <li>Generado automáticamente mediante estrategia IDENTITY.</li>
     * </ol>
     *
     * @since v0.1
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;

    /**
     * Nombre de la empresa.
     *
     * <ol>
     *     <li>Representa el nombre comercial o identificativo.</li>
     *     <li>Debe ser claro y reconocible para los jurados.</li>
     * </ol>
     *
     * @since v0.1
     */
    private String nombre;

    /**
     * Descripción general de la empresa.
     *
     * <ol>
     *     <li>Proporciona información contextual para los jurados.</li>
     *     <li>Puede incluir historia, misión o detalles relevantes.</li>
     * </ol>
     *
     * @since v0.1
     */
    private String descripcion;

    /**
     * URL del logo de la empresa.
     *
     * <ol>
     *     <li>Permite mostrar la imagen corporativa en el frontend.</li>
     *     <li>Debe ser una URL válida accesible por el cliente.</li>
     * </ol>
     *
     * @since v0.1
     */
    private String logoUrl;

    /**
     * Administrador que registró la empresa.
     *
     * <ul>
     *     <li>Representa la relación entre la empresa y el administrador que la creó.</li>
     *     <li>Relación ManyToOne: un administrador puede registrar múltiples empresas.</li>
     *     <li>Se persiste en la columna <code>id_admin_registro</code> de la tabla correspondiente.</li>
     *     <li>Es un dato obligatorio para mantener trazabilidad y auditoría.</li>
     * </ul>
     *
     * @since v0.1
     */
    @ManyToOne
    @JoinColumn(name = "id_admin_registro", nullable = false)
    private Administrador adminRegistro;


    /**
     * Fecha y hora en la que se creó la empresa.
     *
     * <ol>
     *     <li>Asignada al registrar la empresa.</li>
     *     <li>Permite auditoría temporal.</li>
     * </ol>
     *
     * @since v0.1
     */
    private LocalDateTime creadoEn;

    /**
     * Fecha y hora de la última actualización de la empresa.
     *
     * <ol>
     *     <li>Actualizada cada vez que se modifica la empresa.</li>
     *     <li>Permite control de cambios.</li>
     * </ol>
     *
     * @since v0.1
     */
    private LocalDateTime actualizadoEn;

    /**
     * Obtiene el identificador único de la empresa.
     *
     * @return ID de la empresa.
     * @since v0.1
     */
    public Long getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * Establece el identificador único de la empresa.
     *
     * @param idEmpresa ID de la empresa.
     * @since v0.1
     */
    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * Obtiene el nombre de la empresa.
     *
     * @return nombre de la empresa.
     * @since v0.1
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la empresa.
     *
     * @param nombre nombre comercial.
     * @since v0.1
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la empresa.
     *
     * @return descripción de la empresa.
     * @since v0.1
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la empresa.
     *
     * @param descripcion texto descriptivo.
     * @since v0.1
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la URL del logo de la empresa.
     *
     * @return URL del logo.
     * @since v0.1
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * Establece la URL del logo de la empresa.
     *
     * @param logoUrl dirección del logo.
     * @since v0.1
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    /**
     * Obtiene el administrador que registró la empresa.
     *
     * @return administrador creador.
     * @since v0.1
     */
    public Administrador getAdminRegistro() {
        return adminRegistro;
    }

    /**
     * Establece el administrador que registró la empresa.
     *
     * @param adminRegistro administrador creador.
     * @since v0.1
     */
    public void setAdminRegistro(Administrador adminRegistro) {
        this.adminRegistro = adminRegistro;
    }

    /**
     * Obtiene la fecha de creación de la empresa.
     *
     * @return fecha de creación.
     * @since v0.1
     */
    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    /**
     * Establece la fecha de creación de la empresa.
     *
     * @param creadoEn fecha de creación.
     * @since v0.1
     */
    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    /**
     * Obtiene la fecha de última actualización de la empresa.
     *
     * @return fecha de actualización.
     * @since v0.1
     */
    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    /**
     * Establece la fecha de última actualización de la empresa.
     *
     * @param actualizadoEn fecha de actualización.
     * @since v0.1
     */
    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }
}
