/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fachada;

import dto.AlumnoDTO;
import dto.ReporteDTO;
import dto.ReporteExpedienteDTO;
import excepciones.SubsistemaException;
import java.util.List;

/**
 *
 * @author Oliver Valle
 */
public interface IFachadaGestionarIncidencias {
    ReporteDTO validarReporte(ReporteDTO reporteNuevo) throws SubsistemaException ;
    
    void crearReporte(ReporteDTO reporteNuevo) throws SubsistemaException ;
    
    boolean notificarReporte(ReporteDTO reporteNuevo) throws SubsistemaException ;
    
    List<ReporteDTO> recuperarReportes() throws SubsistemaException ;
    
    public void insertDatosSimulados() throws SubsistemaException ;
    
    public List<AlumnoDTO> recuperarAlumnosPorGrado(String grado) throws SubsistemaException ;
    
    public List<AlumnoDTO> recuperarAlumnosPorGrupo(String grupo) throws SubsistemaException ;
    
    public List<AlumnoDTO> recuperarAlumnosPorGradoYGrupo(String grado, String grupo) throws SubsistemaException ;
    
    public List<ReporteDTO> recuperarReportesAlumno(String curp) throws SubsistemaException ;
    
    public List<ReporteExpedienteDTO> convertirReportesAReporteExpediente(List<ReporteDTO> reportes) throws SubsistemaException ;
}
