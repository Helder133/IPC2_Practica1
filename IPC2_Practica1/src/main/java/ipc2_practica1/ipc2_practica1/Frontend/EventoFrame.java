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
public class EventoFrame extends FrameBase {

    private static final int LARGO = 150;
    private static final int ANCHO = 20;
    private JInternalFrame frameEvento;

    public void agregarVentanillaEvento(JDesktopPane jframe, int x, int y) {
        String titulo = "Ventanilla de evento";
        frameEvento = agragarVentanilla(jframe, titulo, x, y);
        agregarComponentes();
    }

    private void agregarComponentes() {
        Font font1 = new Font("Showcard Gothic", Font.BOLD, 15);
        Font font2 = new Font("Helvetica", Font.ITALIC, 15);

        JLabel codigoEvento = new JLabel("Codigo De Evento: ");
        setBonsJLabel(codigoEvento, 10, 50, LARGO, ANCHO, font1);
        frameEvento.add(codigoEvento);

        JTextField textCodigo = new JTextField();
        setBonsJText(textCodigo, 190, 50, LARGO, ANCHO, font2);
        frameEvento.add(textCodigo);

        JLabel fechaEvento = new JLabel("Fecha del evento: ");
        setBonsJLabel(fechaEvento, 10, 90, LARGO, ANCHO, font1);
        frameEvento.add(fechaEvento);

        JDateChooser eventoFecha = new JDateChooser();
        setBonsJDate(eventoFecha, 190, 90, LARGO, ANCHO, font2);
        frameEvento.add(eventoFecha);

        JLabel tipoEvento = new JLabel("Tipo De Evento: ");
        setBonsJLabel(tipoEvento, 10, 130, LARGO, ANCHO, font1);
        frameEvento.add(tipoEvento);

        String[] tipo = {"Seleccionar", "CHARLA", "CONGRESO", "TALLER", "DEBATE"};

        JComboBox<String> opciones = new JComboBox(tipo);
        setBonsJComboBox(opciones, 190, 130, LARGO, ANCHO, font2);
        frameEvento.add(opciones);

        JLabel tituloLabel = new JLabel("Titulo Del Evento: ");
        setBonsJLabel(tituloLabel, 10, 170, LARGO, ANCHO, font1);
        frameEvento.add(tituloLabel);

        JTextField textTitulo = new JTextField();
        setBonsJText(textTitulo, 190, 170, LARGO, ANCHO, font2);
        frameEvento.add(textTitulo);

        JLabel ubicacionJLabel = new JLabel("Ubicación: ");
        setBonsJLabel(ubicacionJLabel, 10, 210, LARGO, ANCHO, font1);
        frameEvento.add(ubicacionJLabel);

        JTextField textUbicacion = new JTextField();
        setBonsJText(textUbicacion, 190, 210, LARGO, ANCHO, font2);
        frameEvento.add(textUbicacion);

        JLabel cupoMaxJLabel = new JLabel("Cupo Máximo: ");
        setBonsJLabel(cupoMaxJLabel, 10, 250, LARGO, ANCHO, font1);
        frameEvento.add(cupoMaxJLabel);

        SpinnerNumberModel cupoMax = new SpinnerNumberModel(0, 0, 1000, 1);
        JSpinner jspinnerCupoMax = new JSpinner(cupoMax);
        setBonsJSpinner(jspinnerCupoMax, 190, 250, LARGO, ANCHO, font2);
        frameEvento.add(jspinnerCupoMax);

        JLabel costoJLabel = new JLabel("Costo Inscripción");
        setBonsJLabel(costoJLabel, 10, 290, LARGO, ANCHO, font1);
        frameEvento.add(costoJLabel);

        SpinnerNumberModel costo = new SpinnerNumberModel(0.00, 0.00, 1000.00, 1.50);
        JSpinner jspinnerCosto = new JSpinner(costo);
        setBonsJSpinner(jspinnerCosto, 190, 290, LARGO, ANCHO, font2);
        frameEvento.add(jspinnerCosto);

        JButton enviarFormularioJButton = new JButton("Enviar Formulario");
        setBonsJButton(enviarFormularioJButton, 25, 330, (LARGO + 50), ANCHO, font1);
        frameEvento.add(enviarFormularioJButton);
        enviarFormularioJButton.addActionListener(e -> {
            try {
                String codigo = textCodigo.getText();
                Date fecha = eventoFecha.getDate();
                String fechaStr = fecha != null ? new SimpleDateFormat(eventoFecha.getDateFormatString()).format(fecha) : "";
                String opcionEvento = (String) opciones.getSelectedItem();
                String titulo = textTitulo.getText();
                String ubicacion = textUbicacion.getText();
                int cupo = (int) jspinnerCupoMax.getValue();
                BigDecimal costoInscripcion = new BigDecimal(jspinnerCosto.getValue().toString());

                if (!codigo.equals("") && fecha != null && !opcionEvento.equals("Seleccionar") && !titulo.equals("")
                        && !ubicacion.equals("") && cupo > 0 && (costoInscripcion.compareTo(BigDecimal.ZERO) >= 0)) {
                    System.out.println("Entrando sin importar la validacion ");
                    
                    ControladorEntreBackendYFrontend controlador = new ControladorEntreBackendYFrontend();
                    controlador.insetarFormularioEvento(codigo, fechaStr, opcionEvento, titulo, ubicacion, cupo, costoInscripcion);
                    JOptionPane.showMessageDialog(null, "<html><p style=\"color:green; font:20px; \">Evento Guardado Exitosamente.</p></html>");

                    textCodigo.setText("");
                    eventoFecha.setDate(null);
                    opciones.setSelectedIndex(0);
                    textTitulo.setText("");
                    textUbicacion.setText("");
                    jspinnerCupoMax.setValue(0);
                    jspinnerCosto.setValue(0);
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
