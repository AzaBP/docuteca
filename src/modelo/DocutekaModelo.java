/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Modelo Docuteka
 */
package modelo;

import control.DatabaseConnection;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DocutekaModelo{
    private PropertyChangeSupport observadores;
    
    /*
    * Construye el modelo de la docuteka
    */
    public DocutekaModelo(){
        observadores = new PropertyChangeSupport(this);
    }
    
    public void nuevoObservador(PropertyChangeListener observador) {
        this.observadores.addPropertyChangeListener(observador);
    }

    public void desconectar() {
        System.out.println("Desconectando...");
    }

    public void borrar() {
        System.out.println("Borrando...");
    }

    public void modificar() {
        System.out.println("Modificando...");
    }

    public void insertar() {
    System.out.println("Insertando...");
    }

    public void insertarDocumento(int numeroCorrelativo, String fechaCreacion, String nombre, String tipo, String descripcionContenido, String clave, String nombreApp) throws SQLException {
        if (nombre == null || nombre.isEmpty() || tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("Error: Los campos 'nombre' y 'tipo' no pueden estar vacíos.");
        }
    
        String sql = "INSERT INTO public.\"Documento\" (\"numero correlativo\", \"fecha creacion\", nombre, tipo, \"descirpcion contenido\", clave, nombre_app) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numeroCorrelativo);
            stmt.setDate(2, java.sql.Date.valueOf(fechaCreacion)); // Convierte la fecha a java.sql.Date
            stmt.setString(3, nombre);
            stmt.setString(4, tipo);
            stmt.setString(5, descripcionContenido);
            stmt.setString(6, clave);
            stmt.setString(7, nombreApp);
            stmt.executeUpdate();
            System.out.println("Documento insertado correctamente.");
        }
    }
}
