package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.DTO.CrearUsuarioDTO;
import com.newgaming.votacionGestor.components.VerificacionAdmin;
import com.newgaming.votacionGestor.models.Administrador;
import com.newgaming.votacionGestor.models.Jurado;
import com.newgaming.votacionGestor.models.Usuario;
import com.newgaming.votacionGestor.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
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
 * @version v0.3
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Crea un nuevo usuario en el sistema y lo registra con el rol correspondiente.
     *
     * <p>Este método valida que el nombre de usuario no exista previamente y construye
     * la entidad adecuada según el rol indicado en el DTO. Además, registra el
     * administrador responsable cuando se crea un jurado y establece las marcas
     * temporales de creación y actualización.</p>
     *
     * <ul>
     *     <li>Verifica que el nombre de usuario no esté ya registrado.</li>
     *     <li>Determina el tipo de usuario a crear según el rol recibido.</li>
     *     <li>Asigna el administrador creador cuando el usuario es un jurado.</li>
     *     <li>Encripta la contraseña antes de almacenarla.</li>
     *     <li>Registra fechas de creación y actualización.</li>
     *     <li>Guarda el usuario en la base de datos.</li>
     * </ul>
     *
     * @since v0.1
     * @param dto datos necesarios para registrar al nuevo usuario.
     * @param admin administrador que realiza el registro (solo relevante para jurados).
     * @return usuario recién creado y persistido.
     * @throws RuntimeException si el nombre de usuario ya existe.
     */
    public Usuario crearUsuario(CrearUsuarioDTO dto, Administrador admin) {
        if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        Usuario usuario;

        switch (dto.getRol().toUpperCase()) {
            case "ADMIN":
                usuario = new Administrador();
                break;
            case "JURADO":
                Jurado j = new Jurado();
                j.setAdminRegistro(admin);
                usuario = j;
                break;
            default:
                usuario = new Usuario();
        }

        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol().toUpperCase());
        usuario.setCreadoEn(LocalDateTime.now());
        usuario.setActualizadoEn(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @since v0.1
     * @param username nombre de usuario.
     * @return un {@link Optional} que contiene el usuario si existe.
     */
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    /**
     * Recupera un usuario a partir de su identificador único.
     *
     * <p>Este método consulta el repositorio para localizar al usuario solicitado.
     * Si no existe un registro asociado al ID proporcionado, se lanza una excepción
     * con estado HTTP 404, indicando que el recurso no fue encontrado.</p>
     *
     * <ul>
     *     <li>Busca el usuario mediante su ID.</li>
     *     <li>Devuelve la entidad si está presente.</li>
     *     <li>Lanza una excepción con código 404 si no existe.</li>
     * </ul>
     * @since 0.2
     * @param id identificador del usuario que se desea obtener.
     * @return entidad <code>Usuario</code> correspondiente al ID.
     * @throws ResponseStatusException si el usuario no existe en la base de datos.
     */
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"
                ));
    }
    /**
     * Obtiene la lista completa de usuarios registrados en el sistema.
     *
     * <p>La operación se ejecuta en modo de solo lectura y únicamente puede ser
     * realizada por administradores. Si el usuario autenticado no es administrador,
     * se devuelve un estado 403.</p>
     *
     * <p>En caso de autorización válida, se devuelve la lista completa de entidades
     * {@link Usuario} almacenadas en el repositorio.</p>
     *
     * @since v0.3
     * @return lista de todos los usuarios existentes.
     * @throws ResponseStatusException si el usuario no es administrador.
     */
    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodos() {
        if (VerificacionAdmin.esAdmin())
            return usuarioRepository.findAll();
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sólo los administradores pueden revisar todos los usuarios.");
    }

    public Usuario obtenerPorUsername(String name) {
        Optional<Usuario> u = usuarioRepository.findByUsername(name);
        if (u.isPresent())
            return u.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
    }
}
