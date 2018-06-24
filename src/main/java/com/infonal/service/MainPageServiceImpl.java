package com.infonal.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.infonal.model.User;

// Save Database and controlling at user pojo

public class MainPageServiceImpl implements MainPageService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	// Save users to DB
	public boolean insertUser(User myInsertedUser) {

		try {
			mongoTemplate.insert(myInsertedUser);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	// Delete users from DB
	public boolean removeUser(String id) {

		try {
			mongoTemplate.remove(new Query(Criteria.where("userId").is(id)),
					User.class);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// Update users
	public boolean updateUser(User user) {

		try {
			Update update = new Update();
			update.set("userName", user.getUserName());
			update.set("userSurname", user.getUserSurname());
			update.set("phoneNo", user.getPhoneNo());
			mongoTemplate.updateFirst(
					new Query(Criteria.where("userId").is(user.getUserId())),
					update, User.class);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// Get Users
	public ArrayList<User> getAllUsers() {

		ArrayList<User> savedUser = (ArrayList<User>) mongoTemplate
				.findAll(User.class);

		
		/*for (User user : savedUser) {
			System.out.println(" find - savedUser : " + user.getUserName()
					+ " " + user.getUserSurname());
		}*/

		return savedUser;
	}

}
