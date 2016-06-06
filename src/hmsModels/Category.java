package hmsModels;

import hmsControllers.HmsFactory;

import java.io.Serializable;

public class Category implements Serializable {

	private int catid;
	private String name;
	
	
	public String toJson(){
		return HmsFactory.toJson(this);
	}
	
	public Category(){}
	
	public void setCatid(int catid){
		this.catid = catid;
	}
	public int getCatid(){
		return this.catid;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
}
