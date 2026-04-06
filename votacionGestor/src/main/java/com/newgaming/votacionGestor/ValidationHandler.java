package com.newgaming.votacionGestor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de validaciones para los controladores REST.
 *
 * <p>Intercepta las excepciones de tipo {@link MethodArgumentNotValidException},
 * generadas cuando fallan las validaciones de los DTOs anotados con
 * {@code @Valid}, y construye una respuesta clara y estructurada con los errores
 * de cada campo.</p>
 *
 * <p>Este componente permite centralizar el tratamiento de errores de validación,
 * evitando duplicación de lógica en los controladores y garantizando respuestas
 * consistentes.</p>
 *
 * <p>El resultado devuelto es un mapa donde la clave es el nombre del campo y
 * el valor es el mensaje de error correspondiente.</p>
 *
 * <p>La anotación {@link RestControllerAdvice} permite que Spring lo aplique
 * automáticamente a todos los controladores REST.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@RestControllerAdvice
public class ValidationHandler {

    /**
     * Maneja los errores de validación capturando los mensajes asociados a cada campo.
     *
     * @param ex excepción lanzada cuando falla la validación de un DTO.
     * @return respuesta con un mapa de errores por campo.
     * @since 0.1
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }
}
