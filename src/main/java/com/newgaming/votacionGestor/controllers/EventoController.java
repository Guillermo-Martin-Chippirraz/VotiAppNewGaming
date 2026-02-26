package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.models.Administrador;
import com.newgaming.votacionGestor.models.Evento;
import com.newgaming.votacionGestor.services.AdministradorService;
import com.newgaming.votacionGestor.services.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
 * @version 0.1
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
     * @since 0.1
     * @param eventoService servicio encargado de la gestión de eventos.
     * @param administradorService servicio encargado de la gestión de administradores.
     */
    public EventoController(EventoService eventoService, AdministradorService administradorService) {
        this.eventoService = eventoService;
        this.administradorService = administradorService;
    }

    /**
     * Endpoint para crear un nuevo evento.
     *
     * <ol>
     *     <li>Recibe un objeto Evento desde el cuerpo de la petición.</li>
     *     <li>Envía la entidad al servicio para ser registrada en la base de datos.</li>
     *     <li>Devuelve el evento creado como respuesta.</li>
     * </ol>
     *
     * @since 0.1
     * @param evento entidad Evento a registrar.
     * @return evento creado.
     */
    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento) {
        return ResponseEntity.ok(eventoService.crearEvento(evento));
    }

    /**
     * Endpoint para habilitar un evento.
     *
     * <ol>
     *     <li>Recibe el ID del evento y el ID del administrador mediante la URL.</li>
     *     <li>Obtiene el administrador desde la base de datos.</li>
     *     <li>Solicita al servicio habilitar el evento correspondiente.</li>
     *     <li>Devuelve el evento actualizado.</li>
     * </ol>
     *
     * @since 0.1
     * @param id identificador del evento.
     * @param idAdmin identificador del administrador que habilita el evento.
     * @return evento habilitado.
     */
    @PutMapping("/{id}/habilitar/{idAdmin}")
    public ResponseEntity<Evento> habilitarEvento(
            @PathVariable Long id,
            @PathVariable Long idAdmin) {

        Administrador admin = administradorService.obtenerPorId(idAdmin);
        return ResponseEntity.ok(eventoService.habilitarEvento(id, admin));
    }

    /**
     * Endpoint para deshabilitar un evento.
     *
     * <ol>
     *     <li>Recibe el ID del evento y el ID del administrador mediante la URL.</li>
     *     <li>Obtiene el administrador desde la base de datos.</li>
     *     <li>Solicita al servicio deshabilitar el evento correspondiente.</li>
     *     <li>Devuelve el evento actualizado.</li>
     * </ol>
     *
     * @since 0.1
     * @param id identificador del evento.
     * @param idAdmin identificador del administrador que deshabilita el evento.
     * @return evento deshabilitado.
     */
    @PutMapping("/{id}/deshabilitar/{idAdmin}")
    public ResponseEntity<Evento> deshabilitarEvento(
            @PathVariable Long id,
            @PathVariable Long idAdmin) {

        Administrador admin = administradorService.obtenerPorId(idAdmin);
        return ResponseEntity.ok(eventoService.deshabilitarEvento(id, admin));
    }
}
