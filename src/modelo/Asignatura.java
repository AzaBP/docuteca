/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Clase Entidad Asignatura
 */
package modelo;

public class Asignatura {
    String nombre;
    String clave;
    String curso;
    String titulacion;
    
    public Asignatura(String nombre, String clave, String curso, String titulación){
        this.nombre = nombre;
        this.clave = clave;
        this.curso = curso;
        this.titulacion = titulación;
    }
    
    String getClave() {
        return clave;      
    }

    public static String[] getAtributos(){
        return new String[] {"clave", "nombre", "curso", "titulación"};
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return clave == ((Asignatura) obj).clave;
    }

    public static String getAtributoPK() {
        return "clave";
    }
    
}
