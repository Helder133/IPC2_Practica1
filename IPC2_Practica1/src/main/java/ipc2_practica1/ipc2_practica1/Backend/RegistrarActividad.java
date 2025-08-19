/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.time.LocalTime;

/**
 *
 * @author helder
 */
public class RegistrarActividad {
    private final String codigoActividad;
    private final String codigoEvento;
    private final String tipoActividad;
    private final String tituloActividad;
    private final String emailParticipante;
    private final LocalTime horaInico;
    private final LocalTime horaFinal;
    private final int cupoMaximo;

    public RegistrarActividad(String codigoActividad, String codigoEvento, String tipoActividad, String tituloActividad, String emailParticipante, LocalTime horaInico, LocalTime horaFinal, int cupoMaximo) {
        this.codigoActividad = codigoActividad;
        this.codigoEvento = codigoEvento;
        this.tipoActividad = tipoActividad;
        this.tituloActividad = tituloActividad;
        this.emailParticipante = emailParticipante;
        this.horaInico = horaInico;
        this.horaFinal = horaFinal;
        this.cupoMaximo = cupoMaximo;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public String getTituloActividad() {
        return tituloActividad;
    }

    public String getEmailParticipante() {
        return emailParticipante;
    }

    public LocalTime getHoraInicio() {
        return horaInico;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }
    
    
}
