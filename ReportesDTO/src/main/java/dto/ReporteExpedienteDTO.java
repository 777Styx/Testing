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
public class ReporteExpedienteDTO {
    private String curp ;
    private String nombreAlumno ;
    private String nombreDocente ;
    private String nivelIncidencia ;
    String descripcion ;
    String motivo ;
    String fechaHora ;

    public ReporteExpedienteDTO(String curp, String nombreAlumno, String nombreDocente, String nivelIncidencia, String descripcion, String motivo, String fechaHora) {
        this.curp = curp ;
        this.nombreAlumno = nombreAlumno;
        this.nombreDocente = nombreDocente;
        this.nivelIncidencia = nivelIncidencia;
        this.descripcion = descripcion;
        this.motivo = motivo;
        this.fechaHora = fechaHora;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
    
    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getNivelIncidencia() {
        return nivelIncidencia;
    }

    public void setNivelIncidencia(String nivelIncidencia) {
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

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

}
