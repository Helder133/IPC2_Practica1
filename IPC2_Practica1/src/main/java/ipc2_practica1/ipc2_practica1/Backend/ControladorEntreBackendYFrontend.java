/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author helder
 */
public class ControladorEntreBackendYFrontend {

    public void insetarFormularioEvento(String codigo, String fecha,
            String tipo, String titulo, String ubicacion, int max, BigDecimal costo) throws SQLException {
        try {
            RegistrarEvento evento = new RegistrarEvento(codigo, fecha, tipo, titulo, ubicacion, max, costo);
            RegistrarEventoDAO eventoDAO = new RegistrarEventoDAO();
            eventoDAO.insetar(evento);
        } catch (SQLException e) {
            throw new SQLException("Error: " + e.getMessage());
        }
    }
    
    public String[] insetarArchivoEvento (String path) throws IOException {
        try {
            LecturaDeArchivos lectura = new LecturaDeArchivos();
            return lectura.leerArchivoEvento(path);
        } catch (IOException e) {
            throw new IOException("Error: " + e.getMessage());
        }
    } 
    
}