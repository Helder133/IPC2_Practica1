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
public class PagoDAO implements CRUD<Pago> {

    private static final String INSERT_PAGO = "INSERT INTO pagar "
            + " (email_participante, codigo_de_evento, metodo_de_pago, monto) "
            + " values (?,?,?,?);";
    private final ConexionBD conexion = new ConexionBD();
    private final RegistrarEventoDAO eventoDAO = new RegistrarEventoDAO();

    @Override
    public void insetar(Pago entidad) throws SQLException {

        if (!eventoDAO.getMontoDePago(entidad.getCodigoEvento()).equals(entidad.getMonto())) {
            throw new SQLException("El monto a pagar no es igual al del evento");
        }

        try (Connection connection = conexion.conexion(); PreparedStatement stmt = connection.prepareStatement(INSERT_PAGO)) {
            stmt.setString(1, entidad.getEmailParticipante());
            stmt.setString(2, entidad.getCodigoEvento());
            stmt.setString(3, entidad.getMetodoPago());
            stmt.setBigDecimal(4, entidad.getMonto());

            System.out.println("Ejecutando SQL: " + INSERT_PAGO);
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("Filas insertadas: " + filasAfectadas);

        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void actualizar(Pago entidad) throws SQLException {

    }

    @Override
    public String[][] listar() throws SQLException {
        return null;
    }

    @Override
    public void borrar() throws SQLException {

    }

}
