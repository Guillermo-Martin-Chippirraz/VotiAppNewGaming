package com.newgaming.votacionGestor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuración general de la aplicación.
 *
 * <p>Esta clase define componentes esenciales que deben estar disponibles en el
 * contenedor de Spring. Su función principal es exponer un codificador de
 * contraseñas basado en <code>BCrypt</code>, utilizado en todo el sistema para
 * garantizar el almacenamiento seguro de credenciales.</p>
 *
 * <p>Al estar anotada con <code>@Configuration</code>, Spring la reconoce como una
 * clase de configuración y procesa los métodos anotados con <code>@Bean</code> para
 * registrar instancias administradas dentro del contexto de la aplicación.</p>
 *
 * <p>El uso de <code>BCryptPasswordEncoder</code> asegura que las contraseñas se
 * almacenen mediante un algoritmo robusto de hashing con salt incorporado,
 * aumentando la protección frente a ataques de fuerza bruta.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
@Configuration
public class AppConfig {

    /**
     * Proporciona un codificador de contraseñas basado en BCrypt.
     *
     * <p>Este bean es utilizado por los servicios encargados de gestionar usuarios
     * para encriptar contraseñas antes de almacenarlas en la base de datos. BCrypt
     * aplica un algoritmo de hashing seguro y adaptable, lo que permite aumentar
     * su complejidad con el tiempo.</p>
     *
     * @return instancia de <code>PasswordEncoder</code> configurada con BCrypt.
     * @since v0.2
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}