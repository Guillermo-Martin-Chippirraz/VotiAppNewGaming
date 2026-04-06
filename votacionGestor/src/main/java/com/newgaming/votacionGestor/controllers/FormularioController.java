package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.DTO.CrearFormularioDTO;
import com.newgaming.votacionGestor.DTO.EditarFormularioDTO;
import com.newgaming.votacionGestor.models.Administrador;
import com.newgaming.votacionGestor.models.Formulario;
import com.newgaming.votacionGestor.services.AdministradorService;
import com.newgaming.votacionGestor.services.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
 * @version v0.3
 */
@RestController
@RequestMapping("/api/formularios")
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    @Autowired
    private AdministradorService administradorService;

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
     * Obtiene un formulario por su identificador.
     *
     * <p>Devuelve el formulario asociado al {@code idFormulario} indicado. Si el
     * formulario no existe, se responde con un estado 404. La operación está
     * disponible para cualquier usuario autenticado.</p>
     *
     * @since v0.3
     * @param idFormulario identificador del formulario a consultar.
     * @return el formulario solicitado.
     */
    @GetMapping("/{idFormulario}")
    public ResponseEntity<Formulario> obtenerPorId(@PathVariable Long idFormulario) {
        return ResponseEntity.ok(formularioService.obtenerPorId(idFormulario));
    }

    /**
     * Obtiene la lista completa de formularios registrados.
     *
     * <p>Devuelve todos los formularios existentes en el sistema. La operación está
     * disponible para cualquier usuario autenticado y responde con estado 200
     * junto con la lista de formularios.</p>
     *
     * @since v0.3
     * @return lista de todos los formularios almacenados.
     */
    @GetMapping
    public ResponseEntity<List<Formulario>> obtenerTodos() {
        return ResponseEntity.ok(formularioService.obtenerTodos());
    }

    /**
     * Actualiza los datos de un formulario existente.
     *
     * <p>Solo un administrador puede modificar un formulario. Si el formulario no
     * existe, se devuelve un estado 404. Si la actualización se realiza
     * correctamente, se devuelve el formulario actualizado con estado 200.</p>
     *
     * @since v0.3
     * @param idFormulario identificador del formulario a actualizar.
     * @param dto datos que se desean modificar en el formulario.
     * @return el formulario actualizado.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{idFormulario}")
    public ResponseEntity<Formulario> editarFormulario(
            @PathVariable Long idFormulario,
            @RequestBody EditarFormularioDTO dto) {

        return ResponseEntity.ok(formularioService.editarFormulario(idFormulario, dto));
    }

    /**
     * Elimina un formulario del sistema.
     *
     * <p>Esta operación solo puede realizarla un administrador. Si el formulario no
     * existe, se devuelve un estado 404. Si la eliminación se completa
     * correctamente, se devuelve un estado 204 (sin contenido).</p>
     *
     * @since v0.3
     * @param idFormulario identificador del formulario a eliminar.
     * @return respuesta sin contenido si la eliminación fue exitosa.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idFormulario}")
    public ResponseEntity<Void> eliminarFormulario(@PathVariable Long idFormulario) {
        formularioService.eliminarFormulario(idFormulario);
        return ResponseEntity.noContent().build();
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
