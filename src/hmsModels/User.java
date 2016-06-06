package hmsModels;

import hmsControllers.HmsFactory;

import java.io.Serializable;

public class User implements Serializable {

	private String username;
	private String password;
	private int uid;
	private String type;
	
	public User(){}
	
	public String toJson(){
		return HmsFactory.toJson(this);
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}
	public void setUid(int uid){
		this.uid = uid;
	}
	public int getUid(){
		return this.uid;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	
}
