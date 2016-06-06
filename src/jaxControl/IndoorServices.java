package jaxControl;

import hmsControllers.HmsFactory;

import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("")

public class IndoorServices {

	HmsFactory hms = new HmsFactory();
	
	@GET
	@Path("indoor/all")
	@Produces("application/json")
	public String getIndoorsJson(){
		return hms.getIndoorsJson();
	}
	@GET
	@Path("indoor/{ipid}")
	@Produces("application/json")
	public String getIndoorJson(@PathParam("ipid") int ipid){
		try {
			return hms.getIndoorJson(ipid);
			
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
	
	//room assignment to indoors
	@PUT
	@Path("indoor/{ipid}/room")
	@Produces("application/json")
	public String updateIndoorRid(@PathParam("ipid") int ipid, @FormParam("rid") int rid){
		try {
			
			
			return hms.updateIndoorRid(ipid,rid);
			
		} catch (SQLException e) {
			Response.serverError();
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
