package hmsModels;

import hmsControllers.HmsFactory;

import java.io.Serializable;

public class Medicine implements Serializable {

	
	private int mid;
	private String name;
	private int price;
	
	public Medicine(){}
	
	public String toJson(){
		return HmsFactory.toJson(this);
	}
	
	
	
	public void setMid(int mid){
		this.mid = mid;
	}
	public int getMid(){
		return this.mid;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public int getPrice(){
		return this.price;
	}
}
