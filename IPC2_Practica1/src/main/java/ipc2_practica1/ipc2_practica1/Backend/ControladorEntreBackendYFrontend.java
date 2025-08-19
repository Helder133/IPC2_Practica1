/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipc2_practica1.ipc2_practica1.Backend;

import ipc2_practica1.ipc2_practica1.Frontend.CargaDeArchivoFrame;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author helder
 */
public class ControladorEntreBackendYFrontend {

    private CargaDeArchivoFrame carga;
    private final RegistrarEventoDAO eventoDAO = new RegistrarEventoDAO();
    private final RegistrarParticipanteDAO participanteDAO = new RegistrarParticipanteDAO();
    private final InscripcionDAO inscripcionDAO = new InscripcionDAO();
    private final PagoDAO pagoDAO = new PagoDAO();
    private final RegistrarActividadDAO actividadDAO = new RegistrarActividadDAO();
    private final RegistrarAsistenciaDAO asistenciaDAO = new RegistrarAsistenciaDAO();

    public void setCarga(CargaDeArchivoFrame carga) {
        this.carga = carga;
    }

    public void insetarFormularioEvento(String codigo, String fecha,
            String tipo, String titulo, String ubicacion, int max, BigDecimal costo) throws SQLException {
        try {
            RegistrarEvento evento = new RegistrarEvento(codigo, fecha, tipo, titulo, ubicacion, max, costo);
            eventoDAO.insetar(evento);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new SQLException("El codigo del evento ya existe");
            } else {
                throw new SQLException("Error: " + e.getErrorCode());
            }
        }
    }

    public void insetarArchivo(String path, int tiempo) throws IOException {
        LecturaDeArchivos lectura = new LecturaDeArchivos();
        Thread hilo = new Thread(lectura);
        lectura.setDatos(path, tiempo);
        lectura.setCarga(carga);
        hilo.start();
    }

    public void insetarFormularioParticipante(String nombre, String tipo,
            String institucion, String email) throws SQLException {
        try {
            RegistrarParticipante participante = new RegistrarParticipante(nombre, tipo, institucion, email);
            participanteDAO.insetar(participante);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new SQLException("El emial del participante ya existe");
            } else {
                throw new SQLException("Error: " + e.getErrorCode());
            }
        }
    }

    public void insetarFormularioInscripcion(String emailParticipante, String codigoEvento,
            String tipoInscripcion) throws SQLException {
        try {
            Inscripcion inscripcion = new Inscripcion(emailParticipante, codigoEvento, tipoInscripcion);
            inscripcionDAO.insetar(inscripcion);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new SQLException("El participante ya esta inscrito");
            } else {
                throw new SQLException("Error: " + e.getErrorCode());
            }
        }
    }

    public void insetarFormularioPago(String emailParticipante, String codigoEvento, String metodoPago,
            BigDecimal monto) throws SQLException {
        try {
            Pago pago = new Pago(emailParticipante, codigoEvento, metodoPago, monto);
            pagoDAO.insetar(pago);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public String[][] getParticipantes() throws SQLException {
        return participanteDAO.listar();
    }

    public String[][] getEventos() throws SQLException {
        return eventoDAO.listar();
    }
    
    public List<String> listaCodigoActividad() throws SQLException {
        return actividadDAO.listaCodigoActividad();
    }
    
    public List<String> listaParticipantesEmail() throws SQLException {
        return participanteDAO.listaParticipantesEmail();
    }
    
    public List<String> correoDesdeInscripcion() throws SQLException {
        return inscripcionDAO.getCorreoParticipanteInscripcion();
    }

    public List<String> codigoEventoDesdeInscripcion() throws SQLException {
        return inscripcionDAO.getCodigoEventoInscripcion();
    }

    public List<String> correoDesdePago() throws SQLException {
        return pagoDAO.getCorreoParticipantePagar();
    }

    public List<String> codigoEventoDesdePago() throws SQLException {
        return pagoDAO.getCodigoEventoPagar();
    }

    public void validarPago(String[] entidad) throws SQLException {
        try {
            inscripcionDAO.actualizar(entidad);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void insetarFormularioActividad(String codigoActividad1, String codigoEvento1, String tipoActividad,
            String tituloActividad, String email, LocalTime horaInicio, LocalTime horaFin1, int cupo) throws SQLException {
        try {
            RegistrarActividad actividad = new RegistrarActividad(codigoActividad1, codigoEvento1, tipoActividad, tituloActividad, email, horaInicio, horaFin1, cupo);
            actividadDAO.insetar(actividad);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new SQLException("El participante ya esta inscrito");
            } else {
                throw new SQLException("Error: " + e.getErrorCode());
            }
        }
    }
    
    public void insetarFormularioAsistencia(String emailParticipante, String codigoActividad) throws SQLException {
        try {
            RegistrarAsistencia asistencia = new RegistrarAsistencia(emailParticipante, codigoActividad);
            asistenciaDAO.insetar(asistencia);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new SQLException("El participante ya tiene asisntecia");
            } else {
                throw new SQLException("Error: " + e.getErrorCode());
            }
        }
    }
}
