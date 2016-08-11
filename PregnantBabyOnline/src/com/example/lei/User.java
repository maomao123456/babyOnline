package com.example.lei;

public class User {

	public String userAccount;
	public String userPassword;
	
	public void setUserAccount(String userAccount){
		this.userAccount = userAccount;
	}
	public String getUserAccount(){
		return userAccount;
	}
	
	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}
	public String getUserPassword(){
		return userPassword;
	}
	
	public User(String userAccount, String userPassword){
		super();
		this.userAccount = userAccount;
		this.userPassword = userPassword;
	}
	
	public String toString(){
		return this.userAccount + "," + this.userPassword;
	}
}
