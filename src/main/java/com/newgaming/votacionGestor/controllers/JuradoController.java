package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.DTO.JuradoDTO;
import com.newgaming.votacionGestor.DTO.VotacionDTO;
import com.newgaming.votacionGestor.models.Jurado;
import com.newgaming.votacionGestor.models.Usuario;
import com.newgaming.votacionGestor.services.JuradoService;
import com.newgaming.votacionGestor.services.UsuarioService;
import com.newgaming.votacionGestor.services.VotacionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los jurados
 * dentro del sistema de votación. Permite obtener información de un jurado y
 * consultar las votaciones realizadas por el jurado autenticado.
 *
 * <p>El funcionamiento general del controlador se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Expone endpoints bajo la ruta base <code>/api/jurados</code>.</li>
 *     <li>Permite obtener información detallada de un jurado mediante su ID.</li>
 *     <li>Permite que un jurado autenticado consulte sus propias votaciones.</li>
 *     <li>Utiliza <code>JuradoService</code> y <code>VotacionService</code> para la lógica de negocio.</li>
 * </ol>
 *
 * <p>Este controlador facilita la interacción de los jurados con el sistema,
 * permitiéndoles acceder a su información y a su historial de votaciones.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
@RestController
@RequestMapping("/api/jurados")
public class JuradoController {

    private final JuradoService juradoService;
    private final VotacionService votacionService;
    private final UsuarioService usuarioService;

    /**
     * Constructor por parámetros.
     * Inicializa el controlador con los servicios necesarios para gestionar
     * jurados, votaciones y operaciones relacionadas con usuarios.
     *
     * <ol>
     *     <li>Asigna la dependencia <code>JuradoService</code>.</li>
     *     <li>Asigna la dependencia <code>VotacionService</code>.</li>
     *     <li>Asigna la dependencia <code>UsuarioService</code>.</li>
     * </ol>
     *
     * @since v0.1
     * @param juradoService servicio encargado de la gestión de jurados.
     * @param votacionService servicio encargado de la gestión de votaciones.
     * @param usuarioService servicio encargado de la gestión de usuarios.
     */
    public JuradoController(JuradoService juradoService, VotacionService votacionService, UsuarioService usuarioService) {
        this.juradoService = juradoService;
        this.votacionService = votacionService;
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para obtener la información de un jurado mediante su ID.
     * Solo accesible para usuarios con rol ADMIN.
     *
     * <ol>
     *     <li>Recibe el ID del jurado mediante la URL.</li>
     *     <li>Solicita al servicio obtener la entidad <code>Jurado</code> correspondiente.</li>
     *     <li>Convierte la entidad en un objeto <code>JuradoDTO</code>.</li>
     *     <li>Devuelve el DTO como respuesta.</li>
     * </ol>
     *
     * @since v0.1
     * @param id identificador del jurado.
     * @return DTO con la información del jurado solicitado.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<JuradoDTO> obtenerJurado(@PathVariable Long id) {
        return ResponseEntity.ok(JuradoDTO.fromEntity(juradoService.obtenerPorId(id)));
    }

    /**
     * Endpoint para obtener las votaciones realizadas por el jurado autenticado.
     * Solo accesible para usuarios con rol JURADO.
     *
     * <ol>
     *     <li>Obtiene el username del usuario autenticado desde el contexto de seguridad.</li>
     *     <li>Recupera la entidad <code>Usuario</code> asociada a dicho username.</li>
     *     <li>Solicita al servicio obtener todas las votaciones realizadas por ese jurado.</li>
     *     <li>Convierte cada votación en un objeto <code>VotacionDTO</code>.</li>
     *     <li>Devuelve la lista de votaciones como respuesta.</li>
     * </ol>
     *
     * @since 0.1
     * @param auth objeto de autenticación que contiene el usuario autenticado.
     * @return lista de votaciones realizadas por el jurado autenticado.
     */
    @PreAuthorize("hasRole('JURADO')")
    @GetMapping("/mis-votaciones")
    public ResponseEntity<List<VotacionDTO>> misVotaciones(Authentication auth) {

        String username = (String) auth.getPrincipal();

        Usuario jurado = usuarioService.buscarPorUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        List<VotacionDTO> lista = votacionService
                    .obtenerPorJurado(jurado.getIdUsuario())
                    .stream()
                    .map(VotacionDTO::fromEntity)
                    .toList();

        return ResponseEntity.ok(lista);
    }
}
