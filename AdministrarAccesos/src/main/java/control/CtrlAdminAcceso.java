package control;

import dto.UsuarioDTO;
import persistencia.entidades.UsuarioEntity;
import persistencia.excepciones.PersistenciaException;
import persistencia.persistenciaEscuela.IUsuarioDAO;
import persistencia.persistenciaEscuela.UsuarioDAO;

/**
 *
 * @author Asiel Apodaca Monge
 */
public class CtrlAdminAcceso {
    public static UsuarioDTO iniciarSesion(UsuarioDTO usuarioDTO) throws PersistenciaException {
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        
        UsuarioEntity ue = new UsuarioEntity();
        ue.setCurp(usuarioDTO.getCurp());
        
        ue = usuarioDAO.obtenerDocentePorCurp(ue);
        if(ue == null) return null;
        if(!ue.getPin().equals(usuarioDTO.getPin())) return null;
        
        UsuarioDTO usuario = new UsuarioDTO(
                ue.getId(), 
                ue.getCurp(), 
                ue.getNombre(), 
                ue.getApellidoP(), 
                ue.getApellidoM(), 
                ue.getRol(), 
                ue.getPin()
        );
        
        return usuario;
    }
}
