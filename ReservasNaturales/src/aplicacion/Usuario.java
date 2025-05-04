package aplicacion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author basesdatos
 */
public class Usuario {
    private String dni;
    private String nombre;
    private float sueldo;
    private int horas;
    private Area Area;

    public Usuario(String dni, String nombre, float sueldo, int horas,Area Area) {
        this.dni = dni;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.horas = horas;
        this.Area=Area;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getSueldo() {
        return sueldo;
    }
    public Area getArea(){
        return Area;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setArea(Area area) {
        this.Area=area;
    }
 
    
    
   
   
   
}
