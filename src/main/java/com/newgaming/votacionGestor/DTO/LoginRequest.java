package com.newgaming.votacionGestor.DTO;

/**
 * DTO utilizado para recibir las credenciales de inicio de sesión enviadas por el usuario.
 * Contiene únicamente el nombre de usuario y la contraseña, sin exponer información adicional.
 *
 * <p>El funcionamiento general del DTO se basa en los siguientes puntos:</p>
 * <ol>
 *     <li>Transporta las credenciales necesarias para el proceso de autenticación.</li>
 *     <li>Evita exponer datos sensibles adicionales o innecesarios.</li>
 *     <li>Es utilizado por el controlador de autenticación para validar al usuario.</li>
 * </ol>
 *
 * <p>Este DTO garantiza un intercambio seguro y controlado de credenciales
 * entre el cliente y el backend.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
public class LoginRequest {

    /**
     * Nombre de usuario utilizado para iniciar sesión.
     *
     * <ol>
     *     <li>Debe coincidir con un usuario registrado en el sistema.</li>
     *     <li>Es un dato obligatorio para el proceso de autenticación.</li>
     * </ol>
     *
     * @since 0.1
     */
    private String username;

    /**
     * Contraseña del usuario.
     *
     * <ol>
     *     <li>Debe coincidir con la contraseña almacenada para el usuario.</li>
     *     <li>Es un dato obligatorio para el proceso de autenticación.</li>
     * </ol>
     *
     * @since 0.1
     */
    private String password;

    /**
     * Obtiene el nombre de usuario.
     *
     * @since 0.1
     * @return nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @since 0.1
     * @param username nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @since 0.1
     * @return contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @since 0.1
     * @param password contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
