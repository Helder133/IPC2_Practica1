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
    
    public Connection conexion() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
