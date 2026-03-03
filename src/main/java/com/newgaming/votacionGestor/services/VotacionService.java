package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.DTO.CrearVotacionDTO;
import com.newgaming.votacionGestor.models.*;
import com.newgaming.votacionGestor.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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
 * @version v0.2
 */
@Service
public class VotacionService {

    @Autowired
    private VotacionRepository votacionRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EventoEmpresaRepository eventoEmpresaRepository;

    @Autowired
    private FormularioRepository formularioRepository;

    /**
     * Crea una nueva votación en el sistema y valida todas las condiciones necesarias
     * para garantizar que el proceso sea correcto.
     *
     * <p>Este método realiza múltiples verificaciones antes de registrar la votación,
     * asegurando que el evento, la empresa, el formulario y el jurado cumplan con
     * los requisitos para que la votación sea válida.</p>
     *
     * <ul>
     *     <li>Verifica que el evento exista y esté habilitado.</li>
     *     <li>Comprueba que la empresa exista y participe en el evento.</li>
     *     <li>Valida que el formulario exista y corresponda al evento.</li>
     *     <li>Comprueba que el jurado no haya votado previamente a esa empresa en ese evento.</li>
     *     <li>Crea la entidad <code>Votacion</code> con los datos proporcionados.</li>
     *     <li>Registra la fecha de votación y calcula la puntuación total.</li>
     *     <li>Guarda la votación en la base de datos.</li>
     * </ul>
     *
     * @since v0.1
     * @param dto datos necesarios para registrar la votación.
     * @param jurado jurado que realiza la votación.
     * @return votación registrada y persistida.
     * @throws ResponseStatusException si alguna validación falla.
     */
    public Votacion crearVotacion(CrearVotacionDTO dto, Jurado jurado) {

        Evento evento = eventoRepository.findById(dto.getIdEvento())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado"));

        if (!"HABILITADO".equals(evento.getEstado())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El evento no está habilitado");
        }

        Empresa empresa = empresaRepository.findById(dto.getIdEmpresa())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa no encontrada"));

        boolean participa = eventoEmpresaRepository
                .findById(new EventoEmpresaId(evento.getIdEvento(), empresa.getIdEmpresa()))
                .isPresent();

        if (!participa) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La empresa no participa en este evento");
        }

        Formulario formulario = formularioRepository.findById(dto.getIdFormulario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Formulario no encontrado"));

        if (!formulario.getEvento().getIdEvento().equals(evento.getIdEvento())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El formulario no corresponde al evento");
        }

        boolean yaVoto = votacionRepository
                .findByJuradoIdUsuario(jurado.getIdUsuario())
                .stream()
                .anyMatch(v -> v.getEvento().getIdEvento().equals(evento.getIdEvento())
                        && v.getEmpresa().getIdEmpresa().equals(empresa.getIdEmpresa()));

        if (yaVoto) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya has votado a esta empresa en este evento");
        }

        Votacion votacion = new Votacion();
        votacion.setJurado(jurado);
        votacion.setEmpresa(empresa);
        votacion.setEvento(evento);
        votacion.setFormulario(formulario);
        votacion.setRespuestasJson(dto.getRespuestasJson());
        votacion.setFechaVotacion(LocalDateTime.now());

        votacion.setPuntuacionTotal(calcularPuntuacion(dto.getRespuestasJson()));
        System.out.println("Respuestas recibidas: " + dto.getRespuestasJson());

        return votacionRepository.save(votacion);
    }

    /**
     * Calcula la puntuación total de una votación a partir de las respuestas enviadas.
     *
     * <p>Este método recibe el JSON con las respuestas del jurado y devuelve un valor
     * numérico que representa la puntuación final. Actualmente devuelve un valor fijo,
     * pero está preparado para implementar lógica de cálculo basada en el contenido
     * del formulario.</p>
     *
     * @since v0.2
     * @param respuestasJson respuestas del jurado en formato JSON.
     * @return puntuación total calculada.
     */
    private Double calcularPuntuacion(String respuestasJson) {
        return 0.0;
    }


    /**
     * Obtiene todas las votaciones asociadas a un evento concreto.
     *
     * @param idEvento identificador del evento.
     * @return lista de votaciones del evento.
     * @since v0.1
     */
    public List<Votacion> obtenerPorEvento(Long idEvento) {
        return votacionRepository.findByEventoIdEvento(idEvento);
    }

    /**
     * Obtiene todas las votaciones asociadas a una empresa concreta.
     *
     * @param idEmpresa identificador de la empresa.
     * @return lista de votaciones de la empresa.
     * @since v0.1
     */
    public List<Votacion> obtenerPorEmpresa(Long idEmpresa) {
        return votacionRepository.findByEmpresaIdEmpresa(idEmpresa);
    }

    /**
     * Obtiene todas las votaciones realizadas por un jurado concreto.
     *
     * @param idJurado identificador del jurado.
     * @return lista de votaciones realizadas por el jurado.
     * @since v0.1
     */
    public List<Votacion> obtenerPorJurado(Long idJurado) {
        return votacionRepository.findByJuradoIdUsuario(idJurado);
    }
}
