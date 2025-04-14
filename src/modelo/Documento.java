/*
 * Azahara Barjola 902020
 * Jose María Rodriguez 900709
 * Clase Entidad Documento
 */
package modelo;

import java.util.Objects;


public class Documento {
    int numeroCorrelativoDocumento;
    String fechaCreación;
    String nombre;
    String tipo;
    String descripcion_contenido;
    String claveAsignatura;
    Asignatura asignatura;
    String nombreIdentificativoAplicacion;
    Aplicacion aplicacion;
    
    public String getClaveAsignatura(){
        return claveAsignatura = asignatura.getClave();
    }
    
    public String getNombreIdentificativoAplicación(){
        return nombreIdentificativoAplicacion = aplicacion.getNombreIdentificativo();
    }

    private Documento(int numeroCorrelativoDocumento, String fechaCreación, String nombre, String tipo, String descripcion_contenido, String claveAsignatura, String nombreIdentificativoAplicacion) {
        this.numeroCorrelativoDocumento = numeroCorrelativoDocumento;
        this.fechaCreación = fechaCreación;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion_contenido = descripcion_contenido;
        this.claveAsignatura = asignatura.getClave();
        this.nombreIdentificativoAplicacion = aplicacion.getNombreIdentificativo();
    }

    public static String[] getAtributos(){
        return new String[] {"nombre", "tipo", "fechaCreación", "descripcion_contenido", "claveAsignatura", "nombreIdentificativoAplicacion"};
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return numeroCorrelativoDocumento == ((Documento) obj).numeroCorrelativoDocumento;
    }
    
}
