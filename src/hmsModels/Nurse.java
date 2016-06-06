package hmsModels;

import hmsControllers.HmsFactory;

import java.io.Serializable;

public class Nurse implements Serializable {

	private int nid;
	private int eid;
	private String experience;
	private Employee employee;
	
	public Nurse(){}
	
	public String toJson(){
		return HmsFactory.toJson(this);
	}
	
	public void setNid(int nid){
		this.nid = nid;
	}
	public int getNid(){
		return this.nid;
	}
	public void setEid(int eid){
		this.eid = eid;
	}
	public int getEid(){
		return this.eid;
	}
	public void setExperience(String experience){
		this.experience = experience;
	}
	public String getExperience(){
		return this.experience;
	}
	public void setEmployee(Employee employee){
		this.employee = employee;
	}
	public Employee getEmployee(){
		return this.employee;
	}
	
	public String getFullName(){
		return employee.getFirstname()+" "+employee.getLastname();
	}
	
}
