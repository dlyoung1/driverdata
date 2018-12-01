package com.rideshare;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rideshare.model.Profile;
import com.rideshare.model.Vehicle;
import com.rideshare.model.dao.ProfileDAO;
import com.rideshare.model.dao.VehicleDAO;

@Controller
public class SettingsController {
	
	@Autowired
	private ProfileDAO profile;
	@Autowired
	private VehicleDAO vehicle;
	
	@RequestMapping("/settings")
	public String displaySettings() {
		return "settings";
	}

	@RequestMapping("/about")
	public String displayAbout() {
		return "about";
	}
	
//	ADD NEW USER
	@RequestMapping("/addNewUser")
	public String addNewUser() {
		return "addUser";
	}
	
	@RequestMapping(path="/addUser", method=RequestMethod.POST)
	public String inputUser(HttpSession session,
							RedirectAttributes redirAttribs,
							@RequestParam String firstName,
							@RequestParam String lastName,
							@RequestParam String email) {
		
		List<String> errorMessages = new ArrayList<String>();
		boolean isValid = true;
		if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
			isValid = false;
			errorMessages.add("All fields are required.");
		} else {
			String validChars = "abcedfghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-' ";
			Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email);
			if(!usesValidChars(firstName, validChars)) {
				isValid = false;
				errorMessages.add("First name contains invalid characters. Only letters, dashes, apostrophes, and spaces are allowed.");
			}
			if(!usesValidChars(lastName, validChars)) {
				isValid = false;
				errorMessages.add("Last name contains invalid characters. Only letters, dashes, apostrophes, and spaces are allowed.");
			} 
			if(!matcher.find()) {
				isValid = false;
				errorMessages.add("Please enter a valid email address.");
			}
		}	
		if(!isValid) {		
			redirAttribs.addFlashAttribute("firstName", firstName);
			redirAttribs.addFlashAttribute("lastName", lastName);
			redirAttribs.addFlashAttribute("email", email);
			redirAttribs.addFlashAttribute("errorMessages", errorMessages);
			return "redirect:/addNewUser";
		}
		Profile user = new Profile();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		profile.addUser(user);
		session.setAttribute("userName", firstName);
		return "redirect:/addNewVehicle";
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
		
//	DELETE A USER
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpSession session) {
		profile.removeUser((int)session.getAttribute("profileId"));
		return "redirect:/";
	}
	
//	UPDATE USER
	@RequestMapping("/updateUser")
	public String updateUser(ModelMap map) {
		map.put("users", profile.displayAllUsers());
		return "updateUser";
	}
	
	@RequestMapping(path="/updateUserInput", method=RequestMethod.POST)
	public String updateUserInput(HttpSession session,
								RedirectAttributes redirAttribs,
								@RequestParam String firstName,
								@RequestParam String newFirstName,
								@RequestParam String lastName,
								@RequestParam String email) {
		List<String> errorMessages = new ArrayList<String>();
		boolean isValid = true;
		if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
			isValid = false;
			errorMessages.add("All fields are required.");
		} else {
			String validChars = "abcedfghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-' ";
			Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email);
			if(!usesValidChars(newFirstName, validChars)) {
				isValid = false;
				errorMessages.add("First name contains invalid characters. Only letters, dashes, apostrophes, and spaces are allowed.");
			}
			if(!usesValidChars(lastName, validChars)) {
				isValid = false;
				errorMessages.add("Last name contains invalid characters. Only letters, dashes, apostrophes, and spaces are allowed.");
			} 
			if(!matcher.find()) {
				isValid = false;
				errorMessages.add("Please enter a valid email address.");
			}
		}	
		if(!isValid) {		
			redirAttribs.addFlashAttribute("firstName", firstName);
			redirAttribs.addFlashAttribute("lastName", lastName);
			redirAttribs.addFlashAttribute("email", email);
			redirAttribs.addFlashAttribute("errorMessages", errorMessages);
			return "redirect:/updateUser";
		}
		
		
		Profile user = new Profile();
		user.setFirstName(newFirstName);
		user.setLastName(lastName);
		user.setEmail(email);
		profile.updateUser(user, firstName);
		session.setAttribute("userName", newFirstName);
		return "redirect:/home";
	}
	
//	SWITCH USER
	@RequestMapping("/switchUser")
	public String switchUser(ModelMap map) {
		map.put("users", profile.displayAllUsers());
		return "switchUser";
	}
	
	@RequestMapping(path="/switchUserInput", method=RequestMethod.POST)
	public String switchUserInput(@RequestParam String userName, HttpSession session) {
		session.setAttribute("userName", userName);
		return "redirect:/switchVehicle";
	}
	
//	ADD NEW VEHICLE
	@RequestMapping("/addNewVehicle")
	public String addNewVehicle() {
		return "addVehicle";
	}
	
	@RequestMapping(path="/addVehicle", method=RequestMethod.POST)
	public String inputVehicle(@RequestParam String make,
							@RequestParam String model,
							@RequestParam String year,
							@RequestParam String color,
							@RequestParam String image,
							HttpSession session,
							RedirectAttributes redirAttribs) {
		
		List<String> errorMessages = new ArrayList<String>();
		boolean isValid = true;
		if(make.isEmpty() || model.isEmpty() || year.isEmpty() || color.isEmpty()) {
			isValid = false;
			errorMessages.add("All fields (except image) are required.");
		} else {
			String validChars = "abcedfghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-' ()1234567890\\.";
			if(!usesValidChars(make, validChars)) {
				isValid = false;
				errorMessages.add("The Make contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			}
			if(!usesValidChars(model, validChars)) {
				isValid = false;
				errorMessages.add("The Model contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			}
			if(!usesValidChars(color, validChars)) {
				isValid = false;
				errorMessages.add("The Color contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			} 
			Matcher matcher = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", Pattern.CASE_INSENSITIVE).matcher(image);
			if(!image.isEmpty()) {
				if(!matcher.find()) {
					isValid = false;
					errorMessages.add("Please enter a valid Image URL.");
				}
			}
			validChars = "1234567890";
			if(!usesValidChars(year, validChars)) {
				isValid = false;
				errorMessages.add("The Year contains invalid characters. Only numbers are permitted.");
			}
			if(year.length() != 4) {
				isValid = false;
				errorMessages.add("The Year must be 4 digits in length (e.g., '2001').");
			}
		}	
		if(!isValid) {		
			redirAttribs.addFlashAttribute("make", make);
			redirAttribs.addFlashAttribute("model", model);
			redirAttribs.addFlashAttribute("year", year);
			redirAttribs.addFlashAttribute("color", color);
			redirAttribs.addFlashAttribute("image", image);
			redirAttribs.addFlashAttribute("errorMessages", errorMessages);
			return "redirect:/addNewVehicle";
		}
		
		int profileId = profile.getCurrentUser((String)session.getAttribute("userName")).getProfileId();
		Vehicle newVehicle = new Vehicle();
		newVehicle.setProfileId(profileId);
		newVehicle.setMake(make);
		newVehicle.setModel(model);
		newVehicle.setYear(Integer.valueOf(year));
		newVehicle.setColor(color);
		newVehicle.setImage(image);
		vehicle.addVehicle(newVehicle);
		int vehicleId = (int)vehicle.getVehicleId(newVehicle.getImage()).getVehicleId();
		session.setAttribute("vehicleId", vehicleId);
		return "redirect:/home";
	}
	
//	MODIFY VEHICLE
	@RequestMapping("/modifyVehicle")
	public String modifyVehicle(ModelMap map, HttpSession session) {
		int vehicleId = (int)session.getAttribute("vehicleId");
		map.put("vehicleDetails", vehicle.getVehicleDetails(vehicleId));
		return "modifyVehicle";
	}
	
	@RequestMapping(path="/modifyVehicleInput", method=RequestMethod.POST)
	public String modifyVehicleInput(ModelMap map,
									HttpSession session,
									@RequestParam String make,
									@RequestParam String model,
									@RequestParam String year,
									@RequestParam String color,
									@RequestParam String image,
									RedirectAttributes redirAttribs) {
		
		List<String> errorMessages = new ArrayList<String>();
		boolean isValid = true;
		if(make.isEmpty() || model.isEmpty() || year.isEmpty() || color.isEmpty()) {
			isValid = false;
			errorMessages.add("All fields (except image) are required.");
		} else {
			String validChars = "abcedfghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-' ()1234567890\\.";
			if(!usesValidChars(make, validChars)) {
				isValid = false;
				errorMessages.add("The Make contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			}
			if(!usesValidChars(model, validChars)) {
				isValid = false;
				errorMessages.add("The Model contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			}
			if(!usesValidChars(color, validChars)) {
				isValid = false;
				errorMessages.add("The Color contains invalid characters. Only letters, numbers, dashes, apostrophes, decimals, parentheses, and spaces are allowed.");
			} 
			Matcher matcher = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", Pattern.CASE_INSENSITIVE).matcher(image);
			if(!image.isEmpty()) {
				if(!matcher.find()) {
					isValid = false;
					errorMessages.add("Please enter a valid Image URL.");
				}
			}
			validChars = "1234567890";
			if(!usesValidChars(year, validChars)) {
				isValid = false;
				errorMessages.add("The Year contains invalid characters. Only numbers are permitted.");
			}
			if(year.length() != 4) {
				isValid = false;
				errorMessages.add("The Year must be 4 digits in length (e.g., '2001').");
			}
		}	
		if(!isValid) {		
			redirAttribs.addFlashAttribute("make", make);
			redirAttribs.addFlashAttribute("model", model);
			redirAttribs.addFlashAttribute("year", year);
			redirAttribs.addFlashAttribute("color", color);
			redirAttribs.addFlashAttribute("image", image);
			redirAttribs.addFlashAttribute("errorMessages", errorMessages);
			return "redirect:/modifyVehicle";
		}
		
		int vehicleId = (int)session.getAttribute("vehicleId");
		Vehicle modVehicle = new Vehicle();
		modVehicle.setMake(make);
		modVehicle.setModel(model);
		modVehicle.setYear(Integer.valueOf(year));
		modVehicle.setColor(color);
		modVehicle.setImage(image);
		vehicle.modifyVehicle(modVehicle, Integer.valueOf(vehicleId));
		return "redirect:/home";
	}
	
//	SWITCH VEHICLE
	@RequestMapping("/switchVehicle")
	public String switchVehicle(ModelMap map,
								HttpSession session) {
		int profileId = profile.getCurrentUser((String)session.getAttribute("userName")).getProfileId();
		map.put("vehicles", vehicle.getVehicle(profileId));
		return "switchVehicle";
	}
	
	@RequestMapping(path="/switchVehicleInput", method=RequestMethod.POST)
	public String switchVehicleInput(@RequestParam String vehicleId, HttpSession session) {
		session.setAttribute("vehicleId", Integer.valueOf(vehicleId));
		return "redirect:/home";
	}
	
//	DELETE A VEHICLE
	@RequestMapping("/deleteVehicle")
	public String deleteVehicle(ModelMap map, HttpSession session) {
		int profileId = profile.getCurrentUser((String)session.getAttribute("userName")).getProfileId();
		map.put("vehicles", vehicle.getVehicle(profileId));
		return "deleteVehicle";
	}
	
	@RequestMapping("/deleteVehicleInput")
	public String deleteVehicleInput(@RequestParam String vehicleId, HttpSession session) {
		vehicle.removeVehicle(Integer.valueOf(vehicleId));
		int profileId = profile.getCurrentUser((String)session.getAttribute("userName")).getProfileId();
		List<Vehicle> vehicles = vehicle.getVehicle(profileId);
		if(vehicles.isEmpty()) {
			return "redirect:/addNewVehicle";
		} else if (vehicles.size() > 1) {
			return "redirect:/switchVehicle";
		} else {
			session.setAttribute("vehicleId", vehicles.get(0).getVehicleId());
			return "redirect:/home";
		}
	}
	
	@RequestMapping("/deleteUserVehicleInput")
	public String deleteUserVehicleInput(HttpSession session) {
		int profileId = (int)session.getAttribute("profileId");
		List <Vehicle> vehicles = vehicle.getVehicle(profileId);
		for(int i = 0; i < vehicles.size(); i++) {
			int vehicleId = (int)vehicles.get(i).getVehicleId();
			vehicle.removeVehicle(vehicleId);
		}
		return "redirect:/deleteUserExpenses";
	}
	
}
