/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

/**
 *
 * @author helder
 */
public class RegistrarParticipante {
    private final String nombre;
    private final String tipoParticipante;
    private final String institucionProcedencia;
    private final String email;

    public RegistrarParticipante(String nombre, String tipoParticipante, String institucionProcedencia, String email) {
        this.nombre = nombre;
        this.tipoParticipante = tipoParticipante;
        this.institucionProcedencia = institucionProcedencia;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoParticipante() {
        return tipoParticipante;
    }

    public String getInstitucionProcedencia() {
        return institucionProcedencia;
    }

    public String getEmail() {
        return email;
    }
    
}
