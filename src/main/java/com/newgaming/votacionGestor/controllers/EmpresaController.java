package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.models.Administrador;
import com.newgaming.votacionGestor.models.Empresa;
import com.newgaming.votacionGestor.services.AdministradorService;
import com.newgaming.votacionGestor.services.EmpresaService;
import com.newgaming.votacionGestor.services.EventoEmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con las empresas
 * dentro del sistema de votación. Permite crear nuevas empresas y asignarlas a eventos.
 *
 * <p>El funcionamiento general del controlador se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Expone endpoints bajo la ruta base <code>/api/empresa</code>.</li>
 *     <li>Permite registrar nuevas empresas en el sistema.</li>
 *     <li>Permite asignar empresas a eventos específicos.</li>
 *     <li>Utiliza los servicios <code>EmpresaService</code> y <code>EventoService</code>
 *         para la lógica de negocio.</li>
 * </ol>
 *
 * <p>Este controlador facilita la gestión de empresas participantes en los eventos
 * evaluados por los jurados.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final EventoEmpresaService eventoEmpresaService;
    private final AdministradorService administradorService;

    /**
     * Constructor por parámetros.
     * Inicializa el controlador con los servicios necesarios para gestionar
     * empresas, asignaciones a eventos y operaciones realizadas por administradores.
     *
     * <ol>
     *     <li>Asigna la dependencia <code>EmpresaService</code>.</li>
     *     <li>Asigna la dependencia <code>EventoEmpresaService</code>.</li>
     *     <li>Asigna la dependencia <code>AdministradorService</code>.</li>
     * </ol>
     *
     * @since v0.1
     * @param empresaService servicio encargado de la gestión de empresas.
     * @param eventoService servicio encargado de la gestión de asignaciones empresa–evento.
     * @param administradorService servicio encargado de operaciones realizadas por administradores.
     */
    public EmpresaController(EmpresaService empresaService, EventoEmpresaService eventoService, AdministradorService administradorService) {
        this.empresaService = empresaService;
        this.eventoEmpresaService = eventoService;
        this.administradorService = administradorService;
    }

    /**
     * Endpoint para crear una nueva empresa.
     * Solo accesible para usuarios con rol ADMIN.
     *
     * <ol>
     *     <li>Obtiene el username del administrador autenticado desde el token JWT.</li>
     *     <li>Recupera la entidad <code>Administrador</code> asociada a dicho username.</li>
     *     <li>Asigna al administrador como responsable del registro de la empresa.</li>
     *     <li>Envía la entidad al servicio para ser almacenada en la base de datos.</li>
     *     <li>Devuelve la empresa creada como respuesta.</li>
     * </ol>
     *
     * @since v0.1
     * @param empresa entidad Empresa a registrar.
     * @return empresa creada.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa empresa){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(">>> Username del token = " + username);
        Administrador admin = administradorService.obtenerPorUsername(username);
        empresa.setAdminRegistro(admin);
        return ResponseEntity.ok(empresaService.crearEmpresa(empresa));
    }

    /**
     * Endpoint para asignar una empresa a un evento.
     * Solo accesible para usuarios con rol ADMIN.
     *
     * <ol>
     *     <li>Obtiene el username del administrador autenticado desde el token JWT.</li>
     *     <li>Recupera la entidad <code>Administrador</code> asociada a dicho username.</li>
     *     <li>Recibe los IDs de empresa y evento mediante la URL.</li>
     *     <li>Solicita al servicio correspondiente la asignación empresa–evento.</li>
     *     <li>Devuelve un mensaje confirmando la operación.</li>
     * </ol>
     *
     * @since v0.1
     * @param idEmpresa identificador de la empresa.
     * @param idEvento identificador del evento.
     * @return mensaje indicando que la empresa fue asignada correctamente.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{idEmpresa}/asignar/{idEvento}")
    public ResponseEntity<?> asignarEmpresaAEvento(
            @PathVariable Long idEmpresa,
            @PathVariable Long idEvento) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Administrador admin = administradorService.obtenerPorUsername(username);

        eventoEmpresaService.asignarEmpresa(idEvento, idEmpresa);

        return ResponseEntity.ok("Empresa asignada correctamente al evento");
    }
}
