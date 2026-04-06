package com.newgaming.votacionGestor.DTO;

/**
 * DTO utilizado para enviar al cliente el token JWT generado tras un inicio de sesión exitoso.
 * Contiene únicamente el token, evitando exponer información adicional o sensible.
 *
 * <p>El funcionamiento general del DTO se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Transporta el token JWT generado por el sistema de autenticación.</li>
 *     <li>Permite encapsular el token en una estructura clara y sencilla.</li>
 *     <li>Es utilizado por el controlador de autenticación como respuesta al login.</li>
 * </ol>
 *
 * <p>Este DTO facilita la comunicación entre backend y frontend, proporcionando
 * el token necesario para acceder a los endpoints protegidos.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
public class TokenResponse {

    /**
     * Token JWT generado tras la autenticación.
     *
     * <ol>
     *     <li>Es un valor no nulo.</li>
     *     <li>Debe ser enviado al cliente para futuras peticiones autenticadas.</li>
     * </ol>
     *
     * @since 0.1
     */
    private String token;

    /**
     * Constructor que inicializa el DTO con el token generado.
     *
     * @since 0.1
     * @param token token JWT generado.
     */
    public TokenResponse(String token) {
        this.token = token;
    }

    /**
     * Obtiene el token JWT.
     *
     * @since 0.1
     * @return token JWT.
     */
    public String getToken() {
        return token;
    }

    /**
     * Establece el token JWT.
     *
     * @since 0.1
     * @param token token JWT.
     */
    public void setToken(String token) {
        this.token = token;
    }
}