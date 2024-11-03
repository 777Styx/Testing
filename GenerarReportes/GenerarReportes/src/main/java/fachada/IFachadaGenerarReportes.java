/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fachada;

import dto.ReporteExpedienteDTO;
import java.util.List;

/**
 *
 * @author Oliver Valle
 */
public interface IFachadaGenerarReportes {
    
    void generarReporte(List<ReporteExpedienteDTO> reportesAlumno) ;
    
}
