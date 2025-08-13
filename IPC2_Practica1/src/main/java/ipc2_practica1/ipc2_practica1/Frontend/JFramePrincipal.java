/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author helder
 */
public class JFramePrincipal extends JFrame{
    public void inicio() {
        setTitle("Administrador de eventos");
        setLayout(null);
        setVisible(true);
        setResizable(false);
        getContentPane().setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(3);
        setSize(1000,700);
        setLocationRelativeTo(null);
        
        Font font1 = new Font("Helvetica", Font.BOLD, 30);
        
        JLabel tituloJLabel = new JLabel("Administrador de eventos y actividades");
        tituloJLabel.setLayout(null);
        tituloJLabel.setVisible(true);
        tituloJLabel.setForeground(Color.white);
        tituloJLabel.setBounds(100, 20, 900, 60);
        tituloJLabel.setFont(font1);
        add(tituloJLabel);
        
        //Agreando un panel para los botones
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
        panelButtons.setBackground(Color.GRAY);
        panelButtons.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelButtons.setBounds(25, 100, 225, 550);
        
        //Creando botones
        JButton buttonEvento = new JButton("Registrar Evento");
        JButton buttonParticipante = new JButton("Registrar Participante");
        JButton buttonInscripcion = new JButton("Inscripción");
        JButton buttonPago = new JButton("Pago");
        JButton buttonValidarInscripcion = new JButton("validar Inscripción");
        JButton buttonActividad = new JButton("Registrar Actividad");
        JButton buttonAsistencia = new JButton("Registrar Asistencia");
        JButton buttonCertificado = new JButton("Certidicado");
        JButton buttonReportePaticipantes = new JButton("Reporte De Participantes");
        JButton buttonReporteActividad = new JButton("Reporte De Actividades");
        JButton buttonReporteEvento = new JButton("Reporte De Eventos");
        
        //Agragando los botones al panel
        panelButtons.add(buttonEvento);
        panelButtons.add(Box.createVerticalStrut(15));
        panelButtons.add(buttonParticipante);
        panelButtons.add(Box.createVerticalStrut(15));
        panelButtons.add(buttonInscripcion);
        panelButtons.add(Box.createVerticalStrut(15));
        panelButtons.add(buttonPago);
        panelButtons.add(Box.createVerticalStrut(15));
        panelButtons.add(buttonValidarInscripcion);
        panelButtons.add(Box.createVerticalStrut(15));
        panelButtons.add(buttonActividad);
        panelButtons.add(Box.createVerticalStrut(15));
        panelButtons.add(buttonAsistencia);
        panelButtons.add(Box.createVerticalStrut(15));
        panelButtons.add(buttonCertificado);
        panelButtons.add(Box.createVerticalStrut(15));
        panelButtons.add(buttonReportePaticipantes);
        panelButtons.add(Box.createVerticalStrut(15));
        panelButtons.add(buttonReporteActividad);
        panelButtons.add(Box.createVerticalStrut(15));
        panelButtons.add(buttonReporteEvento);
        //Agragar el panel al JFrame al lado izquierdo 
        add(panelButtons, BorderLayout.WEST);
        
        
        JDesktopPane escritorio = new JDesktopPane();
        escritorio.setBackground(Color.GRAY);
        escritorio.setBounds(275, 100, 700, 550);
        add(escritorio);
        
        buttonEvento.addActionListener(e -> {
            EventoFrame base = new EventoFrame();
            base.agregarVentanillaEvento(escritorio, 20, 20);
            base.agregarComponentes();
        });
        
        
        
        repaint();
    }
}
