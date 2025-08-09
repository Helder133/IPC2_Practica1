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

/**
 *
 * @author helder
 */
public class RegistrarEventoDAO  extends ConexionBD{
    private final Connection c;
    private RegistrarEvento evento = null;
    private static final String INSERT_EVENTO = "INSERT INTO evento "
            + "(codigo_de_evento, fecha_del_evento, tipo_de_evento, titulo_de_evento, ubicacion, cupo_max) "
            + "values (?,?,?,?,?,?)";
    private static final DateTimeFormatter FORMAT_DDMMYYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public RegistrarEventoDAO() throws SQLException{
        
        super.conexion();
        c = super.getConnection();
        if (c == null) throw new SQLException("No se puedo conectar con la base de datos");
    }
    
    @Override
    public void agregar () throws SQLException {
        try (PreparedStatement stmt = c.prepareStatement(INSERT_EVENTO)) {
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
        }
    }

    public void setEvento(RegistrarEvento evento) {
        this.evento = evento;
    }
    
    
}
