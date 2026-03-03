package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.DTO.CrearEventoDTO;
import com.newgaming.votacionGestor.DTO.EventoDTO;
import com.newgaming.votacionGestor.models.Administrador;
import com.newgaming.votacionGestor.models.Evento;
import com.newgaming.votacionGestor.services.AdministradorService;
import com.newgaming.votacionGestor.services.EventoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los eventos
 * dentro del sistema de votación. Permite crear eventos y cambiar su estado
 * (habilitado o deshabilitado) mediante la intervención de un administrador.
 *
 * <p>El funcionamiento general del controlador se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Expone endpoints bajo la ruta base <code>/api/eventos</code>.</li>
 *     <li>Permite registrar nuevos eventos en el sistema.</li>
 *     <li>Permite habilitar o deshabilitar eventos según la acción del administrador.</li>
 *     <li>Utiliza los servicios <code>EventoService</code> y <code>AdministradorService</code>
 *         para la lógica de negocio.</li>
 * </ol>
 *
 * <p>Este controlador facilita la gestión del ciclo de vida de los eventos
 * que serán evaluados por los jurados.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final AdministradorService administradorService;

    /**
     * Constructor por parámetros.
     * Inicializa el controlador con los servicios necesarios para gestionar
     * eventos y administradores.
     *
     * <ol>
     *     <li>Asigna la dependencia <code>EventoService</code>.</li>
     *     <li>Asigna la dependencia <code>AdministradorService</code>.</li>
     * </ol>
     *
     * @since v0.1
     * @param eventoService servicio encargado de la gestión de eventos.
     * @param administradorService servicio encargado de la gestión de administradores.
     */
    public EventoController(EventoService eventoService, AdministradorService administradorService) {
        this.eventoService = eventoService;
        this.administradorService = administradorService;
    }

    /**
     * Endpoint para crear un nuevo evento.
     * Solo accesible para usuarios con rol ADMIN.
     *
     * <ol>
     *     <li>Obtiene el username del administrador autenticado desde el token JWT.</li>
     *     <li>Recupera la entidad <code>Administrador</code> asociada a dicho username.</li>
     *     <li>Convierte el DTO recibido en una entidad <code>Evento</code>.</li>
     *     <li>Asigna al administrador como creador del evento.</li>
     *     <li>Registra las fechas de creación y actualización.</li>
     *     <li>Envía la entidad al servicio para ser almacenada en la base de datos.</li>
     *     <li>Devuelve un DTO con la información del evento creado.</li>
     * </ol>
     *
     * @since v0.1
     * @param dto datos necesarios para crear un nuevo evento.
     * @return objeto <code>EventoDTO</code> con la información del evento registrado.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EventoDTO> crearEvento(
            @Valid @RequestBody CrearEventoDTO dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Administrador admin = administradorService.obtenerPorUsername(username);

        Evento evento = dto.toEntity();
        evento.setUsuarioCreador(admin);
        evento.setCreadoEn(LocalDateTime.now());
        evento.setActualizadoEn(LocalDateTime.now());

        Evento creado = eventoService.crearEvento(evento);

        return ResponseEntity.status(HttpStatus.CREATED).body(EventoDTO.fromEntity(creado));
    }


    /**
     * Endpoint para habilitar un evento.
     * Solo accesible para usuarios con rol ADMIN.
     *
     * <ol>
     *     <li>Obtiene el username del administrador autenticado desde el token JWT.</li>
     *     <li>Recupera la entidad <code>Administrador</code> asociada a dicho username.</li>
     *     <li>Solicita al servicio habilitar el evento indicado por su ID.</li>
     *     <li>Devuelve el evento actualizado tras la operación.</li>
     * </ol>
     *
     * @since v0.1
     * @param id identificador del evento a habilitar.
     * @return evento habilitado.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/habilitar")
    public ResponseEntity<Evento> habilitarEvento(
            @PathVariable Long id
    ) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Administrador admin = administradorService.obtenerPorUsername(username);

        return ResponseEntity.ok(eventoService.habilitarEvento(id, admin));
    }

    /**
     * Endpoint para deshabilitar un evento.
     * Solo accesible para usuarios con rol ADMIN.
     *
     * <ol>
     *     <li>Obtiene el username del administrador autenticado desde el token JWT.</li>
     *     <li>Recupera la entidad <code>Administrador</code> asociada a dicho username.</li>
     *     <li>Solicita al servicio deshabilitar el evento indicado por su ID.</li>
     *     <li>Devuelve el evento actualizado tras la operación.</li>
     * </ol>
     *
     * @since 0.1
     * @param id identificador del evento a deshabilitar.
     * @return evento deshabilitado.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/deshabilitar")
    public ResponseEntity<Evento> deshabilitarEvento(
            @PathVariable Long id
    ) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Administrador admin = administradorService.obtenerPorUsername(username);

        return ResponseEntity.ok(eventoService.deshabilitarEvento(id, admin));
    }
}
