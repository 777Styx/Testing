/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dto.AlumnoDTO;
import dto.ReporteDTO;
import dto.ReporteExpedienteDTO;
import excepciones.SubsistemaException;
import java.util.List;
import negocios.bo.IIncidenciasBO;
import negocios.bo.IncidenciasBO;
import negocios.excepciones.NegociosException;



/**
 *
 * @author Oliver Valle
 */
public class GestionarIncidencias {
    private IIncidenciasBO incidenciasBO ;
    
    public GestionarIncidencias() {
        incidenciasBO = new IncidenciasBO() ;
    }
    
    public void crearReporte(ReporteDTO reporteNuevo) throws SubsistemaException{
        try {
            incidenciasBO.crearReporte(reporteNuevo);
        } catch (NegociosException e) {
            throw new SubsistemaException(e.getMessage()) ;
        }
    }
    
    public ReporteDTO validarReporte(ReporteDTO reporteNuevo) throws SubsistemaException{
        try {
            reporteNuevo.setValidado(true);
            return incidenciasBO.validarReporte(reporteNuevo) ;
        } catch (NegociosException e) {
            throw new SubsistemaException(e.getMessage()) ;
        }
    }

    public boolean notificarReporte(ReporteDTO reporteNuevo) throws SubsistemaException{
        try {
            reporteNuevo.setNotificado(true);
            return incidenciasBO.notificarReporte(reporteNuevo) ;
        } catch (NegociosException e) {
            throw new SubsistemaException(e.getMessage()) ;
        }
    }

    public List<ReporteDTO> recuperarReportes() throws SubsistemaException{
        try {
            return incidenciasBO.recuperarReportes() ;
        } catch (NegociosException e) {
            throw new SubsistemaException(e.getMessage()) ;
        }
    }
    
    public void insertDatosSimulados() throws SubsistemaException{
        try {
            incidenciasBO.insertDatosSimulados();
        } catch (NegociosException e) {
            throw new SubsistemaException(e.getMessage()) ;
        }
    }

    public List<AlumnoDTO> recuperarAlumnosPorGrado(String grado) throws SubsistemaException{
        try {
            return incidenciasBO.recuperarAlumnosPorGrado(grado) ;
        } catch (NegociosException e) {
            throw new SubsistemaException(e.getMessage()) ;
        }
    }
    
    public List<AlumnoDTO> recuperarAlumnosPorGrupo(String grupo) throws SubsistemaException{
        try {
            return incidenciasBO.recuperarAlumnosPorGrupo(grupo) ;
        } catch (NegociosException e) {
            throw new SubsistemaException(e.getMessage()) ;
        }
    }
    
    public List<AlumnoDTO> recuperarAlumnosPorGradoYGrupo(String grado, String grupo) throws SubsistemaException{
        try {
            return incidenciasBO.recuperarAlumnosPorGradoYGrupo(grado, grupo) ;
        } catch (NegociosException e) {
            throw new SubsistemaException(e.getMessage()) ;
        }
    }
    
    public List<ReporteDTO> recuperarReportesAlumno(String curp) throws SubsistemaException{
        try {
            return incidenciasBO.recuperarReportesAlumno(curp) ;
        } catch (NegociosException e) {
            throw new SubsistemaException(e.getMessage()) ;
        }
    }
    
    public List<ReporteExpedienteDTO> convertirReportesAReporteExpediente(List<ReporteDTO> reportes) throws SubsistemaException{
        try {
            return incidenciasBO.convertirReporteAReporteExpediente(reportes) ;
        } catch (NegociosException e) {
            throw new SubsistemaException(e.getMessage()) ;
        }
    }
}
