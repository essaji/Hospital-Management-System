package hmsModels;

import hmsControllers.HmsFactory;

import java.io.Serializable;

public class Patient implements Serializable {

	private int pid;
	private int did;
	private int catid;
	private String name;
	private String gender;
	private String dob;
	private String type;
	private Category category;
	private Doctor doctor;
	
	public Patient(){}
	
	public String toJson(){
		return HmsFactory.toJson(this);
	}
	
	
	public void setDoctor(Doctor doctor){
		this.doctor = doctor;
	}
	public Doctor getDoctor(){
		return this.doctor;
	}
	public void setCategory(Category category){
		this.category = category;
	}
	public Category getCategory(){
		return this.category;
	}
	
	public void setCatid(int catid){
		this.catid = catid;
	}
	
	public int getCatid(){
		return this.catid;
	}
	
	public void setPid(int pid){
		this.pid = pid;
	}
	public int getPid(){
		return this.pid;
	}
	public void setDid(int did){
		this.did = did;
	}
	public int getDid(){
		return this.did;
	}
	
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setGender(String gender){
		this.gender = gender;
	}
	
	public String getGender(){
		return this.gender;
	}
	
	public void setDob(String dob){
		this.dob = dob;
	}
	public String getDob(){
		return this.dob;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	
}
