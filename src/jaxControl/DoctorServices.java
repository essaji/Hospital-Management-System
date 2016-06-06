package jaxControl;

import hmsControllers.HmsFactory;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("")
public class DoctorServices {

	HmsFactory hms = new HmsFactory();
	
	@GET
	@Path("doctor/all")
	@Produces("application/json")
	public String getDoctorsJson(){
		return hms.getDoctorsJson();
	}
	
	
	@GET
	@Path("doctor/{did}")
	@Produces("application/json")
	public String getDoctorJson(@PathParam("did") int did){
		try {
			return hms.getDoctorJson(did);
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
}
