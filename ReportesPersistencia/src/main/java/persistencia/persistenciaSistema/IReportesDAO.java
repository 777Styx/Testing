/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia.persistenciaSistema;

import java.util.List;
import persistencia.entidades.ReporteEntity;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author Oliver Valle
 */
public interface IReportesDAO {
    
    ReporteEntity insertarReporte(ReporteEntity reporte) throws PersistenciaException ;
    
    ReporteEntity validarReporte(ReporteEntity reporte) throws PersistenciaException ;
    
    ReporteEntity modificarReporte(ReporteEntity reporte) throws PersistenciaException ;
    
    boolean notificarReporte(ReporteEntity reporte) throws PersistenciaException ;
    
    List<ReporteEntity> recuperarReportes() throws PersistenciaException ;
    
    public void insertarReportesSimulados() throws PersistenciaException ;
    
    List<ReporteEntity> recuperarReportesAlumno(String curp) throws PersistenciaException ;
    
}
