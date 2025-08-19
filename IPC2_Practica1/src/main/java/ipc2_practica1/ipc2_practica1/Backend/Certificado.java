/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

/**
 *
 * @author helder
 */
public class Certificado {

    private final String emailParticipante;
    private final String codigoEvento;

    public Certificado(String emailParticipante, String codigoEvento) {
        this.emailParticipante = emailParticipante;
        this.codigoEvento = codigoEvento;
    }

    public String getEmailParticipante() {
        return emailParticipante;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

}
