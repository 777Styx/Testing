package fachada;

import dto.UsuarioDTO;
import excepciones.SubsistemaException;

/**
 *
 * @author Asiel Apodaca Monge
 */
public interface IadminAcceso {
    public UsuarioDTO iniciarSesion(UsuarioDTO usuarioDTO) throws SubsistemaException;
}
