package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.models.Empresa;
import com.newgaming.votacionGestor.models.Evento;
import com.newgaming.votacionGestor.services.EmpresaService;
import com.newgaming.votacionGestor.services.EventoService;
import org.springframework.http.ResponseEntity;
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
 * @version 0.1
 */
@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final EventoService eventoService;

    /**
     * Constructor por parámetros.
     * Inicializa el controlador con los servicios necesarios para gestionar
     * empresas y eventos.
     *
     * <ol>
     *     <li>Asigna la dependencia <code>EmpresaService</code>.</li>
     *     <li>Asigna la dependencia <code>EventoService</code>.</li>
     * </ol>
     *
     * @since 0.1
     * @param empresaService servicio encargado de la gestión de empresas.
     * @param eventoService servicio encargado de la gestión de eventos.
     */
    public EmpresaController(EmpresaService empresaService, EventoService eventoService) {
        this.empresaService = empresaService;
        this.eventoService = eventoService;
    }

    /**
     * Endpoint para crear una nueva empresa.
     *
     * <ol>
     *     <li>Recibe un objeto Empresa desde el cuerpo de la petición.</li>
     *     <li>Envía la entidad al servicio para ser registrada en la base de datos.</li>
     *     <li>Devuelve la empresa creada como respuesta.</li>
     * </ol>
     *
     * @since 0.1
     * @param empresa entidad Empresa a registrar.
     * @return empresa creada.
     */
    @PostMapping
    public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa empresa){
        return ResponseEntity.ok(empresaService.crearEmpresa(empresa));
    }

    /**
     * Endpoint para asignar una empresa a un evento.
     *
     * <ol>
     *     <li>Recibe los IDs de la empresa y del evento mediante la URL.</li>
     *     <li>Obtiene ambas entidades desde sus respectivos servicios.</li>
     *     <li>Asigna la empresa al evento mediante el servicio correspondiente.</li>
     *     <li>Devuelve un mensaje confirmando la operación.</li>
     * </ol>
     *
     * @since 0.1
     * @param idEmpresa identificador de la empresa.
     * @param idEvento identificador del evento.
     * @return mensaje indicando que la empresa fue asignada correctamente.
     */
    @PostMapping("/{idEmpresa}/asignar/{idEvento}")
    public ResponseEntity<String> asignarEmpresaAEvento(
            @PathVariable Long idEmpresa,
            @PathVariable Long idEvento) {

        Empresa empresa = empresaService.obtenerPorId(idEmpresa);
        Evento evento = eventoService.obtenerPorId(idEvento);

        empresaService.asignarEmpresaAEvento(empresa, evento);

        return ResponseEntity.ok("Empresa asignada al evento");
    }
}
