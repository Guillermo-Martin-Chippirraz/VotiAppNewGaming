package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.models.Usuario;
import com.newgaming.votacionGestor.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con los usuarios.
 * Permite registrar nuevos usuarios, buscarlos por nombre de usuario y obtenerlos
 * por su identificador.
 *
 * <p>Actúa como capa intermedia entre los controladores y el repositorio
 * {@link UsuarioRepository}, encapsulando la lógica necesaria para operar
 * con la entidad {@link Usuario}.</p>
 *
 * <p>Responsabilidades principales:</p>
 * <ul>
 *     <li>Registrar usuarios.</li>
 *     <li>Buscar usuarios por nombre de usuario.</li>
 *     <li>Consultar usuarios por su ID.</li>
 * </ul>
 *
 * <p>La anotación {@link Service} permite que Spring gestione esta clase como
 * un componente de servicio dentro del contexto de la aplicación.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor que inyecta el repositorio de usuarios.
     *
     * @param usuarioRepository repositorio de usuarios.
     * @since 0.1
     */
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param usuario usuario a registrar.
     * @return usuario registrado.
     * @since 0.1
     */
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username nombre de usuario.
     * @return un {@link Optional} que contiene el usuario si existe.
     * @since 0.1
     */
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    /**
     * Obtiene un usuario por su identificador.
     *
     * <p>Si el usuario no existe, se lanza una excepción.</p>
     *
     * @param id identificador del usuario.
     * @return usuario correspondiente.
     * @throws RuntimeException si no se encuentra el usuario.
     * @since 0.1
     */
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
