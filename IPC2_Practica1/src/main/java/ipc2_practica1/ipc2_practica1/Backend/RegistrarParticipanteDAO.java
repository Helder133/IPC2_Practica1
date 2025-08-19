/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author helder
 */
public class RegistrarParticipanteDAO implements CRUD<RegistrarParticipante> {

    private static final String INSERT_PARTICIPANTE = "INSERT INTO participante "
            + " (nombre, tipo_de_participante, institucion_de_procedencia, email) "
            + " values (?,?,?,?);";

    @Override
    public void insetar(RegistrarParticipante participante) throws SQLException {
        ConexionBD conexion = new ConexionBD();

        try (Connection connection = conexion.conexion(); PreparedStatement stmt = connection.prepareStatement(INSERT_PARTICIPANTE)) {
            stmt.setString(1, participante.getNombre());
            stmt.setString(2, participante.getTipoParticipante());
            stmt.setString(3, participante.getInstitucionProcedencia());
            stmt.setString(4, participante.getEmail());
            System.out.println("Ejecutando SQL: " + INSERT_PARTICIPANTE);

            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Filas insertadas: " + filasAfectadas);
        } catch (SQLException e) {
            throw e;
        }

    }

    @Override
    public void actualizar(RegistrarParticipante entidad) throws SQLException {

    }

    @Override
    public RegistrarParticipante leer() throws SQLException {
        return null;
    }

    @Override
    public List<RegistrarParticipante> listar() throws SQLException {
        return null;
    }

    @Override
    public void borrar() throws SQLException {

    }

}
