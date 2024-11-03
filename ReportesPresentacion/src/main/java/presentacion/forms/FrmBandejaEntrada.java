/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.forms;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import dto.ReporteDTO;
import dto.UsuarioDTO;
import excepciones.SubsistemaException;
import fachada.FachadaGestionarIncidencias;
import fachada.IFachadaGestionarIncidencias;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import persistencia.conexionBD.ConexionMongo;
import presentacion.iniciarsesion.FrmMenuGeneral;

/**
 *
 * @author Oliver Valle
 */
public class FrmBandejaEntrada extends javax.swing.JFrame {

    private IFachadaGestionarIncidencias gestionIncidencias ;
    private UsuarioDTO usuario;
    private FotosManager fotosManager;
    
    /**
     * Creates new form FrmBandejaEntrada
     */
    public FrmBandejaEntrada(UsuarioDTO usuario) {
        initComponents();
        setLocationRelativeTo(null);
        fondoFrame();
        botonesImagenes();
        this.usuario=usuario;
        this.gestionIncidencias = new FachadaGestionarIncidencias() ;
        insertDatosSimulados(); // reportes de ejemplo, dado que aún no hay un caso de uso para registrar nuevos reportes.
        refrescarTabla() ;
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Método que se ejecutará al cerrar el formulario
                ConexionMongo.cerrarConexion();
            }
        });
    }
    
    private void fondoFrame() {
        this.fotosManager = new FotosManager();
        lblFondo.setIcon(fotosManager.getFoto("src/main/java/presentacion/forms/media/bandejaEntrada.png"));
    }
    
    public void insertDatosSimulados() {
        try {
            gestionIncidencias.insertDatosSimulados();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, e.getMessage(), "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE) ;
        }
    }
    
    public FrmBandejaEntrada(IFachadaGestionarIncidencias gestionIncidencias) {
        initComponents();
        this.gestionIncidencias = gestionIncidencias ;
        refrescarTabla() ;
    }

    
    public ActionListener botonValidar() {
        ActionListener validarListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ReporteDTO reporteSelec = gestionIncidencias.recuperarReportes().get(tablaReportes.getSelectedRow()) ;
                if (!reporteSelec.isValidado()) {
                    dispose() ;
                    System.out.println((reporteSelec.getAlumno().getNombre())) ;
                    FrmValidarReporte frmValidar = new FrmValidarReporte(gestionIncidencias, reporteSelec, usuario) ;
                    frmValidar.setVisible(true);
                } else {
                    JOptionPane.showConfirmDialog(new JFrame(), "Este reporte ya ha sido validado previamente", "Reporte Validado", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE) ;
                }
                } catch (SubsistemaException ex) {
                    JOptionPane.showConfirmDialog(new JFrame(), ex.getMessage(), "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE) ;
                }
            }
        } ;
        return validarListener ;
    }

    public void refrescarTabla() {
        try {
            DefaultTableModel modeloTabla = new DefaultTableModel() ;
        List<ReporteDTO> reportes = gestionIncidencias.recuperarReportes() ;
        Object[] datosTabla = new Object[9];
        modeloTabla.addColumn("CURP");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Grado y Grupo");
        modeloTabla.addColumn("Fecha y Hora");
        modeloTabla.addColumn("Gravedad");
        modeloTabla.addColumn("Motivo");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Notificado");
        modeloTabla.addColumn("Validar");
        
        
        
        for (int i = 0; i < reportes.size(); i++) {
            datosTabla[0] = reportes.get(i).getAlumno().getCurp() ;
            datosTabla[1] = reportes.get(i).getAlumno().getNombre() ;
            datosTabla[2] = reportes.get(i).getAlumno().getGradoGrupo() ;
            datosTabla[3] = reportes.get(i).getFechaHora().getTime() ;
            datosTabla[4] = reportes.get(i).getNivelIncidencia() ;
            datosTabla[5] = reportes.get(i).getMotivo() ;
            datosTabla[6] = reportes.get(i).getDescripcion() ;
            if (reportes.get(i).isNotificado()) {
                datosTabla[7] = "NOTIFICADO" ;
            } else {
                datosTabla[7] = "PENDIENTE" ;
            }
            if (reportes.get(i).isValidado()) {
                datosTabla[8] = "VALIDADO" ;
            } 
            
            modeloTabla.addRow(datosTabla);
        }
        
        tablaReportes.setModel(modeloTabla);
        tablaReportes.setRowHeight(30);
        tablaReportes.getColumnModel().getColumn(8).setCellRenderer(new JButtonRenderer("Validar"));
        tablaReportes.getColumnModel().getColumn(8).setCellEditor(new JButtonCellEditor("Validar",botonValidar()));
        } catch (SubsistemaException e) {
            JOptionPane.showConfirmDialog(this, e.getMessage(), "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE) ;
            dispose() ;
            FrmMenuGeneral frm = new FrmMenuGeneral(usuario) ;
            frm.setVisible(true);
        }
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
    
    
//    public void formatearTabla() {
////        tblPersonas.getTableHeader().setBackground(new Color(106, 27, 49));
////        tblPersonas.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
////        tblPersonas.getTableHeader().setForeground(new Color(188, 149, 92));
//
//        ActionListener onEditarClickListener = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //Metodo para seleccionar una persona
//                seleccionar();
//            }
//        };
//        int indiceColumnaSeleccionar = 3;
//        TableColumnModel modeloColumnas = this.tblPersonas.getColumnModel();
//        modeloColumnas.getColumn(indiceColumnaSeleccionar).setCellRenderer(new JButtonRenderer("Seleccionar"));
//        modeloColumnas.getColumn(indiceColumnaSeleccionar).setCellEditor(new JButtonCellEditor("Seleccionar", onEditarClickListener));
//    }
//
//    private void seleccionar() {
//        //Metodo para regresar el empleado seleccionado de la tabla empleados
//        String curp = this.getCurpSeleccionada();
//        FrmHistorialResultados frmHistorialResultados = new FrmHistorialResultados();
//        frmHistorialResultados.setVisible(true);
//        this.dispose();
//    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReportes = new javax.swing.JTable();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bandeja de Reportes");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setBackground(new java.awt.Color(137, 21, 71));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 110, 80));

        tablaReportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CURP", "Nombre", "Grado y Grupo", "Fecha y Hora", "Gravedad", "Motivo", "Descripción", "Notificado", "Validar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaReportes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 1130, -1));
        jPanel1.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 570));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1207, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
        FrmMenuGeneral frmMenu = new FrmMenuGeneral(usuario);
        frmMenu.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

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
//            java.util.logging.Logger.getLogger(FrmBandejaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmBandejaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmBandejaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmBandejaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmBandejaEntrada().setVisible(true);
//            }
//        });
//    }
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JTable tablaReportes;
    // End of variables declaration//GEN-END:variables
}
