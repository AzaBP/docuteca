/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Clase principal
 */
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import modelo.DocutekaModelo;
import vista.DocutekaVista;

public class Docuteka implements OyenteVista{
    private static String VERSION = "DOCUTEKA BASES DATOS";
    private DocutekaVista vista;
    private DocutekaModelo docutekaModelo;
    
    /**
     * Construye la aplicación
     */
    public Docuteka(){
        docutekaModelo = new DocutekaModelo();
        vista = DocutekaVista.instancia(this, docutekaModelo);
    
        docutekaModelo.nuevoObservador(vista);
    }
    
    /**
     * Recibe eventos de la vista
     */
    @Override
    public void eventoProducido(Evento evento, Object obj) throws Exception {
        switch(evento){
            case MODIFICAR:
                System.out.println("Modificar: " + obj.toString());
                String[] datosModificar = obj.toString().split(",");
                if (datosModificar.length != 4) {
                    throw new IllegalArgumentException("Formato incorrecto para modificar. Se esperaba: entidad,atributo,valor,clave");
                }
                
                String entidadModificar = datosModificar[0];
                String nombreAtributoModificar = datosModificar[1];
                String nuevoValorModificar = datosModificar[2];
                String clavePrimariaModificar = datosModificar[3];
                String nombreAtributoPKModificar = obtenerAtributoPK(entidadModificar);
                
                docutekaModelo.modificar(nombreAtributoModificar, nombreAtributoPKModificar, 
                                       nuevoValorModificar, clavePrimariaModificar, entidadModificar);
                break;
                
                case INSERTAR:
                    System.out.println("Insertar: " + obj.toString());

                    // Divide la cadena en partes separadas por comas
                    String[] datosInsertar = obj.toString().split(",");

                    // Verifica que haya suficientes datos
                    if (datosInsertar.length < 2) { // Cambia "2" por el número mínimo de campos requeridos
                        throw new IllegalArgumentException("Error: Todos los campos deben estar completos.");
                    }

                    // El primer elemento es el nombre de la entidad
                    String entidad = datosInsertar[0];

                    // El resto de los valores
                    String[] valores = Arrays.copyOfRange(datosInsertar, 1, datosInsertar.length);

                    System.out.println("Entidad: " + entidad);
                    System.out.println("Valores: " + Arrays.toString(valores)); // Depuración

                    // Llama al modelo para insertar
                    docutekaModelo.insertar(valores, entidad);
                    System.out.println("Insertado correctamente en la entidad: " + entidad);
                    break;
                
            case BORRAR:
                System.out.println("Borrar: " + obj.toString());
                String[] datosBorrar = obj.toString().split(",");
                if (datosBorrar.length != 3) {
                    throw new IllegalArgumentException("Formato incorrecto para borrar. Se esperaba: entidad,clave,atributoPK");
                }
                
                String entidadBorrar = datosBorrar[0];
                String clavePrimaria = datosBorrar[1];
                String nombreAtributoPK = datosBorrar[2];
                
                docutekaModelo.borrar(entidadBorrar, nombreAtributoPK, clavePrimaria);
                break;
                
            case SALIR:
                docutekaModelo.desconectar();
                System.exit(0);
                break;
        }
    }
    
    // Método auxiliar para obtener la PK de cada entidad
    private String obtenerAtributoPK(String entidad) {
        switch(entidad) {
            case "Documento": return "numero_correlativo";
            case "Aplicacion": return "nombre_identificativo";
            case "Asignatura": return "clave";
            default: throw new IllegalArgumentException("Entidad desconocida: " + entidad);
        }
    }
    
    /**
     * Metodo main
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {
        new Docuteka();

        Connection conn = DatabaseConnection.getConnection();
        try {
            System.out.println("¡Conexión exitosa a PostgreSQL!");
            System.out.println("Versión del servidor: " + conn.getMetaData().getDatabaseProductVersion());
        } catch (SQLException e) {
            System.err.println("Error de conexión:");
            e.printStackTrace();
        }

        String sql = "INSERT INTO asignatura (clave_asignatura, nombre_asignatura, curso, titulación) VALUES (1234, Lengua, 2, Historia)";
        System.out.println("Se ha insertado");
        PreparedStatement stmt = conn.prepareStatement(sql);

    }
}
