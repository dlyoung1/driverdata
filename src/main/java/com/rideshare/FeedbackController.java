package com.rideshare;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rideshare.model.Feedback;
import com.rideshare.model.dao.FeedbackDAO;

@Controller
public class FeedbackController {
	
	@Autowired
	private FeedbackDAO feedback;
	
	@RequestMapping("/feedback")
	public String allFeedback() {
		return "feedback";
	}
	
	@RequestMapping(path="feedbackInput", method=RequestMethod.POST)
	public String feedbackInput(@RequestParam String name,
								@RequestParam String source,
								@RequestParam String rating,
								@RequestParam String notes,
								RedirectAttributes redirAttribs) {
		
		List<String> errorMessages = new ArrayList<String>();
		boolean isValid = true;
		if(name.isEmpty() || rating.isEmpty() || notes.isEmpty()) {
			isValid = false;
			errorMessages.add("All fields are required.");
		} else {
			String validChars = "abcedfghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-' ()1234567890\\.";
			if(!usesValidChars(name, validChars)) {
				isValid = false;
				errorMessages.add("The Name input contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			}
			if(!usesValidChars(notes, validChars)) {
				isValid = false;
				errorMessages.add("The Notes section contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			}
			validChars = "12345";
			if(!usesValidChars(rating, validChars)) {
				isValid = false;
				errorMessages.add("The Rating contains invalid characters. Only numbers from 5 (most preferable) to 1 (highly discouraged!) are permitted.");
			} 
		}	
		if(!isValid) {		
			redirAttribs.addFlashAttribute("name", name);
			redirAttribs.addFlashAttribute("notes", notes);
			redirAttribs.addFlashAttribute("rating", rating);
			redirAttribs.addFlashAttribute("errorMessages", errorMessages);
			return "redirect:/feedback";
		}
		
		Feedback review = new Feedback();
		review.setName(name);
		review.setSource(source);
		review.setRating(Integer.valueOf(rating));
		review.setNotes(notes);
		feedback.addFeedback(review);
		return "redirect:feedbackResult";
	}
	
	private boolean usesValidChars(String value, String validChars) {
		boolean hasInvalidChars = false;
		for(int i = 0; i < value.length() && !hasInvalidChars; i++) {
			if(validChars.indexOf(value.substring(i, i + 1)) < 0) {
				hasInvalidChars = true;
			}
		}
		return !hasInvalidChars;
	}
	
	@RequestMapping("feedbackResult")
	public String feedbackResult(ModelMap map) {
		List<Feedback> reviews = feedback.allFeedback();
		List<Feedback> trolls = new ArrayList<>();
		List<Feedback> experts = new ArrayList<>();
		int totalRating = 0;
		int possibleRating = 0;
		for(int i = 0; i < reviews.size(); i++) {
			totalRating += reviews.get(i).getRating();
			possibleRating += 5;
			if(reviews.get(i).getRating() < 3) {
				trolls.add(reviews.get(i));
			} else {
				experts.add(reviews.get(i));
			}
		}
		map.put("possibleRating", possibleRating);
		map.put("totalRating", totalRating);
		map.put("trolls", trolls);
		map.put("experts", experts);
		return "feedbackResult";
	}

}
