package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.models.Formulario;
import com.newgaming.votacionGestor.repositories.FormularioRepository;
import org.springframework.stereotype.Service;

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
 * @version 0.1
 */
@Service
public class FormularioService {

    private final FormularioRepository formularioRepository;

    /**
     * Constructor que inyecta el repositorio de formularios.
     *
     * @param formularioRepository repositorio de formularios.
     * @since 0.1
     */
    public FormularioService(FormularioRepository formularioRepository) {
        this.formularioRepository = formularioRepository;
    }

    /**
     * Crea un nuevo formulario en el sistema.
     *
     * @param formulario formulario a registrar.
     * @return formulario registrado.
     * @since 0.1
     */
    public Formulario crearFormulario(Formulario formulario) {
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
     * @since 0.1
     */
    public Formulario obtenerPorEvento(Long idEvento) {
        return formularioRepository.findByEventoIdEvento(idEvento)
                .orElseThrow(() -> new RuntimeException("Formulario no encontrado"));
    }
}
