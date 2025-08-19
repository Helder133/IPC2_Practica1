/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author helder
 */
public class JFramePrincipal extends JFrame {

    public void inicio() {
        setTitle("Administrador de eventos");
        setLayout(new BorderLayout());
        setResizable(false);
        getContentPane().setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(3);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        Font font1 = new Font("Helvetica", Font.BOLD, 30);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.DARK_GRAY);
        JLabel tituloJLabel = new JLabel("Administrador de eventos y actividades", SwingConstants.CENTER);
        tituloJLabel.setForeground(Color.WHITE);
        tituloJLabel.setFont(font1);
        topPanel.add(tituloJLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        //Barra de menú
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.LIGHT_GRAY);
        setJMenuBar(menuBar);

        Font menuFont = new Font("Helvetica", Font.BOLD, 14);
        Font menuItemFont = new Font("Helvetica", Font.PLAIN, 13);
        
        // Menú "Carga de archivo"
        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setFont(menuFont);
        JMenuItem cargarArchivo = new JMenuItem("Cargar Archivo");
        cargarArchivo.setFont(menuItemFont);
        
        menuArchivo.add(cargarArchivo);
        
        // Menú "Registro"
        JMenu menuRegistro = new JMenu("Registro"); 
        menuRegistro.setFont(menuFont);
        
        JMenuItem registrarEvento = new JMenuItem("Registrar Evento"); 
        registrarEvento.setFont(menuItemFont);
        JMenuItem registrarParticipante = new JMenuItem("Registrar Participante"); 
        registrarParticipante.setFont(menuItemFont);
        JMenuItem registrarActividad = new JMenuItem("Registrar Actividad"); 
        registrarActividad.setFont(menuItemFont);

        menuRegistro.add(registrarEvento);
        menuRegistro.add(registrarParticipante);
        menuRegistro.add(registrarActividad);

        // Menú "Operaciones"
        JMenu menuOperaciones = new JMenu("Operaciones");
        menuOperaciones.setFont(menuFont);
        JMenuItem inscripcion = new JMenuItem("Inscripción");
        inscripcion.setFont(menuItemFont);
        JMenuItem pago = new JMenuItem("Pago");
        pago.setFont(menuItemFont);
        JMenuItem validarInscripcion = new JMenuItem("Validar Inscripción");
        validarInscripcion.setFont(menuItemFont);
        
        menuOperaciones.add(inscripcion);
        menuOperaciones.add(pago);
        menuOperaciones.add(validarInscripcion);

        // Menú "Reportes"
        JMenu menuReportes = new JMenu("Reportes");
        JMenuItem repParticipantes = new JMenuItem("Reporte de Participantes");
        JMenuItem repActividad = new JMenuItem("Reporte de Actividades");
        JMenuItem repEvento = new JMenuItem("Reporte de Eventos");

        menuReportes.add(repParticipantes);
        menuReportes.add(repActividad);
        menuReportes.add(repEvento);

        // Agregar menús al menuBar
        menuBar.add(menuArchivo);
        menuBar.add(menuRegistro);
        menuBar.add(menuOperaciones);
        menuBar.add(menuReportes);

        JDesktopPane escritorio = new JDesktopPane();
        escritorio.setBackground(Color.GRAY);
        add(escritorio, BorderLayout.CENTER);
        
        cargarArchivo.addActionListener( e -> {
            CargaDeArchivoFrame carga = new CargaDeArchivoFrame();
            carga.CargaDeArchivoFrame(escritorio, 20, 20);
        });
        
        registrarEvento.addActionListener(e -> {
            EventoFrame base = new EventoFrame();
            base.agregarVentanillaEvento(escritorio, 40, 20);
        });
        
        registrarParticipante.addActionListener(e -> {
            ParticipanteJFrame participante = new ParticipanteJFrame();
            participante.agregarVentanillaEvento(escritorio, 60, 20);
        });
        
        inscripcion.addActionListener(e -> {
            InscripcionJFrame inscripcionJ = new InscripcionJFrame();
            inscripcionJ.agregarVentanillaEvento(escritorio, 80, 20);
        });
        
        pago.addActionListener(e -> {
            PagoJFrame pago1 = new PagoJFrame();
            pago1.agregarVentanillaEvento(escritorio, 100, 20);
        });
        repaint();
        setVisible(true);
        
        validarInscripcion.addActionListener(e -> {
            ValidarPagoJFrame validarPago = new ValidarPagoJFrame();
            validarPago.agregarVentanillaEvento(escritorio, 120, 20);
        });
        
        registrarActividad.addActionListener(e->{
            ActividadJFrame actividad = new ActividadJFrame();
            actividad.agregarVentanillaEvento(escritorio, 140, 20);
        });
    }
}
