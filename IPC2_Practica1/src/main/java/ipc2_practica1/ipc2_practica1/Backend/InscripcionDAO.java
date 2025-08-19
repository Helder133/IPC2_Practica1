/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public void actualizar(Inscripcion entidad) throws SQLException {

    }

    @Override
    public String[][] listar() throws SQLException {
        return null;
    }

    @Override
    public void borrar() throws SQLException {

    }

}
