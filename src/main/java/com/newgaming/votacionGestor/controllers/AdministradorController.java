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
 * @version v0.2
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
     * @since v0.1
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
     *     <li>Convierte el DTO en una entidad <code>Jurado</code>.</li>
     *     <li>Registra el jurado mediante el servicio correspondiente.</li>
     *     <li>Devuelve un DTO con la información del jurado creado.</li>
     * </ol>
     *
     * <p>Este endpoint permite a un administrador crear nuevos jurados dentro del sistema,
     * asociando cada registro al administrador que lo realizó.</p>
     *
     * @since v0.1
     * @param idAdmin identificador del administrador que registra al jurado.
     * @param dto datos necesarios para crear un nuevo jurado.
     * @return objeto <code>JuradoDTO</code> con la información del jurado registrado.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{idAdmin}/registrar-jurado")
    public ResponseEntity<JuradoDTO> registrarJurado(
            @PathVariable Long idAdmin,
            @Valid @RequestBody CrearJuradoDTO dto) {

        System.out.println(">>> DTO recibido: username=" + dto.getUsername() + ", password=" + dto.getPassword());

        Administrador admin = administradorService.obtenerPorId(idAdmin);
        Jurado nuevo = administradorService.registrarJurado(dto.toEntity(), admin);

        return ResponseEntity.ok(JuradoDTO.fromEntity(nuevo));
    }
}