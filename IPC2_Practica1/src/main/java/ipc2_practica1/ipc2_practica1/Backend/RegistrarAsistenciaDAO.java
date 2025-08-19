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
public class RegistrarAsistenciaDAO implements CRUD<RegistrarAsistencia>{

    private static final String INSERT_ASISTENCIA = "INSERT INTO asistencia "
            + " (email_participante, codigo_de_actividad) "
            + " values (?,?);";
    private final ConexionBD conexion = new ConexionBD();
    
    @Override
    public void insetar(RegistrarAsistencia entidad) throws SQLException {
        
        try (Connection connection = conexion.conexion(); PreparedStatement stmt = connection.prepareStatement(INSERT_ASISTENCIA)) {
            stmt.setString(1, entidad.getEmailParticipante());
            stmt.setString(2, entidad.getCodigoActividad());

            System.out.println("Ejecutando SQL: " + INSERT_ASISTENCIA);
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Filas insertadas: " + filasAfectadas);

        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void actualizar(String[] entidad) throws SQLException {
        
    }

    @Override
    public String[][] listar() throws SQLException {
        return null;
    }

    @Override
    public void borrar() throws SQLException {
        
    }
    
}
