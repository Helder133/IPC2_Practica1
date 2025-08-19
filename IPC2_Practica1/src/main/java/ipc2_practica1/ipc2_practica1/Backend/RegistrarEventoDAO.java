/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helder
 */
public class RegistrarEventoDAO implements CRUD<RegistrarEvento> {

    private static final String INSERT_EVENTO = "INSERT INTO evento "
            + " (codigo_de_evento, fecha_del_evento, tipo_de_evento, titulo_de_evento, ubicacion, cupo_maximo, costo_inscripcion) "
            + " values (?,?,?,?,?,?,?);";
    private static final DateTimeFormatter FORMAT_DDMMYYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final ConexionBD conexion = new ConexionBD();

    @Override
    public void insetar(RegistrarEvento evento) throws SQLException {

        try (Connection connection = conexion.conexion(); PreparedStatement stmt = connection.prepareStatement(INSERT_EVENTO)) {
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
    public void actualizar(String[] entidad) throws SQLException {

    }

    @Override
    public void borrar() throws SQLException {

    }

    @Override
    public String[][] listar() throws SQLException {
        List<String[]> resultado = new ArrayList<>();
        String query = "SELECT codigo_de_evento, titulo_de_evento FROM evento";
        try (Connection connetion = conexion.conexion(); PreparedStatement stmt = connetion.prepareStatement(query); ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                String[] fila = new String[2];
                fila[0] = rs.getString("codigo_de_evento");
                fila[1] = rs.getString("titulo_de_evento");
                resultado.add(fila);
            }

        } catch (SQLException e) {
            throw e;
        }
        return resultado.toArray(new String[0][0]);
    }

    public BigDecimal getMontoDePago(String codigo) throws SQLException {
        String query = "SELECT costo_inscripcion FROM evento WHERE codigo_de_evento = ?";

        try (Connection connetion = conexion.conexion(); PreparedStatement stmt = connetion.prepareStatement(query)) {
            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("costo_inscripcion");
                }
            }
        } catch (SQLException e) {
            throw e;
        }
        return null;
    }
}
