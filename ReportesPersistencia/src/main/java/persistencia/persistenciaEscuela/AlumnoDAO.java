package persistencia.persistenciaEscuela;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.conexionBD.ConexionEscuela;
import persistencia.entidades.AlumnoEntity;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author Asiel Apodaca Monge
 */
public class AlumnoDAO implements IAlumnoDAO{

    private MongoCollection<AlumnoEntity> coleccion;
    private static final Logger LOG = Logger.getLogger(AlumnoDAO.class.getName());
    
    
    public AlumnoDAO() {
        this.coleccion = ConexionEscuela.obtenerBaseDeDatos().getCollection("Alumnos", AlumnoEntity.class);;
    }
    
    @Override
    public void insertarAlumnosSimulados() throws PersistenciaException {
        try {
            if(coleccion.countDocuments() == 0) {
                coleccion.insertMany(listaAlumnosSimulados());
            }
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Ya hay alumnos insertados", e.getMessage());
            throw new PersistenciaException("Ya hay alumnos insertados") ;
        }
    }
    
    @Override
    public AlumnoEntity obtenerAlumno(AlumnoEntity ae) throws PersistenciaException {
        try {
            if(ae.getId() == null) throw new PersistenciaException("El id del alumno es nulo.");
            return coleccion.find(Filters.eq("_id", ae.getId())).first();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("Error al obtener Alumno") ;
        }
    }

    @Override
    public AlumnoEntity obtenerAlumnoPorCurp(AlumnoEntity ae) throws PersistenciaException {
        try {
            if(ae.getCURP() == null) throw new PersistenciaException("La curp del docente es nula.");
            return coleccion.find(Filters.eq("CURP", ae.getCURP())).first();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("Error al obtener Alumno" );
        }
    }
    
    @Override
    public List<AlumnoEntity> recuperarAlumnosPorGrado(String grado) throws PersistenciaException {
        try {
            return coleccion.find(Filters.regex("gradoGrupo", "^" + grado)).into(new ArrayList()) ;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener Alumnos") ;
        }
    }

    @Override
    public List<AlumnoEntity> recuperarAlumnosPorGrupo(String grupo) throws PersistenciaException {
        try {
            return coleccion.find(Filters.regex("gradoGrupo", grupo + "$")).into(new ArrayList()) ;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener Alumnos") ;
        }
    }

    @Override
    public List<AlumnoEntity> recuperarAlumnosPorGradoYGrupo(String grado, String grupo) throws PersistenciaException {
        try {
            return coleccion.find(Filters.eq("gradoGrupo", grado + grupo)).into(new ArrayList()) ;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener Alumnos") ;
        }
    }
    
    // Para pruebas
    public List<AlumnoEntity> listaAlumnosSimulados() {
        AlumnoEntity alumno1 = new AlumnoEntity ("IUVO040706HSLNLLA2", "Oliver", "Inzunza", "Valle", "3B", "oliver.inzunza244748@potros.itson.edu.mx", "src/main/java/fotos/foto_oliver.jpeg") ;
        AlumnoEntity alumno2 = new AlumnoEntity ("CAMG040802HSRSLLA5", "Gael Rafael", "Castro", "Molina", "2A", "asiel.apodaca247722@potros.itson.edu.mx", "src/main/java/fotos/foto_gael.jpeg") ;
        AlumnoEntity alumno3 = new AlumnoEntity ("AOMA040301HSRPNSA3", "Asiel", "Apodaca", "Monge", "1C", "juan.delrio216014@potros.itson.edu.mx", "src/main/java/fotos/foto_asiel.jpeg") ;
        AlumnoEntity alumno4 = new AlumnoEntity ("OUQA040309HSRLRMA5", "Amós Helí", "Olguín", "Quiróz", "3A", "oliver.inzunza244748@potros.itson.edu.mx", "src/main/java/fotos/foto_amos.jpeg") ;
        AlumnoEntity alumno5 = new AlumnoEntity ("AEPG040701HSRRRMA6", "Gamaliel", "Armenta", "Perez", "1C", "asiel.apodaca247722@potros.itson.edu.mx", "src/main/java/fotos/foto_gama.jpg") ;
        AlumnoEntity alumno6 = new AlumnoEntity ("RITJ010224HSRXPNA4", "Juan Pablo", "Del Río", "Tapia", "3C", "juan.delrio216014@potros.itson.edu.mx", "src/main/java/fotos/foto_jp.jpeg");
        
        List<AlumnoEntity> alumnos = new ArrayList<>();
        
        alumnos.add(alumno1);
        alumnos.add(alumno2);
        alumnos.add(alumno3);
        alumnos.add(alumno4);
        alumnos.add(alumno5);
        alumnos.add(alumno6);
        
        return alumnos;
    }

    

}
