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
public class PagoJFrame extends FrameBase {

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

        /*JLabel participanteEmail = new JLabel("Nombre de participante...");
        setBonsJLabel(participanteEmail, 240, 50, LARGO, ANCHO, font1);
        frameEvento.add(participanteEmail);*/

        List<String> participante;
        try {
            participante = controlador.correoDesdeInscripcion();
            emailParticipante.addItem("Seleccione correo...");
            for (String participante1 : participante) {
                emailParticipante.addItem(participante1);
            }

            /*emailParticipante.addActionListener(e -> {
                int index = emailParticipante.getSelectedIndex();
                if (index > 0) {
                    participanteEmail.setText(participante[index-1][0]);
                } else {
                    participanteEmail.setText("Nombre del participante...");
                }
            });*/
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "<html><p style=\"color:red; font:20px; \">Erro: " + ex.getErrorCode() + ".</p></html>");
        }

        JComboBox<String> codigoEvento = new JComboBox<>();
        setBonsJComboBox(codigoEvento, 10, 100, LARGO, ANCHO, font2);
        frameEvento.add(codigoEvento);

        /*JLabel tituloEvento = new JLabel("Titulo de evento...");
        setBonsJLabel(tituloEvento, 240, 100, LARGO, ANCHO, font1);
        frameEvento.add(tituloEvento);*/

        List<String> evento;
        try {
            evento = controlador.codigoEventoDesdeInscripcion();
            codigoEvento.addItem("Seleccione codigo...");
            for (String evento1 : evento) {
                codigoEvento.addItem(evento1);
            }

            /*codigoEvento.addActionListener(ex -> {
                int index = codigoEvento.getSelectedIndex();
                if (index > 0) {
                    tituloEvento.setText(evento[index-1][1]);
                } else {
                    tituloEvento.setText("Nombre del evento...");
                }
            });*/

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "<html><p style=\"color:red; font:20px; \">Erro: " + e.getErrorCode() + ".</p></html>");
        }
        
        JLabel precio = new JLabel("Cantidad de pago: ");
        setBonsJLabel(precio, 10, 150, LARGO, ANCHO, font1);
        frameEvento.add(precio);
        
        SpinnerNumberModel cantidad = new SpinnerNumberModel(0, 0, 1000, 1);
        JSpinner jspinnerCantidad = new JSpinner(cantidad);
        setBonsJSpinner(jspinnerCantidad, 240, 150, LARGO, ANCHO, font2);
        frameEvento.add(jspinnerCantidad);
        
        JLabel tipoInscripcion = new JLabel("Metodo de pago: ");
        setBonsJLabel(tipoInscripcion, 10, 200, LARGO, ANCHO, font1);
        frameEvento.add(tipoInscripcion);
        
        String[] tipo = {"Seleccionar", "EFECTIVO", "TRANSFERENCIA", "TARJETA"};

        JComboBox<String> metodoPago = new JComboBox(tipo);
        setBonsJComboBox(metodoPago, 240, 200, LARGO, ANCHO, font2);
        frameEvento.add(metodoPago);

        JButton enviarFormularioJButton = new JButton("Enviar Formulario");
        setBonsJButton(enviarFormularioJButton, 25, 250, (LARGO + 50), ANCHO, font1);
        frameEvento.add(enviarFormularioJButton);
        enviarFormularioJButton.addActionListener(e -> {
            try {
                String email = (String) emailParticipante.getSelectedItem();
                String codigo = (String) codigoEvento.getSelectedItem();
                String metodo = (String) metodoPago.getSelectedItem();
                BigDecimal cantidad1 = new BigDecimal(jspinnerCantidad.getValue().toString());

                if (!email.equals("Seleccione correo...") && cantidad1.compareTo(BigDecimal.ZERO) > 0
                        && !codigo.equals("Seleccione codigo...") && !metodo.equals("Seleccionar")) {

                    controlador.insetarFormularioPago(email, codigo, metodo, cantidad1);
                    JOptionPane.showMessageDialog(null, "<html><p style=\"color:green; font:20px; \">Inscripción registrada Exitosamente.</p></html>");

                    emailParticipante.setSelectedIndex(0);
                    codigoEvento.setSelectedIndex(0);
                    metodoPago.setSelectedIndex(0);
                    jspinnerCantidad.setValue(0);
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
    
    private void setBonsJSpinner(JSpinner jspinner, int x, int y, int z, int w, Font font) {
        jspinner.setBounds(x, y, z, w);
        jspinner.setFont(font);
    }

}