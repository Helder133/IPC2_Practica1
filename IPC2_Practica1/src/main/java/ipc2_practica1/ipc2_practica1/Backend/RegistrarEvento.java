/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import java.math.BigDecimal;

/**
 *
 * @author helder
 */
public class RegistrarEvento {
    private final String codigoDeEvento;
    private final String fechaDelEvento;
    private final String tipoDeEvento;
    private final String tituloDeEvento;
    private final String ubicacion;
    private final int cupoMax;
    private final BigDecimal costo;

    public RegistrarEvento(String codigoDeEvento, String fechaDelEvento, String tipoDeEvento, String tituloDeEvento, String ubicacion, int cupoMax, BigDecimal costo) {
        this.codigoDeEvento = codigoDeEvento;
        this.fechaDelEvento = fechaDelEvento;
        this.tipoDeEvento = tipoDeEvento;
        this.tituloDeEvento = tituloDeEvento;
        this.ubicacion = ubicacion;
        this.cupoMax = cupoMax;
        this.costo = costo;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public String getCodigoDeEvento() {
        return codigoDeEvento;
    }

    public String getFechaDelEvento() {
        return fechaDelEvento;
    }

    public String getTipoDeEvento() {
        return tipoDeEvento;
    }

    public String getTituloDeEvento() {
        return tituloDeEvento;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getCupoMax() {
        return cupoMax;
    }

    @Override
    public String toString() {
        return "RegistrarEvento{" + "codigoDeEvento=" + codigoDeEvento + ", fechaDelEvento=" + fechaDelEvento + ", tipoDeEvento=" + tipoDeEvento + ", tituloDeEvento=" + tituloDeEvento + ", ubicacion=" + ubicacion + ", cupoMax=" + cupoMax + ", costo=" + costo + '}';
    }

    
    
    
    
}
