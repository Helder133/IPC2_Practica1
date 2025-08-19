/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

/**
 *
 * @author helder
 */
public class RegistrarAsistencia {
    private final String emailParticipante;
    private final String codigoActividad;

    public RegistrarAsistencia(String emailParticipante, String codigoActividad) {
        this.emailParticipante = emailParticipante;
        this.codigoActividad = codigoActividad;
    }

    public String getEmailParticipante() {
        return emailParticipante;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }
    
}
