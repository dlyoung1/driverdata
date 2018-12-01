package com.rideshare.model.dao;

import java.util.List;

import com.rideshare.model.Profile;


public interface ProfileDAO {

	public List<Profile> displayAllUsers();
	public Profile getCurrentUser(String name);
	public void addUser(Profile user);
	public void removeUser(long profileId);
	public void updateUser(Profile user, String firstName);
	
}
