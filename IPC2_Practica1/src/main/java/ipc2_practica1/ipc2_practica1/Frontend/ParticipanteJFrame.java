/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Frontend;

import com.toedter.calendar.JDateChooser;
import ipc2_practica1.ipc2_practica1.Backend.ControladorEntreBackendYFrontend;
import java.awt.Font;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author helder
 */
public class ParticipanteJFrame extends FrameBase {

    private static final int LARGO = 150;
    private static final int ANCHO = 20;
    private JInternalFrame frameEvento;

    public void agregarVentanillaEvento(JDesktopPane jframe, int x, int y) {
        String titulo = "Ventanilla de participante";
        frameEvento = agragarVentanilla(jframe, titulo, x, y);
        agregarComponentes();
    }

    private void agregarComponentes() {
        Font font1 = new Font("Showcard Gothic", Font.BOLD, 15);
        Font font2 = new Font("Helvetica", Font.ITALIC, 15);

        JLabel codigoEvento = new JLabel("Nombre Completo: ");
        setBonsJLabel(codigoEvento, 10, 50, LARGO, ANCHO, font1);
        frameEvento.add(codigoEvento);

        JTextField textNombre = new JTextField();
        setBonsJText(textNombre, 190, 50, LARGO, ANCHO, font2);
        frameEvento.add(textNombre);

        JLabel tipoEvento = new JLabel("Tipo De Participante: ");
        setBonsJLabel(tipoEvento, 10, 100, LARGO, ANCHO, font1);
        frameEvento.add(tipoEvento);

        String[] tipo = {"Seleccionar", "ESTUDIANTE", "CONGRESO", "PROFESIONAL", "INVITADO"};

        JComboBox<String> opciones = new JComboBox(tipo);
        setBonsJComboBox(opciones, 190, 100, LARGO, ANCHO, font2);
        frameEvento.add(opciones);

        JLabel institucionJLabel = new JLabel("Institucion : ");
        setBonsJLabel(institucionJLabel, 10, 150, LARGO, ANCHO, font1);
        frameEvento.add(institucionJLabel);

        JTextField institucionJTitulo = new JTextField();
        setBonsJText(institucionJTitulo, 190, 150, LARGO, ANCHO, font2);
        frameEvento.add(institucionJTitulo);

        JLabel emailJLabel = new JLabel("Correo: ");
        setBonsJLabel(emailJLabel, 10, 200, LARGO, ANCHO, font1);
        frameEvento.add(emailJLabel);

        JTextField emailJText = new JTextField();
        setBonsJText(emailJText, 190, 200, LARGO, ANCHO, font2);
        frameEvento.add(emailJText);

        JButton enviarFormularioJButton = new JButton("Enviar Formulario");
        setBonsJButton(enviarFormularioJButton, 25, 250, (LARGO + 50), ANCHO, font1);
        frameEvento.add(enviarFormularioJButton);
        enviarFormularioJButton.addActionListener(e -> {
            try {
                String nombre = textNombre.getText();
                String opcionParticipante = (String) opciones.getSelectedItem();
                String institucion = institucionJTitulo.getText();
                String email = emailJText.getText();
                
                if (nombre.length() > 45 ) {
                    JOptionPane.showMessageDialog(null, "<html><p style=\"color:red; font:20px; \">Erro: el nombre sobrepasa los 45 caracteres.</p></html>");
                    return;
                } else if (institucion.length() > 150) {
                    JOptionPane.showMessageDialog(null, "<html><p style=\"color:red; font:20px; \">Erro: el nombre de institución sobrepasa los 150 caracteres.</p></html>");
                    return;
                }
                
                if (!nombre.equals("") && !opcionParticipante.equals("Seleccionar") 
                        && !institucion.equals("") && !email.equals("")) {
                    
                    ControladorEntreBackendYFrontend controlador = new ControladorEntreBackendYFrontend();
                    controlador.insetarFormularioParticipante(nombre, opcionParticipante, institucion, email);
                    JOptionPane.showMessageDialog(null, "<html><p style=\"color:green; font:20px; \">Participante Guardado Exitosamente.</p></html>");

                    textNombre.setText("");
                    opciones.setSelectedIndex(0);
                    institucionJTitulo.setText("");
                    emailJText.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "<html><p style=\"color:red; font:20px; \">Erro: todo los campos son obligatorios.</p></html>");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "<html><p style=\"color:red; font:20px; \">" + ex.getMessage() + "\n Vuelva a intentar.</p></html>");
            } catch (HeadlessException er) {
                System.out.println("error");
            }
        });
    }

    private void setBonsJButton(JButton jbutton, int x, int y, int z, int w, Font font) {
        jbutton.setBounds(x, y, z, w);
        jbutton.setFont(font);
    }

    private void setBonsJLabel(JLabel jlabel, int x, int y, int z, int w, Font font) {
        jlabel.setBounds(x, y, z, w);
        jlabel.setFont(font);
    }

    private void setBonsJText(JTextField jtextfield, int x, int y, int z, int w, Font font) {
        jtextfield.setBounds(x, y, z, w);
        jtextfield.setFont(font);
    }

    private void setBonsJComboBox(JComboBox<String> jcombobox, int x, int y, int z, int w, Font font) {
        jcombobox.setBounds(x, y, z, w);
        jcombobox.setFont(font);
    }
}
