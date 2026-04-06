package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.DTO.EditarJuradoDTO;
import com.newgaming.votacionGestor.models.Jurado;
import com.newgaming.votacionGestor.models.Usuario;
import com.newgaming.votacionGestor.repositories.JuradoRepository;
import com.newgaming.votacionGestor.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @version v0.3
 */
@Service
public class JuradoService {

    @Autowired
    private JuradoRepository juradoRepository;

    @Autowired UsuarioRepository usuarioRepository;

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

    /**
     * Permite a un jurado editar sus propios datos.
     *
     * <p>Este método aplica varias validaciones de seguridad antes de realizar
     * cualquier modificación:</p>
     *
     * <ul>
     *     <li>Verifica que exista un usuario autenticado.</li>
     *     <li>Comprueba que el usuario autenticado coincida con el ID del jurado a editar.</li>
     *     <li>Valida que el jurado exista en la base de datos.</li>
     *     <li>Actualiza únicamente los campos proporcionados en el DTO.</li>
     * </ul>
     *
     * <p>Tras aplicar los cambios, se actualiza la marca temporal de última edición
     * y se guarda el jurado en la base de datos.</p>
     *
     * @since v0.3
     * @param idJurado identificador del jurado cuyos datos se desean editar.
     * @param dto datos a modificar.
     * @return el jurado actualizado.
     * @throws ResponseStatusException si el usuario no está autenticado, si intenta editar a otro jurado o si el jurado no existe.
     */
    @Transactional
    public Jurado editarMisDatos(Long idJurado, EditarJuradoDTO dto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getPrincipal() == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No autenticado");
        }

        Usuario usarioActual = (Usuario) auth.getDetails();

        if (!usarioActual.getIdUsuario().equals(idJurado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes editar los datos de otro jurado");
        }

        Jurado jurado = juradoRepository.findById(idJurado)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Jurado no encontrado"
                ));

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            jurado.setEmail(dto.getEmail());
        }

        if (dto.getDatosExtra() != null) {
            jurado.setDatosExtra(dto.getDatosExtra());
        }

        jurado.setActualizadoEn(LocalDateTime.now());

        return juradoRepository.save(jurado);
    }

    /**
     * Obtiene la lista completa de jurados registrados en el sistema.
     *
     * <p>La operación se ejecuta en modo de solo lectura para optimizar el acceso
     * a la base de datos.</p>
     *
     * @since v0.3
     * @return lista de todos los jurados existentes.
     */
    @Transactional(readOnly = true)
    public List<Jurado> obtenerTodos() {
        return juradoRepository.findAll();
    }

    /**
     * Elimina un jurado del sistema por su identificador.
     *
     * <p>Antes de eliminarlo, se verifica que el jurado exista; de lo contrario,
     * se devuelve un estado 404.</p>
     *
     * <p>No se aplican restricciones adicionales, ya que la eliminación puede ser
     * realizada por administradores o procesos internos según la lógica del sistema.</p>
     *
     * @since v0.3
     * @param idJurado identificador del jurado a eliminar.
     * @throws ResponseStatusException si el jurado no existe.
     */
    @Transactional
    public void eliminarJurado(Long idJurado) {
        Jurado jurado = juradoRepository.findById(idJurado)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Jurado no encontrado"
                ));

        juradoRepository.delete(jurado);
    }

    /**
     * Marca a un jurado como retirado del sistema.
     *
     * <p>Este método permite que un jurado se retire voluntariamente. Antes de
     * realizar la operación, se aplican las siguientes validaciones:</p>
     *
     * <ul>
     *     <li>Verifica que exista un usuario autenticado.</li>
     *     <li>Comprueba que el usuario autenticado coincida con el ID del jurado.</li>
     *     <li>Valida que el jurado exista en la base de datos.</li>
     * </ul>
     *
     * <p>Si las validaciones son correctas, se añade la marca {@code "retirado": true}
     * dentro del mapa de datos extra del jurado, y se actualiza la fecha de
     * modificación.</p>
     *
     * @since v0.3
     * @param idJurado identificador del jurado que desea retirarse.
     * @return el jurado actualizado con la marca de retiro.
     * @throws ResponseStatusException si el usuario no está autenticado, si intenta retirarse por otro jurado o si el jurado no existe.
     */
    @Transactional
    public Jurado retirarse(Long idJurado) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getPrincipal() == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No autenticado");
        }

        String username = auth.getName();

        Usuario usarioActual = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN, "Usuario autenticado no encontrado"
                ));

        if (!usarioActual.getIdUsuario().equals(idJurado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No puedes editar los datos de otro jurado");
        }

        Jurado jurado = juradoRepository.findById(idJurado)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Jurado no encontrado"
                ));

        Map<String, Object> datos = jurado.getDatosExtra();
        if (datos == null) datos = new HashMap<>();
        datos.put("retirado", true);

        jurado.setDatosExtra(datos);
        jurado.setActualizadoEn(LocalDateTime.now());

        return juradoRepository.save(jurado);
    }
}
