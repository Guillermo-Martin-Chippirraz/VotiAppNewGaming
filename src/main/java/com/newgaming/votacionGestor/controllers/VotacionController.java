package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.DTO.CrearVotacionDTO;
import com.newgaming.votacionGestor.DTO.EditarVotacionDTO;
import com.newgaming.votacionGestor.DTO.VotacionDTO;
import com.newgaming.votacionGestor.models.Jurado;
import com.newgaming.votacionGestor.models.Usuario;
import com.newgaming.votacionGestor.models.Votacion;
import com.newgaming.votacionGestor.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con las votaciones
 * dentro del sistema. Permite registrar nuevas votaciones y consultar votaciones
 * filtradas por evento, empresa o jurado.
 *
 * <p>El funcionamiento general del controlador se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Expone endpoints bajo la ruta base <code>/api/votaciones</code>.</li>
 *     <li>Permite registrar nuevas votaciones realizadas por los jurados.</li>
 *     <li>Permite consultar votaciones según distintos criterios:
 *         <ul>
 *             <li>Evento</li>
 *             <li>Empresa</li>
 *             <li>Jurado</li>
 *         </ul>
 *     </li>
 *     <li>Utiliza <code>VotacionService</code> para la lógica de negocio.</li>
 * </ol>
 *
 * <p>Este controlador centraliza el acceso a la información de votaciones,
 * facilitando su consulta y registro dentro del sistema.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.3
 */
@RestController
@RequestMapping("/api/votaciones")
public class VotacionController {

    private final VotacionService votacionService;
    private final JuradoService juradoService;
    private final EventoService eventoService;
    private final EmpresaService empresaService;
    private final UsuarioService usuarioService;

    /**
     * Constructor por parámetros.
     * Inicializa el controlador con los servicios necesarios para gestionar
     * votaciones, jurados, eventos, empresas y usuarios.
     *
     * <ol>
     *     <li>Asigna la dependencia <code>VotacionService</code>.</li>
     *     <li>Asigna la dependencia <code>JuradoService</code>.</li>
     *     <li>Asigna la dependencia <code>EventoService</code>.</li>
     *     <li>Asigna la dependencia <code>EmpresaService</code>.</li>
     *     <li>Asigna la dependencia <code>UsuarioService</code>.</li>
     * </ol>
     *
     * @since v0.1
     * @param votacionService servicio encargado de la gestión de votaciones.
     * @param juradoService servicio encargado de la gestión de jurados.
     * @param eventoService servicio encargado de la gestión de eventos.
     * @param empresaService servicio encargado de la gestión de empresas.
     * @param usuarioService servicio encargado de la gestión de usuarios.
     */
    public VotacionController(VotacionService votacionService, JuradoService juradoService, EventoService eventoService,
                              EmpresaService empresaService, UsuarioService usuarioService) {
        this.votacionService = votacionService;
        this.juradoService = juradoService;
        this.eventoService = eventoService;
        this.empresaService = empresaService;
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para registrar una nueva votación.
     * Solo accesible para usuarios con rol JURADO.
     *
     * <ol>
     *     <li>Obtiene el username del jurado autenticado desde el token JWT.</li>
     *     <li>Recupera la entidad <code>Jurado</code> asociada a dicho username.</li>
     *     <li>Recibe un DTO con los datos necesarios para registrar la votación.</li>
     *     <li>Solicita al servicio crear la votación aplicando todas las validaciones correspondientes.</li>
     *     <li>Devuelve la votación creada como respuesta.</li>
     * </ol>
     *
     * @since v0.1
     * @param dto DTO con los datos de la votación a registrar.
     * @return votación creada.
     */
    @PreAuthorize("hasRole('JURADO')")
    @PostMapping
    public ResponseEntity<Votacion> crearVotacion(@RequestBody CrearVotacionDTO dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Jurado jurado = juradoService.obtenerJuradoPorUsername(username);

        Votacion votacion = votacionService.crearVotacion(dto, jurado);

        return ResponseEntity.status(HttpStatus.CREATED).body(votacion);
    }

    /**
     * Obtiene todas las votaciones registradas en el sistema.
     *
     * <p>Esta operación solo puede realizarla un administrador. Devuelve la lista
     * completa de votaciones transformadas a {@link VotacionDTO}, evitando exponer
     * información interna del modelo. Si no existen votaciones, se devuelve una
     * lista vacía con estado 200.</p>
     *
     * @since v0.3
     * @return lista de votaciones en formato DTO.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<VotacionDTO>> obtenerTodas() {
        List<Votacion> votaciones = votacionService.obtenerTodas();
        List<VotacionDTO> lista = new ArrayList<>();

        for (Votacion votacion : votaciones) {
            lista.add(VotacionDTO.fromEntity(votacion));
        }

        return ResponseEntity.ok(lista);
    }

    /**
     * Obtiene una votación por su identificador.
     *
     * <p>La operación puede realizarla un administrador o el jurado propietario de
     * la votación. La verificación se realiza mediante la expresión de seguridad
     * {@code hasRole('ADMIN') or @seguridad.esJuradoDeVotacion(#idVotacion)}.
     * Si la votación no existe, se devuelve un estado 404. Si existe, se devuelve
     * con estado 200 en formato {@link VotacionDTO}.</p>
     *
     * @since v0.3
     * @param idVotacion identificador de la votación a consultar.
     * @return la votación solicitada en formato DTO.
     */
    @PreAuthorize("hasRole('ADMIN') or @seguridad.esJuradoDeVotacion(#idVotacion)")
    @GetMapping("/{idVotacion}")
    public ResponseEntity<VotacionDTO> obtenerPorId(@PathVariable Long idVotacion) {
        return ResponseEntity.ok(VotacionDTO.fromEntity(votacionService.obtenerPorId(idVotacion)));
    }

    /**
     * Edita una votación existente realizada por un jurado.
     *
     * <p>Solo un usuario con rol <strong>JURADO</strong> puede editar una votación,
     * y únicamente si es el propietario de la misma. El identificador del jurado
     * autenticado se obtiene desde el contexto de seguridad. Si la votación no
     * existe o no pertenece al jurado autenticado, se devuelve un estado 403 o 404
     * según corresponda. Si la edición es válida, se devuelve la votación
     * actualizada con estado 200.</p>
     *
     * @since v0.3
     * @param idVotacion identificador de la votación a editar.
     * @param dto datos a modificar en la votación.
     * @param auth información del usuario autenticado.
     * @return la votación actualizada en formato DTO.
     */
    @PreAuthorize("hasRole('JURADO')")
    @PutMapping("/{idVotacion}")
    public ResponseEntity<VotacionDTO> editarVotacion(
            @PathVariable Long idVotacion,
            @RequestBody EditarVotacionDTO dto,
            Authentication auth) {

        Usuario usuario = (Usuario) auth.getDetails();
        Long idJurado = usuario.getIdUsuario();

        return ResponseEntity.ok(
                VotacionDTO.fromEntity(votacionService.editarVotacion(idVotacion, dto, idJurado))
        );
    }

    /**
     * Elimina una votación del sistema.
     *
     * <p>Solo un administrador puede realizar esta acción. Si la votación no existe,
     * se devuelve un estado 404. Si la eliminación se completa correctamente,
     * se devuelve un estado 204 (sin contenido). La eliminación implica retirar
     * la votación de forma permanente del sistema.</p>
     *
     * @since v0.3
     * @param idVotacion identificador de la votación a eliminar.
     * @return respuesta sin contenido si la eliminación fue exitosa.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idVotacion}")
    public ResponseEntity<Void> eliminarVotacion(@PathVariable Long idVotacion) {
        votacionService.eliminarVotacion(idVotacion);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para obtener todas las votaciones asociadas a un evento.
     * Solo accesible para usuarios con rol ADMIN.
     *
     * <ol>
     *     <li>Recibe el ID del evento mediante la URL.</li>
     *     <li>Verifica que el evento exista mediante el servicio correspondiente.</li>
     *     <li>Solicita al servicio obtener todas las votaciones del evento.</li>
     *     <li>Convierte cada votación en un objeto <code>VotacionDTO</code>.</li>
     *     <li>Devuelve la lista de votaciones como respuesta.</li>
     * </ol>
     *
     * @since v0.1
     * @param idEvento identificador del evento.
     * @return lista de votaciones asociadas al evento.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/evento/{idEvento}")
    public ResponseEntity<List<VotacionDTO>> obtenerPorEvento(@PathVariable Long idEvento) {

        eventoService.obtenerPorId(idEvento);

        List<VotacionDTO> lista = votacionService
                .obtenerPorEvento(idEvento)
                .stream()
                .map(VotacionDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(lista);
    }

    /**
     * Endpoint para obtener todas las votaciones asociadas a una empresa.
     * Solo accesible para usuarios con rol ADMIN.
     *
     * <ol>
     *     <li>Recibe el ID de la empresa mediante la URL.</li>
     *     <li>Verifica que la empresa exista mediante el servicio correspondiente.</li>
     *     <li>Solicita al servicio obtener todas las votaciones de la empresa.</li>
     *     <li>Convierte cada votación en un objeto <code>VotacionDTO</code>.</li>
     *     <li>Devuelve la lista de votaciones como respuesta.</li>
     * </ol>
     *
     * @since v0.1
     * @param idEmpresa identificador de la empresa.
     * @return lista de votaciones asociadas a la empresa.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/empresa/{idEmpresa}")
    public ResponseEntity<List<VotacionDTO>> obtenerPorEmpresa(@PathVariable Long idEmpresa) {

        empresaService.obtenerPorId(idEmpresa);

        List<VotacionDTO> lista = votacionService
                .obtenerPorEmpresa(idEmpresa)
                .stream()
                .map(VotacionDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(lista);
    }

    /**
     * Endpoint para obtener todas las votaciones realizadas por un jurado.
     * Accesible para ADMIN y JURADO, con restricciones:
     * <ul>
     *     <li>Un ADMIN puede ver las votaciones de cualquier jurado.</li>
     *     <li>Un jurado solo puede ver sus propias votaciones.</li>
     * </ul>
     *
     * <ol>
     *     <li>Obtiene el username del usuario autenticado desde el token JWT.</li>
     *     <li>Recupera la entidad <code>Usuario</code> asociada a dicho username.</li>
     *     <li>Verifica si el solicitante tiene permiso para ver las votaciones del jurado indicado.</li>
     *     <li>Verifica que el jurado solicitado exista.</li>
     *     <li>Solicita al servicio obtener todas las votaciones realizadas por ese jurado.</li>
     *     <li>Convierte cada votación en un objeto <code>VotacionDTO</code>.</li>
     *     <li>Devuelve la lista de votaciones como respuesta.</li>
     * </ol>
     *
     * @since v0.1
     * @param idJurado identificador del jurado.
     * @param auth objeto de autenticación que contiene el usuario autenticado.
     * @return lista de votaciones realizadas por el jurado.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'JURADO')")
    @GetMapping("/jurado/{idJurado}")
    public ResponseEntity<List<VotacionDTO>> obtenerPorJurado(@PathVariable Long idJurado, Authentication auth) {

        String username = (String) auth.getPrincipal();

        Usuario solicitante = usuarioService.buscarPorUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        if (solicitante.getRol().equals("JURADO") &&
            !solicitante.getIdUsuario().equals(idJurado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes ver votaciones de otros jurados");
        }

        usuarioService.obtenerPorId(idJurado);

        List<VotacionDTO> lista = votacionService
                .obtenerPorJurado(idJurado)
                .stream()
                .map(VotacionDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(lista);
    }
}
