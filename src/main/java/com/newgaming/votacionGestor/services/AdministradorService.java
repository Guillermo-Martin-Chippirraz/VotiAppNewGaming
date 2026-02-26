package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.models.Administrador;
import com.newgaming.votacionGestor.models.Jurado;
import com.newgaming.votacionGestor.repositories.AdministradorRepository;
import com.newgaming.votacionGestor.repositories.JuradoRepository;
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
 * @version 0.1
 */
@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;
    private final JuradoRepository juradoRepository;

    /**
     * Constructor que inyecta los repositorios necesarios para operar con administradores y jurados.
     *
     * @param administradorRepository repositorio de administradores.
     * @param juradoRepository repositorio de jurados.
     */
    public AdministradorService(AdministradorRepository administradorRepository,
                                JuradoRepository juradoRepository) {
        this.administradorRepository = administradorRepository;
        this.juradoRepository = juradoRepository;
    }

    /**
     * Obtiene un administrador por su identificador.
     *
     * <p>Si el administrador no existe, se lanza una excepción.</p>
     *
     * @param id identificador del administrador.
     * @return el administrador correspondiente.
     * @throws RuntimeException si no se encuentra el administrador.
     */
    public Administrador obtenerPorId(Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
    }

    /**
     * Registra un nuevo jurado y lo asocia al administrador que realiza el registro.
     *
     * <p>Este método:</p>
     * <ul>
     *     <li>Asigna el administrador como responsable del registro.</li>
     *     <li>Guarda el jurado en la base de datos.</li>
     * </ul>
     *
     * @param jurado jurado a registrar.
     * @param admin administrador que realiza el registro.
     * @return el jurado registrado.
     */
    public Jurado registrarJurado(Jurado jurado, Administrador admin) {
        jurado.setAdminRegistro(admin);
        return juradoRepository.save(jurado);
    }
}

