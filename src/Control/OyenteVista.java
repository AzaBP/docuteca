/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Interfaz Oyente Vista
 */
package control;


public interface OyenteVista {
   public enum Evento { INSERTAR, BORRAR, MODIFICAR, SALIR }
  
   /**
    *  Llamado para notificar un evento de la interfaz de usuario
    * 
    */ 
   public void eventoProducido(Evento evento, Object obj) throws Exception;
}
