/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package persistencia.persistenciaEscuela;

import java.util.List;
import persistencia.entidades.AlumnoEntity;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author Asiel Apodaca Monge
 */
public interface IAlumnoDAO {
    public void insertarAlumnosSimulados() throws PersistenciaException ;
    public AlumnoEntity obtenerAlumno(AlumnoEntity ae) throws PersistenciaException ;
    public AlumnoEntity obtenerAlumnoPorCurp(AlumnoEntity ae) throws PersistenciaException ;
    public List<AlumnoEntity> recuperarAlumnosPorGrado(String grado) throws PersistenciaException ;
    public List<AlumnoEntity> recuperarAlumnosPorGrupo(String grupo) throws PersistenciaException ;
    public List<AlumnoEntity> recuperarAlumnosPorGradoYGrupo(String grado, String grupo) throws PersistenciaException ;
}
