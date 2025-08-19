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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        int fila = 1;

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
                            cargarEventoBD(datos, fila);
                            Thread.sleep(tiempo);
                        }
                        case "REGISTRO_PARTICIPANTE" -> {
                            String datos = extraerDatos(linea);
                            cargarParticipanteBD(datos, fila);
                            Thread.sleep(tiempo);
                        }
                        case "INSCRIPCION" -> {
                            String datos = extraerDatos(linea);
                            cargarInscripcionBD(datos, fila);
                            Thread.sleep(tiempo);
                        }
                        case "PAGO" -> {
                            String datos = extraerDatos(linea);
                            cargarPagoBD(datos, fila);
                            Thread.sleep(tiempo);
                        }
                        case "VALIDAR_INSCRIPCION" -> {
                            String datos = extraerDatos(linea);
                            validarPago(datos, fila);
                            Thread.sleep(tiempo);
                        }
                        case "REGISTRO_ACTIVIDAD" -> {
                            String datos = extraerDatos(linea);
                            cargarActividadBD(datos, fila);
                            Thread.sleep(tiempo);
                        }
                        case "ASISTENCIA" -> {
                            String datos = extraerDatos(linea);
                            cargarAsistenciaBD(datos, fila);
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
                fila++;
                int porcentaje = (int) ((fila * 100.0) / total);
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

    private void cargarEventoBD(String datos, int fila) throws SQLException {
        String codigo = null;
        try {
            String[] partes = datos.split(",");
            if (partes.length < 6) {
                carga.modificarMensaje("fila " + fila + ": falta campo ");
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

    private void cargarInscripcionBD(String datos, int fila) {
        String emailParticipante = null;
        String codigoEvento = null;
        try {

            String[] partes = datos.split(",");

            if (partes.length < 2) {
                carga.modificarMensaje("fila " + fila + ": falta campo ");
                return;
            }
            emailParticipante = partes[0].replace("\"", "").trim();
            codigoEvento = partes[1].replace("\"", "").trim();
            String tipoInscripcion = partes[2].replace("\"", "").trim();

            Inscripcion inscripcion = new Inscripcion(emailParticipante, codigoEvento, tipoInscripcion);
            InscripcionDAO inscripcionDAO = new InscripcionDAO();
            inscripcionDAO.insetar(inscripcion);

            String mensaje = String.format("Incripcion registrada correctamente: '%s' '%s'", emailParticipante, codigoEvento);
            carga.modificarMensaje(mensaje);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                carga.modificarMensaje(String.format("La inscripcion de : '%s' , '%s', ya existe", emailParticipante, codigoEvento));
            } else {
                carga.modificarMensaje("Error: " + e.getMessage());
            }
        }
    }

    private void cargarPagoBD(String datos, int fila) {
        String emailParticipante = null;
        String codigoEvento = null;
        try {
            String[] partes = datos.split(",");

            if (partes.length < 3) {
                carga.modificarMensaje("fila " + fila + ": falta campo ");
                return;
            }
            emailParticipante = partes[0].replace("\"", "").trim();
            codigoEvento = partes[1].replace("\"", "").trim();
            String metodoPago = partes[2].replace("\"", "").trim();
            BigDecimal monto = new BigDecimal(partes[3].replace("\"", "").trim());

            Pago pago = new Pago(emailParticipante, codigoEvento, metodoPago, monto);
            PagoDAO pagoDAO = new PagoDAO();
            pagoDAO.insetar(pago);

            String mensaje = String.format("Pago registrada correctamente: '%s' '%s'", emailParticipante, codigoEvento);
            carga.modificarMensaje(mensaje);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                carga.modificarMensaje(String.format("El pago de : '%s' , '%s', ya existe", emailParticipante, codigoEvento));
            } else {
                carga.modificarMensaje("Error: " + e.getMessage());
            }
        }
    }

    private void validarPago(String datos, int fila) {
        String[] dato = new String[2];
        try {
            String[] partes = datos.split(",");

            if (partes.length < 1) {
                carga.modificarMensaje("fila " + fila + ": falta campo ");
                return;
            }

            dato[0] = partes[0].replace("\"", "").trim();
            dato[1] = partes[1].replace("\"", "").trim();
            InscripcionDAO inscripcionDAO = new InscripcionDAO();
            inscripcionDAO.actualizar(dato);

            String mensaje = String.format("Pago validado correctamente: '%s' '%s'", dato[0], dato[1]);
            carga.modificarMensaje(mensaje);
        } catch (SQLException e) {
            carga.modificarMensaje("Error: " + e.getMessage());
        }
    }

    private void cargarActividadBD(String datos, int fila) {
        DateTimeFormatter formatear = DateTimeFormatter.ofPattern("HH:mm");
        String codigoActividad = null;
        try {
            String[] partes = datos.split(",");
            if (partes.length < 7) {
                carga.modificarMensaje("fila " + fila + ": falta campo ");
                return;
            }
            codigoActividad = partes[0].replace("\"", "").trim();
            String codigoEvento = partes[1].replace("\"", "").trim();
            String tipo = partes[2].replace("\"", "").trim();
            String titulo = partes[3].replace("\"", "").trim();
            String email = partes[4].replace("\"", "").trim();
            String hora1 = partes[5].replace("\"", "").trim();
            String hora2 = partes[6].replace("\"", "").trim();
            LocalTime horaInicio = LocalTime.parse(hora1, formatear);
            LocalTime horaFin = LocalTime.parse(hora2, formatear);
            int cupoMax = Integer.parseInt(partes[7].trim());
            
            RegistrarActividad actividad = new RegistrarActividad(codigoActividad, codigoEvento, tipo, titulo, email, horaInicio, horaFin, cupoMax);
            RegistrarActividadDAO actividadDAO = new RegistrarActividadDAO();
            actividadDAO.insetar(actividad);

            String mensaje = String.format("Actividad '%s' cargado", codigoActividad);
            carga.modificarMensaje(mensaje);
        } catch (NumberFormatException e) {
            carga.modificarMensaje(String.format("Error al tratar de leer Cupo de: '%s'", codigoActividad));
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                carga.modificarMensaje(String.format("Actividad '%s' ya existe", codigoActividad));
            } else {
                carga.modificarMensaje(String.format("Error al intentar insetar '%s': Posiblemente esta registrado como asistente", codigoActividad));
            }
        }
    }

    private void cargarAsistenciaBD(String datos, int fila) {
        String emailParticipante = null;
        String codigoActividad = null;
        try {
            String[] partes = datos.split(",");

            if (partes.length < 1) {
                carga.modificarMensaje("fila " + fila + ": falta campo ");
                return;
            }
            emailParticipante = partes[0].replace("\"", "").trim();
            codigoActividad = partes[1].replace("\"", "").trim();

            RegistrarAsistencia asistencia = new RegistrarAsistencia(emailParticipante, codigoActividad);
            RegistrarAsistenciaDAO asistenciaDAO = new RegistrarAsistenciaDAO();
            asistenciaDAO.insetar(asistencia);

            String mensaje = String.format("Asitencia registrada correctamente: '%s' '%s'", emailParticipante, codigoActividad);
            carga.modificarMensaje(mensaje);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                carga.modificarMensaje(String.format("Asistencia : '%s' , '%s', ya existe", emailParticipante, codigoActividad));
            } else {
                carga.modificarMensaje("Error: " + e.getMessage());
            }
        }
    }

}
