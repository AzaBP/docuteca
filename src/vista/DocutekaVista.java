/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Vista de la aplicación
 */
package vista;

import control.OyenteVista;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import modelo.Aplicacion;
import modelo.Asignatura;
import modelo.Documento;
import modelo.DocutekaModelo;

public class DocutekaVista implements ActionListener, PropertyChangeListener {
    private OyenteVista oyenteVista;
    private DocutekaModelo docutekaModelo;
    private Documento documento;
    private Asignatura asignatura;
    private Aplicacion aplicacion;
    
    private static DocutekaVista instancia = null;
    /*Elementos vista*/
    private JFrame ventana;
    
    private JButton botonAceptar;
    private JButton botonCancelar;
    private JButton botonModificar;
    private JButton botonInsertar;
    private JButton botonBorrar;
    private JButton botonSalir;
    
    private JTextField cambioAtributo;
    
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
        panelCentral.add(Box.createVerticalStrut(30));
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

    /*
     * Muestra una ventana emergente con un JComboBox para seleccionar el tipo de entidad a modificar
     * y otro JComboBox para seleccionar el atributo a modificar.
     */
    private void modificarInstanciaEntidad() {
        JFrame ventanaEmergente = new JFrame("Modificar Instancia de Entidad");
        configurarVentanaEmergente(ventanaEmergente);

        JPanel panelAtributos = new JPanel(new BorderLayout());
        JPanel panelBotones = new JPanel();
        JPanel panelVentanaEmergente = new JPanel();

        panelVentanaEmergente.setLayout(new BoxLayout(panelVentanaEmergente, BoxLayout.Y_AXIS));
        panelVentanaEmergente.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        panelAtributos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titulo = new JLabel("¿Qué tipo de entidad es la instancia que quieres modificar?");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT); 
        panelVentanaEmergente.add(titulo, BorderLayout.NORTH);
        panelVentanaEmergente.add(Box.createVerticalStrut(10));

        String[] opcionesEntidades = {DOCUMENTO, ASIGNATURA, APLICACION};
        JComboBox<String> selecEntidades = new JComboBox<>(opcionesEntidades);
        panelVentanaEmergente.add(selecEntidades, BorderLayout.CENTER);

        seleccionarEntidad(selecEntidades, panelAtributos);
        panelVentanaEmergente.add(panelAtributos, BorderLayout.SOUTH);

        anyadirBotones(panelBotones);
        panelVentanaEmergente.add(panelBotones, BorderLayout.SOUTH);
        
        ventanaEmergente.getContentPane().add(panelVentanaEmergente, BorderLayout.CENTER);
        
    }

    private void configurarVentanaEmergente(JFrame ventanaEmergente){
        ventanaEmergente.setSize(450, 450);
        ventanaEmergente.setVisible(true);
        ventanaEmergente.setLocationRelativeTo(null);

    }

    private void anyadirBotones(JPanel panelSur) {
        panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 

        botonAceptar = crearBoton(ETIQUETA_ACEPTAR, true);
        botonCancelar = crearBoton(ETIQUETA_CANCELAR, true);
    
        panelSur.add(botonAceptar); 
        panelSur.add(botonCancelar);
    }

    /*
     * Muestra un JComboBox para seleccionar el tipo de entidad a modificar 
     */
    public void seleccionarEntidad(JComboBox<String> selecEntidades, JPanel panelAtributos) {
        selecEntidades.addActionListener(e -> {
            String seleccion = (String) selecEntidades.getSelectedItem();
            modificarEntidad(seleccion, panelAtributos);
        });
    }

    /*
     * Muestra un JComboBox para seleccionar el atributo a modificar de la entidad seleccionada
     */
    public void modificarEntidad(String entidad, JPanel panelAtributos) {
        
        panelAtributos.setLayout(new BoxLayout(panelAtributos, BoxLayout.Y_AXIS));

        String textoEleccion = "Selecciona el atributo que quieres modificar:";
        JLabel etiquetaTextoEleccion = new JLabel(textoEleccion);
        etiquetaTextoEleccion.setAlignmentX(Component.LEFT_ALIGNMENT); 
        panelAtributos.add(etiquetaTextoEleccion);


        mostrarAtributos(entidad, panelAtributos);

        panelAtributos.add(Box.createVerticalStrut(10));

        String textoCampoTexto = "Introduce el nuevo valor del atributo:";
        JLabel etiquetaTextoCampoTexto = new JLabel(textoCampoTexto);
        panelAtributos.add(etiquetaTextoCampoTexto);

        JTextField campoTexto = new JTextField( 20);
        panelAtributos.add(campoTexto);

        panelAtributos.revalidate();
        panelAtributos.repaint();
    }

    /*
     * Muestra un JComboBox con los atributos de la entidad seleccionada
     */
    public void mostrarAtributos(String entidad, JPanel panelAtributos) {
        
        panelAtributos.removeAll(); 

        String[] atributos = null;

        switch (entidad) {
            case DOCUMENTO:
                atributos = Documento.getAtributos(); 
                break;
            case ASIGNATURA:
                atributos = Asignatura.getAtributos(); 
                break;
            case APLICACION:
                atributos = Aplicacion.getAtributos(); 
                break;
        }

        if (atributos != null) {
        selecAtributos = new JComboBox<>(atributos);
        selecAtributos.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinea el JComboBox a la izquierda
        panelAtributos.add(selecAtributos);
        }

        // Actualiza el panel
        panelAtributos.revalidate();
        panelAtributos.repaint();
        
        panelAtributos.setVisible(true);
    }

    /*
     * Muestra una ventana emergente para seleccionar el tipo de entidad a borrar y el atributo a borrar
     */
    private void borrarInstanciaEntidad() {
        JFrame ventanaEmergente = new JFrame("Borrar Instancia de Entidad");
        
        JPanel panelVentanaEmergente = new JPanel();
        panelVentanaEmergente.setLayout(new BoxLayout(panelVentanaEmergente, BoxLayout.Y_AXIS));
        panelVentanaEmergente.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        
        
        String textoVentana = "¿Qué tipo de entidad es la instancia que quieres borrar?";
        JLabel etiquetaTexto = new JLabel(textoVentana);
        etiquetaTexto.setFont(new Font("Arial", Font.BOLD, 10));
        etiquetaTexto.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaTexto.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelVentanaEmergente.add(etiquetaTexto, BorderLayout.NORTH);

        String elegirInstanciaEntidad = "Quieres eliminar la instancia total o solo un atributo?";
        JLabel etiquetaTextoInstancia = new JLabel(elegirInstanciaEntidad);
        etiquetaTextoInstancia.setFont(new Font("Arial", Font.BOLD, 10));
        etiquetaTextoInstancia.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaTextoInstancia.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelVentanaEmergente.add(etiquetaTextoInstancia, BorderLayout.NORTH);


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
