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
public class Pago {

    private final String emailParticipante;
    private final String codigoEvento;
    private final String metodPago;
    private final BigDecimal monto;

    public Pago(String emailParticipante, String codigoEvento, String metodoPago, BigDecimal monto) {
        this.emailParticipante = emailParticipante;
        this.codigoEvento = codigoEvento;
        this.metodPago = metodoPago;
        this.monto = monto;
    }

    public String getEmailParticipante() {
        return emailParticipante;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public String getMetodoPago() {
        return metodPago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

}
