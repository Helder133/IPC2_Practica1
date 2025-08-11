/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Frontend;

import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author helder
 */
public class EventoFrame extends FrameBase {

    private JInternalFrame frameEvento;

    public void agregarVentanillaEvento(JDesktopPane jframe, int x, int y) {
        String title = "Ventanilla de evento";
        frameEvento = super.agragarVentanilla(jframe, title, x, y);
    }

    public void agregarComponentes() {
        Font font1 = new Font("Showcard Gothic", Font.BOLD, 15);
        
        JLabel codigoEvento = new JLabel("Codigo De Evento: ");
        codigoEvento.setBounds(10, 50, 150, 20);
        codigoEvento.setFont(font1);
        frameEvento.add(codigoEvento);
        
        JTextField textCodigo = new JTextField();
        textCodigo.setBounds(190, 50, 150, 20);
        textCodigo.setFont(font1);
        frameEvento.add(textCodigo);
        
        JLabel fechaEvento = new JLabel ("Fecha del evento: ");
        fechaEvento.setBounds(10, 90, 150, 20);
        fechaEvento.setFont(font1);
        frameEvento.add(fechaEvento);
        
        JDateChooser eventoFecha = new JDateChooser();
        eventoFecha.setDateFormatString("dd/MM/yyyy");
        eventoFecha.setBounds(190, 90, 150, 20);
        frameEvento.add(eventoFecha);
        
        JLabel tipoEvento = new JLabel("Tipo De Evento");
        tipoEvento.setBounds(10, 130, 150, 20);
        tipoEvento.setFont(font1);
        frameEvento.add(tipoEvento);
        
        String [] tipo = {"CHARLA", "CONGRESO", "TALLER", "DEBATE"};
        
        JComboBox<String> opciones = new JComboBox(tipo);
        opciones.setBounds(190, 130, 150, 20);
        opciones.setFont(font1);
        frameEvento.add(opciones);
        
        opciones.addActionListener((ActionEvent ae) -> {
            String seleccion = (String) opciones.getSelectedItem();
            tipoEvento.setText(seleccion);
        });
        
    }

}
