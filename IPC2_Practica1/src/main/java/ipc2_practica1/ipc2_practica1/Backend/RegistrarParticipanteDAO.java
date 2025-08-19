/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helder
 */
public class RegistrarParticipanteDAO implements CRUD<RegistrarParticipante> {

    private static final String INSERT_PARTICIPANTE = "INSERT INTO participante "
            + " (nombre, tipo_de_participante, institucion_de_procedencia, email) "
            + " values (?,?,?,?);";
    private final ConexionBD conexion = new ConexionBD();
    
    @Override
    public void insetar(RegistrarParticipante participante) throws SQLException {

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
    public void actualizar(String[] entidad) throws SQLException {

    }

    @Override
    public String[][] listar() throws SQLException {
        List<String[]> resultado = new ArrayList<>(); 
        String query = "SELECT nombre, email FROM participante";
        try (Connection connetion = conexion.conexion();
                PreparedStatement stmt = connetion.prepareStatement(query); 
                ResultSet rs = stmt.executeQuery();){
            
            while(rs.next()) {
                String[] fila = new String [2];
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getString("email");
                resultado.add(fila);
            }
            
        } catch (SQLException e) {
            throw e;
        }
        return resultado.toArray(new String[0][0]);
    }
    
    public List<String> listaParticipantesEmail() throws SQLException {
        List<String> resultado = new ArrayList<>();
        String query = "SELECT email FROM participante";
        try (Connection connetion = conexion.conexion();
                PreparedStatement stmt = connetion.prepareStatement(query); 
                ResultSet rs = stmt.executeQuery()){
            
            while(rs.next()) {
                resultado.add(rs.getString("email"));
            }
            
        } catch (SQLException e) {
            throw e;
        }
        return resultado;
    }
    
    @Override
    public void borrar() throws SQLException {

    }

}
