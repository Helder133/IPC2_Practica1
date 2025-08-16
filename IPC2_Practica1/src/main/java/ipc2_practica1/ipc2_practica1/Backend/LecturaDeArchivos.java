/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import ipc2_practica1.ipc2_practica1.Frontend.CargaDeArchivoFrame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author helder
 */
public class LecturaDeArchivos extends Thread {

    private String ruta1;
    private int tiempo;
    private CargaDeArchivoFrame carga;

    public void setCarga(CargaDeArchivoFrame carga) {
        this.carga = carga;
    }
    
    
    private void leerArchivo() throws IOException, InterruptedException {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta1))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                int inicio = 0;
                int fin = linea.indexOf("(");

                if (inicio != -1 && fin != -1) {
                    String subLinea = linea.substring(inicio, fin);
                    switch (subLinea) {
                        case "REGISTRO_EVENTO" -> {
                            System.out.println("Evento");
                            carga.modificarMensaje("Evento");
                            String datos = extraerDatos(linea);
                            cargarDatosBD(datos);
                            Thread.sleep(tiempo);
                            System.out.println("");
                        }
                        case "REGISTRO_PARTICIPANTE" -> {
                            System.out.println("REGISTRO_PARTICIPANTE");
                            extraerDatos(linea);
                            Thread.sleep(tiempo);
                            System.out.println("");
                        }
                        case "INSCRIPCION" -> {
                            System.out.println("INSCRIPCION");
                            extraerDatos(linea);
                            Thread.sleep(tiempo);
                            System.out.println("");
                        }
                        case "PAGO" -> {
                            System.out.println("PAGO");
                            extraerDatos(linea);
                            Thread.sleep(tiempo);
                            System.out.println("");
                        }
                        case "VALIDAR_INSCRIPCION" -> {
                            System.out.println("Falta logia :v");
                            Thread.sleep(tiempo);
                        }
                        case "REGISTRO_ACTIVIDAD" -> {
                            System.out.println("Falta logia :v");
                            Thread.sleep(tiempo);
                        }
                        case "ASISTENCIA" -> {
                            System.out.println("Falta logia :v");
                            Thread.sleep(tiempo);
                        }
                        case "CERTIFICADO" -> {
                            System.out.println("Falta logia :v");
                            Thread.sleep(tiempo);
                        }
                        case "REPORTE_PARTICIPANTES" -> {
                            System.out.println("Falta logia :v");
                            Thread.sleep(tiempo);
                        }
                        case "REPORTE_ACTIVIDADES" -> {
                            System.out.println("Falta logia :v");
                            Thread.sleep(tiempo);
                        }
                        case "REPORTE_EVENTOS" -> {
                            System.out.println("Falta logia :v");
                            Thread.sleep(tiempo);
                        }
                        default ->
                            throw new AssertionError();
                    }
                }
            }

        } catch (IOException e) {
            throw new IOException("Error al tratar de leer la ruta: " + ruta1 + " ; " + e.getMessage());
        }
    }

    private String extraerDatos(String linea) {
        int inicio = linea.indexOf("(");
        int fin = linea.indexOf(")");

        if (inicio != -1 && fin != -1) {
            return linea.substring(inicio + 1, fin);
        }
        return "";
    }

    public void setDatos(String ruta, int tiempo) {
        this.ruta1 = ruta;
        this.tiempo = tiempo;
    }

    @Override
    public void run() {
        try {
            leerArchivo();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(LecturaDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarDatosBD(String datos) {
        carga.modificarMensaje(datos);
    }

}
