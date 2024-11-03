/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.entidades;

import java.util.Calendar;
import java.util.Date;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

/**
 *
 * @author Oliver Valle
 */
public class ReporteEntity {
    private ObjectId _id ;
    private AlumnoEntity alumno ;
    private UsuarioEntity usuario ;
    private NivelIncidenciaPersistencia nivelIncidencia ;
    private String descripcion ;
    private String motivo ;
    private Date fechaHora ;
    boolean notificado, validado ;
    
    public ReporteEntity() {
        
    }

    public ReporteEntity(ObjectId _id, AlumnoEntity alumno, UsuarioEntity usuario, NivelIncidenciaPersistencia nivelIncidencia, String descripcion, String motivo, Date fechaHora, boolean notificado, boolean validado) {
        this._id = _id ;
        this.alumno = alumno;
        this.usuario = usuario;
        this.nivelIncidencia = nivelIncidencia;
        this.descripcion = descripcion;
        this.motivo = motivo;
        this.fechaHora = fechaHora;
        this.notificado = notificado;
        this.validado = validado ;
    }

    public ReporteEntity(AlumnoEntity alumno, UsuarioEntity usuario, NivelIncidenciaPersistencia nivelIncidencia, String descripcion, String motivo, Date fechaHora, boolean notificado, boolean validado) {
        this.alumno = alumno;
        this.usuario = usuario;
        this.nivelIncidencia = nivelIncidencia;
        this.descripcion = descripcion;
        this.motivo = motivo;
        this.fechaHora = fechaHora;
        this.notificado = notificado;
        this.validado = validado;
    }

    public ObjectId getId() {
        return _id;
    }

    public AlumnoEntity getAlumno() {
        return alumno;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public NivelIncidenciaPersistencia getNivelIncidencia() {
        return nivelIncidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getMotivo() {
        return motivo;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public boolean isNotificado() {
        return notificado;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public void setAlumno(AlumnoEntity alumno) {
        this.alumno = alumno;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public void setNivelIncidencia(NivelIncidenciaPersistencia nivelIncidencia) {
        this.nivelIncidencia = nivelIncidencia;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setNotificado(boolean notificado) {
        this.notificado = notificado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }
    
    
    
}
