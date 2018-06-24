package com.infonal.service;

import java.util.ArrayList;

import com.infonal.model.User;

//CRUD Interface

public interface MainPageService {
	
	public boolean insertUser(User myInsertedUser);
	
	public boolean updateUser(User user);
	
	public ArrayList<User> getAllUsers();
	
	public boolean removeUser(String id);

}
