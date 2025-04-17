/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Clase Entidad Aplicación
 */
package modelo;

public class Aplicacion {
    String nombreIdentificativo;
    String version;
    String nombre;
    String fabricante;
    
    public Aplicacion(String nombreIdentificativo, String version, String fabricante){
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.nombreIdentificativo = nombreIdentificativo;
        this.version = version;
    }
    
    public String getNombreIdentificativo() {
        return nombreIdentificativo;
    }
    
    public static String[] getAtributos(){
        return new String[] { "nombre identificativo", "nombre", "versión", "fabricante"};
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return nombreIdentificativo == ((Aplicacion) obj).nombreIdentificativo;
    }
    
}
