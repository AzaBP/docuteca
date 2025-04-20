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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


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

    public void borrar(String entidad, String nombreAtributoPK , String clavePrimaria) throws Exception {
        System.out.println("Borrando...");
        borrarEntidad(entidad, nombreAtributoPK, clavePrimaria);
    }

    public void modificar(String nombreAtributo, String nombreAtributoPK, String nuevoValor, String clavePrimaria, String entidad) throws SQLException { 
        System.out.println("Modificando...");
        modificarEntidad(nombreAtributo, nombreAtributoPK, entidad, nuevoValor, clavePrimaria);
    }

    public void insertar(String[] data, String entidad) throws SQLException {
        System.out.println("Insertando en la entidad: " + entidad);
        System.out.println("Valores: " + Arrays.toString(data));

        switch (entidad) {
            case "Documento":
                    insertarDocumento(data);
                break;
            case "Aplicación":
                    insertarAplicacion(data);
                break;
            case "Asignatura":
                    insertarAsignatura(data);
                break;
        }
    }
    
    public void insertarDocumento(String[]data) throws SQLException {
        String sql = "INSERT INTO Documento (numero_correlativo, fecha_creacion, nombre, tipo, descripcion_contenido, clave, nombre_app) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                String numeroCorrelativo = data[0];
                String fechaCreacion = data[1];
                String nombre = data[2];
                String tipo = data[3];
                String descripcionContenido = data[4];
                String clave = data[5];
                String nombreApp = data[6];
                stmt.setString(1, numeroCorrelativo);
                stmt.setDate(2, java.sql.Date.valueOf(fechaCreacion)); 
                stmt.setString(3, nombre);
                stmt.setString(4, tipo);
                stmt.setString(5, descripcionContenido);
                stmt.setString(6, clave);
                stmt.setString(7, nombreApp);
                stmt.executeUpdate();
                System.out.println("Documento insertado correctamente.");
            }
    }

    public void insertarAplicacion(String[]data) throws SQLException {
        String sql = "INSERT INTO Aplicacion (nombre_identificativo, nombre, versión, fabricante) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                String nombre_identificativo = data[0];
                String nombre = data[1];
                String version = data[2];
                String fabricante = data[3];
                stmt.setString(1, nombre_identificativo);
                stmt.setString(2, nombre); 
                stmt.setString(3, version);
                stmt.setString(4, fabricante);
                stmt.executeUpdate();
                System.out.println("Documento insertado correctamente.");
            }
    }

    public void insertarAsignatura(String[]data) throws SQLException {
        String sql = "INSERT INTO Asignatura (clave, nombre, curso, titulación) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                String clave = data[0];
                String nombre = data[1];
                String curso = data[2];
                String titulacion = data[3];
                stmt.setString(1, clave);
                stmt.setString(2, nombre); 
                stmt.setString(3, curso);
                stmt.setString(4, titulacion);
                stmt.executeUpdate();
                System.out.println("Documento insertado correctamente.");
            }
    }

    public void modificarEntidad(String nombreAtributo, String nombreAtributoPK, String entidad, String nuevoValor, String clavePrimaria) throws SQLException {
        // Validación de parámetros
        if (nombreAtributo == null || nombreAtributo.isEmpty() || 
            clavePrimaria == null || clavePrimaria.isEmpty()) {
            throw new IllegalArgumentException("Parámetros inválidos");
        }

        String columna = nombreAtributo.replaceAll("\\s+", "_"); 

        // Lista blanca de columnas modificables
        Set<String> columnasPermitidas = new HashSet<>(Arrays.asList(
            "nombre", "tipo", "descripcion_contenido", "clave", "nombre_app",
            "clave", "nombre", "curso", "titulación",
            "nombre_identificativo", "nombre", "versión", "fabricante"
        ));

        if (!columnasPermitidas.contains(columna)) {
            throw new IllegalArgumentException("Atributo no permitido para modificación");
        }

        String sql = "UPDATE " + entidad + " SET " + columna + " = ? WHERE " + nombreAtributoPK + " = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nuevoValor);
            stmt.setString(2, clavePrimaria);
            
            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas == 0) {
                System.out.println("Advertencia: No se modificó ningún documento. ¿Clave primaria existe?");
            } else {
                System.out.println("Documento modificado correctamente.");
            }
        }
    }

    public void borrarEntidad(String nombreEntidad, String nombreAtributoPK, String clavePrimaria) throws SQLException {
        nombreEntidad = nombreEntidad.replaceAll("\\s+", "_");
        nombreAtributoPK = nombreAtributoPK.replaceAll("\\s+", "_");
        
        String sql = "DELETE FROM " + nombreEntidad + " WHERE " + nombreAtributoPK + " = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clavePrimaria);
            stmt.executeUpdate();
            System.out.println("Documento borrado correctamente.");
        }
    }
}


