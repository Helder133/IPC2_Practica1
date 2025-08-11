/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Frontend;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

/**
 *
 * @author helder
 */
public class FrameBase extends JInternalFrame {
    
    public JInternalFrame agragarVentanilla(JDesktopPane jframe, String titulo, int x, int y){
        JInternalFrame ventana = new JInternalFrame(title, true, true, true, true);
        ventana.setLayout(null);
        ventana.setBounds(x, y, 600, 450);
        ventana.setBackground(Color.LIGHT_GRAY);
        ventana.setVisible(true);
        jframe.add(ventana);
        agregarComponentes(ventana);
        return ventana;
    }
    
    private void agregarComponentes (JInternalFrame frameEvento) {
        Font font2 = new Font("Helvetica", Font.BOLD, 15);
        
        JLabel rutaArchivo = new JLabel("Ruta archivo...");
        rutaArchivo.setFont(font2);
        rutaArchivo.setBounds(180, 10, 350, 20);
        frameEvento.add(rutaArchivo);
        
        JButton cargaArchivo = new JButton("Carga de archivos");
        cargaArchivo.setBounds(10, 10, 150, 20);
        frameEvento.add(cargaArchivo);
        cargaArchivo.addActionListener(e -> {
            JFileChooser archivo = new JFileChooser();
            archivo.setDialogTitle("Seleccionar un archivo");
            
            int resultado = archivo.showOpenDialog(null);
            
            if (resultado == JFileChooser.APPROVE_OPTION) {
                java.io.File archivoSeleccionado = archivo.getSelectedFile();
                
                String ruta = archivoSeleccionado.getAbsolutePath();
                
                rutaArchivo.setText(ruta);
            }
        });
    }
}
