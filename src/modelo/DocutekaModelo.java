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
import java.util.HashMap;
import java.util.Map;


public class DocutekaModelo{
    private Map<String, Documento> documentos;
    private Map<String , Aplicacion> aplicaciones;
    private Map<String, Asignatura> asignaturas;
    private PropertyChangeSupport observadores;
    
    /*
    * Construye el modelo de la docuteka
    */
    public DocutekaModelo(){
        documentos = new HashMap<>();
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
    // Inserta un documento en la base de datos
    public void insertarDocumento(String id, String titulo) throws SQLException {
        String sql = "INSERT INTO documentos (id, titulo) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, titulo);
            stmt.executeUpdate();
            System.out.println("Documento insertado correctamente.");
        }
    }
    
}
