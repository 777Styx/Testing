/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.crearreporte;

import dto.AlumnoDTO;
import presentacion.forms.*;
import javax.swing.JOptionPane;
import persistencia.entidades.NivelIncidenciaPersistencia;
import dto.ReporteDTO;
import dto.UsuarioDTO;
import excepciones.SubsistemaException;
import fachada.FachadaGestionarIncidencias;
import fachada.IFachadaGestionarIncidencias;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Oliver Valle
 */
public class FrmCrearReporte extends javax.swing.JFrame {

    
    private IFachadaGestionarIncidencias gestionIncidencias ;
    private FotosManager fotosManager;
    private UsuarioDTO usuario;
    private AlumnoDTO alum;
    
    /**
     * Creates new form FrmValidarReporte
     */

    public FrmCrearReporte(AlumnoDTO alum, UsuarioDTO usuario) {
        initComponents();
        fondoFrame();
        this.gestionIncidencias = gestionIncidencias ;
        fotosManager= new FotosManager();
        fotoAlumno.setIcon(fotosManager.getFoto(alum.getUrlFoto()));

        this.usuario=usuario;
        this.alum=alum;
        
        if (alum != null) {
            txtCURP.setBackground(new java.awt.Color(0, 0, 0, 0));
            txtNombre.setBackground(new java.awt.Color(0, 0, 0, 0));
            txtApellidoPaterno.setBackground(new java.awt.Color(0, 0, 0, 0));
            txtApellidoMaterno.setBackground(new java.awt.Color(0, 0, 0, 0));
            txtGrupo.setBackground(new java.awt.Color(0, 0, 0, 0));
            setDatos(alum);
        }
        txtFecha.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtGrupo.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtProfesor.setBackground(new java.awt.Color(0, 0, 0, 0));
        
        //TEXTAREA - MOTIVO
        panelTxaMotivo.setOpaque(false);
        panelTxaMotivo.getViewport().setOpaque(false);
        panelTxaMotivo.setBorder(null);
        panelTxaMotivo.setViewportBorder(null);
        txaMotivo.setBackground(Color.WHITE); // Establece el fondo en blanco
        txaMotivo.setOpaque(true); // Establece la opacidad del JTextArea

        //TEXTAREA - DESCRIPCIÓN
        panelTxaDescripcion.setOpaque(false);
        panelTxaDescripcion.getViewport().setOpaque(false);
        panelTxaDescripcion.setBorder(null);
        panelTxaDescripcion.setViewportBorder(null);
        txaDescripcion.setBackground(Color.WHITE); // Establece el fondo en blanco
        txaDescripcion.setOpaque(true); // Establece la opacidad del JTextArea
        
        btnCrear.setBackground(new java.awt.Color(0, 0, 0, 0));
        btnCancelar.setBackground(new java.awt.Color(0, 0, 0, 0));
    }

    private void setDatos(AlumnoDTO alum) {
        txtNombre.setText(alum.getNombre());
        txtApellidoPaterno.setText(alum.getApellidoP());
        txtApellidoMaterno.setText(alum.getApellidoM());
        txtCURP.setText(alum.getCurp());
        txtGrupo.setText(alum.getGradoGrupo());
        txtProfesor.setText(usuario.getNombre()+" "+usuario.getApellidoP()+" "+usuario.getApellidoM());
        

        // Obtener la fecha y hora actual
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        // Formatear la fecha y hora en el formato deseado (por ejemplo, "yyyy-MM-dd HH:mm:ss")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String fechaHoraFormateada = fechaHoraActual.format(formatter);

        // Asignar la fecha y hora formateada al campo de texto txtFecha
        txtFecha.setText(fechaHoraFormateada);

    }
    
    
    private void fondoFrame() {
        this.fotosManager = new FotosManager();
        lblFondo.setIcon(fotosManager.getFoto("src/main/java/presentacion/crearreporte/media/generacionIncidencias.png"));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        fotoAlumno = new javax.swing.JLabel();
        panelTxaDescripcion = new javax.swing.JScrollPane();
        txaDescripcion = new javax.swing.JTextArea();
        panelTxaMotivo = new javax.swing.JScrollPane();
        txaMotivo = new javax.swing.JTextArea();
        checkLeve = new javax.swing.JCheckBox();
        checkSevero = new javax.swing.JCheckBox();
        checkGrave = new javax.swing.JCheckBox();
        txtCURP = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellidoPaterno = new javax.swing.JTextField();
        txtApellidoMaterno = new javax.swing.JTextField();
        txtProfesor = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtGrupo = new javax.swing.JTextField();
        btnCrear = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Creación de Reporte");
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setPreferredSize(new java.awt.Dimension(1210, 660));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fotoAlumno.setPreferredSize(new java.awt.Dimension(160, 200));
        jPanel1.add(fotoAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 160, 190));

        txaDescripcion.setColumns(20);
        txaDescripcion.setForeground(new java.awt.Color(0, 0, 0));
        txaDescripcion.setLineWrap(true);
        txaDescripcion.setRows(5);
        txaDescripcion.setBorder(null);
        panelTxaDescripcion.setViewportView(txaDescripcion);

        jPanel1.add(panelTxaDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 460, 330, 160));

        txaMotivo.setColumns(20);
        txaMotivo.setForeground(new java.awt.Color(0, 0, 0));
        txaMotivo.setLineWrap(true);
        txaMotivo.setRows(5);
        txaMotivo.setBorder(null);
        panelTxaMotivo.setViewportView(txaMotivo);

        jPanel1.add(panelTxaMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 460, 330, 160));

        checkLeve.setBackground(new java.awt.Color(255, 255, 255));
        checkLeve.setFont(new java.awt.Font("NATS", 0, 24)); // NOI18N
        checkLeve.setForeground(new java.awt.Color(255, 255, 255));
        checkLeve.setContentAreaFilled(false);
        checkLeve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkLeveActionPerformed(evt);
            }
        });
        jPanel1.add(checkLeve, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 85, -1));

        checkSevero.setBackground(new java.awt.Color(255, 255, 255));
        checkSevero.setFont(new java.awt.Font("NATS", 0, 24)); // NOI18N
        checkSevero.setForeground(new java.awt.Color(0, 0, 0));
        checkSevero.setContentAreaFilled(false);
        checkSevero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSeveroActionPerformed(evt);
            }
        });
        jPanel1.add(checkSevero, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, -1, -1));

        checkGrave.setBackground(new java.awt.Color(255, 255, 255));
        checkGrave.setFont(new java.awt.Font("NATS", 0, 24)); // NOI18N
        checkGrave.setForeground(new java.awt.Color(0, 0, 0));
        checkGrave.setContentAreaFilled(false);
        checkGrave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkGraveActionPerformed(evt);
            }
        });
        jPanel1.add(checkGrave, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 20, -1));

        txtCURP.setEditable(false);
        txtCURP.setBackground(new java.awt.Color(0, 0, 0));
        txtCURP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCURP.setForeground(new java.awt.Color(0, 0, 0));
        txtCURP.setBorder(null);
        txtCURP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCURP.setEnabled(false);
        jPanel1.add(txtCURP, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 142, 360, 40));

        txtNombre.setEditable(false);
        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setBorder(null);
        txtNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNombre.setEnabled(false);
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 360, 40));

        txtApellidoPaterno.setEditable(false);
        txtApellidoPaterno.setBackground(new java.awt.Color(255, 255, 255));
        txtApellidoPaterno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtApellidoPaterno.setBorder(null);
        txtApellidoPaterno.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtApellidoPaterno.setEnabled(false);
        txtApellidoPaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoPaternoActionPerformed(evt);
            }
        });
        jPanel1.add(txtApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 360, 30));

        txtApellidoMaterno.setEditable(false);
        txtApellidoMaterno.setBackground(new java.awt.Color(255, 255, 255));
        txtApellidoMaterno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtApellidoMaterno.setForeground(new java.awt.Color(0, 0, 0));
        txtApellidoMaterno.setBorder(null);
        txtApellidoMaterno.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtApellidoMaterno.setEnabled(false);
        txtApellidoMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoMaternoActionPerformed(evt);
            }
        });
        jPanel1.add(txtApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, 360, 40));

        txtProfesor.setEditable(false);
        txtProfesor.setBackground(new java.awt.Color(255, 255, 255));
        txtProfesor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtProfesor.setForeground(new java.awt.Color(0, 0, 0));
        txtProfesor.setBorder(null);
        txtProfesor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtProfesor.setEnabled(false);
        jPanel1.add(txtProfesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 360, 40));

        txtFecha.setEditable(false);
        txtFecha.setBackground(new java.awt.Color(255, 255, 255));
        txtFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.setBorder(null);
        txtFecha.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFecha.setEnabled(false);
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 330, 90, 40));

        txtGrupo.setEditable(false);
        txtGrupo.setBackground(new java.awt.Color(255, 255, 255));
        txtGrupo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtGrupo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGrupo.setBorder(null);
        txtGrupo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtGrupo.setEnabled(false);
        jPanel1.add(txtGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 190, 90, 40));

        btnCrear.setBackground(new java.awt.Color(0, 111, 22));
        btnCrear.setFont(new java.awt.Font("NATS", 0, 24)); // NOI18N
        btnCrear.setForeground(new java.awt.Color(255, 255, 255));
        btnCrear.setBorder(null);
        btnCrear.setBorderPainted(false);
        btnCrear.setBorderPainted(false);

        btnCrear.setContentAreaFilled(false);

        btnCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnCrear.setOpaque(false);
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 480, 170, 50));

        btnCancelar.setBackground(new java.awt.Color(197, 40, 61));
        btnCancelar.setFont(new java.awt.Font("NATS", 0, 24)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setBorder(null);
        btnCancelar.setBorderPainted(false);

        btnCancelar.setContentAreaFilled(false);

        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnCancelar.setOpaque(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 550, 170, 50));
        jPanel1.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 660));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 100));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose() ;
        FrmBuscarEstudiante bandejaEntrada = new FrmBuscarEstudiante(usuario);
        bandejaEntrada.setVisible(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        int resp = JOptionPane.showConfirmDialog(this, "¿Estás seguro de enviar este reporte?. Los cambios que hayan realizados se verán reflejados en el sistema.", "Crear Reporte", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) ;
        if (resp == JOptionPane.YES_OPTION) {
            NivelIncidenciaPersistencia nivelIncidencia;
            if (resp == JOptionPane.YES_OPTION) {
                if (checkLeve.isSelected()) {
                    nivelIncidencia = NivelIncidenciaPersistencia.LEVE ;
                } else if(checkSevero.isSelected()) {
                    nivelIncidencia = NivelIncidenciaPersistencia.SEVERO ;
                } else {
                    nivelIncidencia = NivelIncidenciaPersistencia.GRAVE ;
                }
            
               ReporteDTO reporteNuevo = new ReporteDTO(
                       alum,
                       usuario,
                       nivelIncidencia,
                       txaDescripcion.getText(),
                       txaMotivo.getText(),
                       new Date(),
                       false,
                       false);
           
                IFachadaGestionarIncidencias fachadaGestionarIncidencias = 
                        new FachadaGestionarIncidencias();
            
            try {
                //Validar que los JTextArea no estén vacíos
                if (txaDescripcion.getText().trim().isEmpty() && txaMotivo.getText().trim().isEmpty()) {
                   throw new SubsistemaException("Campos vacíos");
                }

                //Validar que se haya seleccionado un nivel de incidencia
                if (!checkLeve.isSelected() && !checkSevero.isSelected() && !checkGrave.isSelected()) {
                    throw new SubsistemaException("Nivel de Severidad vacío");
                }
                fachadaGestionarIncidencias.crearReporte(reporteNuevo);
                JOptionPane.showConfirmDialog(this, "¡Se ha enviado el Reporte!", "Reporte Creado", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE) ; 
                dispose();
                FrmBuscarEstudiante frmBuscar = new FrmBuscarEstudiante(usuario) ;
                frmBuscar.setVisible(true);
            } catch (SubsistemaException e) {
                JOptionPane.showConfirmDialog(this, e.getMessage(), "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE) ; 
            }
            


        } 
        }
        
    }//GEN-LAST:event_btnCrearActionPerformed

    private void txtApellidoMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoMaternoActionPerformed

    private void txtApellidoPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoPaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoPaternoActionPerformed

    private void checkGraveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkGraveActionPerformed
        if(checkGrave.isSelected()) {
            checkSevero.setSelected(false);
            checkLeve.setSelected(false);
        }
    }//GEN-LAST:event_checkGraveActionPerformed

    private void checkSeveroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSeveroActionPerformed
        if(checkSevero.isSelected()) {
            checkLeve.setSelected(false);
            checkGrave.setSelected(false);
        }
    }//GEN-LAST:event_checkSeveroActionPerformed

    private void checkLeveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkLeveActionPerformed
        if(checkLeve.isSelected()) {
            checkSevero.setSelected(false);
            checkGrave.setSelected(false);
        }
    }//GEN-LAST:event_checkLeveActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmValidarReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmValidarReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmValidarReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmValidarReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmValidarReporte().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JCheckBox checkGrave;
    private javax.swing.JCheckBox checkLeve;
    private javax.swing.JCheckBox checkSevero;
    private javax.swing.JLabel fotoAlumno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JScrollPane panelTxaDescripcion;
    private javax.swing.JScrollPane panelTxaMotivo;
    private javax.swing.JTextArea txaDescripcion;
    private javax.swing.JTextArea txaMotivo;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtCURP;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtProfesor;
    // End of variables declaration//GEN-END:variables
}
