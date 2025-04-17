/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Vista de la aplicación
 */
package vista;

import control.OyenteVista;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
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
    
    private static final String TEXTO_MODIFICAR = "¿Qué tipo de entidad es la instancia que quieres modificar?";
    private static final String TEXTO_BORRAR = "¿Qué tipo de entidad es la instancia que quieres borrar?";
    private static final String TEXTO_INSERTAR = "¿Qué tipo de entidad es la instancia que quieres insertar?";
    
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
                modificarInstanciaEntidad();
                break;
                
            case ETIQUETA_INSERTAR:
                insertarInstanciaEntidad();
                break;
                
            case ETIQUETA_BORRAR:
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
     */
    private void modificarInstanciaEntidad() {
        JDialog ventanaEmergente = new JDialog(ventana, "Modificar Instancia de Entidad", true);
        JPanel panelContenido = new JPanel();
        JPanel panelEntidad = new JPanel();
        JPanel panelAtributos = new JPanel();
        JPanel panelBotones = new JPanel();
        JTextField campoTexto = new JTextField(20);

        configurarElementosVentanaEmergente(ventanaEmergente, panelContenido, panelEntidad, panelAtributos, panelBotones);
    
        anyadirElementosPanelEntidad(panelEntidad, panelAtributos, TEXTO_MODIFICAR, campoTexto);
    
        // Añadir interacción a los botones
        panelBotonesAceptarCancelarGeneral(panelBotones, ventanaEmergente, panelAtributos, campoTexto, OyenteVista.Evento.MODIFICAR);

        // Añadir componentes al panel principal
        panelContenido.setLayout(new BorderLayout());
        panelContenido.add(panelEntidad, BorderLayout.NORTH);
        panelContenido.add(panelAtributos, BorderLayout.CENTER);
        panelContenido.add(panelBotones, BorderLayout.SOUTH);
    
        ventanaEmergente.add(panelContenido, BorderLayout.CENTER);
        ventanaEmergente.pack();
        ventanaEmergente.setLocationRelativeTo(ventana);
        ventanaEmergente.setVisible(true);
    }
    
    private void anyadirElementosPanelEntidad(JPanel panelEntidad, JPanel panelAtributos, String texto, JTextField campoTexto) {
        JLabel lblEntidad = new JLabel(texto);
        lblEntidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEntidad.add(lblEntidad);
        panelEntidad.add(Box.createRigidArea(new Dimension(0, 10)));
        
        String[] opcionesEntidades = {DOCUMENTO, ASIGNATURA, APLICACION};
        JComboBox<String> selecEntidades = new JComboBox<>(opcionesEntidades);
        selecEntidades.setMaximumSize(new Dimension(Integer.MAX_VALUE, selecEntidades.getPreferredSize().height));
        selecEntidades.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEntidad.add(selecEntidades);
        
        // Configurar acción para el combo de entidades si el texto es "Modificar"
        if(texto.equals(TEXTO_MODIFICAR)){
            selecEntidades.addActionListener(e -> {
                String seleccion = (String) selecEntidades.getSelectedItem();
                mostrarPanelAtributos(seleccion, panelAtributos, campoTexto);
            });
        }
    }

    private void configurarElementosVentanaEmergente(JDialog ventanaEmergente, JPanel panelContenido,
            JPanel panelEntidad, JPanel panelAtributos, JPanel panelBotones) {
            
            ventanaEmergente.setMinimumSize(new Dimension(400, 400));
            ventanaEmergente.setLayout(new BorderLayout());
                
            panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
            panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                    
            panelEntidad.setLayout(new BoxLayout(panelEntidad, BoxLayout.Y_AXIS));
            panelEntidad.setAlignmentX(Component.CENTER_ALIGNMENT);

            panelAtributos.setLayout(new BoxLayout(panelAtributos, BoxLayout.Y_AXIS));
            panelAtributos.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelAtributos.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

    }

    /*
     * Muestra los atributos para la entidad seleccionada
     */
    private void mostrarPanelAtributos(String entidad, JPanel panelAtributos, JTextField campoTexto) {
        panelAtributos.removeAll(); // Limpia el panel antes de agregar nuevos componentes
    
        if (entidad == null || entidad.isEmpty()) {
            panelAtributos.revalidate();
            panelAtributos.repaint();
            return;
        }
    
        // Etiqueta para seleccionar el atributo
        JLabel lblAtributo = new JLabel("Selecciona el atributo que quieres modificar:");
        lblAtributo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelAtributos.add(lblAtributo);
        panelAtributos.add(Box.createRigidArea(new Dimension(0, 10)));
    
        // Obtener los atributos según la entidad seleccionada
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
    
        // Si hay atributos, crea un JComboBox y agrégalo al panel
        if (atributos != null) {
            selecAtributos = new JComboBox<>(atributos);
            selecAtributos.setMaximumSize(new Dimension(Integer.MAX_VALUE, selecAtributos.getPreferredSize().height));
            selecAtributos.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelAtributos.add(selecAtributos);
            panelAtributos.add(Box.createRigidArea(new Dimension(0, 15)));
    
            // Etiqueta para el campo de texto
            JLabel lblNuevoValor = new JLabel("Introduce el nuevo valor del atributo:");
            lblNuevoValor.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelAtributos.add(lblNuevoValor);
            panelAtributos.add(Box.createRigidArea(new Dimension(0, 5)));
    
            // Usa el campoTexto pasado como parámetro
            campoTexto.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoTexto.getPreferredSize().height));
            campoTexto.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelAtributos.add(campoTexto);
        }
    
        // Actualiza el panel
        panelAtributos.revalidate();
        panelAtributos.repaint();
    }

    private void mostrarCamposAtributos(String entidad, JPanel panelAtributos) {
        panelAtributos.removeAll(); // Limpia el panel antes de agregar nuevos componentes
    
        // Obtener los atributos según la entidad seleccionada
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
    
        // Si hay atributos, crea campos de texto y etiquetas dinámicamente
        if (atributos != null) {
            for (String atributo : atributos) {
                JLabel lblAtributo = new JLabel(atributo + ":");
                lblAtributo.setAlignmentX(Component.LEFT_ALIGNMENT);
                panelAtributos.add(lblAtributo);
    
                JTextField campoTexto = new JTextField(20);
                campoTexto.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoTexto.getPreferredSize().height));
                campoTexto.setAlignmentX(Component.LEFT_ALIGNMENT);
                panelAtributos.add(campoTexto);
            }
        }
    
        // Actualiza el panel
        panelAtributos.revalidate();
        panelAtributos.repaint();
    }

    /*
     * Muestra una ventana emergente para seleccionar el tipo INSTANCIA a borrar
     */
    private void borrarInstanciaEntidad() {
        JDialog ventanaEmergente = new JDialog(ventana, "Borrar Instancia de Entidad", true);
        JPanel panelContenido = new JPanel();
        JPanel panelEntidad = new JPanel();
        JPanel panelCentral = new JPanel();
        JPanel panelBotones = new JPanel();
        JTextField campoTexto = new JTextField(20); // Campo de texto para la clave primaria
    
        configurarElementosVentanaEmergente(ventanaEmergente, panelContenido, panelEntidad, panelCentral, panelBotones);
    
        // Añadir elementos al panel de entidad
        anyadirElementosPanelEntidad(panelEntidad, panelCentral, TEXTO_BORRAR, campoTexto);
    
        // Etiqueta y campo de texto para la clave primaria
        JLabel lblClavePrimaria = new JLabel("Introduce la clave primaria de la instancia a eliminar:");
        lblClavePrimaria.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCentral.add(lblClavePrimaria);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
    
        campoTexto.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoTexto.getPreferredSize().height));
        campoTexto.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCentral.add(campoTexto);
    
        // Añadir interacción a los botones
        panelBotonesAceptarCancelarGeneral(panelBotones, ventanaEmergente, null, campoTexto, OyenteVista.Evento.BORRAR);
    
        // Añadir componentes al panel principal
        panelContenido.setLayout(new BorderLayout());
        panelContenido.add(panelEntidad, BorderLayout.NORTH);
        panelContenido.add(panelCentral, BorderLayout.CENTER);
        panelContenido.add(panelBotones, BorderLayout.SOUTH);
    
        ventanaEmergente.add(panelContenido, BorderLayout.CENTER);
        ventanaEmergente.pack();
        ventanaEmergente.setLocationRelativeTo(ventana);
        ventanaEmergente.setVisible(true);
    }

    private void panelBotonesAceptarCancelarGeneral(JPanel panelBotones, JDialog ventanaEmergente, JPanel panelAtributos, JTextField campoTexto, OyenteVista.Evento evento) {
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    
        // Botón Aceptar
        botonAceptar = crearBoton(ETIQUETA_ACEPTAR, true);
        botonAceptar.addActionListener(e -> {
            StringBuilder valores = new StringBuilder();
    
            // Recopilar los valores de los campos de texto
            if (panelAtributos != null) {
                Component[] componentes = panelAtributos.getComponents();
                for (Component componente : componentes) {
                    if (componente instanceof JTextField) {
                        JTextField campo = (JTextField) componente;
                        String texto = campo.getText().trim();
                        if (texto.isEmpty()) {
                            JOptionPane.showMessageDialog(ventanaEmergente, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        valores.append(texto).append(", ");
                    }
                }
            } else if (campoTexto != null) {
                // Si es un caso como BORRAR o MODIFICAR con un solo campo
                String texto = campoTexto.getText().trim();
                if (texto.isEmpty()) {
                    JOptionPane.showMessageDialog(ventanaEmergente, "El campo no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                valores.append(texto);
            }
    
            // Enviar los valores al controlador
            System.out.println("Valores introducidos: " + valores.toString());
            try {
                oyenteVista.eventoProducido(evento, valores.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ventanaEmergente, "Error al procesar la información.", "Error", JOptionPane.ERROR_MESSAGE);
            }
    
            // Cierra la ventana después de aceptar
            ventanaEmergente.dispose();
        });
    
        // Botón Cancelar
        botonCancelar = crearBoton(ETIQUETA_CANCELAR, true);
        botonCancelar.addActionListener(e -> {
            // Cierra solo la ventana emergente
            ventanaEmergente.dispose();
        });
    
        panelBotones.add(botonAceptar);
        panelBotones.add(botonCancelar);
    }
    

    private void insertarInstanciaEntidad() {
        JDialog ventanaEmergente = new JDialog(ventana, "Insertar Instancia de Entidad", true);
        JPanel panelContenido = new JPanel();
        JPanel panelEntidad = new JPanel();
        JPanel panelAtributos = new JPanel();
        JPanel panelBotones = new JPanel();

        configurarElementosVentanaEmergente(ventanaEmergente, panelContenido, panelEntidad, panelAtributos, panelBotones);

    // Etiqueta y ComboBox para seleccionar el tipo de entidad
        JLabel lblEntidad = new JLabel("Selecciona el tipo de entidad:");
        lblEntidad.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEntidad.add(lblEntidad);

        String[] opcionesEntidades = {DOCUMENTO, ASIGNATURA, APLICACION};
        JComboBox<String> selecEntidades = new JComboBox<>(opcionesEntidades);
        selecEntidades.setMaximumSize(new Dimension(Integer.MAX_VALUE, selecEntidades.getPreferredSize().height));
        selecEntidades.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelEntidad.add(selecEntidades);

    // Configurar acción para el ComboBox
        selecEntidades.addActionListener(e -> {
            String seleccion = (String) selecEntidades.getSelectedItem();
            mostrarCamposAtributos(seleccion, panelAtributos);
        });

    // Añadir interacción a los botones
        panelBotonesAceptarCancelarGeneral(panelBotones, ventanaEmergente, panelAtributos, null, OyenteVista.Evento.INSERTAR);

    // Añadir componentes al panel principal
        panelContenido.setLayout(new BorderLayout());
        panelContenido.add(panelEntidad, BorderLayout.NORTH);
        panelContenido.add(panelAtributos, BorderLayout.CENTER);
        panelContenido.add(panelBotones, BorderLayout.SOUTH);

        ventanaEmergente.add(panelContenido, BorderLayout.CENTER);
        ventanaEmergente.pack();
        ventanaEmergente.setLocationRelativeTo(ventana);
        ventanaEmergente.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
