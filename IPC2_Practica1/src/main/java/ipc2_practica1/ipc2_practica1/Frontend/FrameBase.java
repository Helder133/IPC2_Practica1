/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Frontend;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author helder
 */
public class FrameBase extends JInternalFrame {

    public JInternalFrame agragarVentanilla(JDesktopPane jframe, String titulo, int x, int y) {
        JInternalFrame ventana = new JInternalFrame(title, true, true, true, true);
        ventana.setLayout(null);
        ventana.setBounds(x, y, 600, 450);
        ventana.setBackground(Color.LIGHT_GRAY);
        ventana.setVisible(true);
        jframe.add(ventana);
        return ventana;
    }

}
