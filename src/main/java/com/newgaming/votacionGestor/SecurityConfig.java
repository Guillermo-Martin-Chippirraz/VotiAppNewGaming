package com.newgaming.votacionGestor;

import com.newgaming.votacionGestor.components.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Configuración central de seguridad para la aplicación.
 *
 * <p>Define las reglas de acceso, la integración del filtro JWT y la configuración
 * de CORS. Esta clase es detectada automáticamente por Spring gracias a las
 * anotaciones {@link Configuration} y {@link EnableWebSecurity}.</p>
 *
 * <p>Responsabilidades principales:</p>
 * <ul>
 *     <li>Configurar la cadena de filtros de seguridad.</li>
 *     <li>Registrar el filtro JWT antes del filtro de autenticación estándar.</li>
 *     <li>Definir rutas públicas y rutas protegidas.</li>
 *     <li>Configurar CORS para permitir peticiones desde el frontend.</li>
 * </ul>
 *
 * <p>El filtro JWT permite validar tokens en cada petición, garantizando que
 * únicamente usuarios autenticados accedan a los recursos protegidos.</p>
 *
 * <p>La configuración de CORS permite que el frontend (por defecto en
 * <code>http://localhost:3000</code>) pueda comunicarse con la API sin restricciones.</p>
 *
 * <p>Esta clase no contiene anotaciones {@code @since} porque no define métodos
 * funcionales propios, sino beans de configuración.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * Configura la cadena de filtros de seguridad.
     *
     * <p>Este método:</p>
     * <ul>
     *     <li>Deshabilita CSRF (no necesario para APIs REST).</li>
     *     <li>Habilita CORS.</li>
     *     <li>Define rutas públicas y protegidas.</li>
     *     <li>Registra el filtro JWT antes del filtro de autenticación estándar.</li>
     * </ul>
     *
     * @param http objeto de configuración de seguridad HTTP.
     * @return cadena de filtros configurada.
     * @throws Exception si ocurre un error durante la configuración.
     * @since v0.1
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Configura las reglas de CORS para permitir peticiones desde el frontend.
     *
     * <p>Permite:</p>
     * <ul>
     *     <li>Origen: <code>http://localhost:3000</code></li>
     *     <li>Métodos: GET, POST, PUT, DELETE</li>
     *     <li>Headers: cualquiera</li>
     *     <li>Credenciales habilitadas</li>
     * </ul>
     *
     * @return configuración de CORS aplicada a todas las rutas.
     * @since v0.1
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
