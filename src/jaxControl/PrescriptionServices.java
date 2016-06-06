package jaxControl;

import hmsControllers.HmsFactory;
import hmsModels.Indoor;
import hmsModels.Prescription;

import java.sql.SQLException;
import java.util.ArrayList;

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
public class PrescriptionServices {

	HmsFactory hms = new HmsFactory();
	
	@GET
	@Path("prescription/all")
	@Produces("application/json")
	public String getPrescriptionsJson(){
		try {
			return hms.getPrescriptionsJson();
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
	@GET
	@Path("prescription/{prid}")
	@Produces("application/json")
	public String getPrescriptionsJson(@PathParam("prid") int prid){
		try {
			return hms.getPrescriptionsJson(prid);
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
	@GET
	@Path("prescription/doctor/{uid}")
	@Produces("application/json")
	public String getDocPresJson(@PathParam("uid") int uid){
		try {
			return hms.getDocPresJson(uid);
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
	@GET
	@Path("prescription/patient/{pid}")
	@Produces("application/json")
	public String getPatientPresJson(@PathParam("pid") int pid){
		try {
			return hms.getPatientPresJson(pid);
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
	
	@POST
	@Path("prescription/patient/{pid}")
	@Produces("application/json")
	public String setPatientPres(@PathParam("pid") int pid,
			@FormParam("mid1") int mid1,
			@FormParam("mid2") int mid2,
			@FormParam("mid3") int mid3,
			@FormParam("dosage1") String dosage1,
			@FormParam("dosage2") String dosage2,
			@FormParam("dosage3") String dosage3,
			@FormParam("uid") int uid,
			@FormParam("patType") String type,
			@FormParam("disease") String disease){
		try {
			
			if(type!=null){
				
				if(type.equals("indoor")){
					Indoor indo = new Indoor();
					indo.setDisease(disease);
					indo.setPid(pid);
					indo.setStatus("admitted");
					hms.addIndoor(indo);
					hms.updatePatient(pid, uid, type);
				}else
					hms.updatePatient(pid, uid, type);
				
				
			}
			
			ArrayList<Prescription> presList = new ArrayList<Prescription>();
			
			Prescription p1 = new Prescription(), p2 =new Prescription(), p3 = new Prescription();
			p1.setPid(pid);
			p1.setMid(mid1);
			p1.setDosage(dosage1);
			if(dosage1!=null && !dosage1.equals("")){
				hms.addPres(p1,uid);
				presList.add(p1);
			}
			
			
			p2.setPid(pid);
			p2.setMid(mid2);
			p2.setDosage(dosage2);
			if(dosage2!=null && !dosage2.equals("")){
				hms.addPres(p2,uid);
				presList.add(p2);
			}
			
			p3.setPid(pid);
			p3.setMid(mid3);
			p3.setDosage(dosage3);
			if(dosage3!=null && !dosage3.equals("")){
				hms.addPres(p3,uid);
				presList.add(p3);
			}
			
			
			return HmsFactory.toJson(presList);
		} catch (SQLException e) {
			Response.serverError();
			return e.getMessage();
		}
	}
	
	@PUT
	@Path("prescription/{prid}")
	@Produces("application/json")
	public String updatePres(@PathParam("prid") int prid,
			@FormParam("dosage") String dosage,
			@FormParam("mid") int mid,
			@FormParam("uid") int uid){
		try {
			Prescription pres = new Prescription();
			pres.setPrid(prid);
			pres.setMid(mid);
			pres.setDosage(dosage);
			
			return hms.updatePres(pres,uid);
		} catch (SQLException e) {
			Response.serverError();
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@DELETE
	@Path("prescription/{prid}")
	@Produces("text/plain")
	public String removePres(@PathParam("prid") int prid){
		try {
			
			hms.removePres(prid);
			return "Deletion Success!";
			
		} catch (SQLException e) {
			Response.serverError();
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
