package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.DTO.CrearFormularioDTO;
import com.newgaming.votacionGestor.models.Evento;
import com.newgaming.votacionGestor.models.Formulario;
import com.newgaming.votacionGestor.repositories.EventoRepository;
import com.newgaming.votacionGestor.repositories.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
 * @version v0.2
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
