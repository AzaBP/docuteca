/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Clase Entidad Aplicación
 */
package modelo;

public class Aplicacion {
    String nombreIdApp;
    String version;
    String nombreApp;
    String fabricante;
    
    public Aplicacion(String nombreIdApp, String nombreApp, String version, String fabricante){
        this.nombreApp = nombreApp;
        this.fabricante = fabricante;
        this.nombreIdApp = nombreIdApp;
        this.version = version;
    }
    
    public String getNombreIdApp() {
        return nombreIdApp;
    }
    
    public static String[] getAtributos(){
        return new String[] { "nombre id app", "nombre app", "versión", "fabricante"};
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return nombreIdApp == ((Aplicacion) obj).nombreIdApp;
    }

    public static String getAtributoPK() {
        return "nombre_id_app";
    }
    
}
