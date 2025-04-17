/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Clase principal
 */
package control;

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
                String[] datos = obj.toString().split(","); // Suponiendo que los datos vienen separados por comas
                docutekaModelo.insertarDocumento(datos[0], datos[1]);
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
        //new Docuteka();
    }
}
