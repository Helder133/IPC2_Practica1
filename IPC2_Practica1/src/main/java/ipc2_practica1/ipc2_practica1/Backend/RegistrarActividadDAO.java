/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helder
 */
public class RegistrarActividadDAO implements CRUD<RegistrarActividad> {

    private static final String INSERTAR_ACTIVIDAD = "INSERT INTO actividad (codigo_de_actividad, codigo_de_evento, tipo_de_actividad, "
            + "titulo_de_actividad, email_participante, hora_de_inicio, hora_de_fin, cupo_max) "
            + "values (?,?,?,?,?,?,?,?);";
    private final ConexionBD conexion = new ConexionBD();

    @Override
    public void insetar(RegistrarActividad entidad) throws SQLException {
        String query = "SELECT * FROM inscripcion WHERE email_participante = ? AND codigo_de_evento = ? AND tipo_de_inscripcion = 'ASISTENTE'";
        try (Connection connection = conexion.conexion(); 
                PreparedStatement stmt = connection.prepareStatement(INSERTAR_ACTIVIDAD); 
                PreparedStatement stmt1 = connection.prepareStatement(query)) {
            
            stmt1.setString(1, entidad.getEmailParticipante());
            stmt1.setString(2, entidad.getCodigoEvento());
            
            ResultSet rs = stmt1.executeQuery();
            
            if (rs.next()) {
                throw new SQLException("Error: El participante esta inscrito como 'ASISTENTE'");
            }
            
            stmt.setString(1, entidad.getCodigoActividad());
            stmt.setString(2, entidad.getCodigoEvento());
            stmt.setString(3, entidad.getTipoActividad());
            stmt.setString(4, entidad.getTituloActividad());
            stmt.setString(5, entidad.getEmailParticipante());
            
            Time sqlHoraInicio = Time.valueOf(entidad.getHoraInicio());
            Time sqlHoraFin = Time.valueOf(entidad.getHoraFinal());
            
            stmt.setTime(6, sqlHoraInicio);
            stmt.setTime(7, sqlHoraFin);
            stmt.setInt(8, entidad.getCupoMaximo());

            System.out.println("Ejecutando SQL: " + INSERTAR_ACTIVIDAD);
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Filas insertadas: " + filasAfectadas);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<String> listaCodigoActividad() throws SQLException {
        List<String> resultado = new ArrayList<>();
        String query = "SELECT codigo_de_actividad FROM actividad";
        try (Connection connetion = conexion.conexion();
                PreparedStatement stmt = connetion.prepareStatement(query); 
                ResultSet rs = stmt.executeQuery()){
            
            while(rs.next()) {
                resultado.add(rs.getString("codigo_de_actividad"));
            }
            
        } catch (SQLException e) {
            throw e;
        }
        return resultado;
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
