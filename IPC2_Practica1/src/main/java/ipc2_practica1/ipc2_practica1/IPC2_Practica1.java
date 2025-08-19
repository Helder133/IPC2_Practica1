/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ipc2_practica1.ipc2_practica1;

import ipc2_practica1.ipc2_practica1.Frontend.JFramePrincipal;
import javax.swing.SwingUtilities;

/**
 *
 * @author helder
 */
public class IPC2_Practica1 {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFramePrincipal frame = new JFramePrincipal();
            frame.inicio(); // tu método de inicialización
        });
    }

}
