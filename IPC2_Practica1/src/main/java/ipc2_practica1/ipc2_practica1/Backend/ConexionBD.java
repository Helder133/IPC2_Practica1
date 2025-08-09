/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author helder
 */
public class ConexionBD {
    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA =   "admin_eventos";
    private static final String USER_NAME = "Helder";
    private static final String PASSWORD = "Helder2004*";
    private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;
    private Connection connection;
    
    public void conexion() {
        System.out.println("URL de connexion: "+ URL);
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Esquema: " + connection.getSchema());
            System.out.println("Esquema: " + connection.getCatalog());
        } catch (SQLException e) {
            System.out.println("Error al conectase con la base de datos" + e.getMessage());
        }
    }
    
    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexion cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexion: " + e.getMessage());
        }
    }
    
    public void agregar() throws SQLException{
    }
    
    public void elimnar() {
    
    }
    
    public void obtener () {
    }

    public Connection getConnection() {
        return connection;
    }    
}
