/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import ipc2_practica1.ipc2_practica1.Frontend.CargaDeArchivoFrame;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 *
 * @author helder
 */
public class ControladorEntreBackendYFrontend {
    private CargaDeArchivoFrame carga;

    public void setCarga(CargaDeArchivoFrame carga) {
        this.carga = carga;
    }
    
    public void insetarFormularioEvento(String codigo, String fecha,
            String tipo, String titulo, String ubicacion, int max, BigDecimal costo) throws SQLException {
        try {
            RegistrarEvento evento = new RegistrarEvento(codigo, fecha, tipo, titulo, ubicacion, max, costo);
            RegistrarEventoDAO eventoDAO = new RegistrarEventoDAO();
            eventoDAO.insetar(evento);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new SQLException("El codigo del evento ya existe");
            }else {
                throw new SQLException("Error: " + e.getMessage());
            }
        }
    }
    
    public void insetarArchivo (String path, int tiempo) throws IOException {
        LecturaDeArchivos lectura = new LecturaDeArchivos();
        Thread hilo = new Thread(lectura);
        lectura.setDatos(path, tiempo);
        lectura.setCarga(carga);
        hilo.start();
    } 
    
}