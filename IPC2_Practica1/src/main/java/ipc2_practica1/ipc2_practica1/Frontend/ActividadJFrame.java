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
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author helder
 */
public class ActividadJFrame extends FrameBase {

    private static final int LARGO = 150;
    private static final int ANCHO = 20;
    private JInternalFrame frameEvento;

    public void agregarVentanillaEvento(JDesktopPane jframe, int x, int y) {
        String titulo = "Ventanilla de actividad";
        frameEvento = agragarVentanilla(jframe, titulo, x, y);
        agregarComponentes();
    }

    private void agregarComponentes() {
        Font font1 = new Font("Showcard Gothic", Font.BOLD, 15);
        Font font2 = new Font("Helvetica", Font.ITALIC, 15);

        JLabel codigoActividad = new JLabel("Codigo De Actividad: ");
        setBonsJLabel(codigoActividad, 10, 50, LARGO, ANCHO, font1);
        frameEvento.add(codigoActividad);

        JTextField textCodigoActividad = new JTextField();
        setBonsJText(textCodigoActividad, 190, 50, LARGO, ANCHO, font2);
        frameEvento.add(textCodigoActividad);

        JLabel codigoEvento = new JLabel("Codigo De Evento: ");
        setBonsJLabel(codigoEvento, 10, 90, LARGO, ANCHO, font1);
        frameEvento.add(codigoEvento);

        JTextField textCodigoEvento = new JTextField();
        setBonsJText(textCodigoEvento, 190, 90, LARGO, ANCHO, font2);
        frameEvento.add(textCodigoEvento);

        JLabel tipoEvento = new JLabel("Tipo De Actividad: ");
        setBonsJLabel(tipoEvento, 10, 130, LARGO, ANCHO, font1);
        frameEvento.add(tipoEvento);

        String[] tipo = {"Seleccionar", "CHARLA", "TALLER", "DEBATE", "OTRA"};

        JComboBox<String> opciones = new JComboBox(tipo);
        setBonsJComboBox(opciones, 190, 130, LARGO, ANCHO, font2);
        frameEvento.add(opciones);

        JLabel tituloLabel = new JLabel("Titulo Del Actividad: ");
        setBonsJLabel(tituloLabel, 10, 170, LARGO, ANCHO, font1);
        frameEvento.add(tituloLabel);

        JTextField textTitulo = new JTextField();
        setBonsJText(textTitulo, 190, 170, LARGO, ANCHO, font2);
        frameEvento.add(textTitulo);

        JLabel emailJLabel = new JLabel("Correo: ");
        setBonsJLabel(emailJLabel, 10, 210, LARGO, ANCHO, font1);
        frameEvento.add(emailJLabel);

        JTextField textEmail = new JTextField();
        setBonsJText(textEmail, 190, 210, LARGO, ANCHO, font2);
        frameEvento.add(textEmail);

        JLabel horaInicoJLabel = new JLabel("Hora inicio: ");
        setBonsJLabel(horaInicoJLabel, 10, 250, LARGO, ANCHO, font1);
        frameEvento.add(horaInicoJLabel);

        SpinnerDateModel horaInico = new SpinnerDateModel();
        JSpinner jspinnerHoraInicio = new JSpinner(horaInico);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(jspinnerHoraInicio, "HH:mm");
        jspinnerHoraInicio.setEditor(editor);
        setBonsJSpinner(jspinnerHoraInicio, 190, 250, LARGO, ANCHO, font2);
        frameEvento.add(jspinnerHoraInicio);

        JLabel horaFInJLabel = new JLabel("Hora fin: ");
        setBonsJLabel(horaFInJLabel, 10, 290, LARGO, ANCHO, font1);
        frameEvento.add(horaFInJLabel);

        SpinnerDateModel horaFin = new SpinnerDateModel();
        JSpinner jspinnerHoraFin = new JSpinner(horaFin);
        JSpinner.DateEditor editor1 = new JSpinner.DateEditor(jspinnerHoraFin, "HH:mm");
        jspinnerHoraFin.setEditor(editor1);
        setBonsJSpinner(jspinnerHoraFin, 190, 290, LARGO, ANCHO, font2);
        frameEvento.add(jspinnerHoraFin);

        JLabel cupoMaximoJLabel = new JLabel("Cupo Máximo");
        setBonsJLabel(cupoMaximoJLabel, 10, 330, LARGO, ANCHO, font1);
        frameEvento.add(cupoMaximoJLabel);

        SpinnerNumberModel cupoMax = new SpinnerNumberModel(0, 0, 1000, 1);
        JSpinner jspinnerCupoMax = new JSpinner(cupoMax);
        setBonsJSpinner(jspinnerCupoMax, 190, 330, LARGO, ANCHO, font2);
        frameEvento.add(jspinnerCupoMax);

        JButton enviarFormularioJButton = new JButton("Enviar Formulario");
        setBonsJButton(enviarFormularioJButton, 25, 370, (LARGO + 50), ANCHO, font1);
        frameEvento.add(enviarFormularioJButton);
        enviarFormularioJButton.addActionListener(e -> {
            try {
                String codigoActividad1 = textCodigoActividad.getText();
                String codigoEvento1 = textCodigoEvento.getText();
                String tipoActividad = (String) opciones.getSelectedItem();
                String tituloActividad = textTitulo.getText();
                String email = textEmail.getText();
                
                Date dateInicio = (Date) jspinnerHoraInicio.getValue();
                LocalTime horaInicio = dateInicio.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalTime();

                Date dateFin = (Date) jspinnerHoraFin.getValue();
                LocalTime horaFin1 = dateFin.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalTime();
                int cupo = (int) jspinnerCupoMax.getValue();

                if (!codigoActividad1.equals("") && !codigoEvento1.equals("") && opciones.getSelectedIndex() > 0 && !tituloActividad.equals("") && !email.equals("")
                        && horaInicio != null && horaFin1 != null && cupo > 0) {

                    ControladorEntreBackendYFrontend controlador = new ControladorEntreBackendYFrontend();
                    controlador.insetarFormularioActividad(codigoActividad1, codigoEvento1, tipoActividad, tituloActividad, email, horaInicio, horaFin1, cupo);
                    JOptionPane.showMessageDialog(null, "<html><p style=\"color:green; font:20px; \">Evento Guardado Exitosamente.</p></html>");

                    textCodigoActividad.setText("");
                    textCodigoEvento.setText("");
                    opciones.setSelectedIndex(0);
                    textTitulo.setText("");
                    textEmail.setText("");
                    jspinnerHoraInicio.setValue(0);
                    jspinnerHoraFin.setValue(0);
                    jspinnerCupoMax.setValue(0);
                    
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

    private void setBonsJDate(JDateChooser deta, int x, int y, int z, int w, Font font) {
        deta.setBounds(x, y, z, w);
        deta.setFont(font);
        deta.setDateFormatString("dd/MM/yyyy");
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
