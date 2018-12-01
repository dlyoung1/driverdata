package com.rideshare;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rideshare.model.Mileage;
import com.rideshare.model.Vehicle;
import com.rideshare.model.dao.MileageDAO;
import com.rideshare.model.dao.VehicleDAO;

@Controller
public class MileageController {
	
	@Autowired
	private MileageDAO mileage;
	@Autowired
	private VehicleDAO vehicle;
	
	@RequestMapping("/mileage")
	public String displayMileageToday(ModelMap map, HttpSession session) {
		int profileId = (int)session.getAttribute("profileId");
		int vehicleId = (int)session.getAttribute("vehicleId");
		Date today = Calendar.getInstance().getTime();
		List<Mileage> miles = mileage.getMileage(profileId, today);
		if(miles.isEmpty()) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			Date yesterday = cal.getTime();
			miles = mileage.getMileage(profileId, yesterday);
			if(miles.isEmpty()) {
				if(mileage.getLastMileage(vehicleId).getStartMileage() == 0) {
					map.put("message", "Enter starting mileage to begin trip");
				} else {
					if(mileage.getLastMileage(vehicleId).getEndMileage() == 0) {
						map.put("message", "An ending mileage for your last trip was not entered. Please enter an ending mileage for your last trip.");
						map.put("date", mileage.getLastMileage(vehicleId).getStartDate());
						map.put("miles", mileage.getLastMileage(vehicleId).getStartMileage());
					} else {
						map.put("message", "Enter starting mileage to begin trip");
					}
				}
			} else {
				if(mileage.getLastMileage(vehicleId).getEndMileage() == 0) {
					map.put("message", "Trip in Progress");
					map.put("miles", mileage.getLastMileage(vehicleId).getStartMileage());
				}else {
					map.put("message", "Enter starting mileage to begin trip");
				}
			}
		} else {
			if(mileage.getLastMileage(vehicleId).getEndMileage() == 0) {
				map.put("message", "Trip in Progress");
				map.put("miles", mileage.getLastMileage(vehicleId).getStartMileage());
			}else {
				map.put("message", "Enter starting mileage to begin trip");
			}
		}
		
		return "mileage";
	}
	
	@RequestMapping(path="/startMileageInput", method=RequestMethod.POST)
	public String inputStartingMileage(@RequestParam String startMileage, HttpSession session, RedirectAttributes redirAttribs) {
		
		int vehicleId = (int) session.getAttribute("vehicleId");
		List<String> errorMessages = new ArrayList<String>();
		boolean isValid = true;
		if(startMileage.isEmpty()) {
			isValid = false;
			errorMessages.add("Please enter a starting mileage.");
		} else {
			String validChars = "1234567890";
			if(!usesValidChars(startMileage, validChars)) {
				isValid = false;
				errorMessages.add("The Mileage entered contains invalid characters. Only numbers are allowed.");
			}
			if(Integer.valueOf(startMileage) < mileage.getLastMileage(vehicleId).getEndMileage()) {
				isValid = false;
				errorMessages.add("The Mileage must be greater than the ending mileage of your last trip.");
			}
		}	
		if(!isValid) {		
			redirAttribs.addFlashAttribute("startMileage", startMileage);
			redirAttribs.addFlashAttribute("errorMessages", errorMessages);
			return "redirect:/mileage";
		}
		
		Mileage addMiles = new Mileage();
		addMiles.setVehicleId(vehicleId);
		addMiles.setStartMileage(Integer.valueOf(startMileage));
		addMiles.setEndMileage(0);
		mileage.addStartMileage(addMiles);
		return "redirect:/home";
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

	@RequestMapping(path="/endMileageInput", method=RequestMethod.POST)
	public String inputEndingMileage(@RequestParam String endMileage, HttpSession session, RedirectAttributes redirAttribs) {
		
		int vehicleId = (int) session.getAttribute("vehicleId");
		List<String> errorMessages = new ArrayList<String>();
		boolean isValid = true;
		if(endMileage.isEmpty()) {
			isValid = false;
			errorMessages.add("Please enter an ending mileage.");
		} else {
			String validChars = "1234567890";
			if(!usesValidChars(endMileage, validChars)) {
				isValid = false;
				errorMessages.add("The Mileage entered contains invalid characters. Only numbers are allowed.");
			}
			if(Integer.valueOf(endMileage) < mileage.getLastMileage(vehicleId).getStartMileage()) {
				isValid = false;
				errorMessages.add("The ending Mileage must be greater than the starting mileage.");
			}
		}	
		if(!isValid) {		
			redirAttribs.addFlashAttribute("endMileage", endMileage);
			redirAttribs.addFlashAttribute("errorMessages", errorMessages);
			return "redirect:/mileage";
		}
		
		Date today = Calendar.getInstance().getTime();
		Mileage addMiles = new Mileage();
		addMiles.setVehicleId(vehicleId);
		addMiles.setEndDate(today);
		addMiles.setEndMileage(Integer.valueOf(endMileage));
		mileage.addEndMileage(addMiles);
		return "redirect:/home";
	}
	
	@RequestMapping(path="/deleteMileage", method=RequestMethod.POST)
	public String deleteMileage(Model model, @RequestParam String vehicleId) {
		model.addAttribute("vehicleId", vehicleId);
		mileage.removeMileage(Integer.valueOf(vehicleId));
		return "redirect:deleteMaintenanceService";
	}
	
	@RequestMapping("/deleteUserMileage")
	public String deleteUserMileage(ModelMap map, HttpSession session) {
		int profileId = (int)session.getAttribute("profileId");
		List <Vehicle> vehicles = vehicle.getVehicle(profileId);
		List<Integer> ids = new ArrayList<>();
		for(int i = 0; i < vehicles.size(); i++) {
			int vehicleId = (int)vehicles.get(i).getVehicleId();
			ids.add(vehicleId);
			mileage.removeMileage(vehicleId);
		}
		map.addAttribute("vehicleId", ids);
		return "redirect:deleteUserMaintenanceService";
	}
	
	@RequestMapping("/monthlyMileage")
	public String monthlyMileageReport(HttpSession session) {
		session.setAttribute("period", "monthly");
		return "redirect:/mileageReport";
	}
	
	@RequestMapping("/weeklyMileage")
	public String weeklyMileageReport(HttpSession session) {
		session.setAttribute("period", "weekly");
		return "redirect:/mileageReport";
	}
	
	@RequestMapping("/mileageReport")
	public String mileageReport(ModelMap map, HttpSession session) {
		int profileId = (int)session.getAttribute("profileId");
		String period = (String)session.getAttribute("period");
		int miles = 0;
		int totalMiles = 0;
		Map<Date, Integer> mileageMap = new LinkedHashMap<>();
		List<Date> endDate = new ArrayList<>();
		if(period == "weekly") {
			map.put("redirect", "/monthlyMileage");
			map.put("periodRev", "Monthly");
			Calendar cal = Calendar.getInstance();
			for(int i = 0; i < 7; i++) {
				cal.add(Calendar.DATE, -1);
				Date day = cal.getTime();
				List<Mileage> dailyMiles = mileage.getMileage(profileId, day);
				Mileage totalDailyMiles = new Mileage();
				miles = totalDailyMiles.getMiles(dailyMiles);
				totalMiles += miles;
				mileageMap.put(day, miles);
			}
		} else if(period == "monthly") {
			map.put("redirect", "/weeklyMileage");
			map.put("periodRev", "Weekly");
			Calendar cal = Calendar.getInstance();
			for(int j = 0; j < 4; j++) {
				Date endDay = cal.getTime();
				miles = 0;
				for(int i = 0; i < 7; i++) {
					cal.add(Calendar.DATE, -1);
					Date day = cal.getTime();
					List<Mileage> dailyMiles = mileage.getMileage(profileId, day);
					Mileage totalDailyMiles = new Mileage();
					miles += totalDailyMiles.getMiles(dailyMiles);
				}
				Date startDay = cal.getTime();
				totalMiles += miles;
				mileageMap.put(startDay, miles);
				endDate.add(endDay);
			}		
		}
		
		map.put("totalMiles", totalMiles);
		map.put("mileageMap", mileageMap);
		map.put("endDate", endDate);
		return "mileageReport";
	}
	
}
