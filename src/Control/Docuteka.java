/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Clase principal
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;
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
                    docutekaModelo.modificar();
                    break;
                
                case INSERTAR:
                    System.out.println("Insertar: " + obj.toString());
                    String[] datosInsertar = obj.toString().split(",");
                    docutekaModelo.insertarDocumento(
                        Integer.parseInt(datosInsertar[0]), // Assuming the first value is an int
                        datosInsertar[1], 
                        datosInsertar[2], 
                        datosInsertar[3], 
                        datosInsertar[4], 
                        datosInsertar[5], 
                        datosInsertar[6]
                    );
                break;
                
                case BORRAR:
                System.out.println("borrar: " + obj.toString());
                    docutekaModelo.borrar();
                    break;
                   
                case SALIR:
                    docutekaModelo.desconectar();
                    System.exit(0);
                    break;
            }
    }
    
    /**
     * Metodo main
     */
    public static void main(String[] args) {
        new Docuteka();

        String url = "jdbc:postgresql://localhost:5432/Docuteka";
        String user = "postgres";
        String password = "aza05dumask";

        try (Connection conexion = DriverManager.getConnection(url, user, password)) {
            System.out.println("🎉 ¡Conexión exitosa a PostgreSQL!");
            // Aquí puedes usar tus DAOs (ClienteDAO, etc.)
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
