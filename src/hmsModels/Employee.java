package hmsModels;

import hmsControllers.HmsFactory;

import java.io.Serializable;

public class Employee implements Serializable {
	
	private String firstname;
	private String lastname;
	private String gender;
	private String dob;
	private String phone;
	private int salary;
	private int uid;
	private int eid;
	private User user;
	
	
	public Employee(){}
	
	public String toJson(){
		return HmsFactory.toJson(this);
	}
	
	public void setFirstname(String firstname){
		this.firstname = firstname;
	}
	public String getFirstname(){
		return this.firstname;
	}
	public void setLastname(String lastname){
		this.lastname = lastname;
	}
	public String getLastname(){
		return this.lastname;
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
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setSalary(int salary){
		this.salary = salary;
	}
	public int getSalary(){
		return this.salary;
	}
	public int getUid(){
		return this.uid;
	}
	public void setUid(int uid){
		this.uid = uid;
	}
	public int getEid(){
		return eid;
	}
	public void setEid(int eid){
		this.eid = eid;
	}
	public void setUser(User u){
		this.user = u;
	}
	public User getUser(){
		return this.user;
	}
}
