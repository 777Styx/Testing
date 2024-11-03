/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import java.util.List;
import control.GestionarIncidencias;
import dto.AlumnoDTO;
import dto.ReporteDTO;
import dto.ReporteExpedienteDTO;
import excepciones.SubsistemaException;

/**
 *
 * @author Oliver Valle
 */
public class FachadaGestionarIncidencias implements IFachadaGestionarIncidencias{

    private GestionarIncidencias boIncidencias ;
    
    public FachadaGestionarIncidencias() {
        boIncidencias = new GestionarIncidencias() ;
    }
    
    @Override
    public void crearReporte(ReporteDTO reporteNuevo) throws SubsistemaException {
        try {
            boIncidencias.crearReporte(reporteNuevo);
        } catch (SubsistemaException e) {
            throw e;
        }
    }
    
    @Override
    public ReporteDTO validarReporte(ReporteDTO reporteNuevo) throws SubsistemaException {
        try {
            return boIncidencias.validarReporte(reporteNuevo) ;
        } catch (SubsistemaException e) {
            throw e;
        }
    }

    @Override
    public boolean notificarReporte(ReporteDTO reporteNuevo) throws SubsistemaException {
        try {
            return boIncidencias.notificarReporte(reporteNuevo) ;
        } catch (SubsistemaException e) {
            throw e;
        }
    }

    @Override
    public List<ReporteDTO> recuperarReportes() throws SubsistemaException {
        try {
            return boIncidencias.recuperarReportes() ;
        } catch (SubsistemaException e) {
            throw e;
        }
    }
    
    
    public void insertDatosSimulados() throws SubsistemaException {
        try {
            boIncidencias.insertDatosSimulados();
        } catch (SubsistemaException e) {
            throw e;
        }
    }
    
    @Override
    public List<AlumnoDTO> recuperarAlumnosPorGrado(String grado) throws SubsistemaException {
        try {
            return boIncidencias.recuperarAlumnosPorGrado(grado) ;
        } catch (SubsistemaException e) {
            throw e;
        }
    }

    @Override
    public List<AlumnoDTO> recuperarAlumnosPorGrupo(String grupo) throws SubsistemaException {
        try {
            return boIncidencias.recuperarAlumnosPorGrupo(grupo) ;
        } catch (SubsistemaException e) {
            throw e;
        }
    }

    @Override
    public List<AlumnoDTO> recuperarAlumnosPorGradoYGrupo(String grado, String grupo) throws SubsistemaException {
        try {
            return boIncidencias.recuperarAlumnosPorGradoYGrupo(grado, grupo) ;
        } catch (SubsistemaException e) {
            throw e;
        }
    }

    @Override
    public List<ReporteDTO> recuperarReportesAlumno(String curp) throws SubsistemaException {
        try {
            return boIncidencias.recuperarReportesAlumno(curp) ;
        } catch (SubsistemaException e) {
            throw e;
        }
    }
    
    @Override
    public List<ReporteExpedienteDTO> convertirReportesAReporteExpediente(List<ReporteDTO> reportes) throws SubsistemaException {
        try {
            return boIncidencias.convertirReportesAReporteExpediente(reportes) ;
        } catch (SubsistemaException e) {
            throw e;
        }
    }
    
}
