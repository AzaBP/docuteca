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
                    docutekaModelo.modificar();
                    break;
                
                case INSERTAR:
                    docutekaModelo.insertar();
                    break;
                
                case BORRAR:
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
    }

}
