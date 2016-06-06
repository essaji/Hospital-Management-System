package jaxControl;

import hmsControllers.HmsFactory;
import hmsModels.Patient;

import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("")
public class PatientServices {

	HmsFactory hms = new HmsFactory();
	
	@GET
	@Path("patient/doctor/{uid}")
	@Produces("application/json")
	public String getDocPatientsJson(@PathParam("uid") int uid){
		try {
			return hms.getDocPatientsJson(uid);
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
	@GET
	@Path("patient/all")
	@Produces("application/json")
	public String getPatientsJson(){
		return hms.getPatientsJson();
	}
	
	@GET
	@Path("patient/{pid}")
	@Produces("application/json")
	public String getPatientJson(@PathParam("pid") int pid){
		try {
			return hms.getPatientJson(pid);
		} catch (SQLException e) {
			Response.serverError();
			
			return e.getMessage();
		}
	}
	
	@GET
	@Path("patient/nurse/{uid}")
	@Produces("application/json")
	public String getNursePatientsJson(@PathParam("uid") int uid){
		try {
			return hms.getNursePatientsJson(uid);
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
	@POST
	@Path("patient")
	@Produces("application/json")
	public String addPatient(@FormParam("fullname") String name,
			@FormParam("catid") int catid,
			@FormParam("dob") String dob,
			@FormParam("gender") String gender){
		
		Patient patient = new Patient();
		patient.setCatid(catid);
		patient.setDob(dob);
		patient.setName(name);
		patient.setGender(gender);
		
		try {
			return hms.addPatient(patient);
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
		
		
	}
}
