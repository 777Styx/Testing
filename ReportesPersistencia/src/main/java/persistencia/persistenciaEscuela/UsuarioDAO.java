package persistencia.persistenciaEscuela;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.conexionBD.ConexionEscuela;
import persistencia.entidades.UsuarioEntity;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author Asiel Apodaca Monge
 */
public class UsuarioDAO implements IUsuarioDAO{

    private MongoCollection<UsuarioEntity> coleccion;
    private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());
    
    
    public UsuarioDAO() {
        this.coleccion = ConexionEscuela.obtenerBaseDeDatos().getCollection("Docentes", UsuarioEntity.class);;
    }
    
    @Override
    public void insertarDocentesSimulados() throws PersistenciaException {
        try {
            if(coleccion.countDocuments() == 0) {
                coleccion.insertMany(listaDocentesSimulado());
            }
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Ya hay docentes insertados", e.getMessage());
            throw new PersistenciaException("Ya hay docentes insertados") ;
        }
    }
    
    @Override
    public UsuarioEntity obtenerDocente(UsuarioEntity de) throws PersistenciaException {
        try {
            if(de.getId() == null) throw new Exception("El id del docente es nulo.");
            return coleccion.find(Filters.eq("_id", de.getId())).first();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("Error al obtener Docente") ;
        }
    }

    @Override
    public UsuarioEntity obtenerDocentePorCurp(UsuarioEntity de) throws PersistenciaException {
        try {
            if(de.getCurp() == null) throw new Exception("La curp del docente es nula.");
            return coleccion.find(Filters.eq("curp", de.getCurp())).first();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("Error al obtener Docente") ;
        }
    }
    
    // Para pruebas
    private List<UsuarioEntity> listaDocentesSimulado() {
        UsuarioEntity docente1 = new UsuarioEntity("GALJ940519HDFLRN05", "Juan", "García", "López", "DOCENTE", "1234") ;
        UsuarioEntity docente2 = new UsuarioEntity("ROHM000712MDFDRR07", "María", "Rodríguez", "Hernández", "PREFECTO", "1234") ;
        UsuarioEntity docente3 = new UsuarioEntity("PEMC010224HDFRRL00", "Carlos", "Pérez", "Martínez", "DIRECTIVO", "1234") ;
        
        List<UsuarioEntity> docentes = new ArrayList<>();
        docentes.add(docente1);
        docentes.add(docente2);
        docentes.add(docente3);
        
        return docentes;
    }

    

}
