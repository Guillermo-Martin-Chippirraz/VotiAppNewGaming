package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.models.Jurado;
import com.newgaming.votacionGestor.repositories.JuradoRepository;
import org.springframework.stereotype.Service;

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
 * @version 0.1
 */
@Service
public class JuradoService {

    private final JuradoRepository juradoRepository;

    /**
     * Constructor que inyecta el repositorio de jurados.
     *
     * @param juradoRepository repositorio de jurados.
     * @since 0.1
     */
    public JuradoService(JuradoRepository juradoRepository) {
        this.juradoRepository = juradoRepository;
    }

    /**
     * Obtiene un jurado por su identificador.
     *
     * <p>Si el jurado no existe, se lanza una excepción.</p>
     *
     * @param id identificador del jurado.
     * @return jurado correspondiente.
     * @throws RuntimeException si no se encuentra el jurado.
     * @since 0.1
     */
    public Jurado obtenerPorId(Long id) {
        return juradoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jurado no encontrado"));
    }
}
