package hmsModels;
import hmsControllers.HmsFactory;

import java.io.Serializable;

public class Indoor implements Serializable {

	private int ipid;
	private String disease;
	private int pid;
	private int rid;
	private Patient patient;
	private String status;
	private Room room;
	
	public Indoor(){}
	
	public String toJson(){
		return HmsFactory.toJson(this);
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setIpid(int ipid){
		this.ipid = ipid;
	}
	public int getIpid(){
		return this.ipid;
	}
	public void setPid(int pid){
		this.pid = pid;
	}
	public int getPid(){
		return this.pid;
	}
	public void setDisease(String disease){
		this.disease = disease;
	}
	public String getDisease(){
		return this.disease;
	}
	public void setPatient(Patient patient){
		this.patient = patient;
	}
	public Patient getPatient(){
		return this.patient;
	}
	
	public void setRid(int rid){
		this.rid = rid;
	}
	public int getRid(){
		return this.rid;
	}
	
	public void setRoom(Room room){
		this.room = room;
	}
	public Room getRoom(){
		return this.room;
	}
	
}
