package com.newgaming.votacionGestor.components;

import com.newgaming.votacionGestor.repositories.JuradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


/**
 * Componente encargado de verificar si el usuario autenticado posee privilegios
 * de administrador dentro del sistema.
 *
 * <p>Este verificador se utiliza en distintas capas de la aplicación para
 * determinar si la operación solicitada debe permitirse únicamente a usuarios
 * con el rol <strong>ADMIN</strong>. La comprobación se realiza consultando
 * las autoridades presentes en el contexto de seguridad actual.</p>
 *
 * <p>El método {@link #esAdmin()} es estático para permitir su uso directo
 * desde expresiones de seguridad en anotaciones como {@code @PreAuthorize},
 * sin necesidad de instanciar el componente.</p>
 *
 * @author Guillermo Martín Chippirraz
 * @version v0.3
 */
@Component
public class VerificacionAdmin {

    @Autowired
    private JuradoRepository juradoRepository;

    /**
     * Determina si el usuario autenticado tiene el rol de administrador.
     *
     * <p>La verificación se realiza inspeccionando las autoridades asociadas
     * al usuario actual en el {@link SecurityContextHolder}. Si alguna de ellas
     * coincide con {@code ROLE_ADMIN}, se considera que el usuario posee
     * privilegios administrativos.</p>
     *
     * @since v0.3
     * @return {@code true} si el usuario autenticado es administrador;
     *         {@code false} en caso contrario.
     */
    public static boolean esAdmin() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}