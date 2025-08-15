/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author helder
 */
public class LecturaDeArchivos {

    public String[] leerArchivoEvento(String ruta) throws IOException {
        String[] resultado = {"", "", ""};
        List<Integer> llaveDuplicada = new LinkedList<>();
        List<Integer> errorDesconocido = new LinkedList<>();
        List<Integer> columnaFaltante = new LinkedList<>();
        try {
            RegistrarEventoDAO nuevoEvento = new RegistrarEventoDAO();
            List<String> instruccionEvento = leerArchivo(ruta);
            for (int i = 0; i < instruccionEvento.size(); i++) {
                try {
                    String instruccion = instruccionEvento.get(i);

                    String[] partes = instruccion.split(",");

                    String codigo = partes[0].replace("\"", "").trim();
                    String fecha = partes[1].replace("\"", "").trim();
                    String tipo = partes[2].replace("\"", "").trim();
                    String tema = partes[3].replace("\"", "").trim();
                    String lugar = partes[4].replace("\"", "").trim();
                    int capacidad = Integer.parseInt(partes[5].trim());
                    BigDecimal costo = new BigDecimal(partes[6].trim());
                    RegistrarEvento evento = new RegistrarEvento(codigo, fecha, tipo, tema, lugar, capacidad, costo);

                    nuevoEvento.insetar(evento);
                } catch (ArrayIndexOutOfBoundsException c) {
                    columnaFaltante.add(i + 1);
                } catch (SQLException ex) {
                    String error = ex.getMessage();
                    if (error.equals("1062")) {
                        llaveDuplicada.add(i + 1);
                    } else {
                        errorDesconocido.add(i + 1);
                    }
                } catch (NumberFormatException | ArithmeticException n) {
                    errorDesconocido.add(i + 1);
                }
            }

            resultado[0] = !llaveDuplicada.isEmpty() ? "LLaves duplicadas en las siguientes filas: \n" + llaveDuplicada.stream().map(String::valueOf).collect(Collectors.joining(", ")) : "";
            resultado[1] = !errorDesconocido.isEmpty() ? "Error al leer las siguientes filas: \n" + errorDesconocido.stream().map(String::valueOf).collect(Collectors.joining(", ")) : "";
            resultado[2] = !columnaFaltante.isEmpty() ? "Faltan datos en la siguientes filas: " + columnaFaltante.stream().map(String::valueOf).collect(Collectors.joining(", ")) : "";

        } catch (IOException e) {
            throw new IOException("Error en la Lectura de: " + e.getMessage());
        }
        return resultado;
    }

    public List<String> leerArchivo(String ruta) throws IOException {
        List<String> listaInstruccion = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                int inicio = 0;
                int fin = linea.indexOf("(");

                if (inicio != -1 && fin != -1) {
                    String subLinea = linea.substring(inicio, fin);
                    switch (subLinea) {
                        case "REGISTRO_EVENTO" -> {
                            System.out.println("Evento");
                            extraerDatos(linea);
                            System.out.println("");
                        }
                        case "REGISTRO_PARTICIPANTE" -> {
                            System.out.println("REGISTRO_PARTICIPANTE");
                            extraerDatos(linea);
                            System.out.println("");
                        }
                        case "INSCRIPCION" -> {
                            System.out.println("INSCRIPCION");
                            extraerDatos(linea);
                            System.out.println("");
                        }
                        case "PAGO" -> {
                            System.out.println("PAGO");
                            extraerDatos(linea);
                            System.out.println("");
                        }
                        case "VALIDAR_INSCRIPCION" -> {
                        }
                        case "REGISTRO_ACTIVIDAD" -> {
                        }
                        case "ASISTENCIA" -> {
                        }
                        case "CERTIFICADO" -> {
                        }
                        case "REPORTE_PARTICIPANTES" -> {
                        }
                        case "REPORTE_ACTIVIDADES" -> {
                        }
                        case "REPORTE_EVENTOS" -> {
                        }
                        default ->
                            throw new AssertionError();
                    }
                }
            }

        } catch (IOException e) {
            throw new IOException("Error al tratar de leer la ruta: " + ruta + " ; " + e.getMessage());
        }
        return listaInstruccion;
    }

    private void extraerDatos(String linea) {
        int inicio = linea.indexOf("(");
        int fin = linea.indexOf(")");

        if (inicio != -1 && fin != -1) {
            String subLinea = linea.substring(inicio + 1, fin);
            System.out.println(subLinea);
        }
    }

}
