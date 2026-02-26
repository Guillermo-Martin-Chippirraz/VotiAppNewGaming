package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.models.Votacion;
import com.newgaming.votacionGestor.repositories.VotacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con las votaciones.
 * Permite registrar nuevas votaciones y consultarlas según distintos criterios:
 * por evento, por empresa o por jurado.
 *
 * <p>Actúa como capa intermedia entre los controladores y el repositorio
 * {@link VotacionRepository}, encapsulando la lógica necesaria para operar
 * con la entidad {@link Votacion}.</p>
 *
 * <p>Responsabilidades principales:</p>
 * <ul>
 *     <li>Registrar votaciones.</li>
 *     <li>Consultar votaciones por evento.</li>
 *     <li>Consultar votaciones por empresa.</li>
 *     <li>Consultar votaciones por jurado.</li>
 * </ul>
 *
 * <p>La anotación {@link Service} permite que Spring gestione esta clase como
 * un componente de servicio dentro del contexto de la aplicación.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Service
public class VotacionService {

    private final VotacionRepository votacionRepository;

    /**
     * Constructor que inyecta el repositorio de votaciones.
     *
     * @param votacionRepository repositorio de votaciones.
     * @since 0.1
     */
    public VotacionService(VotacionRepository votacionRepository) {
        this.votacionRepository = votacionRepository;
    }

    /**
     * Crea una nueva votación en el sistema.
     *
     * @param votacion votación a registrar.
     * @return votación registrada.
     * @since 0.1
     */
    public Votacion crearVotacion(Votacion votacion) {
        return votacionRepository.save(votacion);
    }

    /**
     * Obtiene todas las votaciones asociadas a un evento concreto.
     *
     * @param idEvento identificador del evento.
     * @return lista de votaciones del evento.
     * @since 0.1
     */
    public List<Votacion> obtenerPorEvento(Long idEvento) {
        return votacionRepository.findByEventoIdEvento(idEvento);
    }

    /**
     * Obtiene todas las votaciones asociadas a una empresa concreta.
     *
     * @param idEmpresa identificador de la empresa.
     * @return lista de votaciones de la empresa.
     * @since 0.1
     */
    public List<Votacion> obtenerPorEmpresa(Long idEmpresa) {
        return votacionRepository.findByEmpresaIdEmpresa(idEmpresa);
    }

    /**
     * Obtiene todas las votaciones realizadas por un jurado concreto.
     *
     * @param idJurado identificador del jurado.
     * @return lista de votaciones realizadas por el jurado.
     * @since 0.1
     */
    public List<Votacion> obtenerPorJurado(Long idJurado) {
        return votacionRepository.findByJuradoIdUsuario(idJurado);
    }
}
