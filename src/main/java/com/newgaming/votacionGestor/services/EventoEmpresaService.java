package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.models.Empresa;
import com.newgaming.votacionGestor.models.Evento;
import com.newgaming.votacionGestor.models.EventoEmpresa;
import com.newgaming.votacionGestor.models.EventoEmpresaId;
import com.newgaming.votacionGestor.repositories.EmpresaRepository;
import com.newgaming.votacionGestor.repositories.EventoEmpresaRepository;
import com.newgaming.votacionGestor.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de gestionar la relación entre eventos y empresas.
 *
 * <p>Permite asignar empresas a eventos mediante la creación de la entidad
 * intermedia <code>EventoEmpresa</code>, que representa una relación de
 * muchos a muchos entre ambas entidades.</p>
 *
 * <p>Este servicio valida la existencia de los elementos involucrados y evita
 * duplicidades en la asignación.</p>
 *
 * <p>Se utiliza principalmente desde la capa de controladores para gestionar
 * la lógica de asociación entre eventos y empresas.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.2
 */
@Service
public class EventoEmpresaService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EventoEmpresaRepository eventoEmpresaRepository;

    /**
     * Asigna una empresa a un evento creando la relación correspondiente.
     *
     * <p>Este método realiza varias validaciones antes de persistir la relación:</p>
     *
     * <ul>
     *     <li>Verifica que el evento exista.</li>
     *     <li>Verifica que la empresa exista.</li>
     *     <li>Comprueba que la relación no exista previamente.</li>
     *     <li>Crea la entidad <code>EventoEmpresa</code> con su ID compuesto.</li>
     *     <li>Guarda la relación en la base de datos.</li>
     * </ul>
     *
     * <p>Si alguna validación falla, se lanza una excepción con un mensaje descriptivo.</p>
     *
     * @since v0.2
     * @param idEvento identificador del evento al que se desea asignar la empresa.
     * @param idEmpresa identificador de la empresa que se desea asociar.
     * @return entidad <code>EventoEmpresa</code> persistida.
     * @throws RuntimeException si el evento o la empresa no existen, o si la relación ya está registrada.
     */
    public EventoEmpresa asignarEmpresa(Long idEvento, Long idEmpresa) {

        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        EventoEmpresaId id = new EventoEmpresaId(idEvento, idEmpresa);

        if (eventoEmpresaRepository.existsById(id)) {
            throw new RuntimeException("La empresa ya está asignada a este evento");
        }

        EventoEmpresa relacion = new EventoEmpresa(id, evento, empresa);

        return eventoEmpresaRepository.save(relacion);
    }
}