package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.models.Administrador;
import com.newgaming.votacionGestor.models.Jurado;
import com.newgaming.votacionGestor.models.Usuario;
import com.newgaming.votacionGestor.repositories.AdministradorRepository;
import com.newgaming.votacionGestor.repositories.JuradoRepository;
import com.newgaming.votacionGestor.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con los administradores.
 * Permite consultar administradores y registrar nuevos jurados bajo la supervisión de uno.
 *
 * <p>Este servicio actúa como intermediario entre los controladores y los repositorios,
 * encapsulando la lógica necesaria para operar con las entidades {@link Administrador}
 * y {@link Jurado}.</p>
 *
 * <p>Responsabilidades principales:</p>
 * <ul>
 *     <li>Obtener administradores por su identificador.</li>
 *     <li>Registrar jurados y asociarlos al administrador que los crea.</li>
 * </ul>
 *
 * <p>La anotación {@link Service} permite que Spring gestione esta clase como un
 * componente de servicio dentro del contexto de la aplicación.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.4
 */
@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private JuradoRepository juradoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * Obtiene un administrador por su identificador.
     *
     * <p>Si el administrador no existe, se lanza una excepción.</p>
     *
     * @since v0.1
     * @param id identificador del administrador.
     * @return el administrador correspondiente.
     * @throws RuntimeException si no se encuentra el administrador.
     */
    public Administrador obtenerPorId(Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
    }

    /**
     * Obtiene un administrador a partir del nombre de usuario proporcionado.
     *
     * <p>Este método realiza una doble verificación para garantizar que el usuario
     * solicitado existe y que, además, corresponde a un administrador registrado
     * en el sistema.</p>
     *
     * <ol>
     *     <li>Busca un usuario cuyo <code>username</code> coincida con el proporcionado.</li>
     *     <li>Si no existe, lanza una excepción indicando que el usuario no fue encontrado.</li>
     *     <li>Con el ID del usuario encontrado, busca la entidad <code>Administrador</code> asociada.</li>
     *     <li>Si no existe un administrador con ese ID, lanza una excepción indicando que no fue encontrado.</li>
     *     <li>Devuelve la entidad <code>Administrador</code> correspondiente.</li>
     * </ol>
     *
     * <p>Este método es fundamental para operaciones donde se requiere validar
     * que el usuario autenticado posee rol de administrador antes de ejecutar
     * acciones restringidas.</p>
     *
     * @since v0.2
     * @param username nombre de usuario a buscar.
     * @return entidad <code>Administrador</code> asociada al usuario.
     * @throws RuntimeException si el usuario o el administrador no existen.
     */
    public Administrador obtenerPorUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return administradorRepository.findById(usuario.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
    }


    /**
     * Registra un nuevo jurado en el sistema y lo asocia al administrador que realiza el registro.
     *
     * <p>Este método se encarga de preparar y persistir una nueva entidad {@link Jurado}
     * aplicando las siguientes acciones:</p>
     *
     * <ul>
     *     <li>Asigna al administrador como responsable del registro del jurado.</li>
     *     <li>Encripta la contraseña proporcionada antes de almacenarla en la base de datos.</li>
     *     <li>Establece el email recibido sin modificarlo (la validación se realiza en capas superiores).</li>
     *     <li>Guarda la entidad resultante en el repositorio.</li>
     * </ul>
     *
     * <p>Este proceso garantiza que la contraseña nunca se almacene en texto plano,
     * cumpliendo con las buenas prácticas de seguridad y manteniendo la integridad
     * de los datos del sistema.</p>
     *
     * @since v0.1
     * @param jurado entidad {@code Jurado} que se desea registrar.
     * @param admin administrador responsable del registro.
     * @return el jurado registrado y persistido en la base de datos.
     */
    public Jurado registrarJurado(Jurado jurado, Administrador admin) {

        jurado.setAdminRegistro(admin);
        jurado.setPassword(passwordEncoder.encode(jurado.getPassword()));
        jurado.setEmail(jurado.getEmail());
        return juradoRepository.save(jurado);
    }
}

