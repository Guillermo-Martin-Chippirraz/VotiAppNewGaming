package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.models.Jurado;
import com.newgaming.votacionGestor.models.Usuario;
import com.newgaming.votacionGestor.repositories.JuradoRepository;
import com.newgaming.votacionGestor.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con los jurados.
 * Permite consultar jurados por su identificador y actuar como capa intermedia
 * entre los controladores y el repositorio {@link JuradoRepository}.
 *
 * <p>Responsabilidades principales:</p>
 * <ul>
 *     <li>Consultar jurados por su ID.</li>
 * </ul>
 *
 * <p>La anotación {@link Service} permite que Spring gestione esta clase como
 * un componente de servicio dentro del contexto de la aplicación.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
@Service
public class JuradoService {

    @Autowired
    private JuradoRepository juradoRepository;

    private final UsuarioService usuarioService;

    /**
     * Constructor del servicio que recibe la dependencia necesaria para gestionar usuarios.
     *
     * <p>Permite inyectar el servicio encargado de las operaciones relacionadas con
     * entidades de tipo <code>Usuario</code>, lo cual es fundamental para validar y
     * recuperar información antes de determinar si un usuario es un jurado.</p>
     *
     * @since v0.1
     * @param usuarioService servicio utilizado para consultar usuarios.
     */
    public JuradoService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Obtiene un jurado a partir de su nombre de usuario.
     *
     * <p>Este método valida que el usuario exista y que efectivamente sea una instancia
     * de <code>Jurado</code>. Si el usuario no existe o pertenece a otro rol, se lanza
     * una excepción indicando el motivo.</p>
     *
     * <ul>
     *     <li>Busca el usuario por su nombre de usuario.</li>
     *     <li>Verifica que el usuario encontrado sea un jurado.</li>
     *     <li>Devuelve la entidad <code>Jurado</code> correspondiente.</li>
     * </ul>
     *
     * @since v0.1
     * @param username nombre de usuario del jurado.
     * @return entidad <code>Jurado</code> asociada al nombre de usuario.
     * @throws RuntimeException si el usuario no existe o no es un jurado.
     */
    public Jurado obtenerJuradoPorUsername(String username) {
        Usuario u = usuarioService.buscarPorUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!(u instanceof Jurado jurado)) {
            throw new RuntimeException("El usuario no es un jurado");
        }

        return jurado;
    }

    /**
     * Obtiene un jurado por su identificador único.
     *
     * <p>Si el jurado no existe en la base de datos, se lanza una excepción con un
     * estado HTTP 404 para indicar que el recurso no fue encontrado.</p>
     *
     * @since v0.1
     * @param id identificador del jurado.
     * @return entidad <code>Jurado</code> correspondiente.
     * @throws ResponseStatusException si no se encuentra el jurado.
     */
    public Jurado obtenerPorId(Long id) {
        return juradoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Jurado no encontrado"
                ));
    }
}
