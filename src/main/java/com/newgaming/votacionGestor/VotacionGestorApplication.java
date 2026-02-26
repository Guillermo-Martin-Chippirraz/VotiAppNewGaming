package com.newgaming.votacionGestor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada principal de la aplicación *VotacionGestor*.
 *
 * <p>Esta clase inicializa el contexto de Spring Boot y arranca toda la aplicación,
 * habilitando la autodetección de componentes, la configuración automática y el
 * escaneo de entidades, servicios y controladores.</p>
 *
 * <p>La anotación {@link SpringBootApplication} combina:</p>
 * <ul>
 *     <li><strong>@Configuration</strong> — Define la clase como fuente de beans.</li>
 *     <li><strong>@EnableAutoConfiguration</strong> — Activa la configuración automática de Spring Boot.</li>
 *     <li><strong>@ComponentScan</strong> — Habilita el escaneo de componentes en el paquete base.</li>
 * </ul>
 *
 * <p>Al ejecutar el método {@code main}, Spring Boot levanta el servidor embebido
 * (normalmente Tomcat) y expone la API REST definida en el proyecto.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@SpringBootApplication
public class VotacionGestorApplication {

	/**
	 * Método principal que inicia la aplicación.
	 *
	 * @param args argumentos de línea de comandos.
	 * @since 0.1
	 */
	public static void main(String[] args) {
		SpringApplication.run(VotacionGestorApplication.class, args);
	}
}
