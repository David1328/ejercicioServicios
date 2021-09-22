/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.ejemploservidor.Dato;

import java.util.ArrayList;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author David
 */
public class Paciente {
    
    @NotNull @Size(min=4, max=12)
    @Pattern(regexp="^([0-9])*$")
    private String cedula;
    @NotNull @Size(min = 3 ,max =11)
    private String nombre;
    @NotNull @Size(min = 3, max = 10)
    private String apellido;
    @NotNull @Min(2) @Max(85)
    private int edad;
    @NotNull @Size(min=1, max=12)
    private ArrayList<String> enfermedades = new ArrayList<>();

    public Paciente() {
    }

    public Paciente(String cedula, String nombre, String apellido, int edad, ArrayList<String> padecimiento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.enfermedades= padecimiento;
    }
    public Paciente(String cedula, String nombre, String apellido, int edad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the enfermedades
     */
    public ArrayList<String> getEnfermedades() {
        return enfermedades;
    }

    /**
     * @param enfermedades the enfermedades to set
     */
    public void setEnfermedades(ArrayList<String> enfermedades) {
        this.enfermedades = enfermedades;
    }
    
    
    
}
