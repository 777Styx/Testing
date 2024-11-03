/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.persistenciaSistema;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.conexionBD.ConexionMongo;
import persistencia.entidades.AlumnoEntity;
import persistencia.entidades.UsuarioEntity;
import persistencia.entidades.NivelIncidenciaPersistencia;
import persistencia.entidades.ReporteEntity;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author Oliver Valle
 */
public class ReportesDAO implements IReportesDAO {
    
    private MongoCollection<ReporteEntity> coleccion;
    private static final Logger LOG = Logger.getLogger(ReportesDAO.class.getName());
    
    
    public ReportesDAO() {
        this.coleccion = ConexionMongo.obtenerBaseDeDatos().getCollection("Reportes", ReporteEntity.class);;
    }
    
    @Override
    public ReporteEntity insertarReporte(ReporteEntity reporte) throws PersistenciaException {
        try {
            coleccion.insertOne(reporte);
            return null ;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Ocurri\u00f3 algo al insertar reportes: {0}", e.getMessage());
            throw new PersistenciaException("Error al insertar reportes") ;
        } 
    }
    
    @Override
    public void insertarReportesSimulados() throws PersistenciaException {
        try {
            if(coleccion.countDocuments() == 0) {
                coleccion.insertMany(listaReportesSimulado());
            }
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Ya hay reportes insertados", e.getMessage());
            throw new PersistenciaException("Ya hay reportes insertados") ;
        }
    }
    
    @Override
    public ReporteEntity validarReporte(ReporteEntity reporte) throws PersistenciaException {
        ReporteEntity reporteBuscado = coleccion.find(Filters.eq("_id", reporte.getId())).first();
        if(reporteBuscado == null) return null;
        
        try {
            coleccion.updateOne(Filters.eq("_id", reporte.getId()), Updates.combine(
                    Updates.set("nivelIncidencia", reporte.getNivelIncidencia().toString()),
                    Updates.set("descripcion", reporte.getDescripcion()),
                    Updates.set("motivo", reporte.getMotivo())
            ));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "No se pudo actualizar el estado de validado: {0}", e.getMessage());
            throw new PersistenciaException("No se pudo actualizar el estado de validado") ;
        }
        return coleccion.find(Filters.eq("_id", reporteBuscado.getId())).first();
    }

    @Override
    public ReporteEntity modificarReporte(ReporteEntity reporte) throws PersistenciaException {
        try {
            coleccion.replaceOne(Filters.eq("_id", reporte.getId()), reporte);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "No se pudo modificar el reporte", e.getMessage());
            throw new PersistenciaException("No se pudo modificar el reporte") ;
        }
        
        return coleccion.find(Filters.eq("_id", reporte.getId())).first();
    }

    @Override
    public boolean notificarReporte(ReporteEntity reporte) throws PersistenciaException {
        try {
            coleccion.updateOne(Filters.eq("_id", reporte.getId()), Updates.set("notificado", true));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Hubo un error al cambiar estado de notificado: ", e.getMessage());
            return false;
        }
        
        return true;
    }

    @Override
    public List<ReporteEntity> recuperarReportes() throws PersistenciaException {
        try {
            return coleccion.find().into(new ArrayList<>());
        } catch (Exception e) {
            throw new PersistenciaException("Hubo un error al recuperar reportes") ;
        }
    }
    
    @Override
    public List<ReporteEntity> recuperarReportesAlumno(String curp) throws PersistenciaException {
        try {
            return coleccion.find(Filters.eq("alumno.cURP", curp)).into(new ArrayList()) ;
        } catch (Exception e) {
            throw new PersistenciaException("Hubo un error al recuperar reportes") ;
        }
    }
    
    // Para pruebas
    private List<ReporteEntity> listaReportesSimulado() {
        AlumnoEntity alumno1 = new AlumnoEntity ("IUVO040706HSLNLLA2", "Oliver", "Inzunza", "Valle", "3B", "oliver.inzunza244748@potros.itson.edu.mx", "src/main/java/fotos/foto_oliver.jpeg") ;
        AlumnoEntity alumno2 = new AlumnoEntity ("CAMG040802HSRSLLA5", "Gael Rafael", "Castro", "Molina", "2A", "asiel.apodaca247722@potros.itson.edu.mx", "src/main/java/fotos/foto_gael.jpeg") ;
        AlumnoEntity alumno3 = new AlumnoEntity ("AOMA040301HSRPNSA3", "Asiel", "Apodaca", "Monge", "1C", "juan.delrio216014@potros.itson.edu.mx", "src/main/java/fotos/foto_asiel.jpeg") ;
        AlumnoEntity alumno4 = new AlumnoEntity ("OUQA040309HSRLRMA5", "Amós Helí", "Olguín", "Quiróz", "3A", "oliver.inzunza244748@potros.itson.edu.mx", "src/main/java/fotos/foto_amos.jpeg") ;
        AlumnoEntity alumno5 = new AlumnoEntity ("AEPG040701HSRRRMA6", "Gamaliel", "Armenta", "Perez", "1C", "asiel.apodaca247722@potros.itson.edu.mx", "src/main/java/fotos/foto_gama.jpg") ;
        AlumnoEntity alumno6 = new AlumnoEntity ("RITJ010224HSRXPNA4", "Juan Pablo", "Del Río", "Tapia", "3C", "juan.delrio216014@potros.itson.edu.mx", "src/main/java/fotos/foto_jp.jpeg") ;
        
        UsuarioEntity docente1 = new UsuarioEntity("GALJ940519HDFLRN05", "Juan", "García", "López", "DOCENTE", "1234") ;
        UsuarioEntity docente2 = new UsuarioEntity("ROHM000712MDFDRR07", "María", "Rodríguez", "Hernández", "PREFECTO", "1234") ;
        UsuarioEntity docente3 = new UsuarioEntity("PEMC010224HDFRRL00", "Carlos", "Pérez", "Martínez", "DIRECTIVO", "1234") ;
        
        ReporteEntity reporte1 = new ReporteEntity(alumno4, docente1, 
                NivelIncidenciaPersistencia.GRAVE, "Se encontró al alumno fumando un tabaco en la entrada de los baños de hombres", 
                "Fumar Tabaco dentro de la Escuela", new Date(), false, false) ;
        ReporteEntity reporte2 = new ReporteEntity(alumno1, docente1, 
                NivelIncidenciaPersistencia.LEVE, "El alumno alzó la voz y ofendió con groserías a su compañero en el Aula", 
                "Hablar con Lenguaje Inapropiado", new Date(), false, false) ;
        ReporteEntity reporte3 = new ReporteEntity(alumno3, docente2, 
                NivelIncidenciaPersistencia.SEVERO, "Este alumno inició una pelea y su compañero al que agredió no pudo defenderse, fué leve y no pasó a mayores", 
                "Iniciar Pelea", new Date(), false, false) ;
        ReporteEntity reporte4 = new ReporteEntity(alumno5, docente2, 
                NivelIncidenciaPersistencia.LEVE, "El alumno lleva 1 semana entera faltando a mi clase de Matemáticas, no ha presentado justificante y diario se le puede ver como se va de otras clases", 
                "Inasistencia frecuente", new Date(), false, false) ;
        ReporteEntity reporte5 = new ReporteEntity(alumno6, docente3, 
                NivelIncidenciaPersistencia.LEVE, "Este alumno estuvo tirando basura dentro de la institución sin importarle las constantes llamadas de atención que le hice", 
                "Contaminación ambiental en la institución", new Date(), false, false) ;
        ReporteEntity reporte6 = new ReporteEntity(alumno2, docente3, 
                NivelIncidenciaPersistencia.GRAVE, "El alumno estuvo ingiriendo bebidas alcoholicas a media clase desde su termo", 
                "Consumo de bebidas alcoholicas dentro del salón", new Date(), false, false) ;
        
        List<ReporteEntity> reportes = new ArrayList<>();
        reportes.add(reporte1);
        reportes.add(reporte2);
        reportes.add(reporte3);
        reportes.add(reporte4);
        reportes.add(reporte5);
        reportes.add(reporte6);
        
        return reportes;
        
    }
    
}
