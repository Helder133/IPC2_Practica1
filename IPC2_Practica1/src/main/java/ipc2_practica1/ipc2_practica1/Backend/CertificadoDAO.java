/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author helder
 */
public class CertificadoDAO {

    private final ConexionBD conexion = new ConexionBD();

    public void genearCertifiacado(Certificado certificado, String path) throws SQLException, IOException {
        if (asistioActividad(certificado)) {
            RegistrarParticipanteDAO participanteDAO = new RegistrarParticipanteDAO();
            RegistrarEventoDAO eventoDAO = new RegistrarEventoDAO();

            RegistrarParticipante participante = participanteDAO.obtenerParticipanteEmail(certificado.getEmailParticipante());
            RegistrarEvento evento = eventoDAO.obtenerEventoPorCodigo(certificado.getCodigoEvento());

            generarHTML(path, participante, evento);

        } else {
            throw new SQLException("NO cumple con la asistencia");
        }

    }

    private boolean asistioActividad(Certificado certificado) throws SQLException {
        String query = "SELECT * FROM actividad WHERE codigo_de_evento = ? AND email_participante = ?";

        try (Connection connetion = conexion.conexion(); PreparedStatement stmt = connetion.prepareStatement(query)) {
            stmt.setString(1, certificado.getCodigoEvento());
            stmt.setString(2, certificado.getEmailParticipante());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    private void generarHTML(String path, RegistrarParticipante participante, RegistrarEvento evento) throws IOException {
        String nombre = participante.getNombre() + ".html";
        String pathAbsolut = path + File.separator + nombre;

        try (PrintWriter writer = new PrintWriter(pathAbsolut)) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Certificado de Participación</title>");
            writer.println("<style>");
            writer.println("body { font-family: 'Verdana', sans-serif; background-color: #f0f0f5; margin: 50px; }");
            writer.println(".certificado { border: 3px dashed #4a90e2; padding: 30px; text-align: center; background-color: #ffffff; box-shadow: 0 0 10px rgba(0,0,0,0.2); }");
            writer.println(".titulo { font-size: 28px; font-weight: bold; color: #4a90e2; margin-bottom: 25px; }");
            writer.println(".contenido { margin: 25px 0; font-size: 16px; color: #333; }");
            writer.println(".contenido p { margin: 10px 0; }");
            writer.println(".footer { margin-top: 30px; font-size: 14px; color: #555; }");
            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<div class='certificado'>");
            writer.println("<div class='titulo'>CERTIFICADO DE PARTICIPACIÓN</div>");
            writer.println("<div class='contenido'>");
            writer.println("<p>Se certifica que <strong>" + participante.getNombre() + "</strong></p>");
            writer.println("<p>ha participado en el evento:</p>");
            writer.println("<p><strong>" + evento.getTituloDeEvento() + "</strong></p>");
            writer.println("<p>realizado el " + evento.getFechaDelEvento() + "</p>");
            writer.println("<p>en el establecimiento y/o dirección <strong>" + evento.getUbicacion() + "</strong></p>");
            writer.println("</div>");
            writer.println("<div class='footer'>Este certificado es válido únicamente para el participante mencionado.</div>");
            writer.println("</div>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (IOException e) {
            throw new IOException("No se puedo encontrar la ruta de la carpeta");
        }
    }
}
