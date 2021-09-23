/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.ejercicioServicios.Controller;

import co.edu.unicundi.ejemploservidor.BaseDeDatos.PacientesLogica;
import co.edu.unicundi.ejemploservidor.Dato.Paciente;
import java.util.ArrayList;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * Clase que va a permitir 
 * @author David
 */
@Path("pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacientesController {
    private static final ArrayList<Paciente> pacientes = new PacientesLogica().obtenerTodoElFichero();
    
    /** 
    * Servicio que obtiene todos los pacientes
    * que existen en el fichero
    * @return Devuelve una lista con todos los pacientes en el fichero
    */
    @GET
    @Path("pacienteRegistros")
    public ArrayList<Paciente> obtenerPacientes(){
        return pacientes;
    }
    
    /**
    * Servicio que obtiene el paciente por cedula
    * Con una vaidacion de min 2 max 30 caracteres
    * Donde solo se admiten numeros en el String
    * @param cedula el numero el cual se va a buscar
    * @return devuelve el resultado de la busqueda por cedula
    */
    @GET
    @Valid
    @Path("obtenerPaciente/{cedula}")
    public Response obtenerPaciente(@NotNull @Size(min=6, max=10) @Pattern(regexp="^([0-9])*$") @PathParam("cedula") String cedula){
        Paciente persona = new Paciente();
        for (Paciente paciente : pacientes) {
            if(paciente.getCedula().equals(cedula)){
                persona = paciente;
                return Response.status(Response.Status.OK).entity(persona).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
    }
    
    
    /**
    * Servicios recibe el paciente a agregar
    * Con validaciones que se hicieron el pojo
    * @param pacieteNuevo recibe un paciente para agregar a la lista
    * @return el resultado del Response según el metodo
    */
    @POST
    @Valid
    @Path("agregarPaciente")
    public Response agregarPaciente(@Valid Paciente pacieteNuevo){
        for (Paciente paciente : pacientes) {
            if(paciente.getCedula().equals(pacieteNuevo.getCedula())){
                return Response.status(Response.Status.CONFLICT).entity("Ya existe").build();
            }
        }
        pacientes.add(pacieteNuevo);
        new PacientesLogica().agregarPaciente(pacientes);
        return Response.status(Response.Status.CREATED).entity("Agregado con exito").build();
    }
    /**
    * Servicio que elimina el paciente por cedula
    * Con una vaidacion de min 2 max 30 caracteres
    * Donde solo se admiten numeros en el String
    * @param cedula recibe una cedula para eliminarla dentro del arrayList y el fichero
    * @return retorna el resultado del Response según lo que haga el metodo
    */
    @DELETE 
    @Valid
    @Path("eliminarPaciente/{cedula}")
    public Response eliminarPaciente(@NotNull @Size(min=6, max=10) @Pattern(regexp="^([0-9])*$") @PathParam("cedula") String cedula){
        for (Paciente paciente : pacientes) {
            if(paciente.getCedula().equals(cedula)){
                pacientes.remove(paciente);
                new PacientesLogica().agregarPaciente(pacientes);
                return Response.noContent().entity("Se elimino el paciente con de numero cedula "+cedula).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado, cedula: "+cedula).build();
    }
    
    /**
    * Permite actualiza un paciente 
    * Con validaciones que se hicieron el pojo
    * @param pacienteNuevosDatos  recibe un paciente con los datos para actualzar donde se
    * buscara según la cedul para poder ser actulizado
    * @return retorna la reesta segun los pasos que haga el metodo
    */
    @PUT
    @Valid
    @Path("actualizarPaciente")
    public Response actualizarPaciente(@Valid Paciente pacienteNuevosDatos){
        for (Paciente paciente : pacientes) {
            if(paciente.getCedula().equals(pacienteNuevosDatos.getCedula())){
                paciente.setNombre(pacienteNuevosDatos.getNombre());
                paciente.setApellido(pacienteNuevosDatos.getApellido());
                paciente.setEdad(pacienteNuevosDatos.getEdad());
                paciente.setEnfermedades(pacienteNuevosDatos.getEnfermedades());
                new PacientesLogica().agregarPaciente(pacientes);
                return Response.ok().entity("El paciente se actualizo con exito").build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("El paciente no existe para actualizar datos").build();
    }
    
}
