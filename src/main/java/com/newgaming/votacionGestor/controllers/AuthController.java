package com.newgaming.votacionGestor.controllers;

import com.newgaming.votacionGestor.DTO.LoginRequest;
import com.newgaming.votacionGestor.DTO.TokenResponse;
import com.newgaming.votacionGestor.components.JwtUtil;
import com.newgaming.votacionGestor.models.Usuario;
import com.newgaming.votacionGestor.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador encargado de gestionar la autenticación de usuarios dentro del sistema.
 * Permite validar credenciales y generar un token JWT para acceder a los endpoints protegidos.
 *
 * <p>El funcionamiento general del controlador se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Expone los endpoints bajo la ruta base <code>/api/auth</code>.</li>
 *     <li>Recibe las credenciales del usuario mediante un DTO.</li>
 *     <li>Busca al usuario en la base de datos por su username.</li>
 *     <li>Valida la contraseña recibida.</li>
 *     <li>Genera un token JWT con el username y el rol del usuario.</li>
 *     <li>Devuelve el token encapsulado en un objeto <code>TokenResponse</code>.</li>
 * </ol>
 *
 * <p>Este controlador es esencial para el proceso de login y la protección
 * de recursos mediante autenticación basada en JWT.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    /**
     * Constructor por parámetros.
     * Inicializa el controlador con las dependencias necesarias para validar
     * credenciales y generar tokens JWT.
     *
     * <ol>
     *     <li>Asigna la utilidad JwtUtil para generar tokens.</li>
     *     <li>Asigna UsuarioService para buscar usuarios en la base de datos.</li>
     * </ol>
     *
     * @since 0.1
     * @param jwtUtil utilidad para manejo de JWT.
     * @param usuarioService servicio para gestión de usuarios.
     */
    public AuthController(JwtUtil jwtUtil, UsuarioService usuarioService) {
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint de login.
     * Valida las credenciales del usuario y genera un token JWT si son correctas.
     *
     * <ol>
     *     <li>Recibe un objeto LoginRequest con username y password.</li>
     *     <li>Busca al usuario en la base de datos mediante su username.</li>
     *     <li>Si no existe, lanza una excepción de credenciales inválidas.</li>
     *     <li>Compara la contraseña recibida con la almacenada.</li>
     *     <li>Si no coinciden, lanza una excepción de credenciales inválidas.</li>
     *     <li>Genera un token JWT con el username y el rol del usuario.</li>
     *     <li>Devuelve el token encapsulado en un TokenResponse.</li>
     * </ol>
     *
     * @since 0.1
     * @param request DTO con las credenciales del usuario.
     * @return objeto TokenResponse con el token generado.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.buscarPorUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!usuario.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtUtil.generarToken(usuario.getUsername(), usuario.getRol());

        return ResponseEntity.ok(new TokenResponse(token));
    }
}