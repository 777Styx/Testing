/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import control.GenerarReportes;
import dto.ReporteExpedienteDTO;
import fachada.IFachadaGenerarReportes;
import java.util.List;

/**
 *
 * @author Oliver Valle
 */
public class FachadaGenerarReportes implements IFachadaGenerarReportes {

    private GenerarReportes boReportes ;
    
    public FachadaGenerarReportes() {
        boReportes = new GenerarReportes() ;
    }
    
    @Override
    public void generarReporte(List<ReporteExpedienteDTO> reportesAlumno) {
        boReportes.generarReporte(reportesAlumno);
    }
    
}
