/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

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

    public RegistrarEvento(String codigoDeEvento, String fechaDelEvento, String tipoDeEvento, String tituloDeEvento, String ubicacion, int cupoMax) {
        this.codigoDeEvento = codigoDeEvento;
        this.fechaDelEvento = fechaDelEvento;
        this.tipoDeEvento = tipoDeEvento;
        this.tituloDeEvento = tituloDeEvento;
        this.ubicacion = ubicacion;
        this.cupoMax = cupoMax;
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
    
    
}
