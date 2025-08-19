/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Frontend;

import ipc2_practica1.ipc2_practica1.Backend.ControladorEntreBackendYFrontend;
import java.awt.Font;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author helder
 */
public class ValidarPago extends FrameBase {

    private static final int LARGO = 200;
    private static final int ANCHO = 30;
    private JInternalFrame frameEvento;
    private final ControladorEntreBackendYFrontend controlador = new ControladorEntreBackendYFrontend();

    public void agregarVentanillaEvento(JDesktopPane jframe, int x, int y) {
        String titulo = "Ventanilla de Validar Pago";
        frameEvento = agragarVentanilla(jframe, titulo, x, y);
        agregarComponentes();
    }

    private void agregarComponentes() {
        Font font1 = new Font("Showcard Gothic", Font.BOLD, 15);
        Font font2 = new Font("Helvetica", Font.ITALIC, 15);

        JComboBox<String> emailParticipante = new JComboBox<>();
        setBonsJComboBox(emailParticipante, 10, 50, LARGO, ANCHO, font2);
        frameEvento.add(emailParticipante);

        List<String> participante;
        try {
            participante = controlador.correoDesdePago();
            emailParticipante.addItem("Seleccione correo...");
            for (String participante1 : participante) {
                emailParticipante.addItem(participante1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><p style=\"color:red; font:20px; \">Erro: " + ex.getErrorCode() + ".</p></html>");
        }

        JComboBox<String> codigoEvento = new JComboBox<>();
        setBonsJComboBox(codigoEvento, 10, 100, LARGO, ANCHO, font2);
        frameEvento.add(codigoEvento);

        List<String> evento;
        try {
            evento = controlador.codigoEventoDesdePago();
            codigoEvento.addItem("Seleccione codigo...");
            for (String evento1 : evento) {
                codigoEvento.addItem(evento1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "<html><p style=\"color:red; font:20px; \">Erro: " + e.getErrorCode() + ".</p></html>");
        }

        JButton enviarFormularioJButton = new JButton("Enviar Formulario");
        setBonsJButton(enviarFormularioJButton, 25, 250, (LARGO + 50), ANCHO, font1);
        frameEvento.add(enviarFormularioJButton);
        enviarFormularioJButton.addActionListener(e -> {
            try {
                String[] entidad = new String[2];
                entidad[0] = (String) emailParticipante.getSelectedItem();
                entidad[1] = (String) codigoEvento.getSelectedItem();

                if (!entidad[0].equals("Seleccione correo...") && !entidad[1].equals("Seleccione codigo...") ) {
                    controlador.validarPago(entidad);
                    JOptionPane.showMessageDialog(null, "<html><p style=\"color:green; font:20px; \">Inscripción registrada Exitosamente.</p></html>");

                    emailParticipante.setSelectedIndex(0);
                    codigoEvento.setSelectedIndex(0);
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

    private void setBonsJComboBox(JComboBox<String> jcombobox, int x, int y, int z, int w, Font font) {
        jcombobox.setBounds(x, y, z, w);
        jcombobox.setFont(font);
    }

}
