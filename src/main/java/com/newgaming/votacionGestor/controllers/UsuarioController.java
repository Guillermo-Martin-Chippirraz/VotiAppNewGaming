package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.DTO.CrearUsuarioDTO;
import com.newgaming.votacionGestor.DTO.UsuarioDTO;
import com.newgaming.votacionGestor.models.Administrador;
import com.newgaming.votacionGestor.models.Usuario;
import com.newgaming.votacionGestor.services.AdministradorService;
import com.newgaming.votacionGestor.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
 * @version v0.2
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AdministradorService administradorService;

    /**
     * Constructor por parámetros.
     * Inicializa el controlador con los servicios necesarios para gestionar
     * usuarios y operaciones realizadas por administradores.
     *
     * <ol>
     *     <li>Asigna la dependencia <code>UsuarioService</code>.</li>
     *     <li>Asigna la dependencia <code>AdministradorService</code>.</li>
     *     <li>Permite acceder a la lógica de negocio desde los endpoints.</li>
     * </ol>
     *
     * @since v0.1
     * @param usuarioService servicio encargado de la gestión de usuarios.
     * @param administradorService servicio encargado de operaciones realizadas por administradores.
     */
    public UsuarioController(UsuarioService usuarioService, AdministradorService administradorService){
        this.usuarioService = usuarioService;
        this.administradorService = administradorService;
    }

    /**
     * Endpoint para crear un nuevo usuario.
     * Solo accesible para usuarios con rol ADMIN.
     *
     * <ol>
     *     <li>Obtiene el username del administrador autenticado desde el token JWT.</li>
     *     <li>Recupera la entidad <code>Administrador</code> asociada a dicho username.</li>
     *     <li>Recibe un DTO validado con los datos necesarios para registrar un usuario.</li>
     *     <li>Solicita al servicio crear el nuevo usuario en la base de datos.</li>
     *     <li>Convierte la entidad resultante en un objeto <code>UsuarioDTO</code>.</li>
     *     <li>Devuelve el DTO con la información del usuario creado.</li>
     * </ol>
     *
     * @since v0.1
     * @param dto DTO con los datos del usuario a registrar.
     * @return DTO con la información del usuario creado.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@Valid @RequestBody CrearUsuarioDTO dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Administrador admin = administradorService.obtenerPorUsername(username);

        Usuario nuevo = usuarioService.crearUsuario(dto, admin);
        return ResponseEntity.ok(UsuarioDTO.fromEntity(nuevo));
    }

    /**
     * Endpoint para obtener la información de un usuario mediante su ID.
     * Accesible para ADMIN, JURADO y USUARIO, con restricciones:
     * <ul>
     *     <li>Un ADMIN puede ver cualquier usuario.</li>
     *     <li>Un usuario no administrador solo puede ver su propia información.</li>
     * </ul>
     *
     * <ol>
     *     <li>Obtiene el username del usuario autenticado desde el token JWT.</li>
     *     <li>Recupera la entidad <code>Usuario</code> asociada a dicho username.</li>
     *     <li>Verifica si el solicitante tiene permiso para ver el usuario solicitado.</li>
     *     <li>Solicita al servicio obtener la entidad correspondiente al ID recibido.</li>
     *     <li>Convierte la entidad en un objeto <code>UsuarioDTO</code>.</li>
     *     <li>Devuelve el DTO como respuesta.</li>
     * </ol>
     *
     * @since v0.1
     * @param id identificador del usuario solicitado.
     * @return DTO con la información del usuario.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'JURADO', 'USUARIO')")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario solicitante = usuarioService.buscarPorUsername(username).orElseThrow();

        if (!solicitante.getRol().equals("ADMIN") && !solicitante.getIdUsuario().equals(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes permiso para ver este usuario");
        }

        Usuario u = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(UsuarioDTO.fromEntity(u));
    }
}
