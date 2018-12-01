package com.rideshare.model.dao;

import java.util.List;

import com.rideshare.model.Feedback;

public interface FeedbackDAO {

	public List<Feedback> allFeedback();
	public void addFeedback(Feedback review);
	
}
