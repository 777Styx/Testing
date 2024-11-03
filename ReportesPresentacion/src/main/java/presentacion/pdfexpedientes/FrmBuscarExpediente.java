/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.pdfexpedientes;

import dto.AlumnoDTO;
import dto.ReporteDTO;
import dto.ReporteExpedienteDTO;
import dto.UsuarioDTO;
import excepciones.SubsistemaException;
import fachada.FachadaGenerarReportes;
import fachada.FachadaGestionarIncidencias;
import fachada.IFachadaGenerarReportes;
import fachada.IFachadaGestionarIncidencias;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import presentacion.forms.FotosManager;
import presentacion.iniciarsesion.FrmMenuGeneral;

/**
 *
 * @author Oliver Valle
 */
public class FrmBuscarExpediente extends javax.swing.JFrame {
    
    IFachadaGestionarIncidencias gestionIncidencias ;
    IFachadaGenerarReportes generarReportes ;
    List<AlumnoDTO> alumnos ;
    private FotosManager fotosManager;
    private UsuarioDTO usuario;
    /**
     * Creates new form FrmBuscarExpediente
     */
    public FrmBuscarExpediente(UsuarioDTO usuario) {
        initComponents();
        setLocationRelativeTo(null);
        botonesImagenes();
        fondoFrame();
        this.usuario=usuario;
        gestionIncidencias = new FachadaGestionarIncidencias() ;
        generarReportes = new FachadaGenerarReportes() ;
        alumnos = new ArrayList() ;
        btnBuscar.setBackground(new java.awt.Color(0, 0, 0, 0));
    
    }
    
    private void fondoFrame() {
        this.fotosManager = new FotosManager();
        fondoFrame.setIcon(fotosManager.getFoto("src/main/java/presentacion/pdfexpedientes/media/exportacionExpedientesPDF.png"));
    }
    
    private void botonesImagenes() {
        this.fotosManager = new FotosManager();

        // Cargar la imagen
        ImageIcon iconoOriginal = new ImageIcon("src/main/java/presentacion/botones/flecha-izquierda.png");
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

        // Establecer el ImageIcon escalado como icono del botón
        btnRegresar.setIcon(iconoEscalado);
        btnRegresar.setVerticalTextPosition(JButton.BOTTOM);
        btnRegresar.setHorizontalTextPosition(JButton.CENTER);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setText("Regresar");
    }
    
    public void refrescarTabla() {
        
        DefaultTableModel modeloTabla = new DefaultTableModel() ;
        Object[] datosTabla = new Object[4];
        modeloTabla.addColumn("CURP");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Grado y Grupo");
        modeloTabla.addColumn("Expediente");
        
        
        
        for (int i = 0; i < alumnos.size(); i++) {
            datosTabla[0] = alumnos.get(i).getCurp() ;
            datosTabla[1] = alumnos.get(i).getNombre() ;
            datosTabla[2] = alumnos.get(i).getGradoGrupo() ;
            datosTabla[3] = "Ver" ;
            
            
            modeloTabla.addRow(datosTabla);
        }
        
        tablaExpedientes.setModel(modeloTabla);
        tablaExpedientes.setRowHeight(30);
        tablaExpedientes.getColumnModel().getColumn(3).setCellRenderer(new JButtonRenderer("Ver"));
        tablaExpedientes.getColumnModel().getColumn(3).setCellEditor(new JButtonCellEditor("Ver",botonValidar()));

    }
    
    public ActionListener botonValidar() {
        ActionListener validarListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<ReporteDTO> reportesAlumno = gestionIncidencias.recuperarReportesAlumno(alumnos.get(tablaExpedientes.getSelectedRow()).getCurp()) ;
                List<ReporteExpedienteDTO> reportesExpedientesAlumno = gestionIncidencias.convertirReportesAReporteExpediente(reportesAlumno) ;
                
                generarReportes.generarReporte(reportesExpedientesAlumno);
                } catch (SubsistemaException ex) {
                    JOptionPane.showConfirmDialog(new JFrame(), ex.getMessage(), "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE) ;
                }
            }
        } ;
        
        return validarListener ;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegresar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        comboBoxGrupo = new javax.swing.JComboBox<>();
        comboBoxGrado = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaExpedientes = new javax.swing.JTable();
        fondoFrame = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setBackground(new java.awt.Color(137, 21, 71));
        btnRegresar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnRegresar.setBorder(null);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 90, 90));

        btnBuscar.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.disabledSelectedBackground"));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setBorder(null);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 200, 170, 60));

        comboBoxGrupo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        comboBoxGrupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir...", "A", "B", "C", "D", "E", "F" }));
        comboBoxGrupo.setBorder(null);
        getContentPane().add(comboBoxGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 210, 200, 40));

        comboBoxGrado.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        comboBoxGrado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir...", "1", "2", "3" }));
        comboBoxGrado.setBorder(null);
        getContentPane().add(comboBoxGrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 160, 40));

        tablaExpedientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CURP", "Nombre", "Grado y Grupo", "Expediente"
            }
        ));
        jScrollPane1.setViewportView(tablaExpedientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 920, 320));
        getContentPane().add(fondoFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            if(comboBoxGrado.getSelectedIndex() == 0 && comboBoxGrupo.getSelectedIndex() == 0) {
            JOptionPane.showConfirmDialog(new JFrame(), "Elige un filtro", "Elige un filtro", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE) ;
            return ;
        } else if (comboBoxGrado.getSelectedIndex() != 0 && comboBoxGrupo.getSelectedIndex() == 0){
            alumnos = gestionIncidencias.recuperarAlumnosPorGrado(comboBoxGrado.getSelectedItem().toString()) ;
        } else if(comboBoxGrado.getSelectedIndex() == 0 && comboBoxGrupo.getSelectedIndex() != 0) {
            alumnos = gestionIncidencias.recuperarAlumnosPorGrupo(comboBoxGrupo.getSelectedItem().toString()) ;
        } else {
            alumnos = gestionIncidencias.recuperarAlumnosPorGradoYGrupo(comboBoxGrado.getSelectedItem().toString(), comboBoxGrupo.getSelectedItem().toString()) ;
        }
        
        if(alumnos != null) {
            refrescarTabla() ;
        } else {
            JOptionPane.showConfirmDialog(this, "No hay alumnos en este grado o grupo", "No hay alumnos", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE) ;
        }
        } catch (SubsistemaException e) {
            JOptionPane.showConfirmDialog(this, e.getMessage(), "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE) ;
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
        FrmMenuGeneral frmMenu = new FrmMenuGeneral(usuario);
        frmMenu.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    /**
     * @param args the command line arguments
     */
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
//            java.util.logging.Logger.getLogger(FrmBuscarExpediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmBuscarExpediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmBuscarExpediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmBuscarExpediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmBuscarExpediente().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> comboBoxGrado;
    private javax.swing.JComboBox<String> comboBoxGrupo;
    private javax.swing.JLabel fondoFrame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaExpedientes;
    // End of variables declaration//GEN-END:variables

    public class JButtonCellEditor implements TableCellEditor {

    private final JButton button;
    private int row;
    private ActionListener actionListener;

    public JButtonCellEditor(String text, ActionListener actionListener) {
        this.button = new JButton(text);
//        this.button.setFont(new Font("Sans Serif", Font.BOLD, 16));
//        this.button.setBackground(new Color(188, 149, 92));
//        this.button.setForeground(new Color(242, 242, 242));
        this.actionListener = actionListener;
        this.button.addActionListener((ActionEvent evt)->{
            this.actionListener.actionPerformed(
                new ActionEvent(this.button, ActionEvent.ACTION_PERFORMED, this.row+"")
            );
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        return this.button;
    }

    @Override
    public Object getCellEditorValue() {
        return true;
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }

    @Override
    public void cancelCellEditing() {}

    @Override
    public void addCellEditorListener(CellEditorListener l) {}

    @Override
    public void removeCellEditorListener(CellEditorListener l) {}
}
    
    public class JButtonRenderer implements TableCellRenderer {

    private final JButton button;

    public JButtonRenderer(String text) {
        this.button = new JButton(text);
//        this.button.setFont(new Font("Sans Serif", Font.BOLD, 16));
//        this.button.setBackground(new Color(188, 149, 92));
//        this.button.setForeground(new Color(242, 242, 242));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this.button;
    }

}               
}


