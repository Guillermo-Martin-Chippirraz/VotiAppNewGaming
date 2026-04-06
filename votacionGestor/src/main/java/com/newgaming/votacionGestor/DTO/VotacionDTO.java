package com.newgaming.votacionGestor.DTO;

import com.newgaming.votacionGestor.models.Votacion;

/**
 * DTO utilizado para representar información esencial de una votación dentro del sistema.
 * Permite transportar datos seguros hacia el cliente sin exponer detalles internos como
 * respuestas completas o estructuras complejas del formulario.
 *
 * <p>Este DTO se emplea principalmente para mostrar votaciones asociadas a jurados,
 * empresas o eventos, proporcionando únicamente los datos necesarios para identificar
 * la votación y su resultado.</p>
 *
 * <p>Incluye un método estático para convertir una entidad <code>Votacion</code> en un
 * objeto <code>VotacionDTO</code>, facilitando la transformación entre capas.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
public class VotacionDTO {

    /**
     * Identificador único de la votación.
     *
     * @since 0.1
     */
    private Long id;

    /**
     * Identificador del jurado que realizó la votación.
     *
     * @since 0.1
     */
    private Long idJurado;

    /**
     * Identificador de la empresa evaluada.
     *
     * @since 0.1
     */
    private Long idEmpresa;

    /**
     * Identificador del evento al que pertenece la votación.
     *
     * @since 0.1
     */
    private Long idEvento;

    /**
     * Puntuación total otorgada por el jurado.
     *
     * @since 0.1
     */
    private Double puntuacionTotal;

    /**
     * Obtiene el identificador de la votación.
     *
     * @since 0.1
     * @return ID de la votación.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de la votación.
     *
     * @since 0.1
     * @param id ID de la votación.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del jurado que realizó la votación.
     *
     * @since 0.1
     * @return ID del jurado.
     */
    public Long getIdJurado() {
        return idJurado;
    }

    /**
     * Establece el identificador del jurado que realizó la votación.
     *
     * @since 0.1
     * @param idJurado ID del jurado.
     */
    public void setIdJurado(Long idJurado) {
        this.idJurado = idJurado;
    }

    /**
     * Obtiene el identificador de la empresa evaluada.
     *
     * @since 0.1
     * @return ID de la empresa.
     */
    public Long getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * Establece el identificador de la empresa evaluada.
     *
     * @since 0.1
     * @param idEmpresa ID de la empresa.
     */
    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * Obtiene el identificador del evento asociado a la votación.
     *
     * @since 0.1
     * @return ID del evento.
     */
    public Long getIdEvento() {
        return idEvento;
    }

    /**
     * Establece el identificador del evento asociado a la votación.
     *
     * @since 0.1
     * @param idEvento ID del evento.
     */
    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * Obtiene la puntuación total otorgada en la votación.
     *
     * @since 0.1
     * @return puntuación total.
     */
    public Double getPuntuacionTotal() {
        return puntuacionTotal;
    }

    /**
     * Establece la puntuación total otorgada en la votación.
     *
     * @since 0.1
     * @param puntuacionTotal puntuación total.
     */
    public void setPuntuacionTotal(Double puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    /**
     * Convierte una entidad <code>Votacion</code> en un objeto <code>VotacionDTO</code>.
     *
     * <ol>
     *     <li>Crea una nueva instancia del DTO.</li>
     *     <li>Asigna el ID de la votación.</li>
     *     <li>Asigna los IDs de empresa, evento y jurado.</li>
     *     <li>Asigna la puntuación total otorgada.</li>
     * </ol>
     *
     * @since 0.1
     * @param v entidad Votacion a convertir.
     * @return DTO con la información esencial de la votación.
     */
    public static VotacionDTO fromEntity(Votacion v) {
        VotacionDTO dto = new VotacionDTO();
        dto.setId(v.getIdVotacion());
        dto.setIdEmpresa(v.getEmpresa().getIdEmpresa());
        dto.setIdEvento(v.getEvento().getIdEvento());
        dto.setIdJurado(v.getJurado().getIdUsuario());
        dto.setPuntuacionTotal(v.getPuntuacionTotal());
        return dto;
    }
}
