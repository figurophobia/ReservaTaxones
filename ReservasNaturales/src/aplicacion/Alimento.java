/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class Alimento {
    private int id;
    private String tipo;
    private String nombre;
    private String distribuidor;

    public Alimento(int id, String tipo, String nombre, String distribuidor) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.distribuidor=distribuidor;
    }
    
    public Alimento(String tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public Alimento(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDistribuidor() {
        return distribuidor;
    }
    public void SetDistribuidor(String distribuidor){
        this.distribuidor=distribuidor;
    }
    
    
    
    
}
