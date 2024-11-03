package fachada;

import control.CtrlAdminAcceso;
import dto.UsuarioDTO;
import excepciones.SubsistemaException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author Asiel Apodaca Monge
 */
public class FachadaAdminAcceso implements IadminAcceso{

    @Override
    public UsuarioDTO iniciarSesion(UsuarioDTO usuarioDTO) throws SubsistemaException {
        try {
            return CtrlAdminAcceso.iniciarSesion(usuarioDTO);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FachadaAdminAcceso.class.getName()).log(Level.SEVERE, null, ex);

            throw new SubsistemaException(ex.getMessage());        
        }
    }

}
