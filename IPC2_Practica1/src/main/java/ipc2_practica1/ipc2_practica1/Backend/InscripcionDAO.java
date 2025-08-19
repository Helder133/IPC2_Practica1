/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author helder
 */
public class InscripcionDAO implements CRUD<Inscripcion> {

    private static final String INSERT_INSCRIPCION = "INSERT INTO inscripcion "
            + " (email_participante, codigo_de_evento, tipo_de_inscripcion) "
            + " values (?,?,?);";
    private final ConexionBD conexion = new ConexionBD();

    @Override
    public void insetar(Inscripcion entidad) throws SQLException {

        try (Connection connection = conexion.conexion(); PreparedStatement stmt = connection.prepareStatement(INSERT_INSCRIPCION)) {
            stmt.setString(1, entidad.getEmailParticipante());
            stmt.setString(2, entidad.getCodigoEvento());
            stmt.setString(3, entidad.getTipoInscripcion());

            System.out.println("Ejecutando SQL: " + INSERT_INSCRIPCION);
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Filas insertadas: " + filasAfectadas);

        } catch (SQLException e) {
            throw e;
        }

    }

    @Override
    public void actualizar(String[] entidad) throws SQLException {
        String query = "SELECT * FROM pagar WHERE email_participante = ? AND codigo_de_evento = ?";
        String query1 = "UPDATE inscripcion SET validado = ? WHERE email_participante = ? AND codigo_de_evento = ?";
        int filasAfectadas;
        try (Connection connection = conexion.conexion(); PreparedStatement stmt = connection.prepareStatement(query); PreparedStatement stmt1 = connection.prepareStatement(query1)) {

            stmt.setString(1, entidad[0]);
            stmt.setString(2, entidad[1]);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                stmt1.setByte(1, (byte) 1); //simulando el tinyint en MySql
                stmt1.setString(2, entidad[0]);
                stmt1.setString(3, entidad[1]);

                System.out.println("Ejecutando SQL: " + query1);
                filasAfectadas = stmt1.executeUpdate();
                System.out.println("Filas actualizadas: " + filasAfectadas);
            } else {
                throw new SQLException(entidad[0] + " , " + entidad[1] + ": no hay pago registrado");
            }

        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    public String[][] listar() throws SQLException {
        return null;
    }

    @Override
    public void borrar() throws SQLException {

    }

    public List<String> getCorreoParticipanteInscripcion() throws SQLException {
        List<String> resultado = new LinkedList<>();
        String query = "SELECT email_participante FROM inscripcion";

        try (Connection connetion = conexion.conexion(); PreparedStatement stmt = connetion.prepareStatement(query); ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                resultado.add(rs.getString("email_participante"));
            }
            return resultado;
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> getCodigoEventoInscripcion() throws SQLException {
        List<String> resultado = new LinkedList<>();
        String query = "SELECT codigo_de_evento FROM inscripcion";

        try (Connection connetion = conexion.conexion(); PreparedStatement stmt = connetion.prepareStatement(query); ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                resultado.add(rs.getString("codigo_de_evento"));
            }
            return resultado;
        } catch (SQLException e) {
            throw e;
        }
    }

}
