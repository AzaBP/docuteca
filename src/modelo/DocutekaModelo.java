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
    
        // Verifica que todos los valores estén completos
        for (String valor : data) {
            if (valor == null || valor.isEmpty()) {
                throw new IllegalArgumentException("Error: Todos los campos deben estar completos.");
            }
        }
    
        switch (entidad) {
            case "Documento":
                System.out.println("Insertando Documento...");
                insertarDocumento(data);
                break;
            case "Aplicacion":
                System.out.println("Insertando app");
                insertarAplicacion(data);
                break;
            case "Asignatura":
                System.out.println("Insertando asignatura");
                insertarAsignatura(data);
                break;
            default:
                throw new IllegalArgumentException("Entidad desconocida: " + entidad);
        }
    }
    
    public void insertarDocumento(String[] data) throws SQLException {
        System.out.println("hika");
        String sql = "INSERT INTO Documento (numero_correlativo, fecha_creacion, nombre, tipo, descripcion_contenido, clave_asignatura, nombre_app) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                throw new SQLException("La conexión a la base de datos es nula.");
            }
            PreparedStatement stmt = conn.prepareStatement(sql);
                System.out.println("hika2");
            stmt.setInt(1, Integer.parseInt(data[0])); // numero_correlativo
            System.out.println("hika2.5");
            stmt.setString(2, data[1]); // fecha_creacion
            System.out.println("hika2.6");
            stmt.setString(3, data[2]); // nombre
            System.out.println("hika2.7");
            stmt.setString(4, data[3]); // tipo
            System.out.println("hika2.8");
            stmt.setString(5, data[4]); // descripcion_contenido
            System.out.println("hika2.9");
            stmt.setString(6, data[5]); // clave
            System.out.println("hika2.10");
            stmt.setString(7, data[6]); // nombre_app
            System.out.println("hika3");
            stmt.executeUpdate();
            System.out.println("hika 4");
            System.out.println("Documento insertado correctamente.");
        }catch (SQLException e) {
            System.out.println("Error al insertar el documento: " + e.getMessage());
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
        String sql = "INSERT INTO Asignatura (clave_asignatura, nombre_asignatura, curso, titulación) VALUES (?, ?, ?, ?)";
        System.out.println("Hola Asigna");
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                System.out.println("Hola As1");
                String claveAsignatura = data[0];
                String nombreAsignatura = data[1];
                String curso = data[2];
                String titulacion = data[3];
                System.out.println("Hola As2");
                stmt.setString(1, claveAsignatura);
                stmt.setString(2, nombreAsignatura); 
                stmt.setString(3, curso);
                stmt.setString(4, titulacion);
                System.out.println("Hola As3");
                stmt.executeUpdate();
                conn.commit();
                System.out.println("Hola As4");
                System.out.println("Asignatura insertado correctamente.");
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
            "nombre", "tipo", "descripcion_contenido", "clave_asignatura", "nombre_app",
            "clave", "nombre_asignatura", "curso", "titulación",
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


