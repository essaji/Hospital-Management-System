package jaxControl;
import java.sql.SQLException;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import hmsControllers.*;
import hmsModels.Category;

@Path("")
public class CategoryServices {

	
	HmsFactory hms = new HmsFactory();
	
	@GET
	@Path("category/all")
	@Produces("application/json")
	public String getCategoriesJson(){
		try {
			return hms.getCategoriesJson();
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
	@POST
	@Path("category")
	@Produces("application/json")
	public String addCategory(@FormParam("catName") String name){
		try {
			
			Category cat = new Category();
			cat.setName(name);
			
			return hms.addCategory(cat);
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
	@GET
	@Path("category/{catid}")
	@Produces("application/json")
	public String getCategory(@PathParam("catid") int catid){
		try {
			
			return hms.getCategory(catid);
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
	@PUT
	@Path("category/{catid}")
	@Produces("application/json")
	public String updateCategory(@PathParam("catid") int catid, @FormParam("catName") String name){
		try {
				Category cat = new Category();
				cat.setCatid(catid);
				cat.setName(name);
			return hms.updateCategory(cat);
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
	@DELETE
	@Path("category/{catid}")
	@Produces("text/plain")
	public String updateCategory(@PathParam("catid") int catid){
		try {
			
			hms.deleteCategory(catid);
			return "Success!";
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
}
