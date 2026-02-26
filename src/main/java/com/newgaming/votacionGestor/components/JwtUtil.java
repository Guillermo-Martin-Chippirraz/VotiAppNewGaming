package com.newgaming.votacionGestor.components;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Clase encargada de gestionar la creación, extracción y validación de tokens JWT.
 * Proporciona métodos para generar tokens firmados, obtener información contenida
 * en ellos y verificar su validez.
 *
 * <p>El funcionamiento general de la clase se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>El secreto de firma y el tiempo de expiración se obtienen desde application.properties.</li>
 *     <li>Los tokens generados incluyen:
 *         <ol>
 *             <li>El username como subject.</li>
 *             <li>El rol del usuario como claim adicional.</li>
 *             <li>Fecha de emisión y fecha de expiración.</li>
 *         </ol>
 *     </li>
 *     <li>Los métodos de extracción permiten recuperar el username y el rol desde el token.</li>
 *     <li>La validación comprueba la firma y la expiración del token.</li>
 * </ol>
 *
 * <p>Esta clase es fundamental para la autenticación basada en JWT dentro del sistema.</p>
 *
 * @author Guillermo
 * @version 0.1
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private long EXPIRATION;

    /**
     * Genera un token JWT firmado utilizando el username y el rol del usuario.
     *
     * <ol>
     *     <li>Se establece el subject del token como el username.</li>
     *     <li>Se añade el rol como claim adicional.</li>
     *     <li>Se define la fecha de emisión.</li>
     *     <li>Se calcula la fecha de expiración sumando EXPIRATION al tiempo actual.</li>
     *     <li>Se firma el token con HS256 utilizando el secreto configurado.</li>
     * </ol>
     *
     * @since 0.1
     * @param username nombre del usuario autenticado.
     * @param rol rol del usuario (ADMIN o JURADO).
     * @return token JWT firmado y con fecha de expiración.
     */
    public String generarToken(String username, String rol) {
        return Jwts.builder()
                .setSubject(username)
                .claim("rol", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /**
     * Extrae el nombre de usuario (subject) contenido en el token JWT.
     *
     * <ol>
     *     <li>Se valida la firma utilizando el secreto configurado.</li>
     *     <li>Se obtiene el cuerpo del token.</li>
     *     <li>Se devuelve el subject almacenado.</li>
     * </ol>
     *
     * @since 0.1
     * @param token token JWT recibido.
     * @return username contenido en el token.
     */
    public String extraerUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Extrae el rol del usuario desde los claims del token JWT.
     *
     * <ol>
     *     <li>Se valida la firma del token.</li>
     *     <li>Se obtiene el cuerpo del token.</li>
     *     <li>Se devuelve el claim "rol" como String.</li>
     * </ol>
     *
     * @since 0.1
     * @param token token JWT recibido.
     * @return rol del usuario.
     */
    public String extraerRol(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .get("rol", String.class);
    }

    /**
     * Valida que el token JWT sea correcto.
     *
     * <ol>
     *     <li>Comprueba que la firma sea válida utilizando el secreto.</li>
     *     <li>Verifica que el token no haya expirado.</li>
     *     <li>Si ocurre cualquier excepción, el token se considera inválido.</li>
     * </ol>
     *
     * @since 0.1
     * @param token token JWT recibido.
     * @return true si el token es válido; false en caso contrario.
     */
    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
