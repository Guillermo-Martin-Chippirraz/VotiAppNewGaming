package com.newgaming.votacionGestor.components;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
 * @version v0.2
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private long EXPIRATION;

    /**
     * Obtiene la clave criptográfica utilizada para firmar y validar tokens JWT.
     *
     * <ol>
     *     <li>Lee la clave secreta configurada en las propiedades.</li>
     *     <li>Decodifica el valor Base64 para obtener los bytes reales.</li>
     *     <li>Construye una clave HMAC válida para HS256 mediante Keys.hmacShaKeyFor().</li>
     *     <li>Devuelve un objeto SecretKey listo para firmar y validar tokens.</li>
     * </ol>
     *
     * <p>La clave debe tener al menos 256 bits (32 bytes) para ser válida con HS256.</p>
     *
     * @since v0.2
     * @return clave criptográfica para firma y validación de JWT.
     */
    private SecretKey getSigningKey() {
        System.out.println(">>> SECRET RAW = [" + SECRET + "]");
        byte [] keyBytes = Base64.getDecoder().decode(SECRET);
        System.out.println(">>> SECRET LENGTH (bytes) = " + keyBytes.length);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Genera un token JWT firmado que identifica al usuario y su rol.
     *
     * <ol>
     *     <li>Establece el subject del token como el username.</li>
     *     <li>Añade el rol del usuario como claim personalizado.</li>
     *     <li>Define la fecha de emisión (iat).</li>
     *     <li>Calcula la fecha de expiración sumando EXPIRATION al tiempo actual.</li>
     *     <li>Firma el token con HS256 utilizando la clave configurada.</li>
     * </ol>
     *
     * @since v0.1
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
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrae el nombre de usuario (subject) contenido en el token JWT.
     *
     * <ol>
     *     <li>Valida la firma del token utilizando la clave configurada.</li>
     *     <li>Parsea el token y obtiene los claims.</li>
     *     <li>Devuelve el subject almacenado, que corresponde al username.</li>
     * </ol>
     *
     * @since v0.1
     * @param token token JWT recibido.
     * @return username contenido en el token.
     */
    public String extraerUsername(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Extrae el rol del usuario desde los claims del token JWT.
     *
     * <ol>
     *     <li>Valida la firma del token.</li>
     *     <li>Parsea el cuerpo del token.</li>
     *     <li>Obtiene el claim "rol" y lo devuelve como String.</li>
     * </ol>
     *
     * @since v0.1
     * @param token token JWT recibido.
     * @return rol del usuario contenido en el token.
     */
    public String extraerRol(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody()
                .get("rol", String.class);
    }

    /**
     * Valida que el token JWT sea correcto y no haya expirado.
     *
     * <ol>
     *     <li>Intenta validar la firma utilizando la clave configurada.</li>
     *     <li>Comprueba automáticamente la expiración del token.</li>
     *     <li>Si ocurre cualquier excepción (firma inválida, token caducado, formato incorrecto),
     *         el token se considera inválido.</li>
     * </ol>
     *
     * @since 0.1
     * @param token token JWT recibido.
     * @return true si el token es válido; false en caso contrario.
     */
    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
