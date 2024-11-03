/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dto.ReporteExpedienteDTO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Oliver Valle
 */
public class GenerarReportes {
    
    public GenerarReportes() {
        
    }
    
    public void generarReporte(List<ReporteExpedienteDTO> reportesAlumno) {
            if(!reportesAlumno.isEmpty()) {
                try{
                try {

                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportesAlumno);

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("ColeccionBeanParametro", dataSource);

                InputStream input = GenerarReportes.class.getResourceAsStream("/FormatoExpediente.jrxml");

                JasperDesign jasperDesign = JRXmlLoader.load(input);

                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

                JasperViewer viewer = new JasperViewer(jasperPrint, false); 
                viewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
                viewer.setVisible(true); 

            } catch (Exception e) {
                JOptionPane.showConfirmDialog(new JFrame(), "Error al generar reporte", "Error del sistema", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
            } 

            } catch (Exception e) {
                JOptionPane.showConfirmDialog(new JFrame(), "Error al generar reporte", "Error del sistema", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showConfirmDialog(new JFrame(), "El alumno no tiene reportes", "Expediente vacio", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
            
    }
    
}
