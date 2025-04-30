package aplicacion;

/**
 *
 * @author davidavirn
 */
public class ClinicaMedica {
    private String nombre;         
    private String ubicacion;
    private int numEmpleados;

    // Constructor vacío
    public ClinicaMedica() {
    }

    // Constructor con parámetros
    public ClinicaMedica(String nombre, String ubicacion, int numEmpleados) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.numEmpleados = numEmpleados;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getNumEmpleados() {
        return numEmpleados;
    }

    public void setNumEmpleados(int numEmpleados) {
        this.numEmpleados = numEmpleados;
    }
}
