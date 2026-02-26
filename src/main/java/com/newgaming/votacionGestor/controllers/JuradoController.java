package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.DTO.JuradoDTO;
import com.newgaming.votacionGestor.DTO.VotacionDTO;
import com.newgaming.votacionGestor.models.Jurado;
import com.newgaming.votacionGestor.models.Usuario;
import com.newgaming.votacionGestor.services.JuradoService;
import com.newgaming.votacionGestor.services.VotacionService;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @version 0.1
 */
@RestController
@RequestMapping("/api/jurados")
public class JuradoController {

    private final JuradoService juradoService;
    private final VotacionService votacionService;

    /**
     * Constructor por parámetros.
     * Inicializa el controlador con los servicios necesarios para gestionar
     * jurados y votaciones.
     *
     * <ol>
     *     <li>Asigna la dependencia <code>JuradoService</code>.</li>
     *     <li>Asigna la dependencia <code>VotacionService</code>.</li>
     * </ol>
     *
     * @since 0.1
     * @param juradoService servicio encargado de la gestión de jurados.
     * @param votacionService servicio encargado de la gestión de votaciones.
     */
    public JuradoController(JuradoService juradoService, VotacionService votacionService) {
        this.juradoService = juradoService;
        this.votacionService = votacionService;
    }

    /**
     * Endpoint para obtener la información de un jurado mediante su ID.
     *
     * <ol>
     *     <li>Recibe el ID del jurado mediante la URL.</li>
     *     <li>Solicita al servicio obtener la entidad correspondiente.</li>
     *     <li>Convierte la entidad en un DTO para su devolución.</li>
     * </ol>
     *
     * @since 0.1
     * @param id identificador del jurado.
     * @return DTO con la información del jurado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<JuradoDTO> obtenerJurado(@PathVariable Long id) {
        return ResponseEntity.ok(JuradoDTO.fromEntity(juradoService.obtenerPorId(id)));
    }

    /**
     * Endpoint para obtener las votaciones realizadas por el jurado autenticado.
     * Solo accesible para usuarios con rol JURADO.
     *
     * <ol>
     *     <li>Obtiene el usuario autenticado desde el contexto de seguridad.</li>
     *     <li>Verifica que el usuario no sea nulo.</li>
     *     <li>Solicita al servicio obtener todas las votaciones asociadas al jurado.</li>
     *     <li>Convierte cada votación en un DTO para su devolución.</li>
     * </ol>
     *
     * @since 0.1
     * @param auth objeto de autenticación que contiene el usuario autenticado.
     * @return lista de votaciones realizadas por el jurado.
     */
    @PreAuthorize("hasRole('JURADO')")
    @GetMapping("/mis-votaciones")
    public ResponseEntity<List<VotacionDTO>> misVotaciones(Authentication auth) {

        Usuario jurado = (Usuario) auth.getPrincipal();
        List<VotacionDTO> lista = null;

        if (jurado != null) {
            lista = votacionService
                    .obtenerPorJurado(jurado.getIdUsuario())
                    .stream()
                    .map(VotacionDTO::fromEntity)
                    .toList();
        }

        return ResponseEntity.ok(lista);
    }
}
