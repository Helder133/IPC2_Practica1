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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

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
        
        /*//Creando la barra de menú
        JMenuBar barraMenu = new JMenuBar();
        //Crear menús
        JMenu menuEvento = new JMenu("Evento");
        JMenu menuParticipante = new JMenu("Participante");
        JMenu menuInscripcion = new JMenu("Inscripción");
        //Crear ítems para evento
        JMenuItem cargaArchivo = new JMenuItem("Carga de archivo");
        JMenuItem formulario = new JMenuItem("Formulario");
        //Agregar ítems al menú evento
        menuEvento.add(cargaArchivo);
        menuEvento.add(formulario);
        
        //Agregar menús a la barra
        barraMenu.add(menuEvento);
        barraMenu.add(menuParticipante);
        barraMenu.add(menuInscripcion);
        
        setJMenuBar(barraMenu);
        
        //----------------------------------------------------------------------------------------
        // Crear barra de herramientas vertical
        JToolBar menuVertical = new JToolBar(JToolBar.VERTICAL);
        menuVertical.setFloatable(false); // Para que no se pueda mover

        // Agregar botones simulando opciones de menú
        JButton btnEvento = new JButton("Evento");
        JButton btnParticipante = new JButton("Participante");
        JButton btnInscripcion = new JButton("Inscripción");

        // Agregarlos al menú
        menuVertical.add(btnEvento);
        menuVertical.add(btnParticipante);
        menuVertical.add(btnInscripcion);

        // Agregar el menú vertical a la izquierda
        add(menuVertical, BorderLayout.WEST);

        // Área central
        JTextArea area = new JTextArea("Área de trabajo");
        add(new JScrollPane(area), BorderLayout.CENTER);*/
        
        
        repaint();
    }
}
