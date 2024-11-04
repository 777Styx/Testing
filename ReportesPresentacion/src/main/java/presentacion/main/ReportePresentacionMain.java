/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package presentacion.main;

import negocios.bo.IncidenciasBO;
import negocios.excepciones.NegociosException;
import presentacion.iniciarsesion.FrmIniciarSesion;

/**
 *
 * @author gamaliel
 */
public class ReportePresentacionMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NegociosException {
        IncidenciasBO bo = new IncidenciasBO();

        bo.insertDatosSimulados();
        FrmIniciarSesion frmIniciarSesion = new FrmIniciarSesion();
        frmIniciarSesion.setVisible(true);
    }

}
