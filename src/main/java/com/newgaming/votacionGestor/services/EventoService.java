package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.models.Administrador;
import com.newgaming.votacionGestor.models.Evento;
import com.newgaming.votacionGestor.repositories.EventoRepository;
import com.newgaming.votacionGestor.repositories.EventoUsuarioRepository;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con los eventos.
 * Permite crear eventos, habilitarlos, deshabilitarlos y consultarlos por su identificador.
 *
 * <p>Este servicio actúa como capa intermedia entre los controladores y los repositorios,
 * encapsulando la lógica necesaria para operar con la entidad {@link Evento} y su relación
 * con los usuarios mediante {@link EventoUsuarioRepository}.</p>
 *
 * <p>Responsabilidades principales:</p>
 * <ul>
 *     <li>Registrar nuevos eventos.</li>
 *     <li>Habilitar eventos y registrar qué administrador realizó la acción.</li>
 *     <li>Deshabilitar eventos y registrar qué administrador realizó la acción.</li>
 *     <li>Consultar eventos por su identificador.</li>
 * </ul>
 *
 * <p>La anotación {@link Service} permite que Spring gestione esta clase como un
 * componente de servicio dentro del contexto de la aplicación.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version 0.1
 */
@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final EventoUsuarioRepository eventoUsuarioRepository;

    /**
     * Constructor que inyecta los repositorios necesarios para operar con eventos
     * y sus relaciones con usuarios.
     *
     * @param eventoRepository repositorio de eventos.
     * @param eventoUsuarioRepository repositorio de relaciones Evento-Usuario.
     * @since 0.1
     */
    public EventoService(EventoRepository eventoRepository,
                         EventoUsuarioRepository eventoUsuarioRepository) {
        this.eventoRepository = eventoRepository;
        this.eventoUsuarioRepository = eventoUsuarioRepository;
    }

    /**
     * Crea un nuevo evento en el sistema.
     *
     * @param evento evento a registrar.
     * @return evento registrado.
     * @since 0.1
     */
    public Evento crearEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    /**
     * Habilita un evento y registra qué administrador realizó la acción.
     *
     * <p>Este método:</p>
     * <ul>
     *     <li>Obtiene el evento por su ID.</li>
     *     <li>Actualiza su estado a "HABILITADO".</li>
     *     <li>Registra el administrador que lo habilita.</li>
     *     <li>Guarda los cambios en la base de datos.</li>
     * </ul>
     *
     * @param idEvento identificador del evento.
     * @param admin administrador que habilita el evento.
     * @return evento actualizado.
     * @since 0.1
     */
    public Evento habilitarEvento(Long idEvento, Administrador admin) {
        Evento evento = obtenerPorId(idEvento);
        evento.setEstado("HABILITADO");
        evento.setAdminHabilita(admin);
        return eventoRepository.save(evento);
    }

    /**
     * Deshabilita un evento y registra qué administrador realizó la acción.
     *
     * <p>Este método:</p>
     * <ul>
     *     <li>Obtiene el evento por su ID.</li>
     *     <li>Actualiza su estado a "DESHABILITADO".</li>
     *     <li>Registra el administrador que lo deshabilita.</li>
     *     <li>Guarda los cambios en la base de datos.</li>
     * </ul>
     *
     * @param idEvento identificador del evento.
     * @param admin administrador que deshabilita el evento.
     * @return evento actualizado.
     * @since 0.1
     */
    public Evento deshabilitarEvento(Long idEvento, Administrador admin) {
        Evento evento = obtenerPorId(idEvento);
        evento.setEstado("DESHABILITADO");
        evento.setAdminDeshabilita(admin);
        return eventoRepository.save(evento);
    }

    /**
     * Obtiene un evento por su identificador.
     *
     * <p>Si el evento no existe, se lanza una excepción.</p>
     *
     * @param id identificador del evento.
     * @return evento correspondiente.
     * @throws RuntimeException si no se encuentra el evento.
     * @since 0.1
     */
    public Evento obtenerPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }
}
