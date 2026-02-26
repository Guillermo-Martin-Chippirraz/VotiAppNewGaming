package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.DTO.CrearUsuarioDTO;
import com.newgaming.votacionGestor.DTO.UsuarioDTO;
import com.newgaming.votacionGestor.models.Usuario;
import com.newgaming.votacionGestor.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los usuarios
 * dentro del sistema de votación. Permite registrar nuevos usuarios y obtener
 * información detallada de un usuario mediante su identificador.
 *
 * <p>El funcionamiento general del controlador se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Expone endpoints bajo la ruta base <code>/api/usuarios</code>.</li>
 *     <li>Permite registrar nuevos usuarios mediante un DTO validado.</li>
 *     <li>Permite obtener información de un usuario específico.</li>
 *     <li>Utiliza <code>UsuarioService</code> para la lógica de negocio.</li>
 * </ol>
 *
 * <p>Este controlador centraliza la gestión de usuarios dentro del sistema,
 * garantizando un acceso seguro y estructurado a la información.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Constructor por parámetros.
     * Inicializa el controlador con el servicio necesario para gestionar usuarios.
     *
     * <ol>
     *     <li>Asigna la dependencia <code>UsuarioService</code>.</li>
     *     <li>Permite acceder a la lógica de negocio desde los endpoints.</li>
     * </ol>
     *
     * @since 0.1
     * @param usuarioService servicio encargado de la gestión de usuarios.
     */
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para crear un nuevo usuario.
     *
     * <ol>
     *     <li>Recibe un DTO validado con los datos necesarios para crear un usuario.</li>
     *     <li>Convierte el DTO en una entidad Usuario.</li>
     *     <li>Solicita al servicio registrar el nuevo usuario en la base de datos.</li>
     *     <li>Devuelve un DTO con la información del usuario creado.</li>
     * </ol>
     *
     * @since 0.1
     * @param dto DTO con los datos del usuario a registrar.
     * @return DTO con la información del usuario creado.
     */
    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@Valid @RequestBody CrearUsuarioDTO dto) {
        Usuario nuevo = usuarioService.crearUsuario(dto.toEntity());
        return ResponseEntity.ok(UsuarioDTO.fromEntity(nuevo));
    }

    /**
     * Endpoint para obtener la información de un usuario mediante su ID.
     *
     * <ol>
     *     <li>Recibe el ID del usuario mediante la URL.</li>
     *     <li>Solicita al servicio obtener la entidad correspondiente.</li>
     *     <li>Convierte la entidad en un DTO para su devolución.</li>
     * </ol>
     *
     * @since 0.1
     * @param id identificador del usuario.
     * @return DTO con la información del usuario solicitado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id) {
        Usuario u = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(UsuarioDTO.fromEntity(u));
    }
}
