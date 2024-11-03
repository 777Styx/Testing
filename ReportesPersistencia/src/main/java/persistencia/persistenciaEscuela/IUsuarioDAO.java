package persistencia.persistenciaEscuela;

import persistencia.entidades.UsuarioEntity;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author Asiel Apodaca Monge
 */
public interface IUsuarioDAO {
    public void insertarDocentesSimulados() throws PersistenciaException ;
    public UsuarioEntity obtenerDocente(UsuarioEntity de) throws PersistenciaException ;
    public UsuarioEntity obtenerDocentePorCurp(UsuarioEntity de) throws PersistenciaException ;
}
