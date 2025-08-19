/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Frontend;

import ipc2_practica1.ipc2_practica1.Backend.ControladorEntreBackendYFrontend;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author helder
 */
public class InscripcionJFrame extends FrameBase {

    private static final int LARGO = 200;
    private static final int ANCHO = 30;
    private JInternalFrame frameEvento;
    private final ControladorEntreBackendYFrontend controlador = new ControladorEntreBackendYFrontend();

    public void agregarVentanillaEvento(JDesktopPane jframe, int x, int y) {
        String titulo = "Ventanilla de inscripción";
        frameEvento = agragarVentanilla(jframe, titulo, x, y);
        agregarComponentes();
    }

    private void agregarComponentes() {
        Font font1 = new Font("Showcard Gothic", Font.BOLD, 15);
        Font font2 = new Font("Helvetica", Font.ITALIC, 15);

        JComboBox<String> emailParticipante = new JComboBox<>();
        setBonsJComboBox(emailParticipante, 10, 50, LARGO, ANCHO, font2);
        frameEvento.add(emailParticipante);

        JLabel participanteEmail = new JLabel("Nombre de participante...");
        setBonsJLabel(participanteEmail, 240, 50, LARGO, ANCHO, font1);
        frameEvento.add(participanteEmail);

        String[][] participante;
        try {
            participante = controlador.getParticipantes();
            emailParticipante.addItem("Seleccione correo...");
            for (String[] participante1 : participante) {
                emailParticipante.addItem(participante1[1]);
            }

            emailParticipante.addActionListener(e -> {
                int index = emailParticipante.getSelectedIndex();
                if (index > 0) {
                    participanteEmail.setText(participante[index-1][0]);
                } else {
                    participanteEmail.setText("Nombre del participante...");
                }
            });
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><p style=\"color:red; font:20px; \">Erro: " + ex.getErrorCode() + ".</p></html>");
        }

        JComboBox<String> codigoEvento = new JComboBox<>();
        setBonsJComboBox(codigoEvento, 10, 100, LARGO, ANCHO, font2);
        frameEvento.add(codigoEvento);

        JLabel tituloEvento = new JLabel("Titulo de evento...");
        setBonsJLabel(tituloEvento, 240, 100, LARGO, ANCHO, font1);
        frameEvento.add(tituloEvento);

        String[][] evento;
        try {
            evento = controlador.getEventos();
            codigoEvento.addItem("Seleccione codigo...");
            for (String[] evento1 : evento) {
                codigoEvento.addItem(evento1[0]);
            }

            codigoEvento.addActionListener(ex -> {
                int index = codigoEvento.getSelectedIndex();
                if (index > 0) {
                    tituloEvento.setText(evento[index-1][1]);
                } else {
                    tituloEvento.setText("Nombre del evento...");
                }
            });

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "<html><p style=\"color:red; font:20px; \">Erro: " + e.getErrorCode() + ".</p></html>");
        }
        
        JLabel tipoInscripcion = new JLabel("Tipo inscripcion");
        setBonsJLabel(tipoInscripcion, 10, 150, LARGO, ANCHO, font1);
        frameEvento.add(tipoInscripcion);
        
        String[] tipo = {"Seleccionar", "ASISTENTE", "CONFERENCISTA", "TALLERISTA", "OTRO"};

        JComboBox<String> inscripcionTipo = new JComboBox(tipo);
        setBonsJComboBox(inscripcionTipo, 240, 150, LARGO, ANCHO, font2);
        frameEvento.add(inscripcionTipo);

        JButton enviarFormularioJButton = new JButton("Enviar Formulario");
        setBonsJButton(enviarFormularioJButton, 25, 250, (LARGO + 50), ANCHO, font1);
        frameEvento.add(enviarFormularioJButton);
        enviarFormularioJButton.addActionListener(e -> {
            try {
                String email = (String) emailParticipante.getSelectedItem();
                String codigo = (String) codigoEvento.getSelectedItem();
                String tipoI = (String) inscripcionTipo.getSelectedItem();

                if (!email.equals("Seleccione correo...") && !tipoI.equals("Seleccionar")
                        && !codigo.equals("Seleccione codigo...")) {

                    controlador.insetarFormularioInscripcion(email, codigo, tipoI);
                    JOptionPane.showMessageDialog(null, "<html><p style=\"color:green; font:20px; \">Inscripción registrada Exitosamente.</p></html>");

                    emailParticipante.setSelectedIndex(0);
                    codigoEvento.setSelectedIndex(0);
                    inscripcionTipo.setSelectedIndex(0);
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

    private void setBonsJComboBox(JComboBox<String> jcombobox, int x, int y, int z, int w, Font font) {
        jcombobox.setBounds(x, y, z, w);
        jcombobox.setFont(font);
    }

}
