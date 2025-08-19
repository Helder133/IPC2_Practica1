/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

/**
 *
 * @author helder
 */
public class Inscripcion {
    
    private final String emailParticipante;
    private final String codigoEvento;
    private final String tipoInscripcion;

    public Inscripcion(String emailParticipante, String codigoEvento, String tipoInscripcion) {
        this.emailParticipante = emailParticipante;
        this.codigoEvento = codigoEvento;
        this.tipoInscripcion = tipoInscripcion;
    }

    public String getEmailParticipante() {
        return emailParticipante;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public String getTipoInscripcion() {
        return tipoInscripcion;
    }
    
}
