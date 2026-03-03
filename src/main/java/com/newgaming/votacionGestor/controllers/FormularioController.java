package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.DTO.CrearFormularioDTO;
import com.newgaming.votacionGestor.models.Administrador;
import com.newgaming.votacionGestor.models.Formulario;
import com.newgaming.votacionGestor.services.AdministradorService;
import com.newgaming.votacionGestor.services.FormularioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los formularios
 * utilizados en los eventos del sistema de votación. Permite crear nuevos formularios
 * y obtener el formulario asociado a un evento específico.
 *
 * <p>El funcionamiento general del controlador se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Expone endpoints bajo la ruta base <code>/api/formularios</code>.</li>
 *     <li>Permite registrar nuevos formularios en el sistema.</li>
 *     <li>Permite obtener el formulario asociado a un evento concreto.</li>
 *     <li>Utiliza <code>FormularioService</code> para la lógica de negocio.</li>
 * </ol>
 *
 * <p>Este controlador facilita la gestión de los formularios que los jurados
 * utilizarán para evaluar a las empresas en los eventos.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
@RestController
@RequestMapping("/api/formularios")
public class FormularioController {

    private final FormularioService formularioService;
    private final AdministradorService administradorService;

    /**
     * Constructor por parámetros.
     * Inicializa el controlador con los servicios necesarios para gestionar
     * formularios y operaciones realizadas por administradores.
     *
     * <ol>
     *     <li>Asigna la dependencia <code>FormularioService</code>.</li>
     *     <li>Asigna la dependencia <code>AdministradorService</code>.</li>
     *     <li>Permite acceder a la lógica de negocio desde los endpoints.</li>
     * </ol>
     *
     * @since v0.1
     * @param formularioService servicio encargado de la gestión de formularios.
     * @param administradorService servicio encargado de operaciones realizadas por administradores.
     */
    public FormularioController(FormularioService formularioService, AdministradorService administradorService) {
        this.formularioService = formularioService;
        this.administradorService = administradorService;
    }

    /**
     * Endpoint para crear un nuevo formulario.
     * Solo accesible para usuarios con rol ADMIN.
     *
     * <ol>
     *     <li>Obtiene el username del administrador autenticado desde el token JWT.</li>
     *     <li>Recupera la entidad <code>Administrador</code> asociada a dicho username.</li>
     *     <li>Recibe un DTO con los datos necesarios para crear un formulario.</li>
     *     <li>Solicita al servicio registrar el formulario en la base de datos.</li>
     *     <li>Devuelve el formulario creado como respuesta.</li>
     * </ol>
     *
     * @since v0.1
     * @param dto datos necesarios para registrar un nuevo formulario.
     * @return formulario creado.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Formulario> crearFormulario(@RequestBody CrearFormularioDTO dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Administrador admin = administradorService.obtenerPorUsername(username);

        Formulario creado = formularioService.crearFormulario(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    /**
     * Endpoint para obtener el formulario asociado a un evento.
     * Accesible para usuarios con rol ADMIN o JURADO.
     *
     * <ol>
     *     <li>Recibe el ID del evento mediante la URL.</li>
     *     <li>Solicita al servicio obtener el formulario vinculado a dicho evento.</li>
     *     <li>Devuelve el formulario encontrado como respuesta.</li>
     * </ol>
     *
     * @since 0.1
     * @param idEvento identificador del evento.
     * @return formulario asociado al evento.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'JURADO')")
    @GetMapping("/evento/{idEvento}")
    public ResponseEntity<Formulario> obtenerPorEvento(@PathVariable Long idEvento) {
        return ResponseEntity.ok(formularioService.obtenerPorEvento(idEvento));
    }
}
