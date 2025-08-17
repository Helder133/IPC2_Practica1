/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author helder
 */
public class RegistrarEventoDAO implements CRUD<RegistrarEvento>{
    private static final String INSERT_EVENTO = "INSERT INTO evento "
            + " (codigo_de_evento, fecha_del_evento, tipo_de_evento, titulo_de_evento, ubicacion, cupo_maximo, costo_inscripcion) "
            + " values (?,?,?,?,?,?,?);";
    private static final DateTimeFormatter FORMAT_DDMMYYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    @Override
    public void insetar(RegistrarEvento evento) throws SQLException {
        ConexionBD conexion = new ConexionBD();
        
        try (Connection connection = conexion.conexion(); 
                PreparedStatement stmt = connection.prepareStatement(INSERT_EVENTO)){
            stmt.setString(1, evento.getCodigoDeEvento());

            try {
                LocalDate ld = LocalDate.parse(evento.getFechaDelEvento(), FORMAT_DDMMYYYY);
                stmt.setDate(2, Date.valueOf(ld));
            } catch (DateTimeParseException ex) {
                throw new SQLException("Formato de fecha inválido. Se esperaba dd/MM/yyyy. Valor: "
                        + evento.getFechaDelEvento(), ex);
            }

            stmt.setString(3, evento.getTipoDeEvento());
            stmt.setString(4, evento.getTituloDeEvento());
            stmt.setString(5, evento.getUbicacion());
            stmt.setInt(6, evento.getCupoMax());
            stmt.setBigDecimal(7, evento.getCosto());
            
            System.out.println("Ejecutando SQL: " + INSERT_EVENTO);
            
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Filas insertadas: " + filasAfectadas);
        } catch (SQLException e) {
            throw e;
        }
        
    }
    
    @Override
    public void actualizar(RegistrarEvento entidad) throws SQLException {
        
    }

    @Override
    public RegistrarEvento leer() throws SQLException {
        
        return null;
        
    }

    @Override
    public List<RegistrarEvento> listar() throws SQLException {
        
        return null;
        
    }

    @Override
    public void borrar() throws SQLException {
        
    }
}
