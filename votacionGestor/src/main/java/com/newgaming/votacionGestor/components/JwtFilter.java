package com.newgaming.votacionGestor.components;

import com.newgaming.votacionGestor.models.Usuario;
import com.newgaming.votacionGestor.services.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Filtro encargado de interceptar cada petición HTTP y validar el token JWT
 * enviado en el encabezado Authorization. Si el token es válido, se autentica
 * al usuario dentro del contexto de Spring Security.
 *
 * <p>El comportamiento del filtro sigue la siguiente línea de ejecución:</p>
 * <ol>
 *     <li>Se obtiene el encabezado Authorization de la petición.</li>
 *     <li>Se comprueba si comienza con "Bearer ".</li>
 *     <li>Se extrae el token JWT eliminando el prefijo.</li>
 *     <li>Se obtiene el username contenido en el token.</li>
 *     <li>Se valida el token mediante JwtUtil.</li>
 *     <li>Si el token es válido:
 *         <ol>
 *             <li>Se busca el usuario en la base de datos.</li>
 *             <li>Se extrae el rol del token.</li>
 *             <li>Se crea un objeto Authentication con el usuario y su rol.</li>
 *             <li>Se registra la autenticación en el SecurityContext.</li>
 *         </ol>
 *     </li>
 *     <li>Finalmente, se continúa la cadena de filtros.</li>
 * </ol>
 *
 * <p>Este filtro garantiza que solo usuarios autenticados puedan acceder
 * a los endpoints protegidos del sistema.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    /**
     * Constructor por parámetros.
     * Inicializa el filtro con las dependencias necesarias para validar tokens
     * y obtener usuarios desde la base de datos.
     *
     * <ol>
     *     <li>Se asigna la utilidad JwtUtil para validar y extraer datos del token.</li>
     *     <li>Se asigna UsuarioService para buscar usuarios por username.</li>
     * </ol>
     *
     * @since v0.1
     * @param jwtUtil utilidad para manejo de JWT.
     * @param usuarioService servicio para gestión de usuarios.
     */
    public JwtFilter(JwtUtil jwtUtil, UsuarioService usuarioService) {
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    /**
     * Procesa cada petición HTTP entrante y aplica autenticación basada en JWT.
     *
     * <p>Si la petición incluye un token válido en el encabezado Authorization,
     * el filtro autentica al usuario y registra su identidad y rol en el contexto
     * de Spring Security.</p>
     *
     * <p>Flujo del método:</p>
     * <ol>
     *     <li>Lee el encabezado Authorization.</li>
     *     <li>Comprueba si contiene un token Bearer.</li>
     *     <li>Extrae el token y obtiene el username.</li>
     *     <li>Valida el token mediante JwtUtil.</li>
     *     <li>Si es válido:
     *         <ol>
     *             <li>Busca el usuario real en la base de datos.</li>
     *             <li>Obtiene el rol incluido en el token.</li>
     *             <li>Crea un objeto Authentication con el username y su rol.</li>
     *             <li>Adjunta el objeto Usuario completo como detalles.</li>
     *             <li>Registra la autenticación en el contexto de seguridad.</li>
     *         </ol>
     *     </li>
     *     <li>Continúa la cadena de filtros.</li>
     * </ol>
     *
     * @since v0.1
     * @param request petición HTTP entrante.
     * @param response respuesta HTTP.
     * @param filterChain cadena de filtros de Spring Security.
     * @throws ServletException si ocurre un error en el procesamiento del filtro.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtUtil.extraerUsername(token);

            if (username != null && jwtUtil.validarToken(token)) {
                Usuario usuario = usuarioService.buscarPorUsername(username).orElse(null);

                if (usuario != null) {
                    String rol = jwtUtil.extraerRol(token);
                    SimpleGrantedAuthority authority =
                            new SimpleGrantedAuthority("ROLE_" + rol);

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    usuario.getUsername(),
                                    null,
                                    List.of(authority)
                            );

                    auth.setDetails(usuario);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
