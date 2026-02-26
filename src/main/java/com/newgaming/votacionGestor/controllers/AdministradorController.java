package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.DTO.CrearJuradoDTO;
import com.newgaming.votacionGestor.DTO.JuradoDTO;
import com.newgaming.votacionGestor.models.Administrador;
import com.newgaming.votacionGestor.models.Jurado;
import com.newgaming.votacionGestor.services.AdministradorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador encargado de gestionar las acciones administrativas del sistema.
 * Permite a los administradores registrar jurados y realizar operaciones
 * relacionadas con la gestión de usuarios con rol JURADO.
 *
 * <p>El funcionamiento general del controlador se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Expone endpoints bajo la ruta base <code>/api/admin</code>.</li>
 *     <li>Restringe el acceso a usuarios con rol ADMIN mediante anotaciones de seguridad.</li>
 *     <li>Permite registrar nuevos jurados asociados a un administrador concreto.</li>
 *     <li>Utiliza DTOs para recibir y devolver información de forma segura.</li>
 *     <li>Se apoya en <code>AdministradorService</code> para la lógica de negocio.</li>
 * </ol>
 *
 * <p>Este controlador garantiza que solo administradores autorizados puedan crear
 * nuevos jurados dentro del sistema.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@RestController
@RequestMapping("/api/admin")
public class AdministradorController {

    private final AdministradorService administradorService;

    /**
     * Constructor por parámetros.
     * Inicializa el controlador con el servicio necesario para gestionar
     * administradores y registrar jurados.
     *
     * <ol>
     *     <li>Asigna la dependencia <code>AdministradorService</code>.</li>
     *     <li>Permite acceder a la lógica de negocio desde los endpoints.</li>
     * </ol>
     *
     * @since 0.1
     * @param administradorService servicio encargado de la gestión de administradores.
     */
    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    /**
     * Endpoint para registrar un nuevo jurado.
     * Solo accesible para usuarios con rol ADMIN.
     *
     * <ol>
     *     <li>Recibe el ID del administrador que realiza el registro.</li>
     *     <li>Valida el DTO recibido mediante anotaciones de validación.</li>
     *     <li>Obtiene el administrador desde la base de datos.</li>
     *     <li>Convierte el DTO en una entidad Jurado.</li>
     *     <li>Registra el jurado mediante el servicio correspondiente.</li>
     *     <li>Devuelve un DTO con la información del jurado creado.</li>
     * </ol>
     *
     * @since 0.1
     * @param idAmin identificador del administrador que registra al jurado.
     * @param dto datos necesarios para crear un nuevo jurado.
     * @return objeto <code>JuradoDTO</code> con la información del jurado registrado.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{idAdmin}/registrar-jurado")
    public ResponseEntity<JuradoDTO> registrarJurado(
            @PathVariable Long idAmin,
            @Valid @RequestBody CrearJuradoDTO dto) {

        Administrador admin = administradorService.obtenerPorId(idAmin);
        Jurado nuevo = administradorService.registrarJurado(dto.toEntity(), admin);

        return ResponseEntity.ok(JuradoDTO.fromEntity(nuevo));
    }
}