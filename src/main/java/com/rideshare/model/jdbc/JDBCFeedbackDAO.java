package com.rideshare.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;

import com.rideshare.model.Feedback;
import com.rideshare.model.dao.FeedbackDAO;

@Controller
public class JDBCFeedbackDAO implements FeedbackDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCFeedbackDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Feedback> allFeedback() {
		List<Feedback> reviews = new ArrayList<>();
		String sqlGetAllFeedback = "SELECT * FROM feedback ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllFeedback);
		while(results.next()) {
			Feedback review = new Feedback();
			review.setFeedbackId(results.getInt("feedback_id"));
			review.setDay(results.getDate("day"));
			review.setName(results.getString("name"));
			review.setSource(results.getString("source"));
			review.setRating(results.getInt("rating"));
			review.setNotes(results.getString("notes"));
			reviews.add(review);
		}
		return reviews;
	}

	@Override
	public void addFeedback(Feedback review) {
		String sqlAddFeedback = "INSERT INTO feedback (name, source, rating, notes) VALUES (?, ?, ?, ?) ";
		this.jdbcTemplate.update(sqlAddFeedback, review.getName(), review.getSource(), review.getRating(), review.getNotes());
	}
	
	

}
