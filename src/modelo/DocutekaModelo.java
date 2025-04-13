/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Modelo Docuteka
 */
package modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;
import vista.DocutekaVista;


public class DocutekaModelo{
    private Map<String, Documento> documentos;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void borrar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void modificar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /*
     private void modificarInstanciaEntidad() {
        //QUÉ ENTIDAD MODIFICAR
        //QUÉ ATRIBUTO DE LA ENTIDAD MODIFICAR
        // EL TEXTO QUE VA DENTRO
    }

    private void borrarInstanciaEntidad() {
        //BORRAR INSTANCIA TOTAL O SOLO ATRIBUTO?
        //SI ES INSTANCIA
            //BUCLE QUE RECORRE TODOS LOS ATRIBUTOS Y BORRAR
        //SI ES ATRIBUTO
            //BUSCAR ATRIBUTO   
            //BORRAR
    }

    private void insertarInstanciaEntidad() {
       //CASO DOCUMENTO
            //METER TODOS LOS ATRIBUTOS
       //CASO ASIGNATURA
            //METER TODOS LOS ATRIBUTOS
       //CASO APLICACIÓN
            //METER TODOS LOS ATRIBUTOS
    }
    */
    
}
