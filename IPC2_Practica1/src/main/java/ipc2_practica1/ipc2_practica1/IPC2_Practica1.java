/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ipc2_practica1.ipc2_practica1;

import ipc2_practica1.ipc2_practica1.Backend.LecturaDeArchivos;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author helder
 */
public class IPC2_Practica1 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        /*JFramePrincipal JFrame = new JFramePrincipal();
        JFrame.inicio();*/
        
        LecturaDeArchivos lectura = new LecturaDeArchivos();
        
        System.out.println("Ingrese la ruta: ");
        String ruta = scanner.nextLine();
        try {
            List<String> prueba = lectura.leerArchivo(ruta);
        } catch (IOException e) {
        }

    }

}
