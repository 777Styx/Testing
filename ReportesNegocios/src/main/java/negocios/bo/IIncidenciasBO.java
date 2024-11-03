/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocios.bo;

import dto.AlumnoDTO;
import java.util.List;
import dto.ReporteDTO;
import dto.ReporteExpedienteDTO ;
import negocios.excepciones.NegociosException;

/**
 *
 * @author Oliver Valle
 */
public interface IIncidenciasBO {
    void crearReporte(ReporteDTO reporteNuevo) throws NegociosException;
    
    ReporteDTO validarReporte(ReporteDTO reporteNuevo) throws NegociosException;
    
    boolean notificarReporte(ReporteDTO reporteNuevo) throws NegociosException;
    
    List<ReporteDTO> recuperarReportes() throws NegociosException;
    
    public void insertDatosSimulados() throws NegociosException;
    
    public List<AlumnoDTO> recuperarAlumnosPorGrado(String grado) throws NegociosException;
    
    public List<AlumnoDTO> recuperarAlumnosPorGrupo(String grupo) throws NegociosException;
    
    public List<AlumnoDTO> recuperarAlumnosPorGradoYGrupo(String grado, String grupo) throws NegociosException;
    
    public List<ReporteDTO> recuperarReportesAlumno(String curp) throws NegociosException;
    
    public List<ReporteExpedienteDTO> convertirReporteAReporteExpediente(List<ReporteDTO> reportes) throws NegociosException;
}
