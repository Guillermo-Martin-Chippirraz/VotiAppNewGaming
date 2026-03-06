package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.DTO.CrearFormularioDTO;
import com.newgaming.votacionGestor.DTO.EditarFormularioDTO;
import com.newgaming.votacionGestor.components.VerificacionAdmin;
import com.newgaming.votacionGestor.models.Evento;
import com.newgaming.votacionGestor.models.Formulario;
import com.newgaming.votacionGestor.repositories.EventoRepository;
import com.newgaming.votacionGestor.repositories.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con los formularios.
 * Permite crear nuevos formularios y obtener el formulario asociado a un evento concreto.
 *
 * <p>Actúa como capa intermedia entre los controladores y el repositorio
 * {@link FormularioRepository}, encapsulando la lógica necesaria para operar
 * con la entidad {@link Formulario}.</p>
 *
 * <p>Responsabilidades principales:</p>
 * <ul>
 *     <li>Registrar nuevos formularios.</li>
 *     <li>Consultar formularios asociados a un evento.</li>
 * </ul>
 *
 * <p>La anotación {@link Service} permite que Spring gestione esta clase como un
 * componente de servicio dentro del contexto de la aplicación.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.3
 */
@Service
public class FormularioService {

    @Autowired
    private FormularioRepository formularioRepository;

    @Autowired
    private EventoRepository eventoRepository;

    /**
     * Crea y registra un nuevo formulario asociado a un evento.
     *
     * <p>Este método se encarga de validar la existencia del evento, comprobar que no
     * exista ya un formulario asignado y construir la entidad <code>Formulario</code>
     * con los datos proporcionados. También establece las marcas temporales de creación
     * y actualización antes de persistir la información.</p>
     *
     * <ul>
     *     <li>Busca el evento correspondiente mediante su ID.</li>
     *     <li>Verifica que el evento no tenga ya un formulario asociado.</li>
     *     <li>Crea una nueva instancia de <code>Formulario</code>.</li>
     *     <li>Asigna el evento y la lista de preguntas recibida en el DTO.</li>
     *     <li>Registra las fechas de creación y actualización.</li>
     *     <li>Guarda el formulario en la base de datos.</li>
     * </ul>
     *
     * @param dto objeto con los datos necesarios para crear el formulario.
     * @return formulario recién creado y persistido.
     * @since v0.1
     * @throws RuntimeException si el evento no existe o si ya tiene un formulario asignado.
     */
    public Formulario crearFormulario(CrearFormularioDTO dto) {

        Evento evento = eventoRepository.findById(dto.getIdEvento())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        if (formularioRepository.findByEventoIdEvento(dto.getIdEvento()).isPresent()) {
            throw new RuntimeException("El evento ya tiene un formulario asignado");
        }

        Formulario formulario = new Formulario();
        formulario.setEvento(evento);
        formulario.setListaDePreguntas(dto.getListaDePreguntas());
        formulario.setCreadoEn(LocalDateTime.now());
        formulario.setActualizadoEn(LocalDateTime.now());

        return formularioRepository.save(formulario);
    }

    /**
     * Obtiene un formulario por su identificador.
     *
     * <p>La operación se ejecuta en modo de solo lectura para optimizar el acceso
     * a la base de datos. Si el formulario no existe, se devuelve un estado 404.</p>
     *
     * @since v0.3
     * @param idFormulario identificador del formulario a consultar.
     * @return el formulario correspondiente al ID proporcionado.
     * @throws ResponseStatusException si el formulario no existe.
     */
    @Transactional(readOnly = true)
    public Formulario obtenerPorId(Long idFormulario) {
        return formularioRepository.findById(idFormulario)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Formulario no encontrado"
                ));
    }

    /**
     * Obtiene todos los formularios registrados en el sistema.
     *
     * <p>La operación se ejecuta en modo de solo lectura y devuelve la lista completa
     * de entidades {@link Formulario} almacenadas en el repositorio.</p>
     *
     * @since v0.3
     * @return lista de todos los formularios existentes.
     */
    @Transactional(readOnly = true)
    public List<Formulario> obtenerTodos() {
        return formularioRepository.findAll();
    }

    /**
     * Edita los datos de un formulario existente.
     *
     * <p>Este método permite modificar la lista de preguntas del formulario. Antes
     * de aplicar los cambios, se realizan las siguientes validaciones:</p>
     *
     * <ul>
     *     <li>Se verifica que el formulario exista; de lo contrario, se devuelve un estado 404.</li>
     *     <li>Se comprueba que el usuario autenticado sea administrador.</li>
     *     <li>Solo se actualiza la lista de preguntas si ha sido proporcionada en el DTO.</li>
     * </ul>
     *
     * <p>Tras aplicar las modificaciones, se actualiza la marca temporal de última
     * edición y se guarda el formulario en la base de datos.</p>
     *
     * @since v0.3
     * @param idFormulario identificador del formulario a editar.
     * @param dto datos a modificar en el formulario.
     * @return el formulario actualizado.
     * @throws ResponseStatusException si el formulario no existe o el usuario no es administrador.
     */
    @Transactional
    public Formulario editarFormulario(Long idFormulario, EditarFormularioDTO dto) {

        Formulario formulario = formularioRepository.findById(idFormulario)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Formulario no encontrado"
                ));

        if (!VerificacionAdmin.esAdmin())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sólo los administradores pueden editar formularios");

        if (dto.getListaDePreguntas() != null) {
            formulario.setListaDePreguntas(dto.getListaDePreguntas());
        }

        formulario.setActualizadoEn(LocalDateTime.now());

        return formularioRepository.save(formulario);
    }

    /**
     * Elimina un formulario del sistema por su identificador.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     *
     * <ul>
     *     <li>Verifica que el formulario exista; si no, lanza un estado 404.</li>
     *     <li>Comprueba que el usuario autenticado sea administrador.</li>
     *     <li>Si las validaciones son correctas, elimina el formulario del repositorio.</li>
     * </ul>
     *
     * <p>Este proceso garantiza que solo usuarios autorizados puedan eliminar
     * formularios y evita inconsistencias en la base de datos.</p>
     *
     * @since v0.3
     * @param idFormulario identificador del formulario a eliminar.
     * @throws ResponseStatusException si el formulario no existe o el usuario no es administrador.
     */
    @Transactional
    public void eliminarFormulario(Long idFormulario) {

        Formulario formulario = formularioRepository.findById(idFormulario)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Formulario no encontrado"
                ));

        if (VerificacionAdmin.esAdmin())
            formularioRepository.delete(formulario);
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sólo los administradores pueden eliminar formularios.");
    }

    /**
     * Obtiene el formulario asociado a un evento concreto.
     *
     * <p>Si no existe un formulario para el evento indicado, se lanza una excepción.</p>
     *
     * @param idEvento identificador del evento.
     * @return formulario asociado al evento.
     * @throws RuntimeException si no se encuentra el formulario.
     * @since v0.1
     */
    public Formulario obtenerPorEvento(Long idEvento) {
        return formularioRepository.findByEventoIdEvento(idEvento)
                .orElseThrow(() -> new RuntimeException("Formulario no encontrado"));
    }
}
