package com.infonal.model;

import org.bson.util.annotations.NotThreadSafe;
import org.springframework.data.annotation.Id;

// Util class'i.POJO class.

public class User {
	
	@Id
	private String userId;
	
	private String userName;
	private String userSurname;
	private String phoneNo;
	
	public User(String userId,String userName,String userSurname,String phoneNo) {
		setUserId(userId);
		setUserName(userName);
		setUserSurname(userSurname);
		setPhoneNo(phoneNo);
	}
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSurname() {
		return userSurname;
	}
	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
	

}
