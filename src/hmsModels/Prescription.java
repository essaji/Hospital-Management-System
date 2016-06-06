package hmsModels;

import hmsControllers.HmsFactory;

import java.io.Serializable;

public class Prescription implements Serializable {
	
	private int prid;
	private int pid;
	private int mid;
	private int did;
	private String dosage;
	
	private Medicine medicine;
	private Patient patient;
	private Doctor doctor;
	
	public Prescription(){}
	
	public String toJson(){
		return HmsFactory.toJson(this);
	}
	
	public void setPrid(int prid){
		this.prid = prid;
	}
	public int getPrid(){
		return this.prid;
	}
	public void setPid(int pid){
		this.pid = pid;
	}
	public int getPid(){
		return this.pid;
	}
	public void setMid(int mid){
		this.mid = mid;
	}
	public int getMid(){
		return this.mid;
	}
	public void setDid(int did){
		this.did = did;
	}
	public int getDid(){
		return this.did;
	}
	
	public void setDosage(String dosage){
		this.dosage	= dosage;
	}
	public String getDosage(){
		return this.dosage;
	}
	
	
	public void setMedicine(Medicine medicine){
		this.medicine = medicine;
	}
	public Medicine getMedicine(){
		return this.medicine;
	}
	
	public void setPatient(Patient patient){
		this.patient = patient;
	}
	public Patient getPatient(){
		return this.patient;
	}
	
	public void setDoctor(Doctor doctor){
		this.doctor = doctor;
	}
	public Doctor getDoctor(){
		return this.doctor;
	}
	
}
