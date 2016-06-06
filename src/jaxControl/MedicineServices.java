package jaxControl;

import java.sql.SQLException;
import hmsControllers.*;
import hmsModels.Medicine;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("")
public class MedicineServices {
	
	HmsFactory hms = new HmsFactory();

	@GET
	@Path("medicine/all")
	@Produces("application/json")
	public String getMedicinesJson(){
		try {
			return hms.getMedicinesJson();
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}

	
	@POST
	@Path("medicine")
	@Produces("application/json")
	public String addMedicne(@FormParam("medName") String name, @FormParam("price") int price){
		try {
			
			Medicine med = new Medicine();
			med.setName(name);
			med.setPrice(price);
			
			return hms.addMedicne(med);
			
		} catch (SQLException e) {
			Response.serverError();
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@PUT
	@Path("medicine/{mid}")
	@Produces("application/json")
	public String updateMedicine(@PathParam("mid") int mid, @FormParam("medName") String name, @FormParam("price") int price){
		try {
			Medicine med = new Medicine();
			med.setName(name);
			med.setPrice(price);
			med.setMid(mid);
			
			return hms.updateMedicine(med);
			
		} catch (SQLException e) {
			Response.serverError();
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	
	@GET
	@Path("medicine/{mid}")
	@Produces("application/json")
	public String getMedicine(@PathParam("mid") int mid){
		try {
			return hms.getMedicine(mid);
			
		} catch (SQLException e) {
			Response.serverError();
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@DELETE
	@Path("medicine/{mid}")
	@Produces("text/plain")
	public String removeMedicine(@PathParam("mid") int mid){
		try {
			
			hms.removeMedicine(mid);
			return "Deletion success!";
			
		} catch (SQLException e) {
			Response.serverError();
			e.printStackTrace();
			return e.getMessage();
		}
	}
}