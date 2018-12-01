package com.rideshare.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.rideshare.model.Profile;
import com.rideshare.model.dao.ProfileDAO;

@Component
public class JDBCProfileDAO implements ProfileDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCProfileDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Profile> displayAllUsers() {
		List<Profile> users = new ArrayList<Profile>();
		String sqlDisplayCurrentUser = "SELECT * FROM profile ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlDisplayCurrentUser);
		while(results.next()) {
			Profile user = new Profile();
			user.setProfileId(results.getInt("profile_id"));
			user.setFirstName(results.getString("first_name"));
			user.setLastName(results.getString("last_name"));
			user.setEmail(results.getString("email"));
			users.add(user);
		}
		return users;
	}
	
	@Override
	public Profile getCurrentUser(String name) {
		Profile user = new Profile();
		String sqlDisplayCurrentUser = "SELECT * FROM profile WHERE first_name = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlDisplayCurrentUser, name);
		while(results.next()) {
			user.setProfileId(results.getInt("profile_id"));
			user.setFirstName(results.getString("first_name"));
			user.setLastName(results.getString("last_name"));
			user.setEmail(results.getString("email"));
		}
		return user;
	}

	@Override
	public void addUser(Profile user) {
		String sqlAddUser = "INSERT INTO profile (first_name, last_name, email) "
						+ "VALUES (?, ?, ?) ";
//		user.setProfileId(getNextUserId());
		this.jdbcTemplate.update(sqlAddUser, user.getFirstName(), user.getLastName(), user.getEmail());
	}
	
	@Override
	public void removeUser(long profileId) {
		String sql = "DELETE FROM profile WHERE profile_id = ? ";
		this.jdbcTemplate.update(sql, profileId);
	}
	
	@Override
	public void updateUser(Profile user, String firstName) {
		String sql = "UPDATE profile SET first_name = ?, last_name = ?, email = ? WHERE first_name = ? ";
		this.jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), firstName);
	}
	
//	private int getNextUserId() {
//		String sql = "SELECT nextval('profile_profile_id_seq') ";
//		SqlRowSet result = this.jdbcTemplate.queryForRowSet(sql);
//		result.next();
//		return result.getInt(1);
//	}

	
	
}
