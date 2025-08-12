/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author helder
 */
public class ControladorEntreBackendYFrontend {

    public void insetarFormularioEvento(String codigo, String fecha,
            String tipo, String titulo, String ubicacion, int max) throws SQLException {
        try {
            RegistrarEvento evento = new RegistrarEvento(codigo, fecha, tipo, titulo, ubicacion, max);
            RegistrarEventoDAO eventoDAO = new RegistrarEventoDAO();
            eventoDAO.insetar(evento);
        } catch (SQLException e) {
            throw new SQLException("Error: " + e.getMessage());
        }
    }
    
    public void insetarArchivoEvento (String path) throws Exception {
        try {
            LecturaDeArchivos lectura = new LecturaDeArchivos();
            lectura.leerArchivoEvento(path);
        } catch (IOException | SQLException e) {
            throw new Exception("Error: " + e.getMessage());
        }
    } 

}
