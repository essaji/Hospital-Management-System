package jaxControl;

import hmsControllers.HmsFactory;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("")
public class NurseServices {

	
	HmsFactory hms = new HmsFactory();
	
	@GET
	@Path("nurse/all")
	@Produces("application/json")
	public String getNursesJson(){
		return hms.getNursesJson();
	}
	@GET
	@Path("nurse/{nid}")
	@Produces("application/json")
	public String getNurseJson(@PathParam("nid") int nid){
		try {
			return hms.getNurseJson(nid);
		} catch (SQLException e) {
			
			Response.serverError();
			return e.getMessage();
		}
	}
}
