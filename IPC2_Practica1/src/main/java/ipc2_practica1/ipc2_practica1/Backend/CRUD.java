/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.sql.SQLException;

/**
 *
 * @author helder
 * @param <T>
 */
public interface CRUD <T> {
    void insetar(T entidad) throws SQLException;
    void actualizar(String[] entidad) throws SQLException;
    String[][] listar() throws SQLException;
    void borrar () throws SQLException;
}
