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
    
    private static ArrayList<Paciente> pacientes = new ArrayList<>();
    private static String fichero = "C:\\Users\\David\\Desktop\\Ingenieria de sistemas\\Linea de profundizacion 2\\ejercicioServicios\\src"+File.separator+"pacientes.txt";
    
    public void agregarPaciente(ArrayList<Paciente> personaNueva){
        File prueba = new File(fichero);
        prueba.delete();
        String subir="";
        for (Paciente paciente : personaNueva) {
            subir = subir + paciente.getCedula()+";"+paciente.getNombre()+";"+paciente.getApellido()+";"+paciente.getEdad()+";"+enfermedades(paciente.getEnfermedades());
            subir = subir +"\n";
        }
        try {
            FileWriter archivo = new FileWriter(fichero, true);
            //String subir;
            try(BufferedWriter almacen  = new BufferedWriter(archivo)){
                //subir = personaNueva.getCedula()+";"+personaNueva.getNombre()+";"+personaNueva.getApellido()+";"+personaNueva.getEdad();
                almacen.write(subir);
                almacen.close();
            }
            archivo.close();
         } catch (IOException ex) {
            ex.printStackTrace();
         } 
    }
    
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
                    if(enfermedad!=partesEnfermedad[0]){
                        enfermedadPaciente.add(enfermedad);
                    }
                }
                pacientes.add(new Paciente(partes[0],partes[1],partes[2],Integer.parseInt(partes[3]),enfermedadPaciente));        
            }
            base.close();
        } catch (Exception e) {
            System.out.println("Fichero no encontrado");
        }
        return pacientes;
    }
    public String enfermedades(ArrayList<String> enfermedad){
        String enfermedadPaciente = "";
        for (String padecimiento : enfermedad) {
           enfermedadPaciente += padecimiento+",";
        }
        return enfermedadPaciente;
    }
    
}
