/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.ejemploservidor.BaseDeDatos;

import co.edu.unicundi.ejemploservidor.Dato.Paciente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class PacientesLogica {
    
    //Array List que permite guardar objetos tipo paciente
    private static final ArrayList<Paciente> pacientes = new ArrayList<>();
    //String que tiene con el fin que tenga quemada la dirección del fichero
    private static final String fichero = "C:\\Users\\David\\Desktop\\Ingenieria de sistemas\\Linea de profundizacion 2\\ejercicioServicios\\src"+File.separator+"pacientes.txt";
    
    /**
    * Metodo para crear de nuevo el fichero y agregar el array que viene desde el servicios
    * @param personaNueva para poder agragar la nueva lista para actualizar el fichero
    */
    public void agregarPaciente(ArrayList<Paciente> personaNueva){
        //Elimina el fichero 
        File prueba = new File(fichero);
        prueba.delete();
        //String para enviar la informacion con de array
        String subir="";
        //Bucle para recorrer el arrailist que llega por parametros
        for (Paciente paciente : personaNueva) {
            //Se separa los datos por ; apartir de cada registro 
            subir = subir + paciente.getCedula()+";"+paciente.getNombre()+";"+paciente.getApellido()+";"+paciente.getEdad()+";,"+enfermedades(paciente.getEnfermedades());
            subir = subir +"\n";
        }
        try {
            //Permite crear y subir el String que se lleno apartir del bucle
            FileWriter archivo = new FileWriter(fichero, true);
            try(BufferedWriter almacen  = new BufferedWriter(archivo)){
                almacen.write(subir);
                almacen.close();
            }
            archivo.close();
         } catch (IOException ex) {
            ex.printStackTrace();
         } 
    }
    
    /**
     * Metodo que permite retornar lo que se encuentra en el fichero
     * @return retorna la lista que se lleno apartir del fichero
     */
    public ArrayList<Paciente> obtenerTodoElFichero(){
        try {
            String bfReader;
            BufferedReader base = new BufferedReader(new FileReader(fichero));
            while ((bfReader = base.readLine()) != null) {
                String[] partes;
                String[] partesEnfermedad;
                ArrayList<String> enfermedadPaciente = new ArrayList<>();
                partesEnfermedad = bfReader.split(",");
                partes = bfReader.split(";");
                System.out.println("enfermedad"+partesEnfermedad[0]);
                for (String enfermedad : partesEnfermedad) {
                    //Este condicional se usa apra que no tome la parte 0 de enfermedades
                    //Ya que la 0 son todos los datos según el split
                    if(enfermedad!=partesEnfermedad[0]){
                        enfermedadPaciente.add(enfermedad);
                    }
                }
                //Agrega a la lista lo que se obtiene 
                pacientes.add(new Paciente(partes[0],partes[1],partes[2],Integer.parseInt(partes[3]),enfermedadPaciente));        
            }
            base.close();
        } catch (Exception e) {
            System.out.println("Fichero no encontrado");
        }
        return pacientes;
    }
    /**
     * 
     * Metodo para separar el array List de enfermedades por medio de comas
     * 
     * @param enfermedad la lista de enfermedades para separar por comas
     * @return el resultado de concatenar por comas del array
     */
    public String enfermedades(ArrayList<String> enfermedad){
        String enfermedadPaciente = "";
        for (String padecimiento : enfermedad) {
           enfermedadPaciente += padecimiento+",";
        }
        return enfermedadPaciente;
    }
    
}
