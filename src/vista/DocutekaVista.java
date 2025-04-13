/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Vista de la aplicación
 */
package vista;

import control.OyenteVista;
import control.Docuteka;
import control.OyenteVista.Evento;
import modelo.Aplicacion;
import modelo.Documento;
import modelo.Asignatura;
import modelo.DocutekaModelo;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import javax.swing.*;

public class DocutekaVista implements ActionListener, PropertyChangeListener {
    private OyenteVista oyenteVista;
    private DocutekaModelo docutekaModelo;
    
    private static DocutekaVista instancia = null;
    /*Elementos vista*/
    private JFrame ventana;
    
    private JButton botonAceptar;
    private JButton botonCancelar;
    private JButton botonModificar;
    private JButton botonInsertar;
    private JButton botonBorrar;
    private JButton botonSalir;
    
    private JTextField cambioAtributos;
    
    private JComboBox selecAtributos;
    
    /*Texto vista*/
    private static final String ETIQUETA_MODIFICAR = "MODIFICAR";
    private static final String ETIQUETA_INSERTAR = "INSERTAR";
    private static final String ETIQUETA_BORRAR = "BORRAR";
    private static final String ETIQUETA_SALIR = "SALIR";
    private static final String ETIQUETA_ACEPTAR = "Aceptar";
    private static final String ETIQUETA_CANCELAR = "Cancelar";
    private static final String MODIFICAR = "Modificar";
    private static final String INSERTAR = "Insertar";
    private static final String BORRAR = "Borrar";
    private static final String APLICACION = "Aplicacion";
    private static final String DOCUMENTO = "Documento";
    private static final String ASIGNATURA = "Asignatura";
    
    public static final int ALTURA = 70;
    public static final int ANCHURA = 30;
    
    /**
     * Construye la vista de la aplicación
     */
    private DocutekaVista(OyenteVista oyenteVista, DocutekaModelo docuteka){
        this.oyenteVista = oyenteVista;
        this.docutekaModelo = docutekaModelo;
        
        crearVentana();
    }
    
    /**
     * Crea la ventana de la vista
     */
    private void crearVentana(){
        ventana = new JFrame("Docuteka APP");
        
        ventana.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent event){
                try{
                    oyenteVista.eventoProducido(OyenteVista.Evento.SALIR, null);
                }catch(Exception e){
                    System.out.println("Error al salilr de la aplicación");
                }
            }
        });
        
        ventana.getContentPane().setLayout(new BorderLayout());
        
        JPanel panelCentral = new JPanel();
        crearPanelCentral(panelCentral);
        ventana.getContentPane().add(panelCentral, BorderLayout.CENTER);
        
        
        ventana.setResizable(false);    
        ventana.pack();  // ajusta ventana y sus componentes
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);  // centra en la pantalla
  
    }
    
    /**
     * Devuelve la instancia de la vista de la aplicación
     */
    public static synchronized DocutekaVista instancia(OyenteVista oyenteIU, DocutekaModelo docutekaModelo){
        if (instancia ==  null){
            instancia = new DocutekaVista(oyenteIU, docutekaModelo);
        }
        return instancia;
    }
    
    /**
     * Crea boton
     */
    private JButton crearBoton(String etiqueta, boolean habilitado){
        JButton boton = new JButton(etiqueta);
        boton.addActionListener(this);
        boton.setActionCommand(etiqueta);
        boton.setEnabled(habilitado);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        return boton;
    }
    
    /**
     * Crea el panel principal con los botones
     */
    private void crearPanelCentral(JPanel panelCentral) {
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));  // Márgenes externos

        JLabel titulo = new JLabel("Docuteka");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado horizontal
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel subtitulo = new JLabel("Seleccione la opcion a ejecutar");
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado horizontal
        subtitulo.setFont(new Font("Arial", Font.BOLD, 10));
        
        botonModificar = crearBoton(ETIQUETA_MODIFICAR, true);
        botonInsertar = crearBoton(ETIQUETA_INSERTAR, true);
        botonBorrar = crearBoton(ETIQUETA_BORRAR, true);
        botonSalir = crearBoton(ETIQUETA_SALIR, true);

        panelCentral.add(Box.createVerticalStrut(10)); 
        panelCentral.add(titulo);
        panelCentral.add(Box.createVerticalStrut(10)); 
        panelCentral.add(subtitulo);
        panelCentral.add(Box.createVerticalStrut(20)); 
        panelCentral.add(Box.createVerticalStrut(10));
        panelCentral.add(botonInsertar);
        panelCentral.add(Box.createVerticalStrut(15));
        panelCentral.add(botonBorrar);
        panelCentral.add(Box.createVerticalStrut(15));
        panelCentral.add(botonModificar);
        panelCentral.add(Box.createVerticalStrut(15));
        panelCentral.add(botonSalir);
        panelCentral.add(Box.createVerticalStrut(10));
}
    
    
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){
            case ETIQUETA_MODIFICAR:
                System.out.println("Hola modificar");
                modificarInstanciaEntidad();
                break;
                
            case ETIQUETA_INSERTAR:
            System.out.println("Hola insertar");
                insertarInstanciaEntidad();
                break;
                
           case ETIQUETA_BORRAR:
           
           System.out.println("Hola borrar");
                borrarInstanciaEntidad();
                break;
          case ETIQUETA_SALIR:
                try {
                    oyenteVista.eventoProducido(OyenteVista.Evento.SALIR, null);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
        }
    }

    private void modificarInstanciaEntidad() {
        ventana = new JFrame();
        
        String textoVentana = "¿Qué tipo de entidad es instancia que quieres modificar?";
        JLabel etiquetaTexto = new JLabel(textoVentana);
        String textoEleccion = "Selecciona el atributo que quieres modificar:";
        JLabel etiquetaTextoEleccion = new JLabel(textoEleccion);
        
        String[] opcionesEntidades = {DOCUMENTO, ASIGNATURA, APLICACION};
        JComboBox<String> selecEntidades = new JComboBox<>(opcionesEntidades);
        
        selecEntidades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) selecEntidades.getSelectedItem();

                // Usar un switch para manejar las opciones seleccionadas
                switch (seleccion) {
                    case DOCUMENTO:
                        System.out.println("Seleccionaste la Opción 1");
                        break;
                    case ASIGNATURA:
                        System.out.println("Seleccionaste la Opción 2");
                        break;
                    case APLICACION:
                        System.out.println("Seleccionaste la Opción 3");
                        break;
                }
            }
        });
        //QUÉ ATRIBUTO DE LA ENTIDAD MODIFICAR(JComboBOX)
        // EL TEXTO QUE VA DENTRO
    }

    private void borrarInstanciaEntidad() {
        
    //BORRAR INSTANCIA TOTAL O SOLO ATRIBUTO?
        //SI ES INSTANCIA
            //PEDIR CP
        //SI ES ATRIBUTO
            //MODIFICARiNSTANCIAeNTIDAD PARA BORRAR
    }

    private void insertarInstanciaEntidad() {
       //CASO DOCUMENTO
            //METER TODOS LOS ATRIBUTOS
       //CASO ASIGNATURA
            //METER TODOS LOS ATRIBUTOS
       //CASO APLICACIÓN
            //METER TODOS LOS ATRIBUTOS
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
