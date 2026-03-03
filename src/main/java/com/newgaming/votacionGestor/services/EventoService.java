package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.models.Administrador;
import com.newgaming.votacionGestor.models.Evento;
import com.newgaming.votacionGestor.repositories.EmpresaRepository;
import com.newgaming.votacionGestor.repositories.EventoRepository;
import com.newgaming.votacionGestor.repositories.EventoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
 * @version v0.2
 */
@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EventoUsuarioRepository eventoUsuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    /**
     * Crea un nuevo evento en el sistema.
     *
     * @param evento evento a registrar.
     * @return evento registrado.
     * @since v0.1
     */
    public Evento crearEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    /**
     * Habilita un evento y registra qué administrador realizó la acción.
     *
     * <p>Este método actualiza el estado del evento a <code>HABILITADO</code> y deja constancia
     * del administrador responsable de la acción. Además, actualiza la marca temporal de
     * modificación para mantener la trazabilidad del registro.</p>
     *
     * <ul>
     *     <li>Obtiene el evento mediante su identificador.</li>
     *     <li>Modifica su estado a <code>HABILITADO</code>.</li>
     *     <li>Registra el administrador que realizó la habilitación.</li>
     *     <li>Actualiza la fecha y hora de modificación.</li>
     *     <li>Guarda los cambios en la base de datos.</li>
     * </ul>
     *
     * @param idEvento identificador del evento que se desea habilitar.
     * @param admin administrador que realiza la acción.
     * @return el evento actualizado y persistido.
     * @since v0.1
     */
    public Evento habilitarEvento(Long idEvento, Administrador admin) {
        Evento evento = obtenerPorId(idEvento);
        evento.setEstado("HABILITADO");
        evento.setAdminHabilita(admin);
        evento.setActualizadoEn(LocalDateTime.now());

        return eventoRepository.save(evento);
    }

    /**
     * Deshabilita un evento y registra qué administrador realizó la acción.
     *
     * <p>Este método cambia el estado del evento a <code>DESHABILITADO</code> y registra
     * al administrador responsable de la acción. También actualiza la marca temporal
     * de modificación para mantener un historial claro de cambios.</p>
     *
     * <ul>
     *     <li>Obtiene el evento mediante su identificador.</li>
     *     <li>Modifica su estado a <code>DESHABILITADO</code>.</li>
     *     <li>Registra el administrador que realizó la deshabilitación.</li>
     *     <li>Actualiza la fecha y hora de modificación.</li>
     *     <li>Guarda los cambios en la base de datos.</li>
     * </ul>
     *
     * @param idEvento identificador del evento que se desea deshabilitar.
     * @param admin administrador que realiza la acción.
     * @return el evento actualizado y persistido.
     * @since v0.1
     */
    public Evento deshabilitarEvento(Long idEvento, Administrador admin) {
        Evento evento = obtenerPorId(idEvento);
        evento.setEstado("DESHABILITADO");
        evento.setAdminDeshabilita(admin);
        evento.setActualizadoEn(LocalDateTime.now());
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
     * @since v0.1
     */
    public Evento obtenerPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }
}
