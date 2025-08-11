/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Frontend;

import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author helder
 */
public class EventoFrame extends FrameBase {

    private JInternalFrame frameEvento;

    public void agregarVentanillaEvento(JDesktopPane jframe, int x, int y) {
        String titulo = "Ventanilla de evento";
        frameEvento = super.agragarVentanilla(jframe, titulo, x, y);
    }

    public void agregarComponentes() {
        Font font1 = new Font("Showcard Gothic", Font.BOLD, 15);
        Font font2 = new Font("Helvetica", Font.ITALIC, 15);
        
        JLabel codigoEvento = new JLabel("Codigo De Evento: ");
        codigoEvento.setBounds(10, 50, 150, 20);
        codigoEvento.setFont(font1);
        frameEvento.add(codigoEvento);
        
        JTextField textCodigo = new JTextField();
        textCodigo.setBounds(190, 50, 150, 20);
        textCodigo.setFont(font2);
        //validarCargaArchivos(textCodigo);
        frameEvento.add(textCodigo);
        
        JLabel fechaEvento = new JLabel ("Fecha del evento: ");
        fechaEvento.setBounds(10, 90, 150, 20);
        fechaEvento.setFont(font1);
        frameEvento.add(fechaEvento);
        
        JDateChooser eventoFecha = new JDateChooser();
        eventoFecha.setFont(font2);
        eventoFecha.setDateFormatString("dd/MM/yyyy");
        eventoFecha.setBounds(190, 90, 150, 20);
        //validarCargaArchivos1(eventoFecha);
        frameEvento.add(eventoFecha);
        
        JLabel tipoEvento = new JLabel("Tipo De Evento: ");
        tipoEvento.setBounds(10, 130, 150, 20);
        tipoEvento.setFont(font1);
        frameEvento.add(tipoEvento);
        
        String [] tipo = {"Seleccionar...","CHARLA", "CONGRESO", "TALLER", "DEBATE"};
        
        JComboBox<String> opciones = new JComboBox(tipo);
        opciones.setBounds(190, 130, 150, 20);
        opciones.setFont(font2);
        //validarCargaArchivos2(opciones);
        frameEvento.add(opciones);
        
        
        JLabel tituloLabel = new JLabel("Titulo Del Evento: ");
        tituloLabel.setBounds(10, 170, 150, 20);
        tituloLabel.setFont(font1);
        frameEvento.add(tituloLabel);
        
        JTextField textTitulo = new JTextField();
        textTitulo.setBounds(190, 170, 150, 20);
        textTitulo.setFont(font2);
        //validarCargaArchivos(textTitulo);
        frameEvento.add(textTitulo);
        
        JLabel ubicacionJLabel = new JLabel("Ubicación: ");
        ubicacionJLabel.setBounds(10, 210, 150, 20);
        ubicacionJLabel.setFont(font1);
        frameEvento.add(ubicacionJLabel);
        
        JTextField textUbicacion = new JTextField();
        textUbicacion.setBounds(190, 210, 150, 20);
        textUbicacion.setFont(font2);
        //validarCargaArchivos(textUbicacion);
        frameEvento.add(textUbicacion);
        
        JLabel cupoMaxJLabel = new JLabel("Cupo Máximo: ");
        cupoMaxJLabel.setBounds(10, 250, 150, 20);
        cupoMaxJLabel.setFont(font1);
        frameEvento.add(cupoMaxJLabel);
        
        SpinnerNumberModel cupoMax = new SpinnerNumberModel(0,0,1000,1);
        JSpinner jspinnerCupoMax = new JSpinner(cupoMax);
        jspinnerCupoMax.setBounds(190, 250, 150, 20);
        jspinnerCupoMax.setFont(font2);
        //validarCargaArchivos3(jspinnerCupoMax);
        frameEvento.add(jspinnerCupoMax);
    }
    
    /*@Override
    protected void validarCargaArchivos(JTextField text){
        if (!super.path.equalsIgnoreCase("")) {
            text.setEnabled(false);
        }
    }
    
    @Override
    protected void validarCargaArchivos1(JDateChooser fecha){
        if (!super.path.equalsIgnoreCase("")) {
            fecha.setEnabled(false);
        }
    }
    
    @Override
    protected void validarCargaArchivos2(JComboBox<String> opciones){
        if (!super.path.equalsIgnoreCase("")) {
            opciones.setEnabled(false);
        }
    }
    
    @Override
    protected void validarCargaArchivos3(JSpinner jspiner){
        if (!super.path.equalsIgnoreCase("")) {
            jspiner.setEnabled(false);
        }
    }*/
}
