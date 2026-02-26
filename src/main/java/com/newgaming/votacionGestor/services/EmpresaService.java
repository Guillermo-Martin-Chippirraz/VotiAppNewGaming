package com.newgaming.votacionGestor.services;

import com.newgaming.votacionGestor.models.Empresa;
import com.newgaming.votacionGestor.models.Evento;
import com.newgaming.votacionGestor.models.EventoEmpresa;
import com.newgaming.votacionGestor.models.EventoEmpresaId;
import com.newgaming.votacionGestor.repositories.EmpresaRepository;
import com.newgaming.votacionGestor.repositories.EventoEmpresaRepository;
import org.springframework.stereotype.Service;

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
 * @version 0.1
 */
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EventoEmpresaRepository eventoEmpresaRepository;

    /**
     * Constructor que inyecta los repositorios necesarios para operar con empresas
     * y relaciones entre eventos y empresas.
     *
     * @param empresaRepository repositorio de empresas.
     * @param eventoEmpresaRepository repositorio de relaciones Evento-Empresa.
     */
    public EmpresaService(EmpresaRepository empresaRepository,
                          EventoEmpresaRepository eventoEmpresaRepository) {
        this.empresaRepository = empresaRepository;
        this.eventoEmpresaRepository = eventoEmpresaRepository;
    }

    /**
     * Crea una nueva empresa en el sistema.
     *
     * <p>Este método guarda la empresa en la base de datos y devuelve la entidad
     * persistida.</p>
     *
     * @param empresa empresa a registrar.
     * @return empresa registrada.
     */
    public Empresa crearEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
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
     * Obtiene una empresa por su identificador.
     *
     * <p>Si la empresa no existe, se lanza una excepción.</p>
     *
     * @param idEmpresa identificador de la empresa.
     * @return empresa correspondiente.
     * @throws RuntimeException si no se encuentra la empresa.
     */
    public Empresa obtenerPorId(Long idEmpresa) {
        return empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }
}
