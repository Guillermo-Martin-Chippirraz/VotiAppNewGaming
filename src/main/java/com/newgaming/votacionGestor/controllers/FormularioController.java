package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.models.Formulario;
import com.newgaming.votacionGestor.services.FormularioService;
import org.springframework.http.ResponseEntity;
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
 * @version 0.1
 */
@RestController
@RequestMapping("/api/formularios")
public class FormularioController {

    private final FormularioService formularioService;

    /**
     * Constructor por parámetros.
     * Inicializa el controlador con el servicio necesario para gestionar formularios.
     *
     * <ol>
     *     <li>Asigna la dependencia <code>FormularioService</code>.</li>
     *     <li>Permite acceder a la lógica de negocio desde los endpoints.</li>
     * </ol>
     *
     * @since 0.1
     * @param formularioService servicio encargado de la gestión de formularios.
     */
    public FormularioController(FormularioService formularioService) {
        this.formularioService = formularioService;
    }

    /**
     * Endpoint para crear un nuevo formulario.
     *
     * <ol>
     *     <li>Recibe un objeto Formulario desde el cuerpo de la petición.</li>
     *     <li>Envía la entidad al servicio para ser registrada en la base de datos.</li>
     *     <li>Devuelve el formulario creado como respuesta.</li>
     * </ol>
     *
     * @since 0.1
     * @param formulario entidad Formulario a registrar.
     * @return formulario creado.
     */
    @PostMapping
    public ResponseEntity<Formulario> crearFormulario(@RequestBody Formulario formulario) {
        return ResponseEntity.ok(formularioService.crearFormulario(formulario));
    }

    /**
     * Endpoint para obtener el formulario asociado a un evento.
     *
     * <ol>
     *     <li>Recibe el ID del evento mediante la URL.</li>
     *     <li>Solicita al servicio obtener el formulario correspondiente.</li>
     *     <li>Devuelve el formulario encontrado.</li>
     * </ol>
     *
     * @since 0.1
     * @param idEvento identificador del evento.
     * @return formulario asociado al evento.
     */
    @GetMapping("/evento/{idEvento}")
    public ResponseEntity<Formulario> obtenerPorEvento(@PathVariable Long idEvento) {
        return ResponseEntity.ok(formularioService.obtenerPorEvento(idEvento));
    }
}
