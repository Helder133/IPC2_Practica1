/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ipc2_practica1.ipc2_practica1;

import ipc2_practica1.ipc2_practica1.Backend.LecturaDeArchivos;
import ipc2_practica1.ipc2_practica1.Backend.RegistrarEvento;
import ipc2_practica1.ipc2_practica1.Backend.RegistrarEventoDAO;
import ipc2_practica1.ipc2_practica1.Frontend.JFramePrincipal;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author helder
 */
public class IPC2_Practica1 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        JFramePrincipal JFrame = new JFramePrincipal();
        JFrame.inicio();
        
        /*int opcion;
        System.out.println("1. Formulario");
        System.out.println("2. Carga de archivo");
        opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1 -> formulario();
            case 2 -> cargarDeArchivo();
            default -> System.out.println("No ves que solo hay dos opciones :v");
        }*/

    }

    public static void formulario() {
        RegistrarEvento evento;
        RegistrarEventoDAO eventoDAO = new RegistrarEventoDAO();

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
            eventoDAO.insetar(evento);
        } catch (SQLException e) {
            System.err.println("Error al intentar insertar un evento :v");
        }
    }

    public static void cargarDeArchivo() {
        LecturaDeArchivos lectura = new LecturaDeArchivos();
        
        System.out.println("Ingrese la ruta del archivo: ");
        String ruta = scanner.nextLine();
        try {
            lectura.leerArchivoEvento(ruta);
        } catch (IOException | SQLException  e) {
            System.out.println("Ocurrio un error: " + e.getMessage());
        }
        
    }
}
