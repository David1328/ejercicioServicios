/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.ejercicioServicios.Controller;

import co.edu.unicundi.ejemploservidor.BaseDeDatos.PacientesLogica;
import co.edu.unicundi.ejemploservidor.Dato.Paciente;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author David
 */
@Path("pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacientesController {
    private static ArrayList<Paciente> pacientes = new PacientesLogica().obtenerTodoElFichero();
    
    @GET
    @Path("pacienteRegistros")
    public ArrayList<Paciente> obtenerPacientes(){
        return pacientes;
    }
    
    @GET
    @Path("obtenerPaciente/{cedula}")
    public Paciente obtenerPaciente(@PathParam("cedula") String cedula){
        Paciente persona = new Paciente();
        //pacientes = new PacientesLogica().obtenerTodoElFichero();
        for (Paciente paciente : pacientes) {
            if(paciente.getCedula().equals(cedula)){
                persona = paciente;
                break;
            }
        }
        return persona;
    }
    
    @POST
    @Path("agregarPaciente")
    public String agregarPaciente(Paciente pacieteNuevo){
        boolean bandera = false;
        for (Paciente paciente : pacientes) {
            if(paciente.getCedula().equals(pacieteNuevo.getCedula())){
                bandera = true;
                break;
            }
        }
        if(!bandera){
            pacientes.add(pacieteNuevo);
            new PacientesLogica().agregarPaciente(pacientes);
            return "Agregado con exito";
        }else{
            return "Ya existe";
        }
    }
    
    @DELETE 
    @Path("eliminarPaciente/{cedula}")
    public String eliminarPaciente(@PathParam("cedula") String cedula){
        for (Paciente paciente : pacientes) {
            if(paciente.getCedula().equals(cedula)){
                pacientes.remove(paciente);
                new PacientesLogica().agregarPaciente(pacientes);
                return "Se elimino el paciente con de numero cedula "+cedula;
            }
        }
        return "No se encontro el paciente con cedula "+cedula;
    }
    
    @PUT
    @Path("actualizarPaciente")
    public String actualizarPaciente(Paciente pacienteNuevosDatos){
        for (Paciente paciente : pacientes) {
            if(paciente.getCedula().equals(pacienteNuevosDatos.getCedula())){
                paciente.setNombre(pacienteNuevosDatos.getNombre());
                paciente.setApellido(pacienteNuevosDatos.getApellido());
                paciente.setEdad(pacienteNuevosDatos.getEdad());
                new PacientesLogica().agregarPaciente(pacientes);
                return "El paciente se actualizo con exito";
            }
        }
        return "El paciente no existe para actualizar datos";
    }
    
}
