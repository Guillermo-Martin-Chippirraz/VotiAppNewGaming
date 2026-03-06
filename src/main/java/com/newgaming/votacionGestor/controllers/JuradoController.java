package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.DTO.EditarJuradoDTO;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
 * @version v0.3
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
     * Obtiene la lista completa de jurados registrados en el sistema.
     *
     * <p>Esta operación solo puede realizarla un administrador. Devuelve todos los
     * jurados existentes, transformados a {@link JuradoDTO} para evitar exponer
     * información sensible. Si no existen jurados, se devuelve una lista vacía
     * con estado 200.</p>
     *
     * @since v0.3
     * @return lista de jurados en formato DTO.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<JuradoDTO>> obtenerTodos() {

        List<JuradoDTO> lista = new ArrayList<>();
        List<Jurado> jurados = juradoService.obtenerTodos();

        for (Jurado jurado : jurados) {
            lista.add(JuradoDTO.fromEntity(jurado));
        }

        return ResponseEntity.ok(lista);
    }

    /**
     * Permite que un jurado edite únicamente sus propios datos.
     *
     * <p>La verificación se realiza mediante la expresión de seguridad
     * {@code @seguridadJurado.esElMismoJurado(#idJurado)}, que garantiza que el
     * usuario autenticado coincide con el jurado cuyo identificador se recibe en
     * la ruta. Si el jurado no existe, se devuelve un estado 404. Si la edición
     * es válida, se devuelve el jurado actualizado con estado 200.</p>
     *
     * @since v0.3
     * @param idJurado identificador del jurado que desea modificar sus datos.
     * @param dto datos a actualizar.
     * @return el jurado actualizado en formato DTO.
     */
    @PreAuthorize("@seguridadJurado.esElMismoJurado(#idJurado)")
    @PutMapping("/{idJurado}")
    public ResponseEntity<JuradoDTO> editarMisDatos(
            @PathVariable Long idJurado,
            @RequestBody EditarJuradoDTO dto) {

        return ResponseEntity.ok(JuradoDTO.fromEntity(juradoService.editarMisDatos(idJurado, dto)));
    }

    /**
     * Elimina un jurado del sistema.
     *
     * <p>Solo un administrador puede realizar esta acción. Si el jurado no existe,
     * se devuelve un estado 404. Si la eliminación se completa correctamente,
     * se devuelve un estado 204 (sin contenido). La eliminación implica retirar
     * al jurado del sistema de forma permanente.</p>
     *
     * @since v0.3
     * @param idJurado identificador del jurado a eliminar.
     * @return respuesta sin contenido si la eliminación fue exitosa.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idJurado}")
    public ResponseEntity<Void> eliminarJurado(@PathVariable Long idJurado){
        juradoService.eliminarJurado(idJurado);
        return ResponseEntity.noContent().build();
    }

    /**
     * Permite que un jurado se retire voluntariamente del sistema.
     *
     * <p>La operación solo puede realizarla el propio jurado, verificado mediante
     * la expresión de seguridad {@code #idJurado == authentication.principal.idUsuario}.
     * Si el jurado no existe, se devuelve un estado 404. Si la retirada se completa,
     * se devuelve el jurado actualizado con estado 200.</p>
     *
     * @since v0.3
     * @param idJurado identificador del jurado que desea retirarse.
     * @return el jurado actualizado tras marcarse como retirado.
     */
    @PreAuthorize("#idJurado == authentication.principal.idUsuario")
    @PutMapping("/{idJurado}/retirarse")
    public ResponseEntity<JuradoDTO> retirarse(@PathVariable Long idJurado) {
        return ResponseEntity.ok(JuradoDTO.fromEntity(juradoService.retirarse(idJurado)));
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
