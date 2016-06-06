package jaxControl;

import hmsControllers.HmsFactory;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
@Path("")
public class RoomServices {

	HmsFactory hms = new HmsFactory();
	
	@GET
	@Path("room/all")
	@Produces("application/json")
	public String getRoomsJson(){
		return hms.getRoomsJson();
	}
	@GET
	@Path("room/{rid}")
	@Produces("application/json")
	public String getRoomJson(@PathParam("rid") int rid){
		try {
			return hms.getRoomJson(rid);
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
	@GET
	@Path("room/nurse/{uid}")
	@Produces("application/json")
	public String getNurseRoomsJson(@PathParam("uid") int uid){
		try {
			return hms.getNurseRoomsJsonByUid(uid);
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
}
