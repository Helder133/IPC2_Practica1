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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author helder
 */
public class LecturaDeArchivos implements Runnable {

    private String ruta1;
    private int tiempo;
    private CargaDeArchivoFrame carga;

    public void setCarga(CargaDeArchivoFrame carga) {
        this.carga = carga;
    }

    private void leerArchivo() throws IOException, InterruptedException, SQLException {
        int contador = 1;

        List<String> totalLineas = Files.readAllLines(Paths.get(ruta1));
        int total = totalLineas.size();

        if (total == 0) {
            carga.modificarMensaje("Archivo vacío");
            return;
        }

        if (carga != null) {
            carga.limpiarLogYProgreso();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ruta1))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                int inicio = 0;
                int fin = linea.indexOf("(");

                if (inicio != -1 && fin != -1) {
                    String subLinea = linea.substring(inicio, fin);
                    switch (subLinea) {
                        case "REGISTRO_EVENTO" -> {
                            String datos = extraerDatos(linea);
                            cargarEventoBD(datos, contador);
                            Thread.sleep(tiempo);
                        }
                        case "REGISTRO_PARTICIPANTE" -> {
                            String datos = extraerDatos(linea);
                            cargarParticipanteBD(datos, contador);
                            Thread.sleep(tiempo);
                        }
                        case "INSCRIPCION" -> {
                            carga.modificarMensaje(String.format("Falta logica %s", subLinea));
                            Thread.sleep(tiempo);
                        }
                        case "PAGO" -> {
                            carga.modificarMensaje(String.format("Falta logica %s", subLinea));
                            Thread.sleep(tiempo);
                        }
                        case "VALIDAR_INSCRIPCION" -> {
                            carga.modificarMensaje(String.format("Falta logica %s", subLinea));
                            Thread.sleep(tiempo);
                        }
                        case "REGISTRO_ACTIVIDAD" -> {
                            carga.modificarMensaje(String.format("Falta logica %s", subLinea));
                            Thread.sleep(tiempo);
                        }
                        case "ASISTENCIA" -> {
                            carga.modificarMensaje(String.format("Falta logica %s", subLinea));
                            Thread.sleep(tiempo);
                        }
                        case "CERTIFICADO" -> {
                            carga.modificarMensaje(String.format("Falta logica %s", subLinea));
                            Thread.sleep(tiempo);
                        }
                        case "REPORTE_PARTICIPANTES" -> {
                            carga.modificarMensaje(String.format("Falta logica %s", subLinea));
                            Thread.sleep(tiempo);
                        }
                        case "REPORTE_ACTIVIDADES" -> {
                            carga.modificarMensaje(String.format("Falta logica %s", subLinea));
                            Thread.sleep(tiempo);
                        }
                        case "REPORTE_EVENTOS" -> {
                            carga.modificarMensaje(String.format("Falta logica %s", subLinea));
                            Thread.sleep(tiempo);
                        }
                        default ->
                            throw new AssertionError();
                    }
                }
                contador++;
                int porcentaje = (int) ((contador * 100.0) / total);
                carga.setProgresoBarra(porcentaje);

            }
            carga.setProgresoBarra(100);
            carga.modificarMensaje("Archivo leído completamente");

        } catch (IOException e) {
            carga.modificarMensaje("Error al tratar de leer la ruta: " + ruta1 + " ; " + e.getMessage());
        } catch (SQLException ex) {
            carga.modificarMensaje("Error en la base de datos");
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
        } catch (IOException | InterruptedException | SQLException ex) {
            Logger.getLogger(LecturaDeArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarEventoBD(String datos, int contador) throws SQLException {
        String codigo = null;
        try {
            String[] partes = datos.split(",");
            if (partes.length < 6) {
                carga.modificarMensaje("fila " + contador + ": falta campo ");
                return;
            }
            codigo = partes[0].replace("\"", "").trim();
            String fecha = partes[1].replace("\"", "").trim();
            String tipo = partes[2].replace("\"", "").trim();
            String tema = partes[3].replace("\"", "").trim();
            String lugar = partes[4].replace("\"", "").trim();
            int capacidad = Integer.parseInt(partes[5].trim());
            BigDecimal costo = new BigDecimal(partes[6].trim());
            RegistrarEvento evento = new RegistrarEvento(codigo, fecha, tipo, tema, lugar, capacidad, costo);
            RegistrarEventoDAO registrar = new RegistrarEventoDAO();
            registrar.insetar(evento);

            String mensaje = String.format("Evento '%s' cargado", codigo);
            carga.modificarMensaje(mensaje);
        } catch (NumberFormatException e) {
            carga.modificarMensaje(String.format("Error al tratar de leer Cupo o Costo de: '%s'", codigo));
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                carga.modificarMensaje(String.format("Evento '%s' ya existe", codigo));
            } else {
                carga.modificarMensaje(String.format("Error al intentar insetar '%s': ", codigo));
            }
        }
    }

    private void cargarParticipanteBD(String datos, int fila) {
        String email = null;

        try {

            String[] partes = datos.split(",");

            if (partes.length < 3) {
                carga.modificarMensaje("fila " + fila + ": falta campo ");
                return;
            }
            String nombre = partes[0].replace("\"", "").trim();
            String tipo = partes[1].replace("\"", "").trim();
            String institucion = partes[2].replace("\"", "").trim();
            email = partes[3].replace("\"", "").trim();

            RegistrarParticipante participante = new RegistrarParticipante(nombre, tipo, institucion, email);
            RegistrarParticipanteDAO participanteDAO = new RegistrarParticipanteDAO();
            participanteDAO.insetar(participante);

            String mensaje = String.format("Participante con corre: '%s' cargado", email);
            carga.modificarMensaje(mensaje);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                carga.modificarMensaje(String.format("Paticipante con correo: '%s' ya existe", email));
            } else {
                carga.modificarMensaje(String.format("Error al intentar insetar participante con correo: '%s': ", email));
            }
        }
    }

}
