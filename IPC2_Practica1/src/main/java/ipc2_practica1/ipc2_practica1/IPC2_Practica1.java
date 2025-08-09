/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ipc2_practica1.ipc2_practica1;

import ipc2_practica1.ipc2_practica1.Backend.ConexionBD;
import ipc2_practica1.ipc2_practica1.Backend.RegistrarEvento;
import ipc2_practica1.ipc2_practica1.Backend.RegistrarEventoDAO;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author helder
 */
public class IPC2_Practica1 {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        RegistrarEvento evento;
        RegistrarEventoDAO eventoDAO;
        
        System.out.println("Ingrese el codigo de evento: ");
        String codigo = scanner.nextLine();
        System.out.println("Ingresar la fecha del evento (dd/mm/aaaa): ");
        String fecha = scanner.nextLine();
        System.out.println("Ingresar tipo de evento (CHARLA,CONGRESO,TALLER,DEBATE): ");
        String tipo = scanner.nextLine();
        System.out.println("Ingresa el titulo del evento: ");
        String titulo = scanner.nextLine();
        System.out.println("Ingresar la ubicacion del evento: ");
        String ubicacion = scanner.nextLine();
        System.out.println("Ingresar el cupo maximo: ");
        int max = Integer.parseInt(scanner.nextLine());
        
        evento = new RegistrarEvento(codigo, fecha, tipo, titulo, ubicacion, max);
        try {
            eventoDAO = new RegistrarEventoDAO();
            eventoDAO.setEvento(evento);
            eventoDAO.agregar();
        } catch (SQLException e) {
            System.out.println(e);
        } 
        
    }
}