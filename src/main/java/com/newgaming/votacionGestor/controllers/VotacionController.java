package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.models.Votacion;
import com.newgaming.votacionGestor.services.VotacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
 * @version 0.1
 */
@RestController
@RequestMapping("/api/votaciones")
public class VotacionController {

    private final VotacionService votacionService;

    /**
     * Constructor por parámetros.
     * Inicializa el controlador con el servicio necesario para gestionar votaciones.
     *
     * <ol>
     *     <li>Asigna la dependencia <code>VotacionService</code>.</li>
     *     <li>Permite acceder a la lógica de negocio desde los endpoints.</li>
     * </ol>
     *
     * @since 0.1
     * @param votacionService servicio encargado de la gestión de votaciones.
     */
    public VotacionController(VotacionService votacionService) {
        this.votacionService = votacionService;
    }

    /**
     * Endpoint para registrar una nueva votación.
     *
     * <ol>
     *     <li>Recibe una entidad Votacion desde el cuerpo de la petición.</li>
     *     <li>Envía la entidad al servicio para ser registrada en la base de datos.</li>
     *     <li>Devuelve la votación creada como respuesta.</li>
     * </ol>
     *
     * @since 0.1
     * @param votacion entidad Votacion a registrar.
     * @return votación creada.
     */
    @PostMapping
    public ResponseEntity<Votacion> crearVotacion(@RequestBody Votacion votacion) {
        return ResponseEntity.ok(votacionService.crearVotacion(votacion));
    }

    /**
     * Endpoint para obtener todas las votaciones asociadas a un evento.
     *
     * <ol>
     *     <li>Recibe el ID del evento mediante la URL.</li>
     *     <li>Solicita al servicio obtener todas las votaciones del evento.</li>
     *     <li>Devuelve la lista de votaciones encontradas.</li>
     * </ol>
     *
     * @since 0.1
     * @param idEvento identificador del evento.
     * @return lista de votaciones asociadas al evento.
     */
    @GetMapping("/evento/{idEvento}")
    public ResponseEntity<List<Votacion>> obtenerPorEvento(@PathVariable Long idEvento) {
        return ResponseEntity.ok(votacionService.obtenerPorEvento(idEvento));
    }

    /**
     * Endpoint para obtener todas las votaciones asociadas a una empresa.
     *
     * <ol>
     *     <li>Recibe el ID de la empresa mediante la URL.</li>
     *     <li>Solicita al servicio obtener todas las votaciones de la empresa.</li>
     *     <li>Devuelve la lista de votaciones encontradas.</li>
     * </ol>
     *
     * @since 0.1
     * @param idEmpresa identificador de la empresa.
     * @return lista de votaciones asociadas a la empresa.
     */
    @GetMapping("/empresa/{idEmpresa}")
    public ResponseEntity<List<Votacion>> obtenerPorEmpresa(@PathVariable Long idEmpresa) {
        return ResponseEntity.ok(votacionService.obtenerPorEmpresa(idEmpresa));
    }

    /**
     * Endpoint para obtener todas las votaciones realizadas por un jurado.
     *
     * <ol>
     *     <li>Recibe el ID del jurado mediante la URL.</li>
     *     <li>Solicita al servicio obtener todas las votaciones realizadas por dicho jurado.</li>
     *     <li>Devuelve la lista de votaciones encontradas.</li>
     * </ol>
     *
     * @since 0.1
     * @param idJurado identificador del jurado.
     * @return lista de votaciones realizadas por el jurado.
     */
    @GetMapping("/jurado/{idJurado}")
    public ResponseEntity<List<Votacion>> obtenerPorJurado(@PathVariable Long idJurado) {
        return ResponseEntity.ok(votacionService.obtenerPorJurado(idJurado));
    }
}
