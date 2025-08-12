/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Frontend;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author helder
 */
public class FrameBase extends JInternalFrame {

    protected String path = "";
    protected JButton enviarArchivoJButton;

    public JInternalFrame agragarVentanilla(JDesktopPane jframe, String titulo, int x, int y) {
        JInternalFrame ventana = new JInternalFrame(title, true, true, true, true);
        ventana.setLayout(null);
        ventana.setBounds(x, y, 600, 450);
        ventana.setBackground(Color.LIGHT_GRAY);
        ventana.setVisible(true);
        jframe.add(ventana);
        agregarComponentes(ventana);
        return ventana;
    }

    private void agregarComponentes(JInternalFrame frameEvento) {
        Font font2 = new Font("Helvetica", Font.BOLD, 15);

        JLabel rutaArchivo = new JLabel("Archivo Seleccionado...");
        rutaArchivo.setFont(font2);
        rutaArchivo.setBounds(180, 10, 200, 20);
        frameEvento.add(rutaArchivo);
        
        enviarArchivoJButton = new JButton("Enviar Archivo");
        enviarArchivoJButton.setBounds(250, 290, 200, 20);
        enviarArchivoJButton.setEnabled(false);
        frameEvento.add(enviarArchivoJButton);
        enviarArchivoJButton.addActionListener(e -> {
            ejecutarInstruccionDeBoton();
        });
        
        JButton activarFormJButton = new JButton("Activar Form...");
        activarFormJButton.setBounds(400, 10, 150, 20);
        frameEvento.add(activarFormJButton);
        activarFormJButton.addActionListener(e -> {
            rutaArchivo.setText("Archivo Seleccionado...");
            habilitarCampos(frameEvento.getContentPane());
            enviarArchivoJButton.setEnabled(false);
        });
        
        JButton cargaArchivo = new JButton("Carga de archivos");
        cargaArchivo.setBounds(10, 10, 150, 20);
        frameEvento.add(cargaArchivo);
        cargaArchivo.addActionListener(e -> {
            JFileChooser archivo = new JFileChooser();
            archivo.setDialogTitle("Seleccionar un archivo");

            int resultado = archivo.showOpenDialog(null);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                java.io.File archivoSeleccionado = archivo.getSelectedFile();

                path = archivoSeleccionado.getAbsolutePath();
                String nombreArchivo = archivoSeleccionado.getName();
                rutaArchivo.setText(nombreArchivo);
                
                deshabilitarCampos(frameEvento.getContentPane());
                enviarArchivoJButton.setEnabled(true);
                activarFormJButton.setEnabled(true);
            }
        });
        
    }
    
    protected void ejecutarInstruccionDeBoton(){}
    
    private void deshabilitarCampos(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JTextField
                    || comp instanceof JComboBox
                    || comp instanceof JDateChooser
                    || comp instanceof JSpinner
                    || comp instanceof JButton) {
                comp.setEnabled(false);
            } else if (comp instanceof Container container1) {
                deshabilitarCampos(container1);
            }
        }
    }
    
    private void habilitarCampos(Container container){
        for (Component comp : container.getComponents()) {
            if (comp instanceof JTextField
                    || comp instanceof JComboBox
                    || comp instanceof JDateChooser
                    || comp instanceof JSpinner
                    || comp instanceof JButton) {
                comp.setEnabled(true);
            } else if (comp instanceof Container container1) {
                habilitarCampos(container1);
            }
        }
    }

}
