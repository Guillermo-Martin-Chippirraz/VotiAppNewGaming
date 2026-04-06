package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.DTO.EditarEmpresaDTO;
import com.newgaming.votacionGestor.components.VerificacionAdmin;
import com.newgaming.votacionGestor.models.Empresa;
import com.newgaming.votacionGestor.models.Evento;
import com.newgaming.votacionGestor.models.EventoEmpresa;
import com.newgaming.votacionGestor.models.EventoEmpresaId;
import com.newgaming.votacionGestor.repositories.EmpresaRepository;
import com.newgaming.votacionGestor.repositories.EventoEmpresaRepository;
import com.newgaming.votacionGestor.repositories.FormularioRepository;
import com.newgaming.votacionGestor.repositories.VotacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con las empresas.
 * Permite crear nuevas empresas, asignarlas a eventos y consultarlas por su identificador.
 *
 * <p>Este servicio actúa como intermediario entre los controladores y los repositorios,
 * encapsulando la lógica necesaria para operar con las entidades {@link Empresa},
 * {@link Evento} y {@link EventoEmpresa}.</p>
 *
 * <p>Responsabilidades principales:</p>
 * <ul>
 *     <li>Registrar nuevas empresas.</li>
 *     <li>Asociar empresas a eventos mediante la entidad relacional.</li>
 *     <li>Consultar empresas por su identificador.</li>
 * </ul>
 *
 * <p>La anotación {@link Service} permite que Spring gestione esta clase como un
 * componente de servicio dentro del contexto de la aplicación.</p>
 * @author Guillermo Martín Chippirraz
 * @version v0.3
 */
@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EventoEmpresaRepository eventoEmpresaRepository;

    @Autowired
    private VotacionRepository votacionRepository;

    @Autowired
    private FormularioRepository formularioRepository;

    /**
     * Crea una nueva empresa en el sistema.
     *
     * <p>Este método guarda la empresa en la base de datos y devuelve la entidad
     * persistida.</p>
     *
     * @since v0.1
     * @param empresa empresa a registrar.
     * @return empresa registrada.
     */
    public Empresa crearEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    /**
     * Edita los datos de una empresa existente.
     *
     * <p>Este método permite modificar los campos básicos de una empresa, tales como
     * nombre, descripción y URL del logotipo. Antes de aplicar los cambios, se
     * realizan las siguientes validaciones:</p>
     *
     * <ul>
     *     <li>Se verifica que la empresa exista; de lo contrario, se devuelve un estado 404.</li>
     *     <li>Se comprueba que el usuario autenticado sea administrador.</li>
     *     <li>Solo se actualizan los campos que hayan sido proporcionados en el DTO.</li>
     * </ul>
     *
     * <p>Tras aplicar los cambios, se actualiza la fecha de modificación y se guarda
     * la empresa en la base de datos.</p>
     *
     * @since v0.3
     * @param idEmpresa identificador de la empresa a editar.
     * @param dto datos a modificar en la empresa.
     * @return la empresa actualizada.
     * @throws ResponseStatusException si la empresa no existe o el usuario no es administrador.
     */
    @Transactional
    public Empresa editarEmpresa(Long idEmpresa, EditarEmpresaDTO dto) {

        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Empresa no encontrada"
                ));

        if (!VerificacionAdmin.esAdmin())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sólo los administradores pueden editar los datos de una empresa.");

        if (dto.getNombre() != null && !dto.getNombre().isBlank()) {
            empresa.setNombre(dto.getNombre());
        }

        if (dto.getDescripcion() != null) {
            empresa.setDescripcion(dto.getDescripcion());
        }

        if (dto.getLogoUrl() != null) {
            empresa.setLogoUrl(dto.getLogoUrl());
        }

        empresa.setActualizadoEn(LocalDateTime.now());

        return empresaRepository.save(empresa);
    }

    /**
     * Elimina una empresa por su identificador.
     *
     * <p>Este método realiza las siguientes acciones:</p>
     * <ul>
     *     <li>Verifica que la empresa exista; si no, lanza un estado 404.</li>
     *     <li>Comprueba que el usuario autenticado sea administrador.</li>
     *     <li>Elimina todas las relaciones de la empresa con eventos.</li>
     *     <li>Elimina todas las votaciones asociadas a la empresa.</li>
     *     <li>Finalmente, elimina la empresa del sistema.</li>
     * </ul>
     *
     * <p>Este proceso garantiza la integridad referencial y evita que queden datos
     * huérfanos en la base de datos.</p>
     *
     * @since v0.3
     * @param idEmpresa identificador de la empresa a eliminar.
     * @throws ResponseStatusException si la empresa no existe o el usuario no es administrador.
     */
    @Transactional
    public void eliminarEmpresa(Long idEmpresa) {
        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Empresa no encontrada"
                ));

        if (!VerificacionAdmin.esAdmin())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sólo los administradores pueden eliminar empresas.");

        eventoEmpresaRepository.deleteByEmpresaIdEmpresa(idEmpresa);

        votacionRepository.deleteByEmpresaIdEmpresa(idEmpresa);

        empresaRepository.delete(empresa);
    }

    /**
     * Asigna una empresa a un evento creando una relación en la tabla intermedia.
     *
     * <p>Este método:</p>
     * <ul>
     *     <li>Crea un objeto {@link EventoEmpresa} con su clave compuesta.</li>
     *     <li>Asocia el evento y la empresa proporcionados.</li>
     *     <li>Guarda la relación en la base de datos.</li>
     * </ul>
     *
     * @since v0.1
     * @param empresa empresa que se desea asociar.
     * @param evento evento al que se asigna la empresa.
     */
    public void asignarEmpresaAEvento(Empresa empresa, Evento evento) {
        EventoEmpresa ee = new EventoEmpresa(
                new EventoEmpresaId(evento.getIdEvento(), empresa.getIdEmpresa()),
                evento,
                empresa
        );
        eventoEmpresaRepository.save(ee);
    }

    /**
     * Retira una empresa de un evento específico.
     *
     * <p>Este método elimina la relación entre una empresa y un evento. Antes de
     * realizar la operación, se aplican las siguientes validaciones:</p>
     *
     * <ul>
     *     <li>Se verifica que la relación entre el evento y la empresa exista; de lo contrario, se devuelve un estado 404.</li>
     *     <li>Se comprueba que el usuario autenticado sea administrador.</li>
     * </ul>
     *
     * <p>Si las validaciones son correctas, la relación {@link EventoEmpresa} es eliminada
     * del repositorio, retirando efectivamente a la empresa del evento.</p>
     *
     * @since v0.3
     * @param idEvento identificador del evento del cual se desea retirar la empresa.
     * @param idEmpresa identificador de la empresa que se desea retirar del evento.
     * @throws ResponseStatusException si la relación no existe o el usuario no es administrador.
     */
    @Transactional
    public void retirarEmpresaDeEvento(Long idEvento, Long idEmpresa) {

        EventoEmpresaId id = new EventoEmpresaId(idEvento, idEmpresa);

        EventoEmpresa relacion = eventoEmpresaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "La empresa no está asignada a este evento"
                ));

        if (!VerificacionAdmin.esAdmin())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Sólo los administradores pueden eliminar empresas.");

        eventoEmpresaRepository.delete(relacion);
    }

    /**
     * Obtiene una empresa por su identificador.
     *
     * <p>Si la empresa no existe, se lanza una excepción.</p>
     *
     * @since v0.1
     * @param idEmpresa identificador de la empresa.
     * @return empresa correspondiente.
     * @throws RuntimeException si no se encuentra la empresa.
     */
    public Empresa obtenerPorId(Long idEmpresa) {
        return empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }
}
