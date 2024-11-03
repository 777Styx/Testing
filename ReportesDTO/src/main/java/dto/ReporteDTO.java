/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;
import persistencia.entidades.NivelIncidenciaPersistencia;

/**
 *
 * @author Oliver Valle
 */
public class ReporteDTO {
    
    private String _id;
    private AlumnoDTO alumno ;
    private UsuarioDTO docente ;
    private NivelIncidenciaPersistencia nivelIncidencia ;
    String descripcion ;
    String motivo ;
    Date fechaHora ;
    boolean notificado ;
    boolean validado ;

    public ReporteDTO() {
        
    }

    public ReporteDTO(String _id, AlumnoDTO alumno, UsuarioDTO docente, NivelIncidenciaPersistencia nivelIncidencia, String descripcion, String motivo, Date fechaHora, boolean notificado, boolean validado) {
        this._id = _id;
        this.alumno = alumno;
        this.docente = docente;
        this.nivelIncidencia = nivelIncidencia;
        this.descripcion = descripcion;
        this.motivo = motivo;
        this.fechaHora = fechaHora;
        this.notificado = notificado;
        this.validado = validado;
    }
    
    
    
    public ReporteDTO(AlumnoDTO alumno, UsuarioDTO docente, NivelIncidenciaPersistencia nivelIncidencia, String descripcion, String motivo, Date fechaHora, boolean notificado, boolean validado) {
        this.alumno = alumno;
        this.docente = docente;
        this.nivelIncidencia = nivelIncidencia;
        this.descripcion = descripcion;
        this.motivo = motivo;
        this.fechaHora = fechaHora;
        this.notificado = notificado;
        this.validado = validado ;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }
    
    public AlumnoDTO getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoDTO alumno) {
        this.alumno = alumno;
    }

    public UsuarioDTO getDocente() {
        return docente;
    }

    public void setDocente(UsuarioDTO docente) {
        this.docente = docente;
    }

    public NivelIncidenciaPersistencia getNivelIncidencia() {
        return nivelIncidencia;
    }

    public void setNivelIncidencia(NivelIncidenciaPersistencia nivelIncidencia) {
        this.nivelIncidencia = nivelIncidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isNotificado() {
        return notificado;
    }

    public void setNotificado(boolean notificado) {
        this.notificado = notificado;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }
    
    
    
}
