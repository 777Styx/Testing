/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.forms;

import javax.swing.JOptionPane;
import persistencia.entidades.NivelIncidenciaPersistencia;
import dto.ReporteDTO;
import dto.UsuarioDTO;
import excepciones.SubsistemaException;
import fachada.IFachadaGestionarIncidencias;
import java.awt.Color;
import java.util.Date;
import javax.swing.BorderFactory;

/**
 *
 * @author Oliver Valle
 */
public class FrmValidarReporte extends javax.swing.JFrame {

    
    private IFachadaGestionarIncidencias gestionIncidencias ;
    private ReporteDTO reporte ;
    private FotosManager fotosManager;
    private UsuarioDTO usuario;
    
    /**
     * Creates new form FrmValidarReporte
     */
    public FrmValidarReporte(IFachadaGestionarIncidencias gestionIncidencias, ReporteDTO reporte, UsuarioDTO usuario) {
        initComponents();
        setLocationRelativeTo(null);
        fondoFrame();
        this.gestionIncidencias = gestionIncidencias ;
        this.reporte = reporte ;
        this.usuario=usuario;
        this.fotosManager = new FotosManager() ;
        fotoAlumno.setIcon(fotosManager.getFoto(reporte.getAlumno().getUrlFoto()));
        txtCURP.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtNombre.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtApellidoPaterno.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtApellidoMaterno.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtProfesor.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtFecha.setBackground(new java.awt.Color(0, 0, 0, 0));
        txtGrupo.setBackground(new java.awt.Color(0, 0, 0, 0));
        
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
        txtDescripcion.setBackground(Color.WHITE); // Establece el fondo en blanco
        txtDescripcion.setOpaque(true); // Establece la opacidad del JTextArea
        
        btnValidar.setBackground(new java.awt.Color(0, 0, 0, 0));
        btnCancelar.setBackground(new java.awt.Color(0, 0, 0, 0));
        setDatos() ;
    }

    
    private void fondoFrame() {
        this.fotosManager = new FotosManager();
        lblFondo.setIcon(fotosManager.getFoto("src/main/java/presentacion/forms/media/validacionModificacionReporte.png"));
    }
    
    private void enableCampos() {
        txaMotivo.setEnabled(true);
        txaMotivo.setEditable(true);
        txtDescripcion.setEnabled(true);
        txtDescripcion.setEditable(true);
        checkLeve.setEnabled(true);
        checkSevero.setEnabled(true);
        checkGrave.setEnabled(true);
    }
    
    private void disableCampos() {
        txaMotivo.setEnabled(false);
        txaMotivo.setEditable(false);
        txtDescripcion.setEnabled(false);
        txtDescripcion.setEditable(false);
        checkLeve.setEnabled(false);
        checkSevero.setEnabled(false);
        checkGrave.setEnabled(false);
    }

    private void setDatos() {
        txtNombre.setText(reporte.getAlumno().getNombre());
        txtApellidoPaterno.setText(reporte.getAlumno().getApellidoP());
        txtApellidoMaterno.setText(reporte.getAlumno().getApellidoM());
        txtCURP.setText(reporte.getAlumno().getCurp());
        txtProfesor.setText(reporte.getDocente().getNombre() + " " + reporte.getDocente().getApellidoP() + " " + reporte.getDocente().getApellidoM());
        txtGrupo.setText(reporte.getAlumno().getGradoGrupo());
        txaMotivo.setText(reporte.getMotivo());
        txtDescripcion.setText(reporte.getDescripcion());
        Date fecha = reporte.getFechaHora();
        int anio = fecha.getYear() + 1900;
        int mes = fecha.getMonth() + 1;
        int dia = fecha.getDay();
        
        String fechaFormato = anio + "/" + mes + "/" + dia;
        txtFecha.setText(fechaFormato);

        if (reporte.getNivelIncidencia() == NivelIncidenciaPersistencia.LEVE) {
            checkLeve.setSelected(true);
        } else if(reporte.getNivelIncidencia() == NivelIncidenciaPersistencia.SEVERO) {
            checkSevero.setSelected(true);
        } else {
            checkGrave.setSelected(true) ;
        }
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
        txtDescripcion = new javax.swing.JTextArea();
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
        toggleModificar = new javax.swing.JToggleButton();
        btnValidar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Validación de Reporte");
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fotoAlumno.setPreferredSize(new java.awt.Dimension(160, 200));
        jPanel1.add(fotoAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 160, 190));

        txtDescripcion.setEditable(false);
        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setColumns(20);
        txtDescripcion.setForeground(new java.awt.Color(0, 0, 0));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setBorder(null);
        txtDescripcion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDescripcion.setEnabled(false);
        panelTxaDescripcion.setViewportView(txtDescripcion);

        jPanel1.add(panelTxaDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 460, 320, 160));

        panelTxaMotivo.setBorder(null);

        txaMotivo.setColumns(20);
        txaMotivo.setLineWrap(true);
        txaMotivo.setRows(5);
        txaMotivo.setEnabled(false);
        panelTxaMotivo.setViewportView(txaMotivo);

        jPanel1.add(panelTxaMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 460, 330, 160));

        checkLeve.setBackground(new java.awt.Color(255, 255, 255));
        checkLeve.setFont(new java.awt.Font("NATS", 0, 24)); // NOI18N
        checkLeve.setForeground(new java.awt.Color(255, 255, 255));
        checkLeve.setContentAreaFilled(false);
        checkLeve.setEnabled(false);
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
        checkSevero.setEnabled(false);
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
        checkGrave.setEnabled(false);
        checkGrave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkGraveActionPerformed(evt);
            }
        });
        jPanel1.add(checkGrave, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 20, -1));

        txtCURP.setEditable(false);
        txtCURP.setBackground(new java.awt.Color(255, 255, 255));
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
        txtGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGrupoActionPerformed(evt);
            }
        });
        jPanel1.add(txtGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 190, 90, 40));

        toggleModificar.setBackground(new java.awt.Color(243, 222, 44));
        toggleModificar.setFont(new java.awt.Font("NATS", 0, 24)); // NOI18N
        toggleModificar.setForeground(new java.awt.Color(0, 0, 0));
        toggleModificar.setBorder(null);
        toggleModificar.setBorderPainted(false);

        toggleModificar.setContentAreaFilled(false);

        toggleModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        toggleModificar.setOpaque(false);
        toggleModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleModificarActionPerformed(evt);
            }
        });
        jPanel1.add(toggleModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 450, 170, 50));

        btnValidar.setBackground(new java.awt.Color(0, 111, 22));
        btnValidar.setFont(new java.awt.Font("NATS", 0, 24)); // NOI18N
        btnValidar.setForeground(new java.awt.Color(255, 255, 255));
        btnValidar.setBorder(null);
        btnValidar.setBorderPainted(false);

        btnValidar.setContentAreaFilled(false);

        btnValidar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnValidar.setOpaque(false);
        btnValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidarActionPerformed(evt);
            }
        });
        jPanel1.add(btnValidar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 510, 170, 50));

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
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 570, 170, 50));
        jPanel1.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 660));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose() ;
        FrmBandejaEntrada bandejaEntrada = new FrmBandejaEntrada(usuario) ;
        bandejaEntrada.setVisible(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void toggleModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleModificarActionPerformed
        if(toggleModificar.isSelected()) {
            enableCampos() ;
        } else {
            disableCampos() ;
        }
    }//GEN-LAST:event_toggleModificarActionPerformed

    private void btnValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidarActionPerformed
        int resp = JOptionPane.showConfirmDialog(this, "¿Estás seguro de validar este reporte?. Los cambios que hayan realizados se verán reflejados en el sistema.", "Validar Reporte", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) ;
      
        //Validación del reporte
        NivelIncidenciaPersistencia nivelIncidencia;
        if (resp == JOptionPane.YES_OPTION) {
            ReporteDTO reporteNuevo = new ReporteDTO() ;
            reporteNuevo.setId(reporte.getId());
            reporteNuevo.setMotivo(txaMotivo.getText());
            reporteNuevo.setDescripcion(txtDescripcion.getText());
            reporteNuevo.setValidado(reporte.isValidado());
            
            if (checkLeve.isSelected()) {
                nivelIncidencia = NivelIncidenciaPersistencia.LEVE ;
            } else if(checkSevero.isSelected()) {
                nivelIncidencia = NivelIncidenciaPersistencia.SEVERO ;
            } else {
                nivelIncidencia = NivelIncidenciaPersistencia.GRAVE ;
            }
            reporteNuevo.setNivelIncidencia(nivelIncidencia);
            
            try {
                //Validar que los JTextArea no estén vacíos
                if (txtDescripcion.getText().trim().isEmpty() && txaMotivo.getText().trim().isEmpty()) {
                    throw new SubsistemaException("Campos vacíos");
                }

                //Validar que se haya seleccionado un nivel de incidencia
                if (!checkLeve.isSelected() && !checkSevero.isSelected() && !checkGrave.isSelected()) {
                    throw new SubsistemaException("Nivel de Severidad vacío");
                }
                
                if (gestionIncidencias.notificarReporte(reporte)) {
                    reporteNuevo.setNotificado(true);
                    gestionIncidencias.validarReporte(reporteNuevo);
                    JOptionPane.showConfirmDialog(this, "¡Se ha validado el Reporte!", "Reporte Validado", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showConfirmDialog(this, "Hubo un error al validar el reporte", "Reporte no validado", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
                }

                dispose();
                FrmBandejaEntrada bandejaEntrada = new FrmBandejaEntrada(usuario);
                bandejaEntrada.setVisible(true);
            } catch (SubsistemaException e) {
                JOptionPane.showConfirmDialog(this, e.getMessage(), "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE) ;
            }
            
        }
    }//GEN-LAST:event_btnValidarActionPerformed

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

    private void txtGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGrupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrupoActionPerformed

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
    private javax.swing.JButton btnValidar;
    private javax.swing.JCheckBox checkGrave;
    private javax.swing.JCheckBox checkLeve;
    private javax.swing.JCheckBox checkSevero;
    private javax.swing.JLabel fotoAlumno;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JScrollPane panelTxaDescripcion;
    private javax.swing.JScrollPane panelTxaMotivo;
    private javax.swing.JToggleButton toggleModificar;
    private javax.swing.JTextArea txaMotivo;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtCURP;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtProfesor;
    // End of variables declaration//GEN-END:variables
}
