/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Clase principal
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;
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
                    String entidadModificar = obj.toString().split("\\s+")[0];
                    String nombreAtributoModificar = obj.toString().split("\\s+")[1];
                    String nuevoValorModificar = obj.toString().split("\\s+")[2];

                    String clavePrimariaModificar = "CAMBIAR";
                    String nombreAtributoPKModificar = "CAMBIAR";

                    docutekaModelo.modificar(nombreAtributoModificar, nombreAtributoPKModificar, nuevoValorModificar, clavePrimariaModificar, entidadModificar);    
                    break;
                
                case INSERTAR:
                    System.out.println("Insertar: " + obj.toString());
                    String[] datosInsertar = obj.toString().split("\\s+");
                    String entidad = datosInsertar[0];
                    String[] valores = Arrays.copyOfRange(datosInsertar, 1, datosInsertar.length);

                    docutekaModelo.insertar(valores, entidad);
                break;
                
                case BORRAR:
                    System.out.println("borrar: " + obj.toString());
                    String entidadBorrar = obj.toString().split("\\s+")[0];
                    String nombreAtributoPK = obj.toString().split("\\s+")[2];
                    String clavePrimaria = obj.toString().split("\\s+")[1];
                    docutekaModelo.borrar(entidadBorrar, nombreAtributoPK, clavePrimaria);
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
            System.out.println("¡Conexión exitosa a PostgreSQL!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
