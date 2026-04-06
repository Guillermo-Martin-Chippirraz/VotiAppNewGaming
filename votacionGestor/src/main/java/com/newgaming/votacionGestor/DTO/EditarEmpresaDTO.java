package com.newgaming.votacionGestor.DTO;

/**
 * DTO utilizado para la edición de datos de una empresa.
 *
 * <p>Permite actualizar información básica de una empresa existente, incluyendo
 * su nombre, descripción y URL del logotipo. Este objeto se emplea en las
 * operaciones de actualización realizadas por administradores dentro del
 * sistema.</p>
 *
 * <p>Todos los campos son opcionales; únicamente se modificarán aquellos que
 * sean proporcionados en la solicitud.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.3
 */
public class EditarEmpresaDTO {

    /** Nombre actualizado de la empresa. */
    private String nombre;

    /** Descripción o información adicional de la empresa. */
    private String descripcion;

    /** URL del logotipo asociado a la empresa. */
    private String logoUrl;

    /**
     * Obtiene el nombre propuesto para la empresa.
     *
     * @since v0.3
     * @return nombre de la empresa, o {@code null} si no se ha establecido.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre que se desea asignar a la empresa.
     *
     * @since v0.3
     * @param nombre nuevo nombre de la empresa.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción propuesta para la empresa.
     *
     * @since v0.3
     * @return descripción de la empresa, o {@code null} si no se ha establecido.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción que se desea asignar a la empresa.
     *
     * @since v0.3
     * @param descripcion nueva descripción de la empresa.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la URL del logotipo propuesto para la empresa.
     *
     * @since v0.3
     * @return URL del logotipo, o {@code null} si no se ha establecido.
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * Establece la URL del logotipo que se desea asociar a la empresa.
     *
     * @since v0.3
     * @param logoUrl nueva URL del logotipo de la empresa.
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}