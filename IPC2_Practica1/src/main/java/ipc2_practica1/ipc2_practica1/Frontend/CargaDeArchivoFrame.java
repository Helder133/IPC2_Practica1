/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Frontend;

import ipc2_practica1.ipc2_practica1.Backend.ControladorEntreBackendYFrontend;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author helder
 */
public class CargaDeArchivoFrame extends FrameBase {

    private static final int LARGO = 200;
    private static final int ANCHO = 20;
    private String archivoPath;
    private String carpetaPath;
    private final ControladorEntreBackendYFrontend controlador = new ControladorEntreBackendYFrontend();
    private JInternalFrame frameEvento;
    private JLabel mensaje;
    private JProgressBar progresoBarra;
    private JTextArea logArea;
    private JScrollPane logScroll;
    
    public void CargaDeArchivoFrame(JDesktopPane jframe, int x, int y) {
        String titulo = "Ventanilla de carga de archivo";
        frameEvento = agragarVentanilla(jframe, titulo, x, y);
        cargarComponenetes();
    }

    private void cargarComponenetes() {
        Font font1 = new Font("Showcard Gothic", Font.BOLD, 15);
        Font font2 = new Font("Helvetica", Font.ITALIC, 15);
        
        //---------------- Direccion del archivo a cargar ----------------------
        JButton cargaArchivo = new JButton("Carga de archivos");
        setBonsJButton(cargaArchivo, 10, 10, LARGO, ANCHO, font1);
        frameEvento.add(cargaArchivo);
        
        JLabel rutaArchivo = new JLabel("Archivo Seleccionado...");
        setBonsJLabel(rutaArchivo, 230, 10, LARGO, ANCHO, font2);
        frameEvento.add(rutaArchivo);
        
        cargaArchivo.addActionListener(e -> {
            JFileChooser archivo = new JFileChooser();
            archivo.setDialogTitle("Seleccionar un archivo");

            int resultado = archivo.showOpenDialog(null);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                java.io.File archivoSeleccionado = archivo.getSelectedFile();

                archivoPath = archivoSeleccionado.getAbsolutePath();
                rutaArchivo.setText(archivoSeleccionado.getName());
                
            }
        });
        
        //---------------- Direccion de la carpeta a usar ----------------------
        JButton direccionCarpeta = new JButton("Seleccion de carpeta");
        setBonsJButton(direccionCarpeta, 10, 50, LARGO, ANCHO, font1);
        frameEvento.add(direccionCarpeta);
        
        JLabel rutaCarpeta = new JLabel("Carpeta Seleccionado...");
        setBonsJLabel(rutaCarpeta, 230, 50, LARGO, ANCHO, font2);
        frameEvento.add(rutaCarpeta);
        
        direccionCarpeta.addActionListener(e -> {
            JFileChooser carpeta = new JFileChooser();
            carpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            carpeta.setDialogTitle("Seleccionar un archivo");

            int resultado = carpeta.showOpenDialog(null);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File pathCarpeta = carpeta.getSelectedFile();
                
                
                carpetaPath = pathCarpeta.getAbsolutePath();
                rutaCarpeta.setText(pathCarpeta.getName());
                
            }
        });
        
        //---------------- Tiempo de carga:  ----------------------
        JLabel tiempoJLabel = new JLabel("Tiempo de carga: ");
        setBonsJLabel(tiempoJLabel, 10, 90, LARGO, ANCHO, font1);
        frameEvento.add(tiempoJLabel);
        
        SpinnerNumberModel tiempo = new SpinnerNumberModel(0, 0, 1000000, 100);
        JSpinner jspinnerTiempo = new JSpinner(tiempo);
        setBonsJSpinner(jspinnerTiempo, 230, 90, LARGO, ANCHO, font2);
        frameEvento.add(jspinnerTiempo);
        
        
        //---------------- Enviar datos al backend ----------------------
        JButton enviarArchivo = new JButton("Enviar Archivo");
        setBonsJButton(enviarArchivo, 150, 130, LARGO, ANCHO, font1);
        frameEvento.add(enviarArchivo);
        
        enviarArchivo.addActionListener(e -> {
            if (!rutaArchivo.getText().equalsIgnoreCase("Archivo Seleccionado...") && !rutaCarpeta.getText().equalsIgnoreCase("Carpeta Seleccionado...") && (int) jspinnerTiempo.getValue() > 0) {
                try {
                    controlador.setCarga(this);
                    controlador.insetarArchivo(archivoPath, (int) jspinnerTiempo.getValue(), carpetaPath);
                } catch (IOException ex) {
                    Logger.getLogger(CargaDeArchivoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "<html><p style=\"color:red; font:20px; \">Error: tiene que llenar todos los campos.</p></html>");
            }
        });
        
         // ---------------- Progreso de barra -----------------------
        progresoBarra = new JProgressBar(0, 100);
        progresoBarra.setStringPainted(true);
        progresoBarra.setValue(0);
        progresoBarra.setBounds(10, 190, 560, 20);
        frameEvento.add(progresoBarra);

        // ---------------- Log de mensajes --------------------
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logScroll = new JScrollPane(logArea);
        logScroll.setBounds(10, 220, 560, 180);
        frameEvento.add(logScroll);
        
        //---------------- Mensaje interactivo ----------------------
        mensaje = new JLabel("");
        setBonsJLabel(mensaje, 10, 170, LARGO*3, ANCHO*2, font2);
        //frameEvento.add(mensaje);
        
    }
    
    // Método seguro para cambiar el texto del label y agregar al log (se puede llamar desde cualquier hilo)
    public void modificarMensaje(String texto) {
        SwingUtilities.invokeLater(() -> {
            if (mensaje != null) mensaje.setText(texto);
            if (logArea != null) {
                logArea.append(texto + "\n");
                // desplazar al final
                logArea.setCaretPosition(logArea.getDocument().getLength());
            }
        });
    }

    // Método seguro para actualizar progressBar
    public void setProgresoBarra(int porcentaje) {
        SwingUtilities.invokeLater(() -> {
            if (progresoBarra != null) progresoBarra.setValue(Math.max(0, Math.min(100, porcentaje)));
        });
    }

    // opcional: limpiar log/progreso antes de iniciar
    public void limpiarLogYProgreso() {
        SwingUtilities.invokeLater(() -> {
            if (progresoBarra != null) progresoBarra.setValue(0);
            if (logArea != null) logArea.setText("");
            if (mensaje != null) mensaje.setText("");
        });
    }

    
    private void setBonsJSpinner(JSpinner jspinner, int x, int y, int z, int w, Font font) {
        jspinner.setBounds(x, y, z, w);
        jspinner.setFont(font);
    }
    
    private void setBonsJButton(JButton jbutton, int x, int y, int z, int w, Font font) {
        jbutton.setBounds(x, y, z, w);
        jbutton.setFont(font);
    }

    private void setBonsJLabel(JLabel jlabel, int x, int y, int z, int w, Font font) {
        jlabel.setBounds(x, y, z, w);
        jlabel.setFont(font);
    }

}
