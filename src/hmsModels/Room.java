package hmsModels;

import hmsControllers.HmsFactory;

import java.io.Serializable;

public class Room implements Serializable {

	private int rid;
	private int totalbeds;
	private int nid;
	private int availablebeds;
	private Nurse nurse;
	
	public Room (){}
	
	public String toJson(){
		return HmsFactory.toJson(this);
	}
	
	public void setAvailableBeds(int availablebeds){
		this.availablebeds = availablebeds;
	}
	public int getAvailableBeds(){
		return this.availablebeds;
	}
	
	public void setRid(int rid){
		this.rid = rid;
	}
	public int getRid(){
		return this.rid;
	}
	public void setTotalBeds(int totalbeds){
		this.totalbeds = totalbeds;
	}
	public int getTotalbeds(){
		return this.totalbeds;
	}
	public void setNid(int nid){
		this.nid = nid;
	}
	public int getNid(){
		return this.nid;
	}
	public void setNurse(Nurse nurse){
		this.nurse = nurse;
	}
	public Nurse getNurse(){
		return this.nurse;
	}
}
