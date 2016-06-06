package hmsModels;

import hmsControllers.HmsFactory;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;


@XmlRootElement(name = "doctor")
public class Doctor implements Serializable {

	
	private int catid;
	private int did;
	private int eid;
	private Employee employee;
	private Category category;
	
	public Doctor(){}

	public void setCatid(int catid){
		this.catid = catid;
	}

	public int getCatid(){
		return this.catid;
	}

	public void setDid(int did){
		this.did = did;
	}

	public int getDid(){
		return this.did;
	}

	public void setEid(int eid){
		this.eid = eid;
	}

	public int getEid(){
		return this.eid;
	}

	public void setEmployee(Employee emp){
		this.employee = emp;
	}

	public Employee getEmployee(){
		return this.employee;
	}

	public void setCategory(Category cat){
		this.category = cat;
	}

	public Category getCategory(){
		return this.category;
	}
	
	public String toJson(){
		return HmsFactory.toJson(this);
	}
}
