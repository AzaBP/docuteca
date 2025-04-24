/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Clase Entidad Asignatura
 */
package modelo;

public class Asignatura {
    String nombreAsignatura;
    String claveAsignatura;
    String curso;
    String titulacion;
    
    public Asignatura(String nombreAsignatura, String claveAsignatura, String curso, String titulación){
        this.nombreAsignatura = nombreAsignatura;
        this.claveAsignatura = claveAsignatura;
        this.curso = curso;
        this.titulacion = titulación;
    }
    
    String getClave() {
        return claveAsignatura;      
    }

    public static String[] getAtributos(){
        return new String[] {"clave asignatura", "nombre asignatura", "curso", "titulación"};
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return claveAsignatura == ((Asignatura) obj).claveAsignatura;
    }

    public static String getAtributoPK() {
        return "clave asignatura";
    }
    
}
