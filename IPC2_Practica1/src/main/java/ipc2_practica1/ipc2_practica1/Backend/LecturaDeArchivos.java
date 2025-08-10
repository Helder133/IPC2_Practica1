/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author helder
 */
public class LecturaDeArchivos {

    public void leerArchivoEvento(String ruta) throws SQLException, IOException {
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

                    RegistrarEvento evento = new RegistrarEvento(codigo, fecha, tipo, tema, lugar, capacidad);

                    nuevoEvento.insetar(evento);
                } catch (SQLException ex) {
                    int errorCode = ex.getErrorCode();
                    if(errorCode == 1062) {
                        System.out.println("Clave Primaria Duplicada");
                    }else {
                        throw new SQLException("Error en la base de datos: " + ex.getMessage());
                    }
                } catch (NumberFormatException n) {
                    System.err.println("error al evavular la cantidad como un entero");
                }
            }

        } catch (IOException e) {
            throw new IOException("Error en la Lectura de: " + e.getMessage());
        } catch (NumberFormatException n) {
            System.err.println("error al evavular la cantidad como un entero");
        }
    }

    private List<String> leerArchivo(String ruta) throws IOException {
        List<String> listaInstruccion = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                int inicio = linea.indexOf("(");
                int fin = linea.indexOf(")");

                if (inicio != -1 && fin != -1) {
                    String subLinea = linea.substring(inicio + 1, fin);

                    listaInstruccion.add(subLinea);
                }
            }

        } catch (IOException e) {
            throw new IOException("Error al tratar de leer la ruta: " + ruta + " ; " + e.getMessage());
        }
        return listaInstruccion;
    }

}
