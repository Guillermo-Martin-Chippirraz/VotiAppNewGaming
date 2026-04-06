package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.DTO.CrearVotacionDTO;
import com.newgaming.votacionGestor.DTO.EditarVotacionDTO;
import com.newgaming.votacionGestor.components.VerificacionAdmin;
import com.newgaming.votacionGestor.models.*;
import com.newgaming.votacionGestor.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

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
 * @version v0.3
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

    @Autowired
    private JuradoRepository juradoRepository;

    /**
     * Obtiene todas las votaciones registradas en el sistema.
     *
     * <p>La operación se ejecuta en modo de solo lectura para optimizar el acceso
     * a la base de datos. Devuelve la lista completa de entidades {@link Votacion}
     * almacenadas en el repositorio.</p>
     *
     * @since v0.3
     * @return lista de todas las votaciones existentes.
     */
    @Transactional(readOnly = true)
    public List<Votacion> obtenerTodas() {
        return votacionRepository.findAll();
    }

    /**
     * Obtiene una votación por su identificador.
     *
     * <p>La operación se ejecuta en modo de solo lectura. Si la votación no existe,
     * se lanza una {@link ResponseStatusException} con estado 404 indicando que
     * no se encontró el recurso solicitado.</p>
     *
     * @since v0.3
     * @param idVotacion identificador de la votación a consultar.
     * @return la votación correspondiente al identificador proporcionado.
     * @throws ResponseStatusException si no existe una votación con ese ID.
     */
    @Transactional(readOnly = true)
    public Votacion obtenerPorId(Long idVotacion) {
        return votacionRepository.findById(idVotacion)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Votación no encontrada"
                ));
    }

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
        votacion.setPuntuacionTotal(dto.getPuntuacionTotal());

        return votacionRepository.save(votacion);
    }

    /**
     * Edita una votación existente realizada por un jurado.
     *
     * <p>Este método permite modificar la puntuación total y las respuestas del
     * formulario asociadas a una votación. Antes de aplicar los cambios, se
     * realizan varias validaciones:</p>
     *
     * <ul>
     *   <li>Se verifica que el usuario autenticado sea el propietario de la votación.</li>
     *   <li>Se comprueba que el evento asociado esté en estado {@code HABILITADO}.</li>
     *   <li>Solo se actualizan los campos que hayan sido proporcionados en el DTO.</li>
     * </ul>
     *
     * <p>Tras guardar la votación, se ejecuta una verificación automática para
     * determinar si el evento debe cerrarse (pasar a estado {@code FINALIZADO})
     * en caso de que todos los jurados hayan votado.</p>
     *
     * @since v0.3
     * @param idVotacion identificador de la votación a editar.
     * @param dto datos a modificar en la votación.
     * @param idJurado identificador del jurado que realiza la edición.
     * @return la votación actualizada.
     * @throws ResponseStatusException si el usuario no es el propietario,
     *         si el evento está cerrado o si la votación no existe.
     */
    @Transactional
    public Votacion editarVotacion(Long idVotacion, EditarVotacionDTO dto, Long idJurado) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioActual = (Usuario) auth.getDetails();

        Votacion votacion = obtenerPorId(idVotacion);

        if (!votacion.getJurado().getIdUsuario().equals(usuarioActual.getIdUsuario())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes editar esta votación");
        }

        Evento evento = votacion.getEvento();

        if (!"HABILITADO".equals(evento.getEstado())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El evento está cerrado");
        }

        if (dto.getPuntuacionTotal() != null) {
            votacion.setPuntuacionTotal(dto.getPuntuacionTotal());
        }

        if (dto.getRespuestasJson() != null) {
            votacion.setRespuestasJson(dto.getRespuestasJson());
        }

        votacion.setFechaVotacion(LocalDateTime.now());

        Votacion guardada = votacionRepository.save(votacion);

        verificarCierreEvento(evento.getIdEvento());

        return guardada;
    }

    /**
     * Elimina una votación del sistema.
     *
     * <p>Solo un administrador puede realizar esta operación. Se verifica el rol
     * mediante la utilidad {@code VerificacionAdmin.esAdmin()}. Si el usuario no
     * es administrador, se lanza una excepción con estado 403.</p>
     *
     * <p>Si la votación existe, se elimina del repositorio.</p>
     *
     * @since v0.3
     * @param idVotacion identificador de la votación a eliminar.
     * @throws ResponseStatusException si el usuario no es administrador o si la votación no existe.
     */
    @Transactional
    public void eliminarVotacion(Long idVotacion) {

        boolean esAdmin = VerificacionAdmin.esAdmin();
        if (!esAdmin) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sólo un administrador puede eliminar votaciones");
        }
        Votacion votacion = obtenerPorId(idVotacion);
        votacionRepository.delete(votacion);
    }

    /**
     * Verifica si un evento debe cerrarse automáticamente.
     *
     * <p>Este método evalúa si todos los jurados han emitido su votación para el
     * evento indicado. Si el evento está en estado {@code HABILITADO} y todos los
     * jurados han votado, se procede a:</p>
     *
     * <ul>
     *   <li>Sumar las puntuaciones totales por empresa.</li>
     *   <li>Comprobar si existe un empate en las puntuaciones.</li>
     *   <li>Si no hay empates, cambiar el estado del evento a {@code FINALIZADO}.</li>
     * </ul>
     *
     * <p>Si el evento no existe, se lanza una excepción. Si aún faltan jurados por
     * votar o hay empate, el evento permanece abierto.</p>
     *
     * @since v0.3
     * @param idEvento identificador del evento a verificar.
     * @throws RuntimeException si el evento no existe.
     */
    @Transactional
    public void verificarCierreEvento(Long idEvento) {

        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        if (!"HABILITADO".equals(evento.getEstado())) return;

        List<Votacion> votaciones = votacionRepository.findByEventoIdEvento(idEvento);

        long totalJurados = juradoRepository.count();
        long juradosQueHanVotado = votaciones.stream()
                .map(v -> v.getJurado().getIdUsuario())
                .distinct()
                .count();

        if (juradosQueHanVotado < totalJurados) return;

        Map<Long, Double> puntuacionesPorEmpresa = new HashMap<>();

        for (Votacion v : votaciones) {
            puntuacionesPorEmpresa.merge(
                    v.getEmpresa().getIdEmpresa(),
                    v.getPuntuacionTotal(),
                    Double::sum
            );
        }

        Set<Double> puntuaciones = new HashSet<>(puntuacionesPorEmpresa.values());

        if (puntuaciones.size() != puntuacionesPorEmpresa.size()) {
            return;
        }

        evento.setEstado("FINALIZADO");
        eventoRepository.save(evento);
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
